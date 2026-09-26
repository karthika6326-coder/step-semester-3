interface LeavePolicy {
    int getMaxLeaveDays();
    boolean isEligible(int days);
}

class FullTimePolicy implements LeavePolicy {
    public int getMaxLeaveDays() {
        return 20;
    }

    public boolean isEligible(int days) {
        return days <= 20;
    }
}

class PartTimePolicy implements LeavePolicy {
    public int getMaxLeaveDays() {
        return 10;
    }

    public boolean isEligible(int days) {
        return days <= 10;
    }
}

class ContractorPolicy implements LeavePolicy {
    public int getMaxLeaveDays() {
        return 5;
    }

    public boolean isEligible(int days) {
        return days <= 5;
    }
}

abstract class Employee {
    private String name;
    private LeavePolicy policy;

    public Employee(String name, LeavePolicy policy) {
        this.name = name;
        this.policy = policy;
    }

    public String getName() {
        return name;
    }

    public LeavePolicy getPolicy() {
        return policy;
    }

    public abstract String getEmployeeType();
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name, new FullTimePolicy());
    }

    public String getEmployeeType() {
        return "Full-Time";
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name, new PartTimePolicy());
    }

    public String getEmployeeType() {
        return "Part-Time";
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name, new ContractorPolicy());
    }

    public String getEmployeeType() {
        return "Contractor";
    }
}

class LeaveRequest {
    private Employee employee;
    private int days;
    private String status;

    public LeaveRequest(Employee employee, int days) {
        this.employee = employee;
        this.days = days;
        this.status = "Pending";
    }

    public void submit() {
        if (employee.getPolicy().isEligible(days)) {
            status = "Pending";
            System.out.println("Leave request submitted by "
                    + employee.getName() + ".");
        } else {
            status = "Rejected";
            System.out.println("Leave request rejected for "
                    + employee.getName() + ".");
        }
    }

    public void approve() {
        if (status.equals("Pending")) {
            status = "Approved";
            System.out.println("Leave request approved for "
                    + employee.getName() + ".");
        }
    }

    public void reject() {
        if (status.equals("Pending")) {
            status = "Rejected";
            System.out.println("Leave request rejected for "
                    + employee.getName() + ".");
        }
    }

    public void changeToPending() {
        status = "Pending";
        System.out.println("Leave request changed back to Pending.");
    }
}

public class P2_EmployeeLeaveRequest {
    public static void main(String[] args) {
        Employee employee1 = new FullTimeEmployee("Alice");
        Employee employee2 = new PartTimeEmployee("Bob");
        Employee employee3 = new Contractor("Charlie");

        LeaveRequest request1 = new LeaveRequest(employee1, 5);
        LeaveRequest request2 = new LeaveRequest(employee2, 12);
        LeaveRequest request3 = new LeaveRequest(employee3, 3);

        request1.submit();
        request1.approve();

        request2.submit();

        request3.submit();
        request3.reject();
        request3.changeToPending();
    }
}