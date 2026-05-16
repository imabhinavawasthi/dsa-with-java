package SessionOOPs.ExceptionHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomException {
    public static void issueDrivingLicense(int age, String test) throws DrivingTestNotPassedException,AgeNotEligibleException {
        if(age<18) {
            throw new AgeNotEligibleException("Age should not be less than 18");
        }
        if(!test.equals("PASS")) {
            throw new DrivingTestNotPassedException("Driving test failed");
        }
        System.out.println("Driving License Issued");
    }
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        String name = sc.nextLine();
        int age = sc.nextInt();
        sc.nextLine();
        String test = sc.nextLine(); // PASS

        try {
            issueDrivingLicense(age, test);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
