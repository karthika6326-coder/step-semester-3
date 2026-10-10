import java.util.*;

class Student {
    String rollNumber;
    String name;

    Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Student)) {
            return false;
        }

        Student student = (Student) obj;

        return rollNumber.equals(student.rollNumber);
    }

    @Override
    public int hashCode() {
        return rollNumber.hashCode();
    }
}

public class StudentClubRegistrationManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new HashSet<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("ADD")) {
                String roll = sc.next();
                String name = sc.next();

                Student student = new Student(roll, name);

                if (members.add(student)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }

            } else if (operation.equals("CONTAINS")) {
                String roll = sc.next();
                String name = sc.next();

                Student student = new Student(roll, name);

                System.out.println(
                    "contains: " + members.contains(student)
                );
            }
        }

        System.out.println("member count " + members.size());

        sc.close();
    }
}