package SessionOOPs;

public class IntroductionToOOPS {
    static class Student {
        String name;
        int age;
        int rollNo;
        double marks;
        public void printDetails() {
            System.out.println("Name: " + this.name);
            System.out.println("Roll No: " + this.rollNo);
            System.out.println("Marks: " + this.marks);
        }
        public boolean getResult() {
            if(this.marks>=1000) return true;
            else return false;
        }
    }
    public static void main(String[] args) {
        Student st1 = new Student();
        st1.name = "abhinav";
        st1.age = 1332;
        st1.rollNo = 43567;
        st1.marks = 2143.234;

        st1.printDetails();
        System.out.println("Result of Student 1: " + st1.getResult());

        Student st2 = new Student();
        st2.name = "awasthi";
        st2.age = 345;
        st2.rollNo = 324;
        st2.marks = 435.32;

        st2.printDetails();
        System.out.println("Result of Student 2: " + st2.getResult());

        Student st3 = new Student();
        st3.name = "rahul";
    }
}
