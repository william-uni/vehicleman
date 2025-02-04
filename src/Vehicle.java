public abstract class Vehicle {
    // Setting Up Main Variables
    private final String make;
    private final String model;
    private final int year;
    private final Gearbox gearbox; // Type
    private String colour;
    private int mileage;
    private final String VIN;

    // Constructor To Initialise Values / Take the Class Value
    public Vehicle(String make, String model, int year, Gearbox gearbox, String colour, int mileage, String VIN) {
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

    public String getColour() {
        return colour;
    }

    public int getMileage() {
        return mileage;
    }

    // Setters
    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    // Option Functionality Implementation
    public abstract void addOption(Option options);
    public abstract void printDetails();
}