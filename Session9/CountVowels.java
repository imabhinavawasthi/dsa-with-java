package Session9;

import java.util.Scanner;

public class CountVowels {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();

        int count = 0;

        for(int i=0;i<n;i++) {
            if(
                    s.charAt(i)=='a' ||
                    s.charAt(i)=='e' ||
                    s.charAt(i)=='i' ||
                    s.charAt(i)=='o' ||
                    s.charAt(i)=='u'
            ) {
                count++;
            }
        }

        System.out.println(count);
        // write a program to take string input and print number of vowels in that string
    }
}
