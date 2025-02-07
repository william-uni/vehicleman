public class Estate extends Car {
    public Estate(String make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        super(make, model, year, gearbox, colour, mileage);
    }

    @Override
    public void printDetails() {
        System.out.println("Estate Details:");
        super.printDetails();
    }
}