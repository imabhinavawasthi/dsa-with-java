package SessionSorting;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorSorting3 {
    public static void printArray(int[][] arr) {
        for (int[] x : arr) {
            for (int i : x) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] arr = {
                {9,4},
                {2,7,1,4},
                {1},
                {7,1}
        };

        System.out.println("Before Sorting: ");
        printArray(arr);

        Arrays.sort(arr, Comparator.comparingInt(a -> a.length));

        System.out.println("After Sorting: ");
        printArray(arr);
    }
}
