package SessionOOPs.Encapsulation;

public class Exam {
    String subject;
    String date;
    int[] marks;

    Exam(String sub, String date, int[] marks) {
        subject = sub;
        this.date = date;
        this.marks = marks;
    }

    void printExamDetails() {
        System.out.println(this.subject);
        System.out.println(this.date);
    }

    int getAverageMarks() {
        int n = marks.length;
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }

        return sum/n;
    }

    public static void main(String[] args) {
        int[] marks1 = {23,41,22};
        Exam exam1 = new Exam("Java","12th May",marks1);
        int[] marks2 = {23,41,22,45,21};
        Exam exam2 = new Exam("Maths","13th May",marks2);

        exam1.printExamDetails();
        exam2.printExamDetails();

        System.out.println(exam1.getAverageMarks());
        System.out.println(exam2.getAverageMarks());

        ModifierClass obj = new ModifierClass();
        obj.checkProtected();
        obj.checkDefault();
    }
}
