import java.util.ArrayList;

public class Car extends Vehicle {
    private Body body;
    private ArrayList<Option> option;

    public Car(String make, String model, int year, Gearbox gearbox, String colour, int mileage, String VIN, Body body) {
        super(make, model, year, gearbox, colour, mileage, VIN);
        this.body = body;
        this.option = new ArrayList<>();
    }

    public void addOption(Option option) {
        this.option.add(option);
    }

        public void printDetails() {
            System.out.println("Car Details:");
            System.out.println(
                            "Make: " + getMake() + "\n" +
                            "Model: " + getModel() + "\n" +
                            "Year: " + getYear() + "\n" +
                            "Gearbox: " + getGearbox().toString().substring(0, 1).toUpperCase() + getGearbox().toString().substring(1).toLowerCase() + "\n" + // Code To Convert Printed Case.
                            "Colour: " + getColour() + "\n" +
                            "Mileage: " + getMileage() + "\n" +
                            "VIN: " + getVIN() + "\n" +
                            "Body Type: " + body.toString().substring(0, 1).toUpperCase() + body.toString().substring(1).toLowerCase()); // Code To Convert Printed Case.
            System.out.println("Options: " + this.option);
        }
    }

