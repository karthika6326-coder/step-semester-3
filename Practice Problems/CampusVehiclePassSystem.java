import java.util.*;

interface Chargeable {
    boolean bookChargingBay();
}

abstract class Vehicle {
    String passNumber;
    String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int getPassFee();

    abstract String getType();
}

class Bike extends Vehicle {
    Bike(String pass, String owner) {
        super(pass, owner);
    }

    @Override
    int getPassFee() {
        return 300;
    }

    @Override
    String getType() {
        return "Bike";
    }
}

class Car extends Vehicle {
    Car(String pass, String owner) {
        super(pass, owner);
    }

    @Override
    int getPassFee() {
        return 1000;
    }

    @Override
    String getType() {
        return "Car";
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String pass, String owner) {
        super(pass, owner);
    }

    @Override
    int getPassFee() {
        return 300;
    }

    @Override
    String getType() {
        return "EBike";
    }

    @Override
    public boolean bookChargingBay() {
        return true;
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String pass, String owner) {
        super(pass, owner);
    }

    @Override
    int getPassFee() {
        return 1000;
    }

    @Override
    String getType() {
        return "ECar";
    }

    @Override
    public boolean bookChargingBay() {
        return true;
    }
}

public class CampusVehiclePassSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String pass = sc.next();
                String owner = sc.next();

                Vehicle vehicle = null;

                switch (type) {
                    case "Bike":
                        vehicle = new Bike(pass, owner);
                        break;

                    case "Car":
                        vehicle = new Car(pass, owner);
                        break;

                    case "EBike":
                        vehicle = new EBike(pass, owner);
                        break;

                    case "ECar":
                        vehicle = new ECar(pass, owner);
                        break;
                }

                if (vehicle != null) {
                    vehicles.put(pass, vehicle);

                    System.out.println(
                        pass + " (" + vehicle.getType()
                        + ") pass fee " + vehicle.getPassFee()
                    );
                }

            } else if (operation.equals("CHARGE")) {
                String pass = sc.next();
                Vehicle vehicle = vehicles.get(pass);

                if (vehicle == null) {
                    System.out.println("Vehicle not found");

                } else if (vehicle instanceof Chargeable) {
                    Chargeable chargeable = (Chargeable) vehicle;

                    if (chargeable.bookChargingBay()) {
                        System.out.println(
                            pass + " charging bay allotted"
                        );
                    }

                } else {
                    System.out.println(
                        pass + " rejected: charging unsupported"
                    );
                }
            }
        }

        sc.close();
    }
}