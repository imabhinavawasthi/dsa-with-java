package Session4;

import java.util.Scanner;

public class Pattern3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        sc.close();

        for(int i=1;i<=n;i++) { // rows
            for(int j=0;j<i;j++) { // i stars
                if((i+j)%2==0) System.out.print("#");
                else System.out.print("*");
            }
            System.out.println();
        }
    }
}
