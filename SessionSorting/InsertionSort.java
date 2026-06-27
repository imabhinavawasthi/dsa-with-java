package SessionSorting;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int n = 8;
        int[] arr = {4,6,1,3,8,5,2,7};

        System.out.println("Before Sorting: " + Arrays.toString(arr));

        for(int i=1;i<n;i++) {
            int key = arr[i];
            int j = i-1;
            while(j>=0 && arr[j]>key) {
                arr[j+1] = arr[j];
                j-=1;
            }
            arr[j+1] = key;
        }

        System.out.println("After Sorting: " + Arrays.toString(arr));

        // Time Complexity:
            // Worst Case: O(n^2)
            // Best Case: O(n)
        // Space Complexity: O(1)
    }
}
