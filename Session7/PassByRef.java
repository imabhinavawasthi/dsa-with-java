package Session7;

import java.util.Arrays;

public class PassByRef {
    public static void fun(int[] arr) { // pass by ref
        arr[1] = 100;
        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        fun(arr);
        System.out.println(Arrays.toString(arr));
    }
}
