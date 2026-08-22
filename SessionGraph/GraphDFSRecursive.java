package SessionGraph;

import java.util.ArrayList;
import java.util.Stack;

public class GraphDFSRecursive {
    static ArrayList<Integer> ans = new ArrayList<>();
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
    public static void dfs(int i, ArrayList<Integer>[] adjList, boolean[] visited) {
        visited[i] = true;
        ans.add(i);

        for(int adj: adjList[i]) {
            if(!visited[adj]) {
                dfs(adj, adjList, visited);
            }
        }
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0,2}, {0,3}, {0,4}, {1,3}, {1,4}, {2,4}};

        ArrayList<Integer>[] adjList = getAdjacencyList(n, edges);
        boolean[] visited = new boolean[n];

        dfs(0, adjList, visited);

        System.out.println("DFS: " + ans);
    }
}
