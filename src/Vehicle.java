import devtools.util.Reader;

public abstract class Vehicle {
    // Setting Up Main Variables
    private final String make;
    private final String model;
    private final int year;
    private final Gearbox gearbox; // Type
    private final String VIN;
    private CarColour colour;
    private int mileage;

    // Constructor To Initialise Values / Take the Class Value
    public Vehicle(String make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.gearbox = gearbox;
        this.colour = colour;
        this.mileage = mileage;
        this.VIN = VINGen.generateVIN(); // Automatically generate VIN
    }

    // Methods moved from Main
    public static void printAllVehicles(Vehicle[] vehicles) {
        if (Reader.readBoolean("Would you like an output of all current vehicles?")) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle != null) {
                    vehicle.printDetails();
                }
            }
        }
    }

    public static void printAllVehiclesNoPrompt(Vehicle[] vehicles) {
        System.out.println("Here is every item in the database (basic): ");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null) {
                System.out.println("\n" + (i + 1) + ": " + vehicles[i].getMake() + ", " + vehicles[i].getModel() + ", " + vehicles[i].getYear() + ", " + vehicles[i].getColour() + ", " + vehicles[i].getMileage() + " Miles");
            }
        }
    }

    public static void printAllVehicles2(Vehicle[] vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                vehicle.printDetails();
            }
        }
    }

    public static void removeVehicle(Vehicle[] vehicles) {
        System.out.println("Vehicles in the database:");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null) {
                System.out.println((i + 1) + ": " + vehicles[i].getMake() + " " + vehicles[i].getModel());
            }
        }

        int index = Reader.readInt("Enter the number of the vehicle to remove: ") - 1;

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

    public static void dataConclusion(Vehicle[] vehicles) {
        if (Reader.readBoolean("Would you like an output of all current vehicles?")) {
            System.out.println("Here is every item in the database (basic): ");
            for (int i = 0; i < vehicles.length; i++) {
                if (vehicles[i] != null) {
                    System.out.println("\n" + (i + 1) + ": " + vehicles[i].getMake() + ", " + vehicles[i].getModel() + ", " + vehicles[i].getYear() + ", " + vehicles[i].getColour() + ", " + vehicles[i].getMileage() + " Miles");
                }
            }
        }
    }

    public static Vehicle[] addVehicles(Vehicle[] vehicles, int numVehicles) {
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
        BodyType.Body body = Reader.readEnum("Is the car a Saloon, Estate, Hatchback or SUV? ", BodyType.Body.class);

        vehicles[index] = new Car(make, model, year, gearbox, colour, mileage, body);

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

        vehicles[index] = new Motorbike(make, model, year, gearbox, colour, mileage);

        if (Reader.readBoolean("Would you like to add a Luggage Box?")) {
            vehicles[index].addOption(Option.LUGGAGE_BOX);
        }
    }

    public static void modifyVehicleOptions(Vehicle[] vehicles) {
        printAllVehiclesNoPrompt(vehicles);
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

    public static void searchVehicle(Vehicle[] vehicles) {
        int searchChoice = Reader.readInt("Search by VIN (1) or by Make/Model (2): ");
        if (searchChoice == 1) {
            String vin = Reader.readLine("Enter the VIN of the vehicle to search: ");
            for (Vehicle vehicle : vehicles) {
                if (vehicle != null && vehicle.getVIN().equalsIgnoreCase(vin)) {
                    vehicle.printDetails();
                    return;
                }
            }
            System.out.println("Vehicle not found.");
        } else if (searchChoice == 2) {
            String make = Reader.readLine("Enter the make of the vehicle to search: ");
            boolean found = false;
            for (Vehicle vehicle : vehicles) {
                if (vehicle != null && vehicle.getMake().equalsIgnoreCase(make)) {
                    System.out.println("Found vehicle: " + vehicle.getMake() + " " + vehicle.getModel());
                    found = true;
                }
            }
            if (found) {
                String model = Reader.readLine("Enter the model of the vehicle to search: ");
                for (Vehicle vehicle : vehicles) {
                    if (vehicle != null && vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                        vehicle.printDetails();
                        return;
                    }
                }
                System.out.println("Vehicle not found.");
            } else {
                System.out.println("No vehicles found with the make: " + make);
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // Getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public String getVIN() {
        return VIN;
    }

    public CarColour getColour() {
        return colour;
    }

    // Setters
    public void setColour(CarColour colour) {
        this.colour = colour;
    }

    public static void setColour(Vehicle[] vehicles) {
        System.out.println("Vehicles in the database:");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null) {
                System.out.println((i + 1) + ": " + vehicles[i].getMake() + " " + vehicles[i].getModel() + " " + vehicles[i].getColour());
            }
        }

        int index = Reader.readInt("Enter the number of the vehicle to change colour: ") - 1;

        if (index >= 0 && index < vehicles.length && vehicles[index] != null) {
            CarColour newColour = Reader.readEnum("What colour would you like to change it to? ", CarColour.class);
            vehicles[index].setColour(newColour);
            System.out.println("Updated details: " + vehicles[index].getMake() + " " + vehicles[index].getModel() + " " + vehicles[index].getColour());
        } else {
            System.out.println("Invalid choice. No colour changed.");
        }
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public static void setMileage(Vehicle[] vehicles) {
        System.out.println("Vehicles in the database:");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null) {
                System.out.println((i + 1) + ": " + vehicles[i].getMake() + " " + vehicles[i].getModel() + " " + vehicles[i].getMileage() + " Miles");
            }
        }

        int index = Reader.readInt("Enter the number of the vehicle to change mileage: ") - 1;

        if (index >= 0 && index < vehicles.length && vehicles[index] != null) {
            int newMileage = Reader.readInt("What mileage would you like to change it to? ");
            vehicles[index].setMileage(newMileage);
            System.out.println("Updated details: " + vehicles[index].getMake() + " " + vehicles[index].getModel() + " " + vehicles[index].getMileage() + " Miles");
        } else {
            System.out.println("Invalid choice. No mileage changed.");
        }
    }

    // Option Functionality Implementation
    public abstract void addOption(Option options);

    public abstract void printDetails();
}