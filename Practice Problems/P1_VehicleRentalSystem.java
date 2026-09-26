interface Vehicle {
    String getName();
    double calculateCharge(int days);
}

class Sedan implements Vehicle {
    private String name;

    public Sedan(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV implements Vehicle {
    private String name;

    public SUV(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck implements Vehicle {
    private String name;

    public Truck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double calculateCharge(int days) {
        return days * 100;
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

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private boolean active;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.active = true;
    }

    public void displayRental() {
        System.out.println(vehicle.getName() + " rented successfully by "
                + customer.getName() + ".");
        System.out.printf("Rental charge: $%.2f.%n",
                vehicle.calculateCharge(days));
    }

    public void returnVehicle() {
        active = false;
        System.out.println(vehicle.getName() + " returned by "
                + customer.getName() + ".");
    }

    public boolean isActive() {
        return active;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

class RentalSystem {
    private java.util.ArrayList<Rental> rentals;

    public RentalSystem() {
        rentals = new java.util.ArrayList<>();
    }

    public void rentVehicle(Customer customer, Vehicle vehicle, int days) {
        for (Rental rental : rentals) {
            if (rental.getVehicle() == vehicle && rental.isActive()) {
                System.out.println(vehicle.getName()
                        + " is currently unavailable.");
                return;
            }
        }

        Rental rental = new Rental(customer, vehicle, days);
        rentals.add(rental);
        rental.displayRental();
    }

    public void returnVehicle(Vehicle vehicle) {
        for (Rental rental : rentals) {
            if (rental.getVehicle() == vehicle && rental.isActive()) {
                rental.returnVehicle();
                return;
            }
        }
    }
}

public class P1_VehicleRentalSystem {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        RentalSystem system = new RentalSystem();

        system.rentVehicle(customer1, sedanA, 3);
        system.rentVehicle(customer2, sedanA, 2);

        system.returnVehicle(sedanA);

        system.rentVehicle(customer3, suvB, 5);
    }
}