package Session4;

import java.util.Scanner;

public class Table {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        for(int j=1;j<=n;j++) {
            // table of j
            for(int i=1;i<=10;i++) {
                System.out.print(i*j+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}
