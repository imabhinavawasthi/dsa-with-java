package Session7;

import java.util.Arrays;

public class ArrayClone {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = arr1.clone();
        arr1[0] = 100;
        arr2[1] = 200;

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }
}
