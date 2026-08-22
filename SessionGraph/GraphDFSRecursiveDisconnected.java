package SessionGraph;

import java.util.ArrayList;

public class GraphDFSRecursiveDisconnected {
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
        int n = 8;
        int[][] edges = {{0,1},{1,2},{3,5},{3,6},{4,6},{5,6}};

        ArrayList<Integer>[] adjList = getAdjacencyList(n, edges);
        boolean[] visited = new boolean[n];

        int c = 0;
        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                dfs(i, adjList, visited);
                c+=1;
            }
        }
        // 0->0,1,2
        // 3->3,4,5,6
        // 7->7

        System.out.println("DFS: " + ans);
        System.out.println("Connected Components: " + c);
    }
}
