package Session4;

import java.util.Scanner;

public class Pattern5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        sc.close();

        char ch = 'A';
        for(int i=1;i<=n;i++) { // rows
            for(int j=0;j<i;j++) { // i stars
                if(i==1 || i==n || j==0 || j==i-1) {
                    System.out.print(ch);
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            ch+=1;
        }
    }
}
