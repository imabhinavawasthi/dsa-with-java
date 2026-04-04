package Session3;

import java.util.Scanner;

public class SwitchLanguage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your input: ");
        int num = sc.nextInt();

        switch(num%5) {
            case 1:
                System.out.println("Hindi");
                break;
            case 2:
                System.out.println("English");
                break;
            case 3:
                System.out.println("Marathi");
                break;
            default:
                System.out.println("Wrong Input");
        }
    }
}
