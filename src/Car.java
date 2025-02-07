import java.util.ArrayList;

public abstract class Car extends Vehicle {
    private final ArrayList<Option> options;

    public Car(String make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        super(make, model, year, gearbox, colour, mileage);
        this.options = new ArrayList<>();
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    @Override
    public void printDetails() {
        System.out.println(
                "Make: " + getMake() + "\n" +
                        "Model: " + getModel() + "\n" +
                        "Year: " + getYear() + "\n" +
                        "Gearbox: " + getGearbox().toString().substring(0, 1).toUpperCase() + getGearbox().toString().substring(1).toLowerCase() + "\n" +
                        "Colour: " + getColour().toString().substring(0, 1).toUpperCase() + getColour().toString().substring(1).toLowerCase() + "\n" +
                        "Mileage: " + getMileage() + "\n" +
                        "VIN: " + getVIN() + "\n" +
                        "Options: " + options + "\n"
        );
    }
}