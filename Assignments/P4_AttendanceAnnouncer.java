package Assignments;
public class P4_AttendanceAnnouncer {

    static String batchPrint(GymMember[] members) {

        StringBuilder sb = new StringBuilder();

        for (GymMember member : members) {

            member.displayInfo();

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                sb.append("Premium | Trainer: ")
                        .append(premium.getTrainerName())
                        .append(" | Sessions: ")
                        .append(member.getSessionsAttended())
                        .append(" [Trainer via downcast: ")
                        .append(premium.getTrainerName())
                        .append("] | ");

            } else {

                sb.append("Standard | Sessions: ")
                        .append(member.getSessionsAttended())
                        .append(" | ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        GymMember plain =
                new GymMember("MEM6", 1000);

        PremiumMember premium =
                new PremiumMember("MEM7", 2000, "Coach Riya");

        GymMember[] members = {
            plain, premium
        };

        System.out.println(batchPrint(members));
    }
}
