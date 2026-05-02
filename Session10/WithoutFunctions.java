package Session10;

// a,b,c
// average of a,b,c
// product of a,b,c
// sum of average and product

public class WithoutFunctions {
    public static void main(String[] args) {
        int a = 5, b = 3, c = 10;

        int average = (a+b+c)/3;
        int product = (a*b*c);
        int ans = average+product;
        System.out.println(ans);

        a = 9;
        b = 10;
        c = 9;

        average = (a+b+c)/3;
        product = (a*b*c);
        ans = average+product;
        System.out.println(ans);

        a = 97;
        b = 12;
        c = 95;

        average = (a+b+c)/3;
        product = (a*b*c);
        ans = average+product;
        System.out.println(ans);
    }

    // 100 times
}
