package SessionOOPs.ExceptionHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingExample {
    public static void getDetails() {
        String name = "Abhinav";

        System.out.println(name + " you got exception");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            int a = sc.nextInt(); // 2147483647
            int b = sc.nextInt(); // 2147483647

            int c = a/b;
            System.out.println("division: " + c);

            int[] arr = {1,2,3,4,5};
            String s = "phy";
            int x = sc.nextInt();

            System.out.println(arr[x]);
            System.out.println(s.charAt(x));
        }
        catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception Occurred: " + e);
        }
        catch (ArithmeticException e) {
            getDetails();
            System.out.println("Arithmetic Exception Occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
        finally {
            System.out.println("Try Catch Executed");
        }

        System.out.println("Thanks! Code is Executed");
    }
}
