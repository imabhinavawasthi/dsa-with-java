package Session8;

public class TwoDimensionalArrays {
    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int[][] matrix = new int[n][m];

        for(int i=0;i<n;i++) { // rows
            for(int j=0;j<m;j++) { // columns
                matrix[i][j] = i+j;
            }
        }

        // sum of each row
        for(int i=0;i<n;i++) { // rows
            for(int j=0;j<m;j++) { // columns
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
