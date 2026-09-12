class LibraryMemberP4 {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMemberP4(String memberId, int borrowLimit) {
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
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMemberP4 extends LibraryMemberP4 {
    private String course;

    public StudentMemberP4(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course
                + " | Books: " + booksBorrowed;
    }
}

public class P4_WeeklyCirculationReport {

    static String batchPrint(LibraryMemberP4[] members) {

        StringBuilder report = new StringBuilder();

        for (LibraryMemberP4 member : members) {

            report.append(member.displayInfo());

            if (member instanceof StudentMemberP4) {
                StudentMemberP4 student =
                        (StudentMemberP4) member;

                report.append(" [Course via downcast: ");
                report.append(student.getCourse());
                report.append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMemberP4[] members = {
            new LibraryMemberP4("LB5", 3),
            new StudentMemberP4("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(members));
    }
}