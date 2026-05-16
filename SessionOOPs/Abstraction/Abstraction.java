package SessionOOPs.Abstraction;

public class Abstraction {
    abstract static class Shape {
        abstract int area();
    }
    static class Rectangle extends Shape {
        int length;
        int breadth;
        Rectangle(int l, int b) {
            this.length = l;
            this.breadth = b;
        }
        int area() {
            return this.length * this.breadth;
        }
    }
    public static void main(String[] args) {
        Shape sh = new Rectangle(3,5);
        System.out.println(sh.area());
    }
}