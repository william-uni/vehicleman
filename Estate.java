public class Estate extends Car {
    public Estate(Make make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        super(make, model, year, gearbox, colour, mileage);
    }

    @Override
    public void printDetails() {
        // Removed System.out.println("Body Type: Estate");
        super.printDetails();
    }
}