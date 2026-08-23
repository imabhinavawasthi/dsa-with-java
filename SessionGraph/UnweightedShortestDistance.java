package SessionGraph;

import java.util.*;

public class UnweightedShortestDistance {

    public static int getShortestDistance(Map<Integer, List<Integer>> graph, int start, int target) {
        // Base case: distance to itself is 0
        if (start == target) return 0;

        // Use a set to track visited nodes and prevent re-entry
        Set<Integer> visited = new HashSet<>();
        // Queue stores pairs of: [current_node, current_distance]
        Queue<int[]> queue = new LinkedList<>();

        // Initialize BFS
        visited.add(start);
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currNode = curr[0];
            int currDist = curr[1];

            // Explore all connections
            for (int neighbor : graph.getOrDefault(currNode, new ArrayList<>())) {
                if (neighbor == target) {
                    return currDist + 1; // Found target, return distance immediately
                }

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new int[]{neighbor, currDist + 1});
                }
            }
        }

        return -1; // Return -1 if target is completely unreachable
    }

    public static void main(String[] args) {
        // Graph Initialization
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3, 4));
        graph.put(2, Arrays.asList(0, 5));
        graph.put(3, Arrays.asList(1));
        graph.put(4, Arrays.asList(1, 5));
        graph.put(5, Arrays.asList(2, 4));

        int distance = getShortestDistance(graph, 0, 5);
        System.out.println("Shortest distance: " + distance);
        // Output: Shortest distance: 2
    }
}
