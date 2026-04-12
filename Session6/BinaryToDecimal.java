package Session6;

import java.util.Scanner;

public class BinaryToDecimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter binary number: ");
        String binary = sc.next();
        int l = binary.length();
        int pow = 0;

        int n = 0;
        for(int i=l-1;i>=0;i--) {
            int digit = binary.charAt(i)-'0';
            n+= (int) (digit*Math.pow(2,pow));
            pow+=1;
        }

        System.out.print(n);
        sc.close();
    }
}
