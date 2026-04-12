package Session5;

import java.util.Scanner;

public class LinearSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 7;
        int[] arr = {3,1,4,2,7,9,6};
        int x = 3;

        for(int i=0;i<n;i++) {
            if(i==x) break;
            System.out.print(arr[i] + " ");
        }
        sc.close();
    }
}
