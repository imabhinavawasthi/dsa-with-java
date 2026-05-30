package SessionMaths;

public class PrimeRange {
    // O(square_root(n))
    static boolean isPrime(int n) {
        for(int i=2;i*i<=n;i++) {
            if(n%i==0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int l = 10;
        int r = 100;

        for(int i=l;i<=r;i++) { // (r-l) * O(square_root(n))
            if(isPrime((i))) {
                System.out.println(i+" - Prime");
            }
            else {
                System.out.println(i+" - Not Prime");
            }
        }
    }
}
