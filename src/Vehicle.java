import devtools.util.Reader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Vehicle {
    // Setting Up Main Variables
    private final String make;
    private final String model;
    private final int year;
    private final Gearbox gearbox; // Type
    private CarColour colour;
    private int mileage;
    private final String VIN;

    // Constructor To Initialise Values / Take the Class Value
    public Vehicle(String make, String model, int year, Gearbox gearbox, CarColour colour, int mileage, String VIN) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.gearbox = gearbox;
        this.colour = colour;
        this.mileage = mileage;
        this.VIN = VIN;
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

    public int getMileage() {
        return mileage;
    }

    // Setters
    public void setColour(CarColour colour) {
        this.colour = colour;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    // Option Functionality Implementation
    public abstract void addOption(Option options);
    public abstract void printDetails();

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

    public static void removeVehicle(Vehicle[] vehicles) {
        if (Reader.readBoolean("Do you want to remove any vehicles?")) {
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
    }

    public static void removeMotorbikeOptions(Vehicle[] vehicles) {
        System.out.println("Checking for motorbike modifications...");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] instanceof Motorbike motorbike) {
                System.out.println("\n" + (i + 1) + ": " + motorbike.getMake() + " " + motorbike.getModel());
                motorbike.printDetails();

                if (motorbike.getOptions().isEmpty()) {
                    System.out.println("This motorbike has no modifications.");
                } else if (Reader.readBoolean("Remove modifications from this motorbike?")) {
                    motorbike.getOptions().clear();
                    System.out.println("All modifications have been removed.");
                }
            }
        }
    }

    public static void setColour(Vehicle[] vehicles) {
        System.out.println("Collating each vehicle and their colour...");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null) {
                System.out.println("\n" + (i + 1) + ": " + vehicles[i].getMake() + " " + vehicles[i].getModel() + " " + vehicles[i].getColour());
                if (Reader.readBoolean("Would you like to change the colour?")) {
                    CarColour newColour = Reader.readEnum("What colour would you like to change it to? ", CarColour.class);
                    vehicles[i].setColour(newColour);
                    System.out.println("Here are the updated details: ");
                    System.out.println(vehicles[i].getMake() + " " + vehicles[i].getModel() + " " + vehicles[i].getColour());
                }
            }
        }
    }

    public static void setMileage(Vehicle[] vehicles) {
        System.out.println("Collating each vehicle and their mileage...");
        for (int i = 0; i < vehicles.length; i++) {
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
}