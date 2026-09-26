interface MembershipPlan {
    String getName();
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {
    public String getName() {
        return "Monthly";
    }

    public double calculateFee() {
        return 1000;
    }
}

class QuarterlyPlan implements MembershipPlan {
    public String getName() {
        return "Quarterly";
    }

    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }
}

class AnnualPlan implements MembershipPlan {
    public String getName() {
        return "Annual";
    }

    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private String status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = "Active";
    }

    public void displayDetails() {
        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",
                plan.getName(),
                member.getName(),
                plan.calculateFee(),
                status);
    }

    public void checkIn() {
        if (status.equals("Active")) {
            System.out.println(member.getName() + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + member.getName()
                    + "'s membership is " + status + ".");
        }
    }

    public void freeze() {
        if (status.equals("Expired")) {
            System.out.println("Cannot freeze an Expired membership.");
        } else if (status.equals("Frozen")) {
            System.out.println("Membership is already Frozen.");
        } else {
            status = "Frozen";
            System.out.println(member.getName() + "'s membership frozen.");
            System.out.println("Status: " + status);
        }
    }

    public void unfreeze() {
        if (status.equals("Expired")) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(member.getName() + "'s membership unfrozen.");
            System.out.println("Status: " + status);
        }
    }

    public void expire() {
        status = "Expired";
        System.out.println(member.getName() + "'s membership expired.");
        System.out.println("Status: " + status);
    }
}

public class P4_FitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership m1 = new Membership(asha, new QuarterlyPlan());
        Membership m2 = new Membership(ravi, new MonthlyPlan());

        m1.displayDetails();
        m2.displayDetails();

        m1.checkIn();

        m1.freeze();
        m1.checkIn();

        m2.expire();
        m2.freeze();
    }
}