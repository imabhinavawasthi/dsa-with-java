package Session4;

import java.util.Scanner;

public class SumOfDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        int x = sc.nextInt();
        int sum = 0;

        while(x>0) { // O(log10x)
            int  lastDigit = x%10;
            sum+=lastDigit;
            x/=10;
        }

        System.out.println(sum);

        sc.close();
    }
}
