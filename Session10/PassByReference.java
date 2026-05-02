package Session10;

// a,b,c
// average of a,b,c
// product of a,b,c
// sum of average and product

import java.util.Arrays;

public class PassByReference {
    public static void fun1(int[] arr) {
        arr[0]+=100;
        System.out.println("Value of arr in function call: " + Arrays.toString(arr));
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        fun1(arr);
        System.out.println("Value of arr after function call: " + Arrays.toString(arr));
    }

    // Ayushman - [101,2,3] [1,2,3]
    // Ayush - [101,2,3] [1,2,3]
    // Pushpraj - [101,2,3] [1,2,3]
    // Nitin - [101,2,3] [101,2,3]
    // Piyush - [101,2,3] [1,2,3]
}
