package SessionOOPs;

// a,b,c
// average of a,b,c
// product of a,b,c
// sum of average and product

public class FunctionOverriding {
    static class Animal {
        public void makeSound() {
            System.out.println("Animals make sound");
        }
        private static void checkPrivate() { // default
            System.out.println("I am a checkPrivate");
        }
    }
    static class Dog extends Animal {
        public void makeSound() {
            System.out.println("Dogs make sound");
        }
        void makeSoundParent() {
            super.makeSound();
        }
    }
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();
        dog.makeSoundParent();
    }
}
