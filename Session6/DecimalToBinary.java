package Session6;

import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter decimal number: ");
        int n = sc.nextInt();
        String bin = "";

        while(n>0) {
            int rem = n%2;
            bin = rem + bin;
            n/=2;
        }

        System.out.print(bin);

        sc.close();
    }
}
