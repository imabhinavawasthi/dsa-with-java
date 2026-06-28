package SessionSorting;

import java.util.Arrays;

public class QuickSort {
    public static int partition(int[] arr, int start, int end) { // O(n)
        int pivot = arr[end];

        for(int i=start;i<=end;i++) {
            if(arr[i]<=pivot) {
                int temp = arr[i];
                arr[i] = arr[start];
                arr[start] = temp;
                start+=1;
            }
        }

        return start-1;
    }
    public static void quickSort(int[] arr, int start, int end) { // O(N)
        // base case
        if(start>=end) return;

        int pi = partition(arr, start, end);

        quickSort(arr, start, pi-1);
        quickSort(arr, pi+1, start);
    }
    public static void main(String[] args) {
        int n = 8;
        int[] arr = {4,6,1,3,8,5,2,7};

        System.out.println("Before Sorting: " + Arrays.toString(arr));

        quickSort(arr, 0, n-1); // O(NlogN)

        System.out.println("After Sorting: " + Arrays.toString(arr));

        // Time Complexity:
            // Worst Case: O(N^2)
            // Average Case: O(NlogN)
            // Best Case: O(NlogN)
        // Space Complexity: O(1)

        // Not Adaptable
        // In-place
        // Not Stable
    }
}
