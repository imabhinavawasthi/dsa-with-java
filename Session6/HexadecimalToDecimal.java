package Session6;

import java.util.Scanner;

public class HexadecimalToDecimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter hexadecimal number: ");
        String hexadecimal = sc.next();
        int l = hexadecimal.length();
        int pow = 0;

        int n = 0;
        for(int i=l-1;i>=0;i--) {
            int digit = 0;
            if(hexadecimal.charAt(i)=='A') {
                digit = 10;
            }
            else if(hexadecimal.charAt(i)=='B') {
                digit = 11;
            }
            else if(hexadecimal.charAt(i)=='C') {
                digit = 12;
            }
            else if(hexadecimal.charAt(i)=='D') {
                digit = 13;
            }
            else if(hexadecimal.charAt(i)=='E') {
                digit = 14;
            }
            else if(hexadecimal.charAt(i)=='F') {
                digit = 15;
            }
            else {
                digit = hexadecimal.charAt(i)-'0';
            }
            n+= (int) (digit*Math.pow(16,pow));
            pow+=1;
        }

        System.out.print(n);
        sc.close();
    }
}
