package SessionOOPs;

public class Rectangle {
    double length;
    double breadth;
    // Default Constructor
    Rectangle() {
        this.length = 5;
        this.breadth = 10;
    }
    Rectangle(double l, double b) {
        this.length = l;
        this.breadth = b;
    }
    public void printDetails() {
        System.out.println("Length: " + this.length);
        System.out.println("Breadth: " + this.breadth);
    }
    public double area() {
        return this.breadth * this.length;
    }
    public double perimeter() {
        return 2 * (this.breadth + this.length);
    }
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle(2,5);

        r1.printDetails();
        r2.printDetails();

        System.out.println("R1 Area: " + r1.area());
        System.out.println("R2 Perimeter: " + r1.perimeter());
    }
}