import devtools.util.Reader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int initialSize = 3; // Initial size for pre-populated vehicles
        Vehicle[] vehicles = new Vehicle[initialSize];
        prePopulateVehicles(vehicles);

        System.out.println(initialSize + " vehicles have been pre-populated:");
        Vehicle.printAllVehicles(vehicles);

        // Prompt user to add more vehicles
        if (Reader.readBoolean("Would you like to add more vehicles? ")) {
            int numVehicles = Reader.readInt("How many vehicles are you adding? ");
            vehicles = addVehicles(vehicles, numVehicles);
        }

        // Print all vehicles
        System.out.println("Final list of vehicles:");
        Vehicle.printAllVehicles(vehicles);

        // Remove vehicle functionality
        Vehicle.removeVehicle(vehicles);

        // Ask to remove motorbike modifications
        Vehicle.removeMotorbikeOptions(vehicles);

        // Option to change colour
        Vehicle.setColour(vehicles);

        // Option to change mileage
        Vehicle.setMileage(vehicles);

        // Revised PrintDetails
        Vehicle.dataConclusion(vehicles);
    }

    private static void prePopulateVehicles(Vehicle[] vehicles) {
        vehicles[0] = new Car("Toyota", "Yaris T Sport", 2005, Gearbox.MANUAL, CarColour.RED, 15000, "86512345", BodyType.Body.HATCHBACK);
        vehicles[0].addOption(Option.SAT_NAV);

        vehicles[1] = new Motorbike("Kawasaki", "Ninja", 2017, Gearbox.MANUAL, CarColour.GREEN, 5000, "54365421");

        vehicles[2] = new Car("Ford", "Fiesta ST", 2015, Gearbox.AUTO, CarColour.BLUE, 20000, "08767890", BodyType.Body.HATCHBACK);
        vehicles[2].addOption(Option.PARKING_SENSORS);
        vehicles[2].addOption(Option.ROOF_RACK);
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
}