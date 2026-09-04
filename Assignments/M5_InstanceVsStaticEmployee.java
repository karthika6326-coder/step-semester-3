class Employee {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class M5_InstanceVsStaticEmployee {
    public static void main(String[] args) {
        new Employee("Asha", 40000);
        new Employee("Ravi", 42000);
        new Employee("Meera", 45000);

        Employee.printCompanyInfo();
    }
}
