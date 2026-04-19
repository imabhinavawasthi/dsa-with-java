package Session7;

import java.util.*;

public class MarksAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of students
        int[] marks = new int[n]; // O(n)

        for(int i = 0; i < n ;i++) { // O(n)
            marks[i] = sc.nextInt();
        }

        int sum = 0;
        for(int i = 0; i < n ;i++) {
            sum += marks[i];
        }

        int mx = marks[0];
        int mn = marks[0];
        for(int i=1;i<n;i++) {
            if(marks[i]>mx) mx = marks[i];
            if(marks[i]<mn) mn = marks[i];
        }
        System.out.println("Min: " + mn);
        System.out.println("Max: " + mx);

        System.out.println("Average: " + sum/n);
        for(int i = 0; i < n ;i++) {
            System.out.print(marks[i] + " ");
        }

    }
}
