package Session4;

import java.util.Scanner;

public class SquarePattern {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        for(int i=1;i<=n;i++) { // rows
            for(int j=0;j<n;j++) { // n stars
                System.out.print("*");
            }
            System.out.println();
        }
        sc.close();
    }
}
