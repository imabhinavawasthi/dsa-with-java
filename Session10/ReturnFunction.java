package Session10;

import java.util.Scanner;

public class ReturnFunction {
    // add two numbers
    public static void sumOfNumbers(int a, int b) {
        System.out.println(a+b);
    }
    public static int sumOfNumbersReturn(int a, int b) {
        return a+b;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        sumOfNumbers(a,b);

        int ans = sumOfNumbersReturn(7,3);
        System.out.println(ans);

        int mx = Math.max(3,2);
        System.out.println(mx);
    }
}
