import database.SlowDatabase;
import engine.LRUCache;
import model.Quiz;
import service.QuizService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("      SESSION 48: PRODUCTION-GRADE LRU CACHE & QUIZ PLATFORM   ");
        System.out.println("       High-Performance In-Memory Caching with Custom DLL     ");
        System.out.println("===============================================================");

        // Initialize with realistic cache capacity of 3 items
        int CACHE_CAPACITY = 3;
        long DB_LATENCY_MS = 1500; // 1.5 seconds simulated I/O delay

        SlowDatabase database = new SlowDatabase(DB_LATENCY_MS);
        QuizService quizService = new QuizService(CACHE_CAPACITY, database);
        LRUCache<Integer, Quiz> cache = quizService.getCache();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n-------------------------------------------------------------");
            System.out.println("  LRU CACHE DEMO MENU (Bounded Capacity: " + CACHE_CAPACITY + ")");
            System.out.println("-------------------------------------------------------------");
            System.out.println(" 1. Request a Quiz (Observe Cache Hit vs Cache Miss Latency)");
            System.out.println(" 2. Inspect In-Memory LRU Cache Order (MRU -> LRU Chain)");
            System.out.println(" 3. View All Quizzes Available in Slow Database");
            System.out.println(" 4. Run Automated 4-Step Whiteboard Trace from PPT");
            System.out.println(" 5. Run High-Concurrency Stress Test (Multi-threaded Access)");
            System.out.println(" 0. Exit System");
            System.out.print(" Select option [0-5]: ");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a valid option number.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleQuizRequest(scanner, quizService);
                    break;
                case 2:
                    System.out.println("\n--- CURRENT LRU CACHE INSPECTION ---");
                    cache.printCacheState();
                    System.out.printf("  Telemetry: Hit Ratio = %.1f%%\n", cache.getHitRatio() * 100);
                    break;
                case 3:
                    System.out.println("\n--- QUIZZES STORED IN SLOW DATABASE ---");
                    for (Quiz q : database.getAllAvailableInDb()) {
                        System.out.println("   * " + q);
                    }
                    break;
                case 4:
                    runSlideTraceDemo();
                    break;
                case 5:
                    runConcurrencyStressTest(quizService);
                    break;
                case 0:
                    System.out.println("\n  Shutting down LRU Cache System. Memory released cleanly. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println(" Unknown choice. Select between 0 and 5.");
            }
        }
        scanner.close();
    }

    private static void handleQuizRequest(Scanner sc, QuizService quizService) {
        System.out.print("\nEnter Quiz ID to request (1 to 7): ");
        try {
            int qid = Integer.parseInt(sc.nextLine().trim());
            Quiz quiz = quizService.getQuiz(qid);
            if (quiz != null) {
                System.out.println("\n  Loaded Content:");
                System.out.println("  " + quiz);
                for (String q : quiz.getQuestions()) {
                    System.out.println("    - " + q);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid ID format.");
        }
    }

    /**
     * Recreates the exact step-by-step whiteboard trace from Slide 13-17:
     * Capacity = 2
     * Step 1: put(1, 10) -> [head <-> 1 <-> tail]
     * Step 2: put(2, 20) -> [head <-> 2 <-> 1 <-> tail]
     * Step 3: get(1)     -> [head <-> 1 <-> 2 <-> tail] (1 promoted to MRU)
     * Step 4: put(3, 30) -> [head <-> 3 <-> 1 <-> tail] (2 evicted!)
     */
    private static void runSlideTraceDemo() {
        System.out.println("\n=============================================================");
        System.out.println("     RECREATING SLIDE TRACE (Capacity = 2, Keys: Integer)    ");
        System.out.println("=============================================================");

        LRUCache<Integer, Integer> traceCache = new LRUCache<>(2);

        System.out.println("\n[STEP 1] Executing put(1, 10)...");
        traceCache.put(1, 10);
        traceCache.printCacheState();

        System.out.println("\n[STEP 2] Executing put(2, 20)... (Fill to capacity)");
        traceCache.put(2, 20);
        traceCache.printCacheState();

        System.out.println("\n[STEP 3] Executing get(1)... (Cache Hit & Reorder: 1 promoted, 2 demoted to LRU)");
        Integer val = traceCache.get(1);
        System.out.println("  Retrieved value = " + val);
        traceCache.printCacheState();

        System.out.println("\n[STEP 4] Executing put(3, 30)... (Capacity breached! Evicting tail.prev = Key 2)");
        traceCache.put(3, 30);
        traceCache.printCacheState();

        System.out.println("\nVERIFICATION:");
        System.out.println("  Key 1 in cache? " + (traceCache.get(1) != null ? "YES (" + traceCache.get(1) + ")" : "NO"));
        System.out.println("  Key 2 in cache? " + (traceCache.get(2) != null ? "YES (" + traceCache.get(2) + ")" : "NO (EVICTED!)"));
        System.out.println("  Key 3 in cache? " + (traceCache.get(3) != null ? "YES (" + traceCache.get(3) + ")" : "NO"));
        System.out.println("=============================================================");
    }

    /**
     * Demonstrates multi-threaded safety of our synchronized LRU Cache.
     */
    private static void runConcurrencyStressTest(QuizService quizService) {
        System.out.println("\n=============================================================");
        System.out.println("      CONCURRENCY TEST: 4 THREADS HAMMERING LRU CACHE        ");
        System.out.println("=============================================================");

        Thread[] workers = new Thread[4];
        for (int i = 0; i < workers.length; i++) {
            final int threadId = i + 1;
            workers[i] = new Thread(() -> {
                for (int req = 1; req <= 3; req++) {
                    int quizId = (threadId + req) % 5 + 1;
                    System.out.printf("  [Thread-%d] Requesting Quiz #%d...\n", threadId, quizId);
                    quizService.getQuiz(quizId);
                }
            }, "Worker-" + threadId);
        }

        for (Thread t : workers) t.start();
        for (Thread t : workers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n=============================================================");
        System.out.println("  MULTI-THREADING COMPLETE: Final Cache Integrity Check:");
        quizService.getCache().printCacheState();
        System.out.println("=============================================================");
    }
}
