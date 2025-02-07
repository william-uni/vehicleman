public class Saloon extends Car {
    public Saloon(String make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        super(make, model, year, gearbox, colour, mileage);
    }

    @Override
    public void printDetails() {
        System.out.println("Saloon Details:");
        super.printDetails();
    }
}
