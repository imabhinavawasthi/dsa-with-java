package Session6;

import java.util.Scanner;

public class BuildInMethods {
    public static void main(String[] args) {
        // decimal to ...
        System.out.println(Integer.toBinaryString(100));
        System.out.println(Integer.toOctalString(100));
        System.out.println(Integer.toHexString(1000));

        // ... to decimal
        System.out.println(Integer.parseInt("2345642"));
        System.out.println(Integer.parseInt("100110",2));
        System.out.println(Integer.parseInt("3657",8));
        System.out.println(Integer.parseInt("3E8",16));
    }
}
