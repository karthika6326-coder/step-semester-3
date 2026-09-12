import java.util.Arrays;

class LibraryMemberP3 {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    public LibraryMemberP3(String memberId, int borrowLimit) {
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

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }
}

class StudentMemberP3 extends LibraryMemberP3 {

    public StudentMemberP3(String memberId, int borrowLimit,
                           String course) {
        super(memberId, borrowLimit);
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class P3_StudentFineLedger {

    public static void main(String[] args) {

        StudentMemberP3 student =
                new StudentMemberP3("STU5", 3, "CSE");

        student.chargeFine(100);

        System.out.println(student.getTotalFine());

        int[] history = student.getFineHistory();

        history[0] = 999;

        System.out.println(Arrays.toString(student.getFineHistory()));
    }
}