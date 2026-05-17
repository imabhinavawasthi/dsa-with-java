package SessionRecursion;

public class RecursionExample3 {
    public static void fun(int i) {
        // base case
        if(i<0) return;
        // recursive
        fun(i-1);
        System.out.print(i + " ");
    }
    public static void main(String[] args) {
        fun(5);
    }
}
