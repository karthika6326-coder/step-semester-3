import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract int calculatePay();

    abstract String getType();

    @Override
    public String toString() {
        return "Payslip[name=" + name
            + ", type=" + getType()
            + ", pay=" + calculatePay() + "]";
    }
}

class FullTime extends Staff {
    int salary;

    FullTime(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    int calculatePay() {
        return salary;
    }

    @Override
    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    int hours;
    int rate;

    PartTime(String name, int hours, int rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    int calculatePay() {
        return hours * rate;
    }

    @Override
    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    int stipend;

    Intern(String name, int stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    int calculatePay() {
        return stipend;
    }

    @Override
    String getType() {
        return "Intern";
    }
}

public class PayrollRegisterSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Staff> staffList = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();

            switch (type) {
                case "FullTime":
                    staffList.add(
                        new FullTime(name, sc.nextInt())
                    );
                    break;

                case "PartTime":
                    int hours = sc.nextInt();
                    int rate = sc.nextInt();

                    staffList.add(
                        new PartTime(name, hours, rate)
                    );
                    break;

                case "Intern":
                    staffList.add(
                        new Intern(name, sc.nextInt())
                    );
                    break;
            }
        }

        int total = 0;
        int maxPay = Integer.MIN_VALUE;
        String topEarner = "";

        for (Staff staff : staffList) {
            System.out.println(staff);

            int pay = staff.calculatePay();
            total += pay;

            if (pay > maxPay) {
                maxPay = pay;
                topEarner = staff.name;
            }
        }

        System.out.println("Total " + total);
        System.out.println("top earner " + topEarner);

        sc.close();
    }
}