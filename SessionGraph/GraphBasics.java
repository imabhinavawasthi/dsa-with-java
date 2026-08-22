package SessionGraph;

import java.util.Arrays;

public class GraphBasics {
    public static int[][] getAdjacencyMatrix(int n, int[][] edges) {
        int[][] adjMatrix = new int[n][n];
        for(int i=0;i<n;i++) for(int j=0;j<n;j++) adjMatrix[i][j]=-1;

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
//            int w = edge[2]; // if weighted graph

            adjMatrix[x][y] = 1;
            adjMatrix[y][x] = 1; // undirected
        }

        return adjMatrix;
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0,2}, {0,3}, {0,4}, {1,3}, {1,4}, {2,4}};

        int[][] adjMatrix = getAdjacencyMatrix(n, edges);
        System.out.println("adjMatrix: " + Arrays.deepToString(adjMatrix));
    }
}
