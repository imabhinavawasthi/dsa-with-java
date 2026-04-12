package Session5;

import java.util.Scanner;

public class ConstantTC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = 100;
        int b = 3;
        int c = 1000000000;
        int n = sc.nextInt();

        for(long i = 0;i < 1000000000000000L; i++) {
            System.out.print(n);
        }

        // O(1000000000000000) ~ O(1)

        sc.close();
    }
}
