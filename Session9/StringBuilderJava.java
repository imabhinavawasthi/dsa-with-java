package Session9;

import java.lang.*; // functions/in build

public class StringBuilderJava {
    public static void main(String[] args) {
        String a = "physics wallah";

        StringBuilder sb = new StringBuilder(a);
        sb.append(" classes");
        sb.insert(15,"java ");
        sb.reverse();
        sb.reverse();

        System.out.println(sb.toString());
    }
}