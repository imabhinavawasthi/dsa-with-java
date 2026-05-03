package SessionBitManipulation;

public class BitwiseOperators {
    public static void main(String[] args) {
        int a = 21;
        int b = 39;
        int c = 12;

        int andValue = a&b&c;
        int orValue = a|b|c;
        int xorValue = a^b^c;

        System.out.println(andValue);
        System.out.println(orValue);
        System.out.println(xorValue);

        int x = 13;
        System.out.println(~x);

        x = -25;
        System.out.println(~x);

        x = 40;
        System.out.println(x<<1);
        System.out.println(x<<3);

        System.out.println(x>>1);
        System.out.println(x>>3);

        x = Integer.MAX_VALUE;
        System.out.println(x<<2);

        byte y = Byte.MIN_VALUE;
        System.out.println(y>>2);
    }
}
