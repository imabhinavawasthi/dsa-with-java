package SessionOOPs.Basics;

public class Car {
    String brand;
    int price;
    String color;
    Car(String brand, int price, String color) {
        this.brand = brand;
        this.price = price;
        this.color = color;
    }
    public void printCarDetails() {
        System.out.println("Brand: " + this.brand);
    }
    public static void main(String[] args) {
        Car car1 = new Car("Mahindra", 1000000, "Black");
        Car car2 = new Car("Toyota", 10000000, "White");

        car1.printCarDetails();
    }
}