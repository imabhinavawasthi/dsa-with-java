package Session3;

import java.util.Scanner;

public class MaxNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first value: ");
        int a = sc.nextInt();
        System.out.println("Enter second value: ");
        int b = sc.nextInt();

        if(a>b) {
            System.out.println(a);
        }
        else {
            System.out.println(b);
        }
    }
}
