class LibraryMemberP2 {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMemberP2(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Invalid borrow limit");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: "
                + booksBorrowed;
    }
}

class StudentMemberP2 extends LibraryMemberP2 {

    protected String course;

    public StudentMemberP2(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course
                + " | Books Borrowed: " + booksBorrowed;
    }
}

class HonorsStudentMemberP2 extends StudentMemberP2 {

    private int bonusLimit;

    public HonorsStudentMemberP2(String memberId, int borrowLimit,
                                 String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + course
                + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + booksBorrowed;
    }
}

class FacultyMemberP2 extends LibraryMemberP2 {

    private String department;

    public FacultyMemberP2(String memberId, int borrowLimit,
                           String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department
                + " | Books Borrowed: " + booksBorrowed;
    }
}

public class P2_MembershipTree {

    static String classifyGeneration(LibraryMemberP2 member) {

        if (member instanceof HonorsStudentMemberP2) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMemberP2) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof StudentMemberP2) {
            return "Multilevel descendant";
        }

        return "General / direct member";
    }

    static int getTotalBooksBorrowed(LibraryMemberP2[] members) {

        int total = 0;

        for (LibraryMemberP2 member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    public static void main(String[] args) {

        StudentMemberP2 student =
                new StudentMemberP2("STU2", 3, "CSE");

        student.borrowBook();
        student.borrowBook();

        HonorsStudentMemberP2 honors =
                new HonorsStudentMemberP2("STU3", 3, "ECE", 2);

        honors.borrowBook();

        FacultyMemberP2 faculty =
                new FacultyMemberP2("STU4", 5, "Physics");

        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        LibraryMemberP2[] members = {
            student, honors, faculty
        };

        for (LibraryMemberP2 member : members) {
            System.out.println(member.displayInfo());
        }

        System.out.println(classifyGeneration(honors));
        System.out.println(classifyGeneration(faculty));

        System.out.println(getTotalBooksBorrowed(members));
    }
}