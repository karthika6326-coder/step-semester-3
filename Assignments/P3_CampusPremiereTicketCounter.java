interface Seat {
    String getSeatNumber();
    double getPrice();
}

class RegularSeat implements Seat {
    private String seatNumber;

    public RegularSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat implements Seat {
    private String seatNumber;

    public PremiumSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat implements Seat {
    private String seatNumber;

    public ReclinerSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {
    private String showTime;
    private boolean started;
    private java.util.ArrayList<Seat> bookedSeats;

    public Show(String showTime) {
        this.showTime = showTime;
        this.started = false;
        bookedSeats = new java.util.ArrayList<>();
    }

    public boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(seat);
    }

    public boolean bookSeat(Seat seat) {
        if (!isAvailable(seat)) {
            return false;
        }

        bookedSeats.add(seat);
        return true;
    }

    public void releaseSeat(Seat seat) {
        bookedSeats.remove(seat);
    }

    public void startShow() {
        started = true;
    }

    public boolean hasStarted() {
        return started;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private java.util.ArrayList<Seat> seats;
    private boolean cancelled;

    public Booking(Customer customer, Show show) {
        this.customer = customer;
        this.show = show;
        seats = new java.util.ArrayList<>();
        cancelled = false;
    }

    public void addSeat(Seat seat) {
        if (seats.size() >= 6) {
            System.out.println("Cannot book more than 6 seats.");
            return;
        }

        if (!show.bookSeat(seat)) {
            System.out.println("Seat " + seat.getSeatNumber()
                    + " is already booked for this show.");
            return;
        }

        seats.add(seat);
    }

    public void confirm() {
        if (seats.isEmpty()) {
            return;
        }

        System.out.print("Booking confirmed for " + customer.getName() + ": ");

        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).getSeatNumber());

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(". Total: ₹%.2f.%n", calculateTotal());
    }

    public double calculateTotal() {
        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel() {
        if (show.hasStarted()) {
            System.out.println("Cannot cancel: show has already started.");
            return;
        }

        if (cancelled) {
            return;
        }

        for (Seat seat : seats) {
            show.releaseSeat(seat);
        }

        cancelled = true;

        System.out.println(customer.getName() + "'s booking cancelled.");

        System.out.print("Seats ");

        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).getSeatNumber());

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(" released.");
    }
}

public class P3_CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(asha, show);

        b1.addSeat(new RegularSeat("A1"));
        b1.addSeat(new RegularSeat("A2"));
        b1.addSeat(new PremiumSeat("F5"));

        b1.confirm();

        Booking b2 = new Booking(ravi, show);
        b2.addSeat(new RegularSeat("A2"));
        b2.addSeat(new ReclinerSeat("R1"));
        b2.confirm();

        b1.cancel();

        Booking b3 = new Booking(neha, show);
        b3.addSeat(new RegularSeat("A2"));
        b3.confirm();
    }
}