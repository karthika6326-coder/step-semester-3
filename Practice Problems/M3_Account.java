class Account {
    String regNo;
    double totalFee;

    Account(String r, double f) {
        regNo = r;
        totalFee = f;
    }

    final double calculateLateFee(int daysLate) {
        return totalFee * 0.10;
    }

    final void printSummary(int daysLate) {
        if (daysLate <= 0)
            System.out.println(regNo + " - On time, no late fee");
        else
            System.out.println(regNo + " | Total Fee: Rs " + totalFee +
                    " | Late Fee: Rs " + calculateLateFee(daysLate));
    }
}

public class M3_Account {
    public static void main(String[] args) {
        String[] r = {"RA001","RA002","RA003","RA004"};
        double[] f = {200000,150000,180000,220000};
        int[] d = {10,0,-2,5};

        for (int x = 0; x < r.length; x++)
            new Account(r[x],f[x]).printSummary(d[x]);
    }
}
