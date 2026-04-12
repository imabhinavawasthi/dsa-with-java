package Session4;

import java.util.Scanner;

public class WhileLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        int x = sc.nextInt();

        while(x>0) {
            System.out.println(x);
            x/=10;
        }

        sc.close();
    }
}
