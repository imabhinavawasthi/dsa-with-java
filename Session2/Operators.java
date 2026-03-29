package Session2;

public class Operators {
    public static void main(String[] args) {
        int a = 100;
        int b = 30;

        System.out.println(a+b);
        System.out.println(a-b);
        System.out.println(a*b);
        System.out.println(a/b);
        System.out.println((float) b/a);

        System.out.println((float) a/b);

        // modulus
        System.out.println(a%b);
        System.out.println(10%7);

        // Assignment Operator
        int x = 100;
        x += 5;
        x *= 2;
        x%=100;
        System.out.println(x);

        // Relational Operators (always return boolean value)
        a = 50;
        b = 20;
        int c = 50;

        System.out.println(a==b);
        System.out.println(a!=b);
        System.out.println(a>=c);
    }
}
