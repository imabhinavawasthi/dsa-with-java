package SessionRecursion;

public class RecursionBasics {
    public static void printZeroToX(int i, int x) {
        // base case
        if(i>=x) return;
        System.out.print(i + " ");
        // recursive case
        printZeroToX(i+1,x);
    }
    public static void main(String[] args) {
        int x = 3;

//        for(int i=0;i<x;i++) {
//            System.out.print(i + " ");
//        }

        printZeroToX(0,x);
    }
}
