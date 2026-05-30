package SessionMaths;

public class EuclideanGCD {
    static int lcm(int a, int b) {
        return a*b/gcd(a,b);
    }
    static int gcd(int a, int b) {
        // base case
        if(b==0) return a;
        // recursive case
        return gcd(b,a%b);
    }
    public static void main(String[] args) {
        System.out.println(gcd(18,60));
        System.out.println(gcd(112,120));

        System.out.println(lcm(18,60));
        System.out.println(lcm(112,120));
    }
}
