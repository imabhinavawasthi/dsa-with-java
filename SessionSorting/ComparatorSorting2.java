package SessionSorting;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorSorting2 {
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
                {9,4,2},
                {2,7,1},
                {1,100,0},
                {7,1,3}
        };

        System.out.println("Before Sorting: ");
        printArray(arr);

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0] + a[1] + a[2]));

        System.out.println("After Sorting: ");
        printArray(arr);
    }
}
