package SessionOOPs;

// a,b,c
// average of a,b,c
// product of a,b,c
// sum of average and product

public class SumOverloading {
    static int sum(int a, int b) {
        return a+b;
    }
    static double sum(double a, double b) {
        return a+b;
    }
    static double sum(int a, double b) {
        return a+b;
    }
    static int sum(int[] arr) {
        int ans = 0;
        for(int x: arr) {
            ans+=x;
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(sum(1,2));
        System.out.println(sum(1.546,2.42));
        System.out.println(sum(1,2.42));
        int[] arr = {2,4,4,2,4,2,2,34,3};
        System.out.println(sum(arr));
    }
}
