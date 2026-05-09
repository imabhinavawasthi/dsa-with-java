package SessionOOPs;

public class SuperKeyword {
    static class Company {
        String location;
        int companyId;
        Company(String location, int companyId) {
            this.location = location;
            this.companyId = companyId;
        }
        void printCompanyDetails() {
            System.out.println("PW");
        }
    }
    static class Employee extends Company {
        String name;
        int employeeId;
        Employee(String name, int employeeId, String location, int companyId) {
            super(location,companyId);
            this.name = name;
            this.employeeId = employeeId;
        }
        void printEmployeeDetails() {
            System.out.println("Name: " + this.name);
            System.out.println("Employee ID: " + this.employeeId);
        }
    }
    public static void main(String[] args) {
        Employee ep = new Employee("Abhinav",234567, "Bangalore", 345);
        ep.printCompanyDetails();
        ep.printEmployeeDetails();
    }
}