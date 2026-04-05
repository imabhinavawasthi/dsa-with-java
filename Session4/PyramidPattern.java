package Session4;

import java.util.Scanner;

public class PyramidPattern {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        sc.close();

        for(int i=1;i<=n;i++) { // rows
            for(int j=0;j<n-i;j++) { // n-i spaces
                System.out.print(" ");
            }
            for(int j=0;j<2*i-1;j++) { // 2i-1 stars
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
