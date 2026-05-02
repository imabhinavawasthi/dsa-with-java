package Session10;

import java.util.Scanner;

public class CelsiusesFahrenheit {
    public static float celsiusToFahrenheit(float c) {
        return ((float) 9 /5)*c+32;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float c = sc.nextInt();

        System.out.println(celsiusToFahrenheit(c));
    }
}
