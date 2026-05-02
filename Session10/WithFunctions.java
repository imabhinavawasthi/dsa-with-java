package Session10;

// a,b,c
// average of a,b,c
// product of a,b,c
// sum of average and product

public class WithFunctions {
    public static void fun1(int a, int b, int c) {
        int average = (a+b+c)/3;
        int product = (a*b*c);
        int ans = average+product;
        System.out.println(ans);
    }
    public static void main(String[] args) {
        int a = 5, b = 3, c = 10;
        fun1(a,b,c); // arguments in function call
        int average = 0;

        a = 9;
        b = 10;
        c = 9;
        fun1(a,b,c);

        a = 97;
        b = 12;
        c = 95;
        fun1(a,b,c);
    }

    // 100 times
}
