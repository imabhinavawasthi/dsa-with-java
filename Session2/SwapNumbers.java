package Session2;

import java.util.Scanner;

public class SwapNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first value: ");
        int a = sc.nextInt();
        System.out.print("Enter second value: ");
        int b = sc.nextInt();
        System.out.println("Initial Value: a = " + a + " ; b = " + b);

//        int temp = a;
//        a = b;
//        b = temp;
        a = a+b;
        b = a-b;
        a = a-b;
        System.out.println("Swapped Value: a = " + a + " ; b = " + b);
    }
}
