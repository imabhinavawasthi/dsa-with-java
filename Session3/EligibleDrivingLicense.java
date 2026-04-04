package Session3;

import java.util.Scanner;

public class EligibleDrivingLicense {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        if(age>=18) {
            System.out.print("Enter test result: ");
            String result = sc.next();
            if(result.equals("PASS")) {
                System.out.println("Eligible");
            }
            else {
                System.out.println("Not Eligible");
            }
        }
        else {
            System.out.println("Not Eligible");
        }
    }
}
