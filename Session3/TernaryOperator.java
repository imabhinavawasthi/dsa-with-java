package Session3;

import java.util.Scanner;

public class TernaryOperator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        int num = sc.nextInt();

        String result;

//        if(num%2==0) {
//            result = "Even";
//        }
//        else {
//            result = "Odd";
//        }

        result = (num%2==0) ? "Even" : "Odd";

        System.out.println(result);
    }
}
