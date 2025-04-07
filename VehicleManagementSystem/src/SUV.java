public class SUV extends Car {
    public SUV(Make make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        super(make, model, year, gearbox, colour, mileage);
    }

    @Override
    public void printDetails() {
        // Removed System.out.println("Body Type: SUV");
        super.printDetails();
    }
}