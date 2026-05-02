package Session10;

import java.util.Scanner;

public class LeapYear {
    public static void checkLeapYear(int year) {
        if(year%4!=0) {
            System.out.println("Not a leap year");
        }
        else {
            if(year%100!=0 || year%400==0) {
                System.out.println("Leap year");
            }
            else {
                System.out.println("Not a leap year");
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();

        checkLeapYear(year);
    }
}
