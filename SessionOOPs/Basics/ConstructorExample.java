package SessionOOPs.Basics;

public class ConstructorExample {
    static class Employee {
        String name;
        int id;
        String designation;
        double salary;
        boolean isActive;
        // Default Constructor
        Employee() {
            this.name = "Unknown";
            this.id = 100;
            this.designation = "NA";
            this.salary = 0;
            this.isActive = false;
        }
        // Parametrized Constructor
        Employee(String name, int id, String designation, double salary, boolean isActive) {
            this.name = name;
            this.id = id;
            this.designation = designation;
            this.salary = salary;
            this.isActive = isActive;
        }
        Employee(String name, int id, String designation, double salary) {
            this.name = name;
            this.id = id;
            this.designation = designation;
            this.salary = salary;
            this.isActive = true;
        }
        public void printDetails() {
            if(this.isActive) System.out.println("Person profile is active");
            else System.out.println("Person profile is not active");
            System.out.println("Name: " + this.name);
            System.out.println("Id: " + this.id);
            System.out.println("Designation: " + this.designation);
        }
    }
    public static void main(String[] args) {
        Employee ep1 = new Employee("Abhinav", 25341, "SDE", 324567432145.4, true);
        Employee ep2 = new Employee();
        Employee ep3 = new Employee("Abhinav", 25341, "SDE", 324567432145.4);

        ep1.printDetails();
        ep2.printDetails();
    }
}
