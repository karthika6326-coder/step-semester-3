class LibraryMemberP5 {
    private static int nextNumber = 101;

    protected int borrowLimit;
    protected int booksBorrowed;
    protected final String memberNumber;

    public LibraryMemberP5(int borrowLimit) {
        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Invalid borrow limit");
        }

        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
        this.memberNumber = "LIB-" + nextNumber++;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        borrowBook();
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }
}

class FacultyMemberP5 extends LibraryMemberP5 {
    private String department;

    public FacultyMemberP5(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class P5_MembershipAudit {

    static String processNightlyAudit(LibraryMemberP5[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMemberP5 member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMemberP5) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        LibraryMemberP5 member = new LibraryMemberP5(5);

        System.out.println(member.getMemberNumber());
        System.out.println(member.isValidRenewalCode("R12A"));
        System.out.println(member.isValidRenewalCode("R1AB"));

        member.borrowBook();
        member.borrowBook("Fiction");

        LibraryMemberP5[] members = {
            new FacultyMemberP5(5, "Physics"),
            null,
            new LibraryMemberP5(3)
        };

        System.out.println(processNightlyAudit(members));
    }
}