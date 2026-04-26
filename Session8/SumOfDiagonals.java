package Session8;

import java.util.Scanner;

public class SumOfDiagonals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // rows
        int[][] matrix = new int[n][n];

        for(int i=0;i<n;i++) { // rows
            for(int j=0;j<n;j++) { // columns
                matrix[i][j] = sc.nextInt();
            }
        }

        // sum of diagonal 1
        int sum = 0;
        for(int i=0;i<n;i++) {
            sum+=matrix[i][i];
        }
        System.out.println("First Diagonal: "+ sum);

        // sum of diagonal 2
        sum = 0;
        for(int i=0;i<n;i++) {
            sum+=matrix[i][n-i-1];
        }
        System.out.println("Second Diagonal: "+ sum);
    }
}
