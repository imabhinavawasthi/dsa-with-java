package Session5;

import java.util.Scanner;

public class LinearTC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i = 0;i < n; i++) { // O(n)
            System.out.print(i);
        }

        for(int i = 0;i < n + 10000000; i++) { // O(n+10000000)~O(n)
            System.out.print(i);
        }

        for(int i = 0;i < 5*n - 10000000; i++) { // O(5*n - 10000000)~O(n)
            System.out.print(i);
        }

        for(int i = 0;i < 100*n; i++) {
            System.out.print(i);
        }

        int m = sc.nextInt();
        for(int i = 0;i < m; i++) {
            System.out.print(i);
        }

        sc.close();
    }
}
