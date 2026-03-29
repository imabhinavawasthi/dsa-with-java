package Session2;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first value: ");
        int a = sc.nextInt();
        System.out.println("Enter second value: ");
        int b = sc.nextInt();
        System.out.println("Enter operator: ");
        char op = sc.next().charAt(0);

        if(op=='+') {
            System.out.println(a+b);
        }
        else if(op=='-') {
            System.out.println(a-b);
        }
        else if(op=='*') {
            System.out.println(a*b);
        }
        else if(op=='/') {
            System.out.println(a/b);
        }
        else if(op=='%') {
            System.out.println(a%b);
        }
        else {
            System.out.println("Invalid Operator");
        }
    }
}
