package SessionMaths;

public class ModularArithmetic {
    public static int addition(int a, int b, int m) {
        return (a%m + b%m) % m;
    }
    public static int subtraction(int a, int b, int m) {
        return (a%m - b%m) % m;
    }
    public static int multiplication(int a, int b, int m) {
        return (a%m * b%m) % m;
    }
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int m = 100;

        System.out.println(addition(a,b,m));
        System.out.println(subtraction(a,b,m));
        System.out.println(multiplication(a,b,m));
    }
}
