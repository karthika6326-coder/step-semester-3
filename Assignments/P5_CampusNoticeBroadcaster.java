interface NotificationChannel {
    void send(String studentName, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[Email → " + studentName + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[SMS → " + studentName + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[App → " + studentName + "] " + message);
    }
}

class NoticeStudent {
    private String name;
    private String department;
    private java.util.ArrayList<NotificationChannel> channels;

    public NoticeStudent(String name, String department) {
        this.name = name;
        this.department = department;
        channels = new java.util.ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public void receiveNotice(String message) {
        for (NotificationChannel channel : channels) {
            channel.send(name, message);
        }
    }
}

class Notice {
    private String title;
    private java.util.ArrayList<String> departments;

    public Notice(String title, java.util.ArrayList<String> departments) {
        this.title = title;
        this.departments = departments;
    }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty()
                && departments != null && !departments.isEmpty();
    }

    public String getTitle() {
        return title;
    }

    public java.util.ArrayList<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {
    private java.util.ArrayList<NoticeStudent> students;

    public NoticeBoard() {
        students = new java.util.ArrayList<>();
    }

    public void addStudent(NoticeStudent student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.print("Notice '" + notice.getTitle() + "' posted to ");

        for (int i = 0; i < notice.getDepartments().size(); i++) {
            System.out.print(notice.getDepartments().get(i));

            if (i < notice.getDepartments().size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(".");

        for (NoticeStudent student : students) {
            if (notice.getDepartments().contains(student.getDepartment())) {
                student.receiveNotice(notice.getTitle());
            }
        }
    }
}

public class P5_CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeStudent asha = new NoticeStudent("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        NoticeStudent ravi = new NoticeStudent("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();
        board.addStudent(asha);
        board.addStudent(ravi);

        java.util.ArrayList<String> cse = new java.util.ArrayList<>();
        cse.add("CSE");

        Notice n1 = new Notice("Lab Closed Tomorrow", cse);
        board.postNotice(n1);

        java.util.ArrayList<String> cseEce = new java.util.ArrayList<>();
        cseEce.add("CSE");
        cseEce.add("ECE");

        Notice n2 = new Notice("Fee Deadline Extended", cseEce);
        board.postNotice(n2);

        java.util.ArrayList<String> empty = new java.util.ArrayList<>();

        Notice n3 = new Notice("Sports Day", empty);
        board.postNotice(n3);
    }
}