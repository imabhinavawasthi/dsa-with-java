package SessionSorting;

import java.util.Arrays;

public class MergeSort {
    public static void merge(int[] arr, int start, int mid, int end) { // O(n)
        int n1 = mid-start+1;
        int[] A = new int[n1];
        int n2 = end-mid;
        int[] B = new int[n2];

        int idx = 0;
        for(int i=start;i<=mid;i++) {
            A[idx++] = arr[i];
        }
        idx = 0;
        for(int i=mid+1;i<=end;i++) {
            B[idx++] = arr[i];
        }

        int i = 0;
        int j = 0;
        int k = start;

        while(k<=end) {
            if(i>=n1) {
                arr[k++] = B[j++];
            }
            else if(j>=n2) {
                arr[k++] = A[i++];
            }
            else if(A[i]<B[j]) {
                arr[k++] = A[i++];
            }
            else {
                arr[k++] = B[j++];
            }
        }
    }
    public static void mergeSort(int[] arr, int start, int end) { // O(logN)
        // base case
        if(start==end) return;

        // divide
        int mid = (start+end)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);

        // combine
        merge(arr, start, mid, end); // O(N)
    }
    public static void main(String[] args) {
        int n = 8;
        int[] arr = {4,6,1,3,8,5,2,7};

        System.out.println("Before Sorting: " + Arrays.toString(arr));

        mergeSort(arr, 0, n-1); // O(NlogN)

        System.out.println("After Sorting: " + Arrays.toString(arr));

        // Time Complexity:
            // Worst Case: O(NlogN)
            // Average Case: O(NlogN)
            // Best Case: O(NlogN)
        // Space Complexity: O(1)

        // Not Adaptable
        // Not in-place
        // Stable
    }
}
