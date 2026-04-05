package Session4;

import java.util.Scanner;

public class DoWhileLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt(); // 5

        for(int i = 10; i<=n; i++) {
            System.out.println(i);
        }

        int  i = 10;
        while(i<=n) {
            System.out.println(i);
            i+=1;
        }

        do {
            System.out.println(i);
            i += 1;
        }
        while (i <= n);

        sc.close();
    }
}
