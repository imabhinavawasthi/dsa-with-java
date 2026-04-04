package Session3;

import java.util.Scanner;

public class SwitchCase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your character: ");
        char ch = sc.next().charAt(0);

        switch(ch) {
            case 'A':
                System.out.println("Good");
                break;
            case 'B':
                System.out.println("Fair");
                break;
            case 'C':
                System.out.println("Fail");
                break;
            default:
                System.out.println("Invalid");
        }

        sc.close();
    }
}
