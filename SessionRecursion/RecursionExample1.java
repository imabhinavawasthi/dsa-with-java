package SessionRecursion;

public class RecursionExample1 {
    public static void printFun(int n) {
        // base case
        if(n<=0) return;
        System.out.print(n*n + " ");
        // recursive case
        printFun(n/2);
    }
    // n = 20 -> 10 -> 5 -> 2 -> 1 -> 0
    // 400, 100, 25, 4, 1
    public static void main(String[] args) {
        int n = 20;

        printFun(n);
    }
}
