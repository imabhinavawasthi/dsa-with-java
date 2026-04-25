package Session8;

import java.util.Scanner;

public class SumOfRows {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // rows
        int m = sc.nextInt(); // columns
        int[][] matrix = new int[n][m];

        int rows = matrix.length;
        int columns = matrix[0].length;

        for(int i=0;i<n;i++) { // rows
            for(int j=0;j<m;j++) { // columns
                matrix[i][j] = sc.nextInt();
            }
        }

        // sum of each row
        for(int i=0;i<n;i++) { // rows
            int sum = 0;
            for(int j=0;j<m;j++) { // columns
               sum+=matrix[i][j];
            }
            System.out.println("Sum of row " + i + " is " + sum);
        }

        // sum of each column
        for(int j=0;j<m;j++) { // columns
            int sum = 0;
            for(int i=0;i<n;i++) { // rows
                sum+=matrix[i][j];
            }
            System.out.println("Sum of column " + j + " is " + sum);
        }
    }
}
