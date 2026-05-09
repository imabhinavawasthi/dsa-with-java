package SessionOOPs;

public class Vehicle {
    static class Car {
        String name;
        int price;

        void printName() {
            System.out.println(this.name);
        }
    }
    static class Scorpio extends Car {
        int id;
        String brand;

        int getId() {
            return this.id;
        }
    }
    public static void main(String[] args) {
        Scorpio s1 = new Scorpio();
        s1.id=1000;
        s1.name="Scorpio";
        s1.printName();
    }
}