import devtools.util.Reader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To The Vehicle Management System...");
        int initialSize = 10; // Initial size for pre-populated vehicles
        Vehicle[] vehicles = new Vehicle[initialSize];
        prePopulateVehicles(vehicles);

        System.out.println(initialSize + " vehicles have been pre-populated:");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a new vehicle");
            System.out.println("2. Remove a vehicle");
            System.out.println("3. Add or remove options to/from a vehicle");
            System.out.println("4. Search for a vehicle");
            System.out.println("5. Display all vehicles");
            System.out.println("6. Exit");

            int choice = Reader.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    int numVehicles = Reader.readInt("How many vehicles are you adding? ");
                    vehicles = addVehicles(vehicles, numVehicles);
                    break;
                case 2:
                    Vehicle.removeVehicle(vehicles);
                    break;
                case 3:
                    modifyVehicleOptions(vehicles);
                    break;
                case 4:
                    searchVehicle(vehicles);
                    break;
                case 5:
                    Vehicle.printAllVehicles(vehicles);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void prePopulateVehicles(Vehicle[] vehicles) {
        vehicles[0] = new Car("Toyota", "Yaris T Sport", 2005, Gearbox.MANUAL, CarColour.RED, 15000, "86512345", BodyType.Body.HATCHBACK);
        vehicles[0].addOption(Option.SAT_NAV);

        vehicles[1] = new Motorbike("Kawasaki", "Ninja", 2017, Gearbox.MANUAL, CarColour.GREEN, 5000, "54365421");

        vehicles[2] = new Car("Ford", "Fiesta ST", 2015, Gearbox.AUTO, CarColour.BLUE, 20000, "08767890", BodyType.Body.HATCHBACK);
        vehicles[2].addOption(Option.PARKING_SENSORS);
        vehicles[2].addOption(Option.ROOF_RACK);

        vehicles[3] = new Car("Honda", "Civic", 2018, Gearbox.MANUAL, CarColour.BLACK, 12000, "12345678", BodyType.Body.HATCHBACK);

        vehicles[4] = new Motorbike("Yamaha", "MT-07", 2020, Gearbox.MANUAL, CarColour.BLUE, 3000, "87654321");

        vehicles[5] = new Car("BMW", "X5", 2021, Gearbox.AUTO, CarColour.WHITE, 10000, "23456789", BodyType.Body.SUV);
        vehicles[5].addOption(Option.TOW_BAR);

        vehicles[6] = new Car("Audi", "A4", 2019, Gearbox.AUTO, CarColour.SILVER, 15000, "34567890", BodyType.Body.ESTATE);
        vehicles[6].addOption(Option.ROOF_RACK);
        vehicles[6].addOption(Option.THIRD_ROW_SEAT);

        vehicles[7] = new Motorbike("Ducati", "Panigale V4", 2022, Gearbox.MANUAL, CarColour.RED, 2000, "45678901");

        vehicles[8] = new Car("Mercedes-Benz", "C-Class", 2020, Gearbox.AUTO, CarColour.GREY, 8000, "56789012", BodyType.Body.SALOON);

        vehicles[9] = new Car("Tesla", "Model 3", 2021, Gearbox.AUTO, CarColour.WHITE, 5000, "67890123", BodyType.Body.SALOON);
        vehicles[9].addOption(Option.PARKING_SENSORS);
    }

    private static Vehicle[] addVehicles(Vehicle[] vehicles, int numVehicles) {
        Vehicle[] expandedVehicles = new Vehicle[vehicles.length + numVehicles];
        System.arraycopy(vehicles, 0, expandedVehicles, 0, vehicles.length);

        for (int i = vehicles.length; i < expandedVehicles.length; i++) {
            String type = Reader.readPattern("Select vehicle type (1 for Car, 2 for Motorbike): ", "[12]", "Invalid selection. Please enter 1 for Car or 2 for Motorbike.");

            if (type.equals("1")) {
                addCar(expandedVehicles, i);
            } else if (type.equals("2")) {
                addMotorbike(expandedVehicles, i);
            }
        }
        return expandedVehicles;
    }

    private static void addCar(Vehicle[] vehicles, int index) {
        String make = Reader.readLine("Please enter the make: ");
        String model = Reader.readLine("Please enter the model: ");
        int year = Reader.readInt("Please enter the year: ");
        Gearbox gearbox = Reader.readEnum("Please enter the gearbox type (Manual or Auto): ", Gearbox.class);
        CarColour colour = Reader.readEnum("Please enter the colour: ", CarColour.class);
        int mileage = Reader.readInt("Please enter the mileage: ");
        String VIN = Reader.readLine("Please enter the VIN: ");
        BodyType.Body body = Reader.readEnum("Is the car a Saloon, Estate, Hatchback or SUV? ", BodyType.Body.class);

        vehicles[index] = new Car(make, model, year, gearbox, colour, mileage, VIN, body);

        if (Reader.readBoolean("Would you like to add any options to the car?")) {
            addCarOptions(vehicles[index], body);
        }
    }

    private static void addCarOptions(Vehicle vehicle, BodyType.Body body) {
        if (Reader.readBoolean("Add Sat-Nav?")) vehicle.addOption(Option.SAT_NAV);
        if (Reader.readBoolean("Add Parking Sensors?")) vehicle.addOption(Option.PARKING_SENSORS);
        if (Reader.readBoolean("Add Tow Bar?")) vehicle.addOption(Option.TOW_BAR);
        if (Reader.readBoolean("Add Roof Rack?")) vehicle.addOption(Option.ROOF_RACK);

        if (Reader.readBoolean("Add All Wheel Drive?")) {
            if (body == BodyType.Body.SUV) {
                vehicle.addOption(Option.ALL_WHEEL_DRIVE);
            } else {
                System.out.println("AWD is only available for SUVs.");
            }
        }

        if (Reader.readBoolean("Add Third Row Seat?")) {
            if (body == BodyType.Body.ESTATE) {
                vehicle.addOption(Option.THIRD_ROW_SEAT);
            } else {
                System.out.println("Third Row Seat is only available for Estates.");
            }
        }
    }

    private static void addMotorbike(Vehicle[] vehicles, int index) {
        String make = Reader.readLine("Please enter the make: ");
        String model = Reader.readLine("Please enter the model: ");
        int year = Reader.readInt("Please enter the year: ");
        Gearbox gearbox = Reader.readEnum("Please enter the gearbox type (Manual or Auto): ", Gearbox.class);
        CarColour colour = Reader.readEnum("Please enter the colour: ", CarColour.class);
        int mileage = Reader.readInt("Please enter the mileage: ");
        String VIN = Reader.readLine("Please enter the VIN: ");

        vehicles[index] = new Motorbike(make, model, year, gearbox, colour, mileage, VIN);

        if (Reader.readBoolean("Would you like to add a Luggage Box?")) {
            vehicles[index].addOption(Option.LUGGAGE_BOX);
        }
    }

    private static void modifyVehicleOptions(Vehicle[] vehicles) {
        int index = Reader.readInt("Enter the number of the vehicle to modify options: ") - 1;
        if (index >= 0 && index < vehicles.length && vehicles[index] != null) {
            Vehicle vehicle = vehicles[index];
            if (vehicle instanceof Car) {
                BodyType.Body body = ((Car) vehicle).getBody();
                addCarOptions(vehicle, body);
            } else if (vehicle instanceof Motorbike) {
                if (Reader.readBoolean("Add Luggage Box?")) {
                    vehicle.addOption(Option.LUGGAGE_BOX);
                } else if (Reader.readBoolean("Remove Luggage Box?")) {
                    ((Motorbike) vehicle).removeOption(Option.LUGGAGE_BOX);
                }
            }
        } else {
            System.out.println("Invalid vehicle number.");
        }
    }

    private static void searchVehicle(Vehicle[] vehicles) {
        String vin = Reader.readLine("Enter the VIN of the vehicle to search: ");
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null && vehicle.getVIN().equalsIgnoreCase(vin)) {
                vehicle.printDetails();
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }
}