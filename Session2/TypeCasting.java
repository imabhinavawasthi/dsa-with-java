package Session2;

public class TypeCasting {
    public static void main(String[] args) {
        //implicit

        short age = 100;

        int x = 100;
        System.out.println(x);
        long z = x;
        System.out.println(z);

        double y = x; // implicit
        System.out.println(y);

        char ch = '^';
        int a = ch;
        System.out.println(a);

        // explicit
        double p = 123.322;
        int q = (int) p;
        System.out.println(q);

        String hello = "1234";
        int r = Integer.parseInt(hello);
        System.out.println(r);
    }
}
