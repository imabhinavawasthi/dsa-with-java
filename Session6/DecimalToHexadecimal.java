package Session6;

import java.util.Scanner;

public class DecimalToHexadecimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter decimal number: ");
        int n = sc.nextInt();
        String hex = "";

        while(n>0) {
            int rem = n%16;
            if(rem<=9) {
                hex = rem + hex;
            }
            else if(rem==10) {
                hex = "A" + hex;
            }
            else if(rem==11) {
                hex = "B" + hex;
            }
            else if(rem==12) {
                hex = "C" + hex;
            }
            else if(rem==13) {
                hex = "D" + hex;
            }
            else if(rem==14) {
                hex = "E" + hex;
            }
            else {
                hex = "F" + hex;
            }
            n/=16;
        }

        System.out.print(hex);

        sc.close();
    }
}
