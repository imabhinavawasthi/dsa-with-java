package SessionSorting;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int n = 8;
        int[] arr = {4,6,1,3,8,5,2,7};

        System.out.println("Before Sorting: " + Arrays.toString(arr));

        for(int i=0;i<n;i++) {
            int mni = i;
            for(int j=i+1;j<n;j++) {
                if(arr[j]<arr[mni]) {
                    mni = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[mni];
            arr[mni] = temp;
        }

        System.out.println("After Sorting: " + Arrays.toString(arr));

        // Time Complexity:
            // Worst Case: O(n^2)
            // Best Case: O(n^2)
        // Space Complexity: O(1)
    }
}
