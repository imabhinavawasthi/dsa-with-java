package SessionSorting;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorSorting1 {
    public static void printArray(int[][] arr) {
        for (int[] x : arr) {
            for (int i : x) {
                System.out.print(i + "");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] arr = {
                {9,4,2},
                {2,7,1},
                {1,1,0},
                {7,1,3}
        };

        System.out.println("Before Sorting: ");
        printArray(arr);

        Arrays.sort(arr, Comparator.comparingInt(a -> a[2]));
        // Arrays.sort(arr, (a,b)-> a[2]-b[2]);

        System.out.println("After Sorting: ");
        printArray(arr);
    }
}
