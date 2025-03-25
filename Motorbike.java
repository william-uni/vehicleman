import java.util.ArrayList;

public class Motorbike extends Vehicle {
    private final ArrayList<Option> options;

    public Motorbike(Make make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        super(make, model, year, gearbox, colour, mileage);
        this.options = new ArrayList<>();
    }

    public void addOption(Option option) {
        if (!this.options.contains(option)) {
            this.options.add(option);
        }
    }

    public void removeOption(Option option) {
        this.options.remove(option);
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "Make: " + getMake() + "\n" +
                "Model: " + getModel() + "\n" +
                "Year: " + getYear() + "\n" +
                "Gearbox: " + getGearbox().toString().substring(0, 1).toUpperCase() + getGearbox().toString().substring(1).toLowerCase() + "\n" +
                "Colour: " + getColour().toString().substring(0, 1).toUpperCase() + getColour().toString().substring(1).toLowerCase() + "\n" +
                "Mileage: " + getMileage() + "\n" +
                "VIN: " + getVIN() + "\n" +
                "Options: " + options + "\n";
    }

    @Override
    public void printDetails() {
        System.out.println(this.toString());
    }
}