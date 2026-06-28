package SessionSorting;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {3,4,3,2,1,2,3,4,4,4,2,2,1,2};
        int n = arr.length;

        System.out.println("Before Sorting: " + Arrays.toString(arr));

        int mx = 0;
        for (int k : arr) mx = Math.max(mx, k);

        int[] freq = new int[mx+1];
        for(int i=0;i<=mx;i++) freq[i] = 0;

        for (int j : arr) {
            freq[j]++;
        }

        int idx = 0;
        for(int i=0;i<=mx;i++) {
            while(freq[i]>0) {
                arr[idx++] = i;
                freq[i]-=1;
            }
        }

        System.out.println("After Sorting: " + Arrays.toString(arr));

        // Time Complexity:
            // Worst Case: O(N+K)
            // Average Case: O(N+K)
            // Best Case: O(N+K)
        // Space Complexity: O(N+K)

        // Not Adaptable
        // Not In-place
        // Not Stable
    }
}
