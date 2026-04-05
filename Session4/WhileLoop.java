package Session4;

import java.util.Scanner;

public class WhileLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        int x = sc.nextInt(); // 100->10->1->0

        while(x>0) {
            System.out.println(x);
            x/=10;
        }

        // 100, 10, 1

        sc.close();
    }
}
