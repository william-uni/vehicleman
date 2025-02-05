import java.util.ArrayList;

public class Car extends Vehicle {
    private final BodyType.Body body;
    private final ArrayList<Option> options;

    public Car(String make, String model, int year, Gearbox gearbox, CarColour colour, int mileage, BodyType.Body body) {
        super(make, model, year, gearbox, colour, mileage);
        this.body = body;
        this.options = new ArrayList<>();
    }

    public BodyType.Body getBody() {
        return body;
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void printDetails() {
        System.out.println("Car Details:");
        System.out.println(
                "Make: " + getMake() + "\n" +
                        "Model: " + getModel() + "\n" +
                        "Year: " + getYear() + "\n" +
                        "Gearbox: " + getGearbox().toString().substring(0, 1).toUpperCase() + getGearbox().toString().substring(1).toLowerCase() + "\n" +
                        "Colour: " + getColour().toString().substring(0, 1).toUpperCase() + getColour().toString().substring(1).toLowerCase() + "\n" +
                        "Mileage: " + getMileage() + "\n" +
                        "VIN: " + getVIN() + "\n" +
                        "Body Type: " + body.toString().substring(0, 1).toUpperCase() + body.toString().substring(1).toLowerCase() + "\n" +
                        "Options: " + options
                        + "\n");
    }
}