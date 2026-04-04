package Session3;

import java.util.Scanner;

public class MarksGradesTernary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your marks: ");
        int marks = sc.nextInt();

        if(marks>=70) {
            System.out.println("Excellent");
        }
        else if(marks>=40) {
            System.out.println("Good");
        }
        else {
            System.out.println("Poor");
        }

        String grade = (marks>=70) ? "Excellent" : (marks>=40) ? "Good" : "Poor";
        System.out.println(grade);
    }
}
