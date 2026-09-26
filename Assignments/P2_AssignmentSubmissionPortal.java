interface AssignmentType {
    String getName();
    double applyPenalty(double marks, int lateDays);
}

class CodingAssignment implements AssignmentType {
    public String getName() {
        return "Coding";
    }

    public double applyPenalty(double marks, int lateDays) {
        double penalty = lateDays * 10;
        return marks - (marks * penalty / 100);
    }
}

class WrittenAssignment implements AssignmentType {
    public String getName() {
        return "Written";
    }

    public double applyPenalty(double marks, int lateDays) {
        double penalty = lateDays * 20;
        return marks - (marks * penalty / 100);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Assignment {
    private String title;
    private int maxMarks;
    private int dueDay;
    private AssignmentType type;

    public Assignment(String title, int maxMarks, int dueDay, AssignmentType type) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getDueDay() {
        return dueDay;
    }

    public AssignmentType getType() {
        return type;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private int submissionDay;
    private String status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, int submissionDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.status = "Submitted";
    }

    public void displaySubmission() {
        int lateDays = Math.max(0, submissionDay - assignment.getDueDay());

        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '"
                    + assignment.getTitle() + "' received (on time).");
        } else {
            System.out.println(student.getName() + "'s submission for '"
                    + assignment.getTitle() + "' received ("
                    + lateDays + " days late).");
        }

        System.out.println("Status: " + status);
    }

    public void grade(double awardedMarks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade this submission.");
            return;
        }

        int lateDays = Math.max(0, submissionDay - assignment.getDueDay());

        finalMarks = assignment.getType().applyPenalty(awardedMarks, lateDays);
        status = "Graded";

        double penalty = awardedMarks - finalMarks;
        double penaltyPercent = 0;

        if (awardedMarks > 0) {
            penaltyPercent = (penalty / awardedMarks) * 100;
        }

        if (lateDays == 0) {
            System.out.printf("%s graded: %.0f/%d.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks());
        } else {
            System.out.printf("%s graded: %.0f/%d after %.0f%% late penalty.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks(),
                    penaltyPercent);
        }

        System.out.println("Status: " + status);
    }

    public void resubmit(int newDay) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle()
                    + "' has already been graded.");
        } else {
            submissionDay = newDay;
            System.out.println("Submission updated.");
        }
    }
}

public class P2_AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new Assignment(
                "Linked List Lab", 50, 10, new CodingAssignment());

        Assignment written = new Assignment(
                "Design Essay", 50, 12, new WrittenAssignment());

        Submission s1 = new Submission(asha, coding, 10);
        Submission s2 = new Submission(ravi, written, 14);

        s1.displaySubmission();
        s2.displaySubmission();

        s1.grade(45);
        s2.grade(40);

        s1.resubmit(11);
    }
}