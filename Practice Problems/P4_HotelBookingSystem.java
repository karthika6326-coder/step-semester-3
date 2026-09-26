interface Room {
    String getRoomNumber();
    double getPricePerDay();
}

class StandardRoom implements Room {
    private String roomNumber;

    public StandardRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerDay() {
        return 100;
    }
}

class DeluxeRoom implements Room {
    private String roomNumber;

    public DeluxeRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerDay() {
        return 150;
    }
}

class Suite implements Room {
    private String roomNumber;

    public Suite(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerDay() {
        return 250;
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

class Reservation {
    private Customer customer;
    private Room room;
    private int checkIn;
    private int checkOut;
    private boolean active;

    public Reservation(Customer customer, Room room, int checkIn, int checkOut) {
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.active = true;
    }

    public boolean overlaps(int start, int end) {
        return active && start < checkOut && end > checkIn;
    }

    public void cancel() {
        active = false;
        System.out.println("Reservation cancelled for "
                + customer.getName() + ".");
    }

    public Room getRoom() {
        return room;
    }

    public void displayReservation() {
        int days = checkOut - checkIn;
        double total = days * room.getPricePerDay();

        System.out.println("Room " + room.getRoomNumber()
                + " booked by " + customer.getName() + ".");
        System.out.printf("Total cost: $%.2f.%n", total);
    }
}

class Hotel {
    private java.util.ArrayList<Reservation> reservations;

    public Hotel() {
        reservations = new java.util.ArrayList<>();
    }

    public void bookRoom(Customer customer, Room room,
                          int checkIn, int checkOut) {

        for (Reservation reservation : reservations) {
            if (reservation.getRoom() == room
                    && reservation.overlaps(checkIn, checkOut)) {
                System.out.println("Room " + room.getRoomNumber()
                        + " is unavailable.");
                return;
            }
        }

        Reservation reservation =
                new Reservation(customer, room, checkIn, checkOut);

        reservations.add(reservation);
        reservation.displayReservation();
    }

    public void cancelBooking(Room room) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom() == room) {
                reservation.cancel();
                return;
            }
        }
    }
}

public class P4_HotelBookingSystem {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");

        Room room1 = new StandardRoom("101");
        Room room2 = new DeluxeRoom("202");

        Hotel hotel = new Hotel();

        hotel.bookRoom(customer1, room1, 1, 4);
        hotel.bookRoom(customer2, room1, 2, 5);

        hotel.cancelBooking(room1);

        hotel.bookRoom(customer2, room1, 5, 8);
        hotel.bookRoom(customer2, room2, 3, 6);
    }
}