package SessionSorting;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorSorting5 {
    public static void main(String[] args) {
        String[] arr = {
                "Mat",
                "Shiv",
                "Abhinav",
                "Zaid",
                "Kumud"
        };

        System.out.println("Before Sorting: " + Arrays.toString(arr));

        Arrays.sort(arr, Comparator.comparingInt(a -> a.charAt(a.length() - 1)));

        System.out.println("Before Sorting: " + Arrays.toString(arr));
    }
}
