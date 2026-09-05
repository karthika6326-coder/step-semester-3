class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

public class P1_AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return accessorContext.equals("SAME_CLASS") ||
                       accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";

            case "protected":
                return accessorContext.equals("SAME_CLASS") ||
                       accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (String[] attempt : attempts) {
            for (int i = 0; i < modifiers.length; i++) {
                if (modifiers[i].equals(attempt[0])) {
                    if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                        allowed[i]++;
                    else
                        denied[i]++;
                }
            }
        }

        return "private: " + allowed[0] + " allowed / " + denied[0] + " denied | "
             + "default: " + allowed[1] + " allowed / " + denied[1] + " denied | "
             + "protected: " + allowed[2] + " allowed / " + denied[2] + " denied | "
             + "public: " + allowed[3] + " allowed / " + denied[3] + " denied";
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));
    }
}