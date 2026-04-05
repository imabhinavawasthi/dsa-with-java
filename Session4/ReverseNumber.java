package Session4;

import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        int x = sc.nextInt();
        int reverse = 0;

        while(x>0) {
            int  lastDigit = x%10;
            reverse = reverse*10 + lastDigit;
            x/=10;
        }

        System.out.println(reverse);

        sc.close();
    }
}
