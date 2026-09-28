package service;

import database.SlowDatabase;
import engine.LRUCache;
import model.Quiz;

/**
 * QuizService acting as a Cache Interceptor:
 * 1. Checks in-memory LRU Cache first (< 1ms access).
 * 2. If HIT: returns immediately with zero database round-trip.
 * 3. If MISS: queries SlowDatabase (2-3s delay), stores in LRU Cache, and returns.
 */
public class QuizService {
    private final LRUCache<Integer, Quiz> cache;
    private final SlowDatabase database;

    public QuizService(int cacheCapacity, SlowDatabase database) {
        this.cache = new LRUCache<>(cacheCapacity);
        this.database = database;
    }

    public Quiz getQuiz(int quizId) {
        long startTime = System.currentTimeMillis();

        // 1. Check LRU Cache
        Quiz cachedQuiz = cache.get(quizId);
        if (cachedQuiz != null) {
            long latency = System.currentTimeMillis() - startTime;
            System.out.printf("  >>> [CACHE HIT] Found in LRU Cache! Served in %d ms! (MRU Promoted)\n", latency);
            return cachedQuiz;
        }

        // 2. Cache Miss: Fallback to slow persistent storage
        System.out.println("  >>> [CACHE MISS] Quiz not in memory. Querying external database...");
        Quiz dbQuiz = database.queryQuizFromDisk(quizId);
        long totalLatency = System.currentTimeMillis() - startTime;

        if (dbQuiz != null) {
            // 3. Store into cache for future ultra-fast access
            cache.put(quizId, dbQuiz);
            System.out.printf("  >>> [CACHE POPULATE] Stored Quiz #%d into LRU Cache. Total fetch time: %d ms\n", quizId, totalLatency);
        } else {
            System.out.printf("  >>> [ERROR] Quiz #%d does not exist in Database either.\n", quizId);
        }

        return dbQuiz;
    }

    public LRUCache<Integer, Quiz> getCache() {
        return cache;
    }

    public SlowDatabase getDatabase() {
        return database;
    }
}
