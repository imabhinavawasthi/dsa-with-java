package SessionCollections;

import java.util.ArrayList;
import java.util.Collections;

public class ReverseWords {
    public static void main(String[] args) {
        String s = "  a good   example ";
        s = s.trim();
        String[] arr = s.split(" ");

        ArrayList<String> al = new ArrayList<>();
        for(String x: arr) {
            if(!x.isEmpty()) {
                al.add(x);
            }
        }
        Collections.reverse(al);

        System.out.println(String.join(" ",al));
    }
}
