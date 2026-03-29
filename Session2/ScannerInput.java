package Session2;

import java.util.Scanner;

public class ScannerInput {
    public static void main(String[] args) {
        // object of scanner class
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = sc.nextLine(); // input of whole line
        System.out.print("Enter your ID: ");
        String id = sc.next(); // input of a word
        System.out.print("Enter your Age: ");
        int age = sc.nextInt();
        System.out.print("Enter your Height: ");
        double height = sc.nextDouble();

        System.out.println("======= User Details =======");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);

        sc.close();
    }
}
