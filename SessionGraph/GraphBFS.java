package SessionGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class GraphBFS {
    public static ArrayList<Integer>[] getAdjacencyList(int n, int[][] edges) {
        ArrayList<Integer>[] adjList = new ArrayList[n];
        for(int i=0;i<n;i++) adjList[i] = new ArrayList<Integer>();

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            adjList[x].add(y);
            adjList[y].add(x);
        }

        return adjList;
    }
    public static ArrayList<Integer> bfs(ArrayList<Integer>[] adjList) {
        int n = adjList.length;
        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        boolean[] visited = new boolean[n];

        visited[0] = true;
        ans.add(0);
        q.add(0);

        while(!q.isEmpty()) {
            int front = q.poll();
            for(int adj: adjList[front]) {
                if(!visited[adj]) {
                    visited[adj] = true;
                    ans.add(adj);
                    q.add(adj);
                }
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0,2}, {0,3}, {0,4}, {1,3}, {1,4}, {2,4}};

        ArrayList<Integer>[] adjList = getAdjacencyList(n, edges);

        ArrayList<Integer> ans = bfs(adjList);

        System.out.println("BFS: " + ans);
    }
}
