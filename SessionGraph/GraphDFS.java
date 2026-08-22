package SessionGraph;

import java.util.ArrayList;
import java.util.Stack;

public class GraphDFS {
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
    public static ArrayList<Integer> dfs(ArrayList<Integer>[] adjList) {
        int n = adjList.length;
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        boolean[] visited = new boolean[n];

        visited[0] = true;
        ans.add(0);
        st.add(0);

        while(!st.isEmpty()) {
            int top = st.pop();
            for(int adj: adjList[top]) {
                if(!visited[adj]) {
                    visited[adj] = true;
                    ans.add(adj);
                    st.add(adj);
                }
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0,2}, {0,3}, {0,4}, {1,3}, {1,4}, {2,4}};

        ArrayList<Integer>[] adjList = getAdjacencyList(n, edges);

        ArrayList<Integer> ans = dfs(adjList);

        System.out.println("DFS: " + ans);
    }
}
