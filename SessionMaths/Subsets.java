package SessionMaths;

public class Subsets {
    static String decimalToBinary(int n, int minDigits) {
        String bin = "";

        while(n>0) {
            int rem = n%2;
            bin = rem + bin;
            n/=2;
        }

        while(bin.length()<minDigits) bin = "0" + bin;

        return bin;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int n = arr.length;

        double total = Math.pow(2,n);

        for(int i=0;i<total;i++) {
            String bin = decimalToBinary(i,n);

            System.out.println(i + ": " + bin);
        }
    }
}
