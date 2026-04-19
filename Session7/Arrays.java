package Session7;

import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {
        int[] arr; // declaration
        arr = new int[7]; // allocation
        // int[] arr = new int[6];
        arr[0] = 100;
        arr[1] = 9;

        System.out.println(arr[0]);
        System.out.println(arr.length);

        int[] arr2 = {1,2,3,4,5,6};
        System.out.println(arr2.length);

        int[] arr3 = new int[20]; // 0
        System.out.println(arr3[0]);
        System.out.println(arr3.length);
    }
}
