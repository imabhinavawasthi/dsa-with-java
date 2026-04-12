package Session5;

import java.util.Scanner;

public class LogarithmicTC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i = 1; i <= n; i*=2) { // O(log2n)
            System.out.print(i);
        }

        for(int j=1;j<n;j++) { // O(n*log2n)
            for(int i = 1; i <= n; i*=2) {
                System.out.print(i);
            }
        }

        for(int i = 1; i <= n; i*=3) { // O(log3n)
            System.out.print(i);
        }

        for(int i = n; i>0; i/=2) { // O(log2n)
            System.out.print(i);
        }

        for(int i = n; i>5; i%=5) {
            System.out.print(i);
        }

        sc.close();
    }
}
