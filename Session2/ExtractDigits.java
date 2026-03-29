package Session2;

import java.util.Scanner;

public class ExtractDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value: ");
        int a = sc.nextInt();

        int x = a/100;
        a%=100;
        int y = a/10;
        a%=10;
        int z = a;

        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }
}
