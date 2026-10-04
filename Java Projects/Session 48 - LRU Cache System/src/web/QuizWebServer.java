package web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import database.SlowDatabase;
import engine.LRUCache;
import model.Quiz;
import service.QuizService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizWebServer {
    private final QuizService quizService;
    private final SlowDatabase database;
    private final HttpServer server;
    private final Object requestLock = new Object();

    public QuizWebServer(QuizService quizService, SlowDatabase database, int port) throws IOException {
        this.quizService = quizService;
        this.database = database;
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        this.server.createContext("/", this::handle);
    }

    public void start() {
        server.start();
    }

    private void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        if (path.equals("/") || path.equals("/index.html")) {
            if (!method.equals("GET")) {
                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }
            serveDashboard(exchange);
            return;
        }

        if (path.equals("/api/state") && method.equals("GET")) {
            sendJson(exchange, 200, stateJson());
            return;
        }

        if (path.equals("/api/quizzes") && method.equals("GET")) {
            sendJson(exchange, 200, quizzesJson());
            return;
        }

        String requestPrefix = "/api/quizzes/";
        String requestSuffix = "/request";
        if (path.startsWith(requestPrefix) && path.endsWith(requestSuffix)) {
            if (!method.equals("POST")) {
                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String idText = path.substring(requestPrefix.length(), path.length() - requestSuffix.length());
            int quizId;
            try {
                quizId = Integer.parseInt(idText);
            } catch (NumberFormatException e) {
                sendJson(exchange, 400, "{\"error\":\"Quiz ID must be a number\"}");
                return;
            }

            serveQuizRequest(exchange, quizId);
            return;
        }

        sendJson(exchange, 404, "{\"error\":\"Not found\"}");
    }

    private void serveDashboard(HttpExchange exchange) throws IOException {
        Path indexPath = findDashboard();
        if (indexPath == null) {
            sendJson(exchange, 500, "{\"error\":\"Dashboard file not found (expected src/web/index.html or web/index.html)\"}");
            return;
        }

        byte[] content;
        try {
            content = Files.readAllBytes(indexPath);
        } catch (IOException e) {
            System.err.println("Unable to read dashboard file: " + e.getMessage());
            sendJson(exchange, 500, "{\"error\":\"Unable to read dashboard file\"}");
            return;
        }

        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        exchange.getResponseHeaders().set("Cache-Control", "no-store");
        exchange.sendResponseHeaders(200, content.length);
        exchange.getResponseBody().write(content);
        exchange.close();
    }

    private Path findDashboard() {
        List<Path> candidates = List.of(Path.of("src", "web", "index.html"), Path.of("web", "index.html"));
        for (Path candidate : candidates) {
            if (Files.isRegularFile(candidate)) {
                return candidate;
            }
        }
        return null;
    }

    private void serveQuizRequest(HttpExchange exchange, int quizId) throws IOException {
        synchronized (requestLock) {
            LRUCache<Integer, Quiz> cache = quizService.getCache();
            boolean cacheHit = cache.containsKey(quizId);
            long startTime = System.nanoTime();
            Quiz quiz = quizService.getQuiz(quizId);
            long elapsedMs = (System.nanoTime() - startTime) / 1_000_000;

            if (quiz == null) {
                sendJson(exchange, 404, "{\"error\":\"Quiz not found\",\"state\":" + stateJson() + "}");
                return;
            }

            String outcome = cacheHit ? "HIT" : "MISS";
            String response = "{\"outcome\":\"" + outcome + "\",\"elapsedMs\":" + elapsedMs
                    + ",\"quiz\":" + quizJson(quiz) + ",\"state\":" + stateJson() + "}";
            sendJson(exchange, 200, response);
        }
    }

    private String quizzesJson() {
        StringBuilder json = new StringBuilder("[");
        List<Quiz> quizzes = database.getAllAvailableInDb();
        for (int i = 0; i < quizzes.size(); i++) {
            if (i > 0) {
                json.append(',');
            }
            Quiz quiz = quizzes.get(i);
            json.append("{\"id\":").append(quiz.getId())
                    .append(",\"title\":\"").append(jsonEscape(quiz.getTitle()))
                    .append("\",\"topic\":\"").append(jsonEscape(quiz.getTopic()))
                    .append("\",\"questionCount\":").append(quiz.getQuestions().size()).append('}');
        }
        return json.append(']').toString();
    }

    private String stateJson() {
        synchronized (requestLock) {
            return buildStateJson();
        }
    }

    private String buildStateJson() {
        LRUCache<Integer, Quiz> cache = quizService.getCache();
        List<Map.Entry<Integer, Quiz>> mapEntries = cache.getEntriesInMapOrder();
        List<Integer> order = cache.getKeysInOrder();
        Map<Integer, Quiz> quizzesById = new HashMap<>();
        for (Map.Entry<Integer, Quiz> entry : mapEntries) {
            quizzesById.put(entry.getKey(), entry.getValue());
        }

        StringBuilder mapJson = new StringBuilder("[");
        for (int i = 0; i < mapEntries.size(); i++) {
            if (i > 0) {
                mapJson.append(',');
            }
            Map.Entry<Integer, Quiz> entry = mapEntries.get(i);
            mapJson.append("{\"id\":").append(entry.getKey())
                    .append(",\"title\":\"").append(jsonEscape(entry.getValue().getTitle()))
                    .append("\",\"topic\":\"").append(jsonEscape(entry.getValue().getTopic())).append("\"}");
        }
        mapJson.append(']');

        StringBuilder orderJson = new StringBuilder("[");
        for (int i = 0; i < order.size(); i++) {
            if (i > 0) {
                orderJson.append(',');
            }
            orderJson.append(order.get(i));
        }
        orderJson.append(']');

        StringBuilder nodesJson = new StringBuilder("[");
        for (int i = 0; i < order.size(); i++) {
            if (i > 0) {
                nodesJson.append(',');
            }
            Quiz quiz = quizzesById.get(order.get(i));
            nodesJson.append("{\"id\":").append(quiz.getId())
                    .append(",\"title\":\"").append(jsonEscape(quiz.getTitle()))
                    .append("\",\"topic\":\"").append(jsonEscape(quiz.getTopic())).append("\"}");
        }
        nodesJson.append(']');

        long hits = cache.getHitCount();
        long misses = cache.getMissCount();
        double hitRatio = hits + misses == 0 ? 0.0 : (double) hits / (hits + misses);
        return "{\"capacity\":" + cache.getCapacity()
                + ",\"size\":" + cache.size()
                + ",\"hits\":" + hits
                + ",\"misses\":" + misses
                + ",\"evictions\":" + cache.getEvictionCount()
                + ",\"hitRatio\":" + hitRatio
                + ",\"map\":" + mapJson
                + ",\"order\":" + orderJson
                + ",\"nodes\":" + nodesJson + "}";
    }

    private String quizJson(Quiz quiz) {
        StringBuilder questionsJson = new StringBuilder("[");
        List<String> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            if (i > 0) {
                questionsJson.append(',');
            }
            questionsJson.append('"').append(jsonEscape(questions.get(i))).append('"');
        }
        questionsJson.append(']');
        return "{\"id\":" + quiz.getId()
                + ",\"title\":\"" + jsonEscape(quiz.getTitle())
                + "\",\"topic\":\"" + jsonEscape(quiz.getTopic())
                + "\",\"questions\":" + questionsJson + "}";
    }

    private static String jsonEscape(String value) {
        StringBuilder escaped = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            switch (character) {
                case '"':
                    escaped.append("\\\"");
                    break;
                case '\\':
                    escaped.append("\\\\");
                    break;
                case '\b':
                    escaped.append("\\b");
                    break;
                case '\f':
                    escaped.append("\\f");
                    break;
                case '\n':
                    escaped.append("\\n");
                    break;
                case '\r':
                    escaped.append("\\r");
                    break;
                case '\t':
                    escaped.append("\\t");
                    break;
                default:
                    if (character < 0x20) {
                        escaped.append(String.format("\\u%04x", (int) character));
                    } else {
                        escaped.append(character);
                    }
            }
        }
        return escaped.toString();
    }

    private static void sendJson(HttpExchange exchange, int status, String body) throws IOException {
        byte[] content = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.getResponseHeaders().set("Cache-Control", "no-store");
        exchange.sendResponseHeaders(status, content.length);
        exchange.getResponseBody().write(content);
        exchange.close();
    }
}
