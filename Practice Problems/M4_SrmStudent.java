class SrmStudent {
    static String collegeName, academicYear;
    String name;

    static {
        collegeName = "SRM University";
        academicYear = "2026-27";
        System.out.println("College info loaded");
    }

    SrmStudent(String name) {
        this.name = name;
    }

    void print() {
        System.out.println("Student record created: " + name);
    }
}

public class M4_SrmStudent {
    public static void main(String[] args) {
        String[] names = {"Ravi","Meera","Karthik","Divya","Anitha"};

        for (String n : names)
            new SrmStudent(n).print();
    }
}
