package SessionOOPs;

public class ModifierClass {
    protected static void checkProtected() {
        System.out.println("I am a ProtectedFunction");
    }
    static void checkDefault() { // default
        System.out.println("I am a checkDefault");
    }
    private static void checkPrivate() { // default
        System.out.println("I am a checkPrivate");
    }
    public static void main(String[] args) {
        checkProtected();
        checkDefault();
        checkPrivate();
        Student.printSubject();
        Student s = new Student("Abhinav");
        s.printName();
    }
}
