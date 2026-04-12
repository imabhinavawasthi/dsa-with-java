package Session6;

import java.util.Scanner;

public class CountNumberOfBits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter decimal number: ");
        int n = sc.nextInt();
        int count = 0;

        while(n>0) {
            int rem = n%2;
            count+=rem;
            n/=2;
        }

        System.out.print(count);

        sc.close();
    }
}
