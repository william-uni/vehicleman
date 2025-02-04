import devtools.util.Reader;

public class Main {
    public static void main(String[] args) {
        int initialSize = 4; // Change this if changing the pre pop array size
        Vehicle[] vehicles = new Vehicle[initialSize]; // Vehicle Array gets the initial Size assigned to the array, so if the initial array size is 10, the array starts as 10
        prePopulateVehicles(vehicles); // Pre-population for easier testing
        System.out.println(initialSize + " vehicles have been pre-populated:");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null) {
                System.out.println((i + 1) + ": " + vehicles[i].getMake() + " " + vehicles[i].getModel());
            }
        }

        // Prompt user to add more vehicles
        if (Reader.readBoolean("Would you like to add more vehicles? ")) {

            int numVehicles = Reader.readInt("How many vehicles are you adding? ");

            // Expand array to accommodate new vehicles
            Vehicle[] expandedVehicles = new Vehicle[vehicles.length + numVehicles]; // New Expanded array is Regular Array + Input
            System.arraycopy(vehicles, 0, expandedVehicles, 0, vehicles.length);

            // Loop that creates a counter to the array and increments it for each vehicle added
            for (int i = vehicles.length; i < expandedVehicles.length; i++) {
                String type = Reader.readLine("What type of vehicle is it? (Car/Motorbike): ");

                if (type.equalsIgnoreCase("car")) {
                    addCar(expandedVehicles, i);
                } else if (type.equalsIgnoreCase("motorbike")) {
                    addMotorbike(expandedVehicles, i);
                }
            }
            vehicles = expandedVehicles; // Assign expanded array back to the original version.
        }

        // Print all vehicles
        System.out.println("Final list of vehicles:");
        printAllVehicles(vehicles);

        // Remove vehicle functionality
        removeVehicle(vehicles);

        // Ask to remove motorbike modifications
        removeMotorbikeOptions(vehicles);

        // Option to change colour
        setColour(vehicles);

        // Option to change mileage
        setMileage(vehicles);

        // Revised PrintDetails
        dataConclusion(vehicles);
    }

    // In order to increase or decrease pre-populated array, enter require info and increase initial value of the array on ln6 to desired number
    private static void prePopulateVehicles(Vehicle[] vehicles) {
        vehicles[0] = new Car("Toyota", "Yaris T Sport", 2005, Gearbox.MANUAL, "Red", 15000, "86512345", Body.HATCHBACK);
        vehicles[0].addOption(Option.SAT_NAV);

        vehicles[1] = new Motorbike("Kawasaki", "Ninja", 2017, Gearbox.MANUAL, "Green", 5000, "54365421");

        vehicles[2] = new Car("Ford", "Fiesta ST", 2015, Gearbox.AUTO, "Blue", 20000, "08767890", Body.HATCHBACK);
        vehicles[2].addOption(Option.PARKING_SENSORS);
        vehicles[2].addOption(Option.ROOF_RACK);

        vehicles[3] = new Car("Tesla", "Cybertruck", 2022, Gearbox.AUTO, "Silver", 12000, "98798765", Body.SUV);
        vehicles[3].addOption(Option.SAT_NAV);
        vehicles[3].addOption(Option.ALL_WHEEL_DRIVE);

    }

    // Index and I separated, to maintain functionality from pre-populated vehicles.
    private static void addCar(Vehicle[] vehicles, int index) {
        String make = Reader.readLine("Please enter the make: ");
        String model = Reader.readLine("Please enter the model: ");
        int year = Reader.readInt("Please enter the year: ");
        Gearbox gearbox = Reader.readEnum("Please enter the gearbox type (Manual or Auto): ", Gearbox.class);
        String colour = Reader.readLine("Please enter the colour: ");
        int mileage = Reader.readInt("Please enter the mileage: ");
        String VIN = Reader.readLine("Please enter the VIN: ");
        Body body = Reader.readEnum("Is the car a Saloon, Estate, Hatchback or SUV? ", Body.class);

        vehicles[index] = new Car(make, model, year, gearbox, colour, mileage, VIN, body);

        if (Reader.readBoolean("Would you like to add any options to the car?")) {
            addCarOptions(vehicles[index], body);
        }
    }

    private static void addCarOptions(Vehicle vehicle, Body body) { // Needs the scanner input, vehicle , body type.
        if (Reader.readBoolean("Add Sat-Nav?")) vehicle.addOption(Option.SAT_NAV);
        if (Reader.readBoolean("Add Parking Sensors?")) vehicle.addOption(Option.PARKING_SENSORS);
        if (Reader.readBoolean("Add Tow Bar?")) vehicle.addOption(Option.TOW_BAR);
        if (Reader.readBoolean("Add Roof Rack?")) vehicle.addOption(Option.ROOF_RACK);

        if (Reader.readBoolean("Add All Wheel Drive?")) {
            if (body == Body.SUV) {
                vehicle.addOption(Option.ALL_WHEEL_DRIVE);
            } else {
                System.out.println("AWD is only available for SUVs.");
            }
        }

        if (Reader.readBoolean("Add Third Row Seat?")) {
            if (body == Body.ESTATE) {
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
        String colour = Reader.readLine("Please enter the colour: ");
        int mileage = Reader.readInt("Please enter the mileage: ");
        String VIN = Reader.readLine("Please enter the VIN: ");

        vehicles[index] = new Motorbike(make, model, year, gearbox, colour, mileage, VIN);

        if (Reader.readBoolean("Would you like to add a Luggage Box?")) {
            vehicles[index].addOption(Option.LUGGAGE_BOX);
        }
    }

    private static void printAllVehicles(Vehicle[] vehicles) { // Error handling code, if
        System.out.println("Here are all your vehicles:");
        for (Vehicle vehicle : vehicles) { // For each vehicle, if not null, print details.
            if (vehicle != null) {
                vehicle.printDetails();
            }
        }
    }

    private static void removeVehicle(Vehicle[] vehicles) {
        if (Reader.readBoolean("Do you want to remove any vehicles?")) {
            System.out.println("Vehicles in the database:");
            for (int i = 0; i < vehicles.length; i++) { // for each vehicle in the array, print make and model, prompts the users with them to remove.
                if (vehicles[i] != null) {
                    System.out.println((i + 1) + ": " + vehicles[i].getMake() + " " + vehicles[i].getModel());
                }
            }

            int index = Reader.readInt("Enter the number of the vehicle to remove: ") - 1; // reduces input one as the number 1 in a list is actually 0 in array

            if (index >= 0 && index < vehicles.length && vehicles[index] != null) {
                System.out.println("Removing: " + vehicles[index].getMake() + " " + vehicles[index].getModel());
                vehicles[index] = null;

                // Shift array to fill the gap
                for (int i = index; i < vehicles.length - 1; i++) {
                    vehicles[i] = vehicles[i + 1];
                }
                vehicles[vehicles.length - 1] = null;
            } else {
                System.out.println("Invalid choice. No vehicle removed.");
            }
        }
    }

    private static void removeMotorbikeOptions(Vehicle[] vehicles) {
        System.out.println("Checking for motorbike modifications...");
        for (int i = 0; i < vehicles.length; i++) { // Runs through the array, and then checks for motorbikes only.
            if (vehicles[i] instanceof Motorbike motorbike) {
                System.out.println("\n" + (i + 1) + ": " + motorbike.getMake() + " " + motorbike.getModel());
                motorbike.printDetails();

                if (motorbike.getOptions().isEmpty()) { // If no modifications, Informs the user and doesn't allow them to choose to remove mods
                    System.out.println("This motorbike has no modifications.");
                } else if (Reader.readBoolean("Remove modifications from this motorbike?")) {
                    motorbike.getOptions().clear();
                    System.out.println("All modifications have been removed.");
                }
            }
        }
    }

    private static void setColour(Vehicle[] vehicles) {
        System.out.println("Collating each vehicle and their colour...");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null) {
                System.out.println("\n" + (i + 1) + ": " + vehicles[i].getMake() + " " + vehicles[i].getModel() + " " + vehicles[i].getColour());
                if (Reader.readBoolean("Would you like to change the colour?")) {
                    String newColour = Reader.readLine("What colour would you like to change it to? ");
                    vehicles[i].setColour(newColour);
                    System.out.println("Here are the updated details: ");
                    System.out.println(vehicles[i].getMake() + " " + vehicles[i].getModel() + " " + vehicles[i].getColour());
                }
            }
        }
    }

    private static void setMileage(Vehicle[] vehicles) {
        System.out.println("Collating each vehicle and their mileage...");
        for (int i = 0; i < vehicles.length; i++) { // Runs through the array, and then checks for motorbikes only.
            if (vehicles[i] != null) {
                System.out.println("\n" + (i + 1) + ": " + vehicles[i].getMake() + " " + vehicles[i].getModel() + " " + vehicles[i].getMileage() + " Miles");
                if (Reader.readBoolean("Would you like to change the mileage?")) {
                    int newMileage = Reader.readInt("What mileage would you like to change it to? ");
                    vehicles[i].setMileage(newMileage);
                    System.out.println("Here are the updated details: ");
                    System.out.println(vehicles[i].getMake() + " " + vehicles[i].getModel() + " " + vehicles[i].getMileage() + " Miles");
                }
            }
        }
    }

    private static void dataConclusion(Vehicle[] vehicles) {
        if (Reader.readBoolean("Would you like an output of all current vehicles?")) {
            System.out.println("Here is every item in the database (basic): ");
            for (int i = 0; i < vehicles.length; i++) { // Runs through the array, and then checks for motorbikes only.
                if (vehicles[i] != null) {
                    System.out.println("\n" + (i + 1) + ": " + vehicles[i].getMake() + ", " + vehicles[i].getModel() + ", " + vehicles[i].getYear() + ", " + vehicles[i].getColour() + ", " + vehicles[i].getMileage() + " Miles");
                }
            }
        }
    }
} // Runs through the array, and then checks for motorbikes only.