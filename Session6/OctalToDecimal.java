package Session6;

import java.util.Scanner;

public class OctalToDecimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter octa number: ");
        String octal = sc.next();
        int l = octal.length();
        int pow = 0;

        int n = 0;
        for(int i=l-1;i>=0;i--) {
            int digit = octal.charAt(i)-'0';
            n+= (int) (digit*Math.pow(8,pow));
            pow+=1;
        }

        System.out.print(n);
        sc.close();
    }
}
