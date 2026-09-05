public class P4_MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    // No-argument constructor
    public P4_MovieBookingProfile() {
    }

    // Convenience constructor
    public P4_MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    // Name property
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Confirmed property
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // OTP is write-only
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public static void main(String[] args) {

        P4_MovieBookingProfile p =
                new P4_MovieBookingProfile("Rahul Dev");

        System.out.println(p.getName());

        p.setConfirmed(true);

        System.out.println(p.isConfirmed());

        p.setOtp("4471");
    }
}
