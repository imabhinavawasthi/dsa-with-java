package SessionOOPs.Inheritance;

public class Inheritance {
    static class Animal {
        void eat() {
            System.out.println("Eat");
        }
    }
    static class Dog extends Animal {
        void bark() {
            System.out.println("Bark");
        }
    }
    static class Bird extends Animal {
        void fly() {
            System.out.println("Fly");
        }
    }
    static class Fish extends Animal {
        void swim() {
            System.out.println("Swim");
        }
    }
    public static void main(String[] args) {
        Dog d1 = new Dog();
        d1.bark();
        Fish f1 = new Fish();
        f1.swim();
        f1.eat();
    }
}