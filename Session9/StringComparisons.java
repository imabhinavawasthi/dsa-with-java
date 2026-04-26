package Session9;

import java.util.Scanner;

public class StringComparisons {
    public static void main(String[] args) {
        String a = "abhinav"; // 98
        String b = "awasthi"; // 119
        String c = "harsh";
        String d = "awasthi";

        System.out.println(a.compareTo(b));
        System.out.println(c.compareTo(b));
        System.out.println(b.compareTo(d));

        char ch = 'd';
        System.out.println((int)ch);

        System.out.println("Abhinav".compareTo("abhinav"));
        System.out.println("Abhinav".compareToIgnoreCase("abhinav"));
    }
}