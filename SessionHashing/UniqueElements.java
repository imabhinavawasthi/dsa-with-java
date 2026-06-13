package SessionHashing;

import java.util.HashSet;

public class UniqueElements {
    public static void main(String[] args) {
        int[] arr = {2,43,5,4,2,1,2,3,45,5,34,2,2,3,4};
        HashSet<Integer> set = new HashSet<>();

        for(int x: arr) { // O(N)
            set.add(x); // O(1)
        }

        for(Integer x: set) {
            System.out.print(x + " ");
        }
    }
}
