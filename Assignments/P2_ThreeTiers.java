package Assignments;
public class P2_ThreeTiers {

    static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof PremiumMember) {
            return "Multilevel descendant (2 generations deep)";
        }

        return "Base member";
    }

    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;

        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }

    public static void main(String[] args) {

        GymMember member = new GymMember("MEM1", 1000);
        PremiumMember premium =
                new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite =
                new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember group =
                new GroupClassMember("MEM4", 1500, "Zumba");

        member.displayInfo();
        premium.displayInfo();
        elite.displayInfo();
        group.displayInfo();

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(group));

        premium.attendSession();
        premium.attendSession();
        premium.attendSession();

        elite.attendSession();
        elite.attendSession();

        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();

        GymMember[] members = {
            premium, elite, group
        };

        System.out.println(getTotalSessionsAttended(members));
    }
}
