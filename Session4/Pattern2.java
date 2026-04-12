package Session4;

import java.util.Scanner;

public class Pattern2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        sc.close();

        int c = 1;
        for(int i=1;i<=n;i++) { // rows
            for(int j=0;j<i;j++) { // i stars
                System.out.print(c+" ");
                c+=1;
            }
            System.out.println();
        }
    }
}
