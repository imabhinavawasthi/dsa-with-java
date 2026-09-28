package database;

import model.Quiz;

import java.util.*;

/**
 * Mocks an expensive external Database with disk / network latency.
 */
public class SlowDatabase {
    private final Map<Integer, Quiz> databaseStore;
    private final long simulatedDelayMs;

    public SlowDatabase(long simulatedDelayMs) {
        this.simulatedDelayMs = simulatedDelayMs;
        this.databaseStore = new HashMap<>();
        seedMockData();
    }

    private void seedMockData() {
        databaseStore.put(1, new Quiz(1, "Java Basics & Memory", "Core Java",
                Arrays.asList("Q1: Difference between Stack and Heap?", "Q2: What is String Pool?", "Q3: Explain pass-by-value.")));
        databaseStore.put(2, new Quiz(2, "OOP & Polymorphism", "OOP",
                Arrays.asList("Q1: Method Overriding vs Overloading?", "Q2: Abstract Class vs Interface?", "Q3: Encapsulation benefits.")));
        databaseStore.put(3, new Quiz(3, "Linked Lists & Pointers", "DSA Linear",
                Arrays.asList("Q1: Floyd Cycle Detection?", "Q2: Why DLL for LRU?", "Q3: Reverse a singly linked list.")));
        databaseStore.put(4, new Quiz(4, "Tree Traversals & BST", "DSA Non-Linear",
                Arrays.asList("Q1: Inorder of BST property?", "Q2: DFS vs BFS queue/stack?", "Q3: Lowest Common Ancestor.")));
        databaseStore.put(5, new Quiz(5, "Graph Shortest Path", "Graphs",
                Arrays.asList("Q1: Dijkstra vs Bellman-Ford?", "Q2: Topological sort Kahn's algorithm.", "Q3: Cycle detection.")));
        databaseStore.put(6, new Quiz(6, "Dynamic Programming Masterclass", "DP",
                Arrays.asList("Q1: Memoization vs Tabulation?", "Q2: Longest Common Subsequence formula.", "Q3: 0/1 Knapsack.")));
        databaseStore.put(7, new Quiz(7, "Backtracking & Recursion", "Algorithms",
                Arrays.asList("Q1: Choose-Explore-Unchoose template?", "Q2: N-Queens pruning.", "Q3: Subsets duplicate logic.")));
    }

    public Quiz queryQuizFromDisk(int quizId) {
        System.out.printf("    [DATABASE I/O] Simulating disk spin & network fetch (%d ms delay)...\n", simulatedDelayMs);
        try {
            Thread.sleep(simulatedDelayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return databaseStore.get(quizId);
    }

    public List<Quiz> getAllAvailableInDb() {
        return new ArrayList<>(databaseStore.values());
    }
}
