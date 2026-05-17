package SessionRecursion;

public class Factorial {
    public static int factorial(int n) {
        // base case
        if(n==1 || n==0) return 1;
        // recursive case
        return n*factorial(n-1);
    }
    public static void main(String[] args) {
        System.out.println(factorial(7));
        System.out.println(factorial(4));
    }
}
