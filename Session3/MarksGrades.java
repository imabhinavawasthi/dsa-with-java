package Session3;

import java.util.Scanner;

public class MarksGrades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your marks: ");
        int marks = sc.nextInt();

        if(marks>=90) {
            System.out.println("Excellent");
        }
        else if(marks>=70) {
            System.out.println("Good");
        }
        else if(marks>=40) {
            System.out.println("Fair");
        }
        else {
            System.out.println("Poor");
        }
    }
}
