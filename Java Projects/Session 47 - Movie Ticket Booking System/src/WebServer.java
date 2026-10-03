import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import model.Booking;
import model.Movie;
import model.Seat;
import model.Show;
import model.User;
import payment.CardPaymentProcessor;
import payment.CashPaymentProcessor;
import payment.PaymentProcessor;
import payment.UPIPaymentProcessor;
import service.BookingService;
import service.MovieService;
import service.ShowService;
import service.UserService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class WebServer {
    private final MovieService movieService;
    private final ShowService showService;
    private final UserService userService;
    private final BookingService bookingService;
    private final String bookingsFile;
    private volatile int activeUserId = 1;

    public WebServer(MovieService movieService, ShowService showService, UserService userService,
                     BookingService bookingService, String bookingsFile) {
        this.movieService = movieService;
        this.showService = showService;
        this.userService = userService;
        this.bookingService = bookingService;
        this.bookingsFile = bookingsFile;
    }

    public void start() throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        server.createContext("/", this::handleRequest);
        server.setExecutor(Executors.newCachedThreadPool());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> bookingService.saveBookingsToFile(bookingsFile)));
        server.start();
        System.out.println("Movie booking UI listening on port " + port);
        System.out.println("Open http://localhost:" + port + " locally.");
        System.out.println("Press Ctrl+C to stop the web server.");
    }

    private void handleRequest(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();
            if ("GET".equals(method) && ("/".equals(path) || "/index.html".equals(path))) {
                serveIndex(exchange);
            } else if (path.startsWith("/api/")) {
                handleApi(exchange, path, method);
            } else {
                sendJson(exchange, 404, error("Not found"));
            }
        } catch (Exception e) {
            sendJson(exchange, 400, error(e.getMessage() == null ? "Request failed" : e.getMessage()));
        }
    }

    private void handleApi(HttpExchange exchange, String path, String method) throws Exception {
        Map<String, String> values = "GET".equals(method)
                ? parseQuery(exchange.getRequestURI().getRawQuery())
                : parseForm(exchange);

        if ("GET".equals(method) && "/api/movies".equals(path)) {
            String query = values.get("query");
            List<Movie> movies = query == null || query.trim().isEmpty()
                    ? movieService.getAllMovies() : movieService.searchMovies(query);
            sendJson(exchange, 200, moviesJson(movies));
        } else if ("GET".equals(method) && "/api/shows".equals(path)) {
            if (values.containsKey("id")) {
                sendJson(exchange, 200, showJson(showService.getShow(integer(values, "id"))));
            } else {
                sendJson(exchange, 200, showsJson(showService.getAllShows()));
            }
        } else if ("GET".equals(method) && "/api/users".equals(path)) {
            sendJson(exchange, 200, usersJson(userService.getAllUsers()));
        } else if ("GET".equals(method) && "/api/session".equals(path)) {
            sendJson(exchange, 200, userJson(userService.getUser(activeUserId)));
        } else if ("POST".equals(method) && "/api/session".equals(path)) {
            if (values.containsKey("userId")) {
                int requestedId = integer(values, "userId");
                if (!userService.containsUser(requestedId)) throw new IllegalArgumentException("User not found");
                activeUserId = requestedId;
            } else {
                String name = required(values, "name");
                String email = required(values, "email");
                int newId = 1;
                for (User user : userService.getAllUsers()) newId = Math.max(newId, user.getId() + 1);
                User user = new User(newId, name, email);
                userService.registerUser(user);
                activeUserId = newId;
            }
            sendJson(exchange, 200, userJson(userService.getUser(activeUserId)));
        } else if ("GET".equals(method) && "/api/bookings".equals(path)) {
            sendJson(exchange, 200, bookingsJson(bookingService.getUserBookings(activeUserId)));
        } else if ("POST".equals(method) && "/api/bookings".equals(path)) {
            int showId = integer(values, "showId");
            List<String> seats = new ArrayList<>();
            for (String seat : required(values, "seats").split(",")) {
                if (!seat.trim().isEmpty()) seats.add(seat.trim().toUpperCase());
            }
            PaymentProcessor processor = paymentProcessor(values);
            Booking booking = bookingService.bookSeats(showId, activeUserId, seats, processor);
            bookingService.saveBookingsToFile(bookingsFile);
            sendJson(exchange, 201, bookingJson(booking));
        } else if ("POST".equals(method) && path.matches("/api/bookings/\\d+/cancel")) {
            int bookingId = Integer.parseInt(path.split("/")[3]);
            Booking booking = bookingService.getBooking(bookingId);
            if (booking.getUser().getId() != activeUserId) throw new IllegalArgumentException("This booking belongs to another user");
            boolean cancelled = bookingService.cancelBooking(bookingId);
            bookingService.saveBookingsToFile(bookingsFile);
            sendJson(exchange, 200, "{\"cancelled\":" + cancelled + ",\"booking\":" + bookingJson(booking) + "}");
        } else if ("POST".equals(method) && "/api/concurrency".equals(path)) {
            runConcurrencyDemo(exchange);
        } else {
            sendJson(exchange, 404, error("Unknown API route"));
        }
    }

    private void runConcurrencyDemo(HttpExchange exchange) throws Exception {
        Show show = showService.getShow(101);
        List<Seat> available = show.getAvailableSeats();
        if (available.isEmpty()) throw new IllegalStateException("Show 101 has no available seats for the test");
        String seatNumber = available.get(0).getSeatNumber();
        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);
        List<String> results = Collections.synchronizedList(new ArrayList<String>());
        Runnable userOne = concurrencyAttempt(1, seatNumber, ready, start, results);
        Runnable userTwo = concurrencyAttempt(2, seatNumber, ready, start, results);
        Thread first = new Thread(userOne, "Race-User-1");
        Thread second = new Thread(userTwo, "Race-User-2");
        first.start();
        second.start();
        ready.await();
        start.countDown();
        first.join();
        second.join();
        bookingService.saveBookingsToFile(bookingsFile);
        sendJson(exchange, 200, "{\"showId\":101,\"seat\":" + quote(seatNumber) + ",\"results\":" + stringsJson(results) + "}");
    }

    private Runnable concurrencyAttempt(int userId, String seatNumber, CountDownLatch ready,
                                        CountDownLatch start, List<String> results) {
        return () -> {
            ready.countDown();
            try {
                start.await();
                Booking booking = bookingService.bookSeats(101, userId,
                        Collections.singletonList(seatNumber), new CashPaymentProcessor());
                results.add("User " + userId + " booked seat " + seatNumber + " as booking #" + booking.getBookingId());
            } catch (Exception e) {
                results.add("User " + userId + " was declined: " + e.getMessage());
            }
        };
    }

    private PaymentProcessor paymentProcessor(Map<String, String> values) {
        String method = values.getOrDefault("payment", "cash");
        if ("upi".equals(method)) return new UPIPaymentProcessor(required(values, "credential"));
        if ("card".equals(method)) return new CardPaymentProcessor(required(values, "credential"));
        if ("cash".equals(method)) return new CashPaymentProcessor();
        throw new IllegalArgumentException("Choose UPI, card, or cash payment");
    }

    private void serveIndex(HttpExchange exchange) throws IOException {
        Path[] candidates = {Paths.get("ui/index.html"), Paths.get("src/ui/index.html")};
        for (Path candidate : candidates) {
            if (Files.isRegularFile(candidate)) {
                byte[] content = Files.readAllBytes(candidate);
                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
                exchange.sendResponseHeaders(200, content.length);
                exchange.getResponseBody().write(content);
                exchange.close();
                return;
            }
        }
        sendJson(exchange, 404, error("UI file not found. Run from the project or src directory."));
    }

    private static Map<String, String> parseForm(HttpExchange exchange) throws IOException {
        byte[] body = exchange.getRequestBody().readAllBytes();
        return parseQuery(new String(body, StandardCharsets.UTF_8));
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> values = new HashMap<>();
        if (query == null || query.isEmpty()) return values;
        for (String pair : query.split("&")) {
            String[] parts = pair.split("=", 2);
            values.put(decode(parts[0]), parts.length > 1 ? decode(parts[1]) : "");
        }
        return values;
    }

    private static String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    private static String required(Map<String, String> values, String key) {
        String value = values.get(key);
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException(key + " is required");
        return value.trim();
    }

    private static int integer(Map<String, String> values, String key) {
        return Integer.parseInt(required(values, key));
    }

    private static void sendJson(HttpExchange exchange, int status, String json) throws IOException {
        byte[] content = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.getResponseHeaders().set("Cache-Control", "no-store");
        exchange.sendResponseHeaders(status, content.length);
        exchange.getResponseBody().write(content);
        exchange.close();
    }

    private static String error(String message) {
        return "{\"error\":" + quote(message) + "}";
    }

    private static String moviesJson(List<Movie> movies) {
        List<String> items = new ArrayList<>();
        for (Movie movie : movies) {
            items.add("{\"id\":" + movie.getId() + ",\"title\":" + quote(movie.getTitle())
                    + ",\"genre\":" + quote(movie.getGenre()) + ",\"duration\":" + movie.getDurationMinutes() + "}");
        }
        return array(items);
    }

    private static String showsJson(List<Show> shows) {
        List<String> items = new ArrayList<>();
        for (Show show : shows) items.add(showJson(show));
        return array(items);
    }

    private static String showJson(Show show) {
        List<String> seats = new ArrayList<>();
        for (Seat seat : show.getSeats().values()) {
            seats.add("{\"number\":" + quote(seat.getSeatNumber()) + ",\"type\":" + quote(seat.getSeatType().name())
                    + ",\"price\":" + seat.getPrice() + ",\"booked\":" + seat.isBooked() + "}");
        }
        return "{\"id\":" + show.getId() + ",\"movie\":" + quote(show.getMovie().getTitle())
                + ",\"movieId\":" + show.getMovie().getId() + ",\"theatre\":" + quote(show.getTheatre().getName())
                + ",\"city\":" + quote(show.getTheatre().getCity()) + ",\"screen\":" + quote(show.getScreen().getName())
                + ",\"time\":" + quote(show.getShowTime()) + ",\"available\":" + show.getAvailableCount()
                + ",\"capacity\":" + show.getSeats().size() + ",\"seats\":" + array(seats) + "}";
    }

    private static String usersJson(List<User> users) {
        List<String> items = new ArrayList<>();
        for (User user : users) items.add(userJson(user));
        return array(items);
    }

    private static String userJson(User user) {
        return "{\"id\":" + user.getId() + ",\"name\":" + quote(user.getName())
                + ",\"email\":" + quote(user.getEmail()) + "}";
    }

    private static String bookingsJson(List<Booking> bookings) {
        List<String> items = new ArrayList<>();
        for (Booking booking : bookings) items.add(bookingJson(booking));
        return array(items);
    }

    private static String bookingJson(Booking booking) {
        List<String> seats = new ArrayList<>();
        for (String seat : booking.getSeatNumbers()) seats.add(quote(seat));
        return "{\"id\":" + booking.getBookingId() + ",\"userId\":" + booking.getUser().getId()
                + ",\"movie\":" + quote(booking.getShow().getMovie().getTitle())
                + ",\"theatre\":" + quote(booking.getShow().getTheatre().getName())
                + ",\"time\":" + quote(booking.getShow().getShowTime()) + ",\"seats\":" + array(seats)
                + ",\"amount\":" + booking.getTotalAmount() + ",\"payment\":" + quote(booking.getPaymentMethod())
                + ",\"status\":" + quote(booking.getStatus().name()) + "}";
    }

    private static String stringsJson(List<String> values) {
        List<String> items = new ArrayList<>();
        for (String value : values) items.add(quote(value));
        return array(items);
    }

    private static String array(List<String> items) {
        return "[" + String.join(",", items) + "]";
    }

    private static String quote(String value) {
        StringBuilder result = new StringBuilder("\"");
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            if (character == '"' || character == '\\') result.append('\\');
            if (character == '\n') result.append("\\n");
            else if (character == '\r') result.append("\\r");
            else result.append(character);
        }
        return result.append('"').toString();
    }
}