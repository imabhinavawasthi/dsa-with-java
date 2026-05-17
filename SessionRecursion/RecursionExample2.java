package SessionRecursion;

public class RecursionExample2 {
    public static void tableOfN(int i, int n) {
        // base case
        if(i>10) return;
        System.out.println(n + " * " + i + " = " + n*i);
        // recursive
        tableOfN(i+1,n);
    }
    public static void main(String[] args) {
        int n = 10;
        tableOfN(1,n);
    }
}
