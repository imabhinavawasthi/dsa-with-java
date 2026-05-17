package SessionRecursion;

public class RecursionExample4 {
    public static void printZeroToX(int i, int x) {
        // base case
        if(i>=x) return;
        // recursive case
        printZeroToX(i+1,x);
        System.out.print(i + " ");
    }
    public static void main(String[] args) {
        int x = 3;

        printZeroToX(0,x);
    }
}
