package Session4;

import java.util.Scanner;

public class ForLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        int x = sc.nextInt(); // 100

        int i = 1;
        for(; i<=x; i*=2) {
            System.out.print(i + " ");
        }
        System.out.print(i); // 128

        // i = 1,2,4,8,16,32,64
        sc.close();
    }
}
