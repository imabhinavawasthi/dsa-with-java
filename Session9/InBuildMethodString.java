package Session9;

import java.util.Scanner;

public class InBuildMethodString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "physics wallah";

        System.out.println(s.substring(3,9));
        System.out.println(s.substring(10));

        System.out.println(s.replace('a','i'));
        System.out.println("abcd".equals("ABcd"));
        System.out.println("abcd".equalsIgnoreCase("ABcd"));

        System.out.println(s.contains("cs wa"));
        System.out.println(s.contains("efwuikhd"));

        System.out.println(s.indexOf("cs wa"));
        System.out.println(s.indexOf("efwuikhd"));

        System.out.println(s);
    }
}
