public class Saloon extends Car {
    public Saloon(Make make, String model, int year, Gearbox gearbox, CarColour colour, int mileage) {
        super(make, model, year, gearbox, colour, mileage);
    }

    @Override
    public void printDetails() {
        System.out.println("Body Type: Saloon");
        super.printDetails();
    }
}
