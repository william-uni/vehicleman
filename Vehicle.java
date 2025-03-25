import devtools.util.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements Serializable {
    private final Make make;
    private final String model;
    private final int year;
    private final Gearbox gearbox;
    private final String VIN;
    private CarColour colour;
    private int mileage;

    public Vehicle(Make make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.gearbox = gearbox;
        this.colour = colour;
        this.mileage = mileage;
        this.VIN = VINGen.generateVIN();
    }

    public static List<Vehicle> addVehicles(Vehicle[] vehicles, int numVehicles) {
        List<Vehicle> expandedVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            expandedVehicles.add(vehicle);
        }
        for (int i = vehicles.length; i < vehicles.length + numVehicles; i++) {
            String type = Reader.readPattern("Select vehicle type (1 for Car, 2 for Motorbike): ", "[12]", "Invalid selection. Please enter 1 for Car or 2 for Motorbike.");
            if (type.equals("1")) {
                addCar(expandedVehicles, i);
            } else if (type.equals("2")) {
                addMotorbike(expandedVehicles, i);
            }
        }
        return expandedVehicles;
    }

    private static void addCar(List<Vehicle> vehicles, int index) {
        Make make = Reader.readEnum("Please enter the make: ", Make.class);
        String model = Reader.readLine("Please enter the model: ");
        int year = Reader.readInt("Please enter the year: ");
        if (year > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid year. The vehicle cannot be newer than the current year.");
        }
        Gearbox gearbox = Reader.readEnum("Please enter the gearbox type (Manual or Auto): ", Gearbox.class);
        CarColour colour = Reader.readEnum("Please enter the colour: ", CarColour.class);
        int mileage = Reader.readInt("Please enter the mileage: ", 0, 1000000);
        boolean validBodyType = false;
        Car car = null;
        while (!validBodyType) {
            System.out.println("Select the body type:");
            System.out.println("1. Saloon");
            System.out.println("2. Estate");
            System.out.println("3. Hatchback");
            System.out.println("4. SUV");
            int bodyTypeChoice = Reader.readInt("Enter the number corresponding to the body type: ");
            switch (bodyTypeChoice) {
                case 1:
                    car = new Saloon(make, model, year, gearbox, colour, mileage);
                    validBodyType = true;
                    break;
                case 2:
                    car = new Estate(make, model, year, gearbox, colour, mileage);
                    validBodyType = true;
                    break;
                case 3:
                    car = new Hatchback(make, model, year, gearbox, colour, mileage);
                    validBodyType = true;
                    break;
                case 4:
                    car = new SUV(make, model, year, gearbox, colour, mileage);
                    validBodyType = true;
                    break;
                default:
                    System.out.println("Invalid selection. Please enter a number between 1 and 4.");
            }
        }
        vehicles.add(car);
        if (Reader.readBoolean("Would you like to add any options to the car?")) {
            addCarOptions(car);
        }
    }

    private static void addCarOptions(Car car) {
        if (Reader.readBoolean("Add Sat-Nav?")) car.addOption(Option.SAT_NAV);
        if (Reader.readBoolean("Add Parking Sensors?")) car.addOption(Option.PARKING_SENSORS);
        if (Reader.readBoolean("Add Tow Bar?")) car.addOption(Option.TOW_BAR);
        if (Reader.readBoolean("Add Roof Rack?")) car.addOption(Option.ROOF_RACK);
        if (Reader.readBoolean("Add All Wheel Drive?")) {
            if (car instanceof SUV) {
                car.addOption(Option.ALL_WHEEL_DRIVE);
            } else {
                System.out.println("AWD is only available for SUVs.");
            }
        }
        if (Reader.readBoolean("Add Third Row Seat?")) {
            if (car instanceof Estate) {
                car.addOption(Option.THIRD_ROW_SEAT);
            } else {
                System.out.println("Third Row Seat is only available for Estates.");
            }
        }
    }

    private static void addMotorbike(List<Vehicle> vehicles, int index) {
        Make make = Reader.readEnum("Please enter the make: ", Make.class);
        String model = Reader.readLine("Please enter the model: ");
        int year = Reader.readInt("Please enter the year: ");
        if (year > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid year. The vehicle cannot be newer than the current year.");
        }
        Gearbox gearbox = Reader.readEnum("Please enter the gearbox type (Manual or Auto): ", Gearbox.class);
        CarColour colour = Reader.readEnum("Please enter the colour: ", CarColour.class);
        int mileage = Reader.readInt("Please enter the mileage: ", 0, 1000000);
        Motorbike motorbike = new Motorbike(make, model, year, gearbox, colour, mileage);
        vehicles.add(motorbike);
        if (Reader.readBoolean("Would you like to add a Luggage Box?")) {
            motorbike.addOption(Option.LUGGAGE_BOX);
        }
    }

    public static void modifyVehicleOptions(Vehicle[] vehicles) {
        printAllVehiclesNoPrompt(vehicles);
        int index = Reader.readInt("Enter the number of the vehicle to modify options: ") - 1;
        if (index >= 0 && index < vehicles.length && vehicles[index] != null) {
            Vehicle vehicle = vehicles[index];
            if (vehicle instanceof Car) {
                addCarOptions((Car) vehicle);
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
        String searchKey = Reader.readLine("Enter search key: ").toLowerCase();
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null && (vehicle.getMake().toString().toLowerCase().contains(searchKey) ||
                    vehicle.getModel().toLowerCase().contains(searchKey) ||
                    vehicle.getColour().toString().toLowerCase().contains(searchKey) ||
                    vehicle.getVIN().toLowerCase().contains(searchKey) ||
                    String.valueOf(vehicle.getYear()).contains(searchKey))) {
                vehicle.printDetails();
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


    public static void removeVehicle(List<Vehicle> vehicles) {
        System.out.println("Vehicles in the database:");
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i) != null) {
                System.out.println((i + 1) + ": " + vehicles.get(i).getMake() + " " + vehicles.get(i).getModel());
            }
        }
        int index = Reader.readInt("Enter the number of the vehicle to remove: ") - 1;
        if (index >= 0 && index < vehicles.size() && vehicles.get(index) != null) {
            System.out.println("Removing: " + vehicles.get(index).getMake() + " " + vehicles.get(index).getModel());
            vehicles.remove(index);
        } else {
            System.out.println("Invalid choice. No vehicle removed.");
        }
    }

    // Getters
    public Make getMake() {
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
            CarColour currentColour = vehicles[index].getColour();
            CarColour newColour = Reader.readEnum("What colour would you like to change it to? ", CarColour.class);
            if (currentColour == newColour) {
                System.out.println("The vehicle is already " + currentColour.toString().toLowerCase() + ". No colour change needed.");
            } else {
                vehicles[index].setColour(newColour);
                System.out.println("Updated details: " + vehicles[index].getMake() + " " + vehicles[index].getModel() + " " + vehicles[index].getColour());
            }
        } else {
            System.out.println("Invalid choice. No colour changed.");
        }
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        if (mileage < 0 || mileage > 1000000) {
            throw new IllegalArgumentException("Invalid mileage. Mileage must be between 0 and 1,000,000.");
        }
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
            int currentMileage = vehicles[index].getMileage();
            if (newMileage < currentMileage) {
                boolean confirm = Reader.readBoolean("Are you sure you want to REDUCE the mileage? (Y/N): ");
                if (confirm) {
                    vehicles[index].setMileage(newMileage);
                    System.out.println("Mileage updated. New details: " + vehicles[index].getMake() + " " + vehicles[index].getModel() + " " + vehicles[index].getMileage() + " Miles");
                } else {
                    System.out.println("Mileage change cancelled.");
                }
            } else if (newMileage == currentMileage) {
                System.out.println("The new mileage is the same as the current mileage. No change made.");
            } else {
                vehicles[index].setMileage(newMileage);
                System.out.println("Mileage updated. New details: " + vehicles[index].getMake() + " " + vehicles[index].getModel() + " " + vehicles[index].getMileage() + " Miles");
            }
        } else {
            System.out.println("Invalid choice. No mileage changed.");
        }
    }

    // Option Functionality Implementation
    public abstract void addOption(Option options);
    public abstract void printDetails();
}
