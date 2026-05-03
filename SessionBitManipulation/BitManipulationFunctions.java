package SessionBitManipulation;

public class BitManipulationFunctions {
    public static boolean checkSetBit(int n, int i) {
        return (n&(1<<i))!=0;
    }
    public static boolean checkEven(int n) {
        return (n&1)==0;
    }
    public static int setIthBit(int n, int i) {
        return n|(1<<i);
    }
    public static int unsetIthBit(int n, int i) {
        return n&-(1<<i);
    }
    public static int toggleIthBit(int n, int i) {
        return n^(1<<i);
    }
    public static boolean isPowerOfTwo(int n) {
        return n>0 && (n&(n-1))==0;
    }
    public static void main(String[] args) {
        int n = 45;

        System.out.println(checkSetBit(n,3));
        System.out.println(checkSetBit(n,4));

        System.out.println(checkEven(3));
        System.out.println(checkEven(4));

        System.out.println(setIthBit(n,1));
        System.out.println(setIthBit(n,3));

        System.out.println(unsetIthBit(n,1));
        System.out.println(unsetIthBit(n,3));

        System.out.println(toggleIthBit(n,1));
        System.out.println(toggleIthBit(n,3));

        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(10));
    }
}
