package SessionSorting;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int n = 8;
        int[] arr = {4,6,1,3,8,5,2,7};

        System.out.println("Before Sorting: " + Arrays.toString(arr));

        for(int i=0;i<n;i++) { // n times
            boolean isSwap = false;
            for(int j=0;j<n-i-1;j++) {
                if(arr[j]>arr[j+1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSwap = true;
                }
            }
            if(!isSwap) break; // array is already sorted
        }

//        Arrays.sort(arr); // optimised - O(NlogN)

        System.out.println("After Sorting: " + Arrays.toString(arr));

        // Time Complexity:
            // Worst Case: O(n^2)
            // Best Case: O(n)
        // Space Complexity: O(1)
    }
}
