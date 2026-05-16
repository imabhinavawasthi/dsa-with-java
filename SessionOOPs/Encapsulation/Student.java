package SessionOOPs.Encapsulation;

// a,b,c
// average of a,b,c
// product of a,b,c
// sum of average and product

public class Student {
    String name;
    static String subject = "Java";
    Student(String name) {
        this.name = name;
    }
    public static void printSubject() {
        System.out.println(subject);
    }
    public void printName() {
        System.out.println(this.name);
    }
    public void fun() {
        printName();
    }
    public static void main(String[] args) {
        Student s1 = new Student("Abhinav");
        Student s2 = new Student("Awasthi");
        printSubject();
        s1.printName();
    }

}
