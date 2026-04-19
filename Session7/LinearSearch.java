package Session7;

import java.util.*;
import java.util.Arrays;

public class LinearSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of elements
        int[] arr = new int[n]; // O(n)

        for(int i = 0; i < n ;i++) { // O(n)
            arr[i] = sc.nextInt();
        }

        int x = sc.nextInt();

        int ans = -1;
        for(int i=0;i<n;i++) {
            if(arr[i]==x) {
                ans = i;
                break;
            }
        }

        if(ans==-1) {
            System.out.println(x + " is not present in the array");
        }
        else {
            System.out.println(x + " is present in the array at index " + ans);
        }

        System.out.println(Arrays.toString(arr));
    }
}
