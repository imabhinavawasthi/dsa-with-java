package SessionHashing;

import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        // unique
        // unordered
        // no indexing

        set.add(10);
        set.add(5);
        set.add(9);
        set.add(400);
        set.add(10);
        set.add(10);
        set.add(9);
        set.add(7);

        set.remove(5);

        System.out.println(set);
        System.out.println(set.size());
        System.out.println(set.contains(10));
        System.out.println(set.contains(5));

        for(Integer x: set) {
            System.out.print(x + " ");
        }
    }
}
