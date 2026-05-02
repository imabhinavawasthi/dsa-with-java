package Session10;

// a,b,c
// average of a,b,c
// product of a,b,c
// sum of average and product

public class Area {
    public static void area(int side) {
        System.out.println("Area of Square: " + side*side);
    }
    public static void area(int length, int breadth) {
        System.out.println("Area of Rectangle: " + length*breadth);
    }
    public static void area(double radius) {
        System.out.println("Area of Circle: " + 22/7*radius*radius);
    }
    public static void main(String[] args) {
        area(10);
        area(2,3);
        area(34.2);
    }
}
