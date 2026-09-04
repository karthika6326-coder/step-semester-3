class FeeAccount {}

class HostelFeeAccount extends FeeAccount {}

class Payment {
    void processPayment(FeeAccount a, double amount) {
        if (a instanceof HostelFeeAccount)
            System.out.println("Paid in two installments (hostel account)");
        else
            System.out.println("Paid in one go (day-scholar account)");
    }
}

public class M5_Payment {
    public static void main(String[] args) {
        FeeAccount[] a = {
            new HostelFeeAccount(), new HostelFeeAccount(),
            new FeeAccount(), new FeeAccount()
        };

        Payment p = new Payment();
        int h = 0, d = 0;

        for (FeeAccount x : a) {
            p.processPayment(x, 60000);

            if (x instanceof HostelFeeAccount)
                h++;
            else
                d++;
        }

        System.out.println("Hostel accounts processed: " + h +
                " | Day-scholar accounts processed: " + d);
    }
}
