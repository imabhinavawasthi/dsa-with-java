package SessionOOPs.Inheritance;

public class MultilevelInheritance {
    static class GrandFather {
        void house() {
            System.out.println("House");
        }
        void eat() {
            System.out.println("eat1");
        }
    }
    static class Father extends GrandFather {
        void car() {
            System.out.println("Car");
        }
        void eat() {
            System.out.println("eat2");
        }
        void eatFunInGrandfather() {
            super.eat();
        }
    }
    static class Child extends Father {

    }
    public static void main(String[] args) {
        Child ch = new Child();
        ch.car();
        ch.house();
        ch.eat();
        ch.eatFunInGrandfather();

        Father f1 = new Father();
        f1.eat();
    }
}