package Session6;

import java.util.Scanner;

public class DecimalToOctal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter decimal number: ");
        int n = sc.nextInt();
        String octal = "";

        while(n>0) {
            int rem = n%8;
            octal = rem + octal;
            n/=8;
        }

        System.out.print(octal);

        sc.close();
    }
}
