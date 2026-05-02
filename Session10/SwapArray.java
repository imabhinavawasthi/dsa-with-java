package Session10;

import java.util.Arrays;
import java.util.Scanner;

public class SwapArray {
    public static void swapArray(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=sc.nextInt();
        }
        int x = sc.nextInt();
        int y = sc.nextInt();

        System.out.println("Before Swapping: " + Arrays.toString(arr));
        swapArray(arr,x,y);
        System.out.println("After Swapping: " + Arrays.toString(arr));
    }
}
