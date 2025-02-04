import java.util.ArrayList;

public class Motorbike extends Vehicle {
    private ArrayList<Option> options; // Use ArrayList for storing options

    public Motorbike(String make, String model, int year, Gearbox gearbox, String colour, int mileage, String VIN) {
        super(make, model, year, gearbox, colour, mileage, VIN);
        this.options = new ArrayList<>();
    }

    public void addOption(Option option) {
        this.options.add(option);
    }
    public void printDetails() {
        System.out.println("Motorbike Details:");
        System.out.println(
                "Make: " + getMake() + "\n" +
                        "Model: " + getModel() + "\n" +
                        "Year: " + getYear() + "\n" +
                        "Gearbox: " + getGearbox().toString().substring(0, 1).toUpperCase() + getGearbox().toString().substring(1).toLowerCase() + "\n" +
                        "Colour: " + getColour() + "\n" +
                        "Mileage: " + getMileage() + "\n" +
                        "VIN: " + getVIN());
        System.out.println("Options: " + options);
    }

    public ArrayList<Option> getOptions() {
        return options;
    }
}
