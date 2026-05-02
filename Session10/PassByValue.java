package Session10;

// a,b,c
// average of a,b,c
// product of a,b,c
// sum of average and product

public class PassByValue {
    public static void fun1(int x) {
        x+=100;
        System.out.println("Value of x in function call: " + x);
    }
    public static void main(String[] args) {
        int x = 10;
        fun1(x);
        System.out.println("Value of x after function call: " + x);
    }
}
