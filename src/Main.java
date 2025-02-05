import devtools.util.Reader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To The Vehicle Management System...");
        int initialSize = 10; // Initial size for pre-populated vehicles
        Vehicle[] vehicles = new Vehicle[initialSize];
        prePopulateVehicles(vehicles);

        System.out.println(initialSize + " vehicles have been pre-populated:");
        Vehicle.printAllVehiclesNoPrompt(vehicles);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a new vehicle");
            System.out.println("2. Remove a vehicle");
            System.out.println("3. Add or remove options to/from a vehicle");
            System.out.println("4. Search for a vehicle");
            System.out.println("5. Display all vehicles");
            System.out.println("6. Set mileage of a vehicle");
            System.out.println("7. Set colour of a vehicle");
            System.out.println("8. Exit");

            int choice = Reader.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    int numVehicles = Reader.readInt("How many vehicles are you adding? ");
                    vehicles = Vehicle.addVehicles(vehicles, numVehicles);
                    break;
                case 2:
                    Vehicle.removeVehicle(vehicles);
                    break;
                case 3:
                    Vehicle.modifyVehicleOptions(vehicles);
                    break;
                case 4:
                    Vehicle.searchVehicle(vehicles);
                    break;
                case 5:
                    Vehicle.printAllVehicles2(vehicles);
                    break;
                case 6:
                    Vehicle.setMileage(vehicles);
                    break;
                case 7:
                    Vehicle.setColour(vehicles);
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void prePopulateVehicles(Vehicle[] vehicles) {
        vehicles[0] = new Car("Toyota", "Yaris T Sport", 2005, Gearbox.MANUAL, CarColour.RED, 15000, "86512345", BodyType.Body.HATCHBACK);
        vehicles[0].addOption(Option.SAT_NAV);

        vehicles[1] = new Motorbike("Kawasaki", "Ninja", 2017, Gearbox.MANUAL, CarColour.GREEN, 5000, "54365421");

        vehicles[2] = new Car("Ford", "Fiesta ST", 2015, Gearbox.AUTO, CarColour.BLUE, 20000, "08767890", BodyType.Body.HATCHBACK);
        vehicles[2].addOption(Option.PARKING_SENSORS);
        vehicles[2].addOption(Option.ROOF_RACK);

        vehicles[3] = new Car("Honda", "Civic", 2018, Gearbox.MANUAL, CarColour.BLACK, 12000, "12345678", BodyType.Body.HATCHBACK);

        vehicles[4] = new Motorbike("Yamaha", "MT-07", 2020, Gearbox.MANUAL, CarColour.BLUE, 3000, "87654321");

        vehicles[5] = new Car("BMW", "X5", 2021, Gearbox.AUTO, CarColour.WHITE, 10000, "23456789", BodyType.Body.SUV);
        vehicles[5].addOption(Option.TOW_BAR);

        vehicles[6] = new Car("Audi", "A4", 2019, Gearbox.AUTO, CarColour.SILVER, 15000, "34567890", BodyType.Body.ESTATE);
        vehicles[6].addOption(Option.ROOF_RACK);
        vehicles[6].addOption(Option.THIRD_ROW_SEAT);

        vehicles[7] = new Motorbike("Ducati", "Panigale V4", 2022, Gearbox.MANUAL, CarColour.RED, 2000, "45678901");

        vehicles[8] = new Car("Mercedes-Benz", "C-Class", 2020, Gearbox.AUTO, CarColour.GREY, 8000, "56789012", BodyType.Body.SALOON);

        vehicles[9] = new Car("Tesla", "Model 3", 2021, Gearbox.AUTO, CarColour.WHITE, 5000, "67890123", BodyType.Body.SALOON);
        vehicles[9].addOption(Option.PARKING_SENSORS);
    }
}