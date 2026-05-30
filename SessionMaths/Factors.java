package SessionMaths;

public class Factors {
    public static void main(String[] args) {
        int n = 60;

        // O(n)
        for(int i=1;i<=n;i++) {
            if(n%i==0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        for(int i=1;i*i<=n;i++) { // O(square_root(n))
            if(n%i==0) {
                System.out.print(i + " ");
                if(i!=(n/i)) {
                    System.out.print(n/i + " ");
                }
            }
        }
        System.out.println();


    }
}
