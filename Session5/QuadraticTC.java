package Session5;

import java.util.Scanner;

public class QuadraticTC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i = 0;i < n*n; i++) { // O(n*n)
            System.out.print(i);
        }

        int m = sc.nextInt();
        for(int i = 0;i < m; i++) { // O(n*m)
            for(int j = 0;j < n;j++) {
                System.out.print(i);
            }
        }

        for(int j = 0;j < n;j++) { // O(n)
            System.out.print(j);
        }
        for(int i = 0;i < n; i++) { // (n^2)
            for(int j = 0;j < n;j++) {
                System.out.print(i);
            }
        }

        sc.close();
    }
}
