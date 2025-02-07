import devtools.util.Reader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To The Vehicle Management System...");
        int initialSize = 18; // Initial size for pre-populated vehicles
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
        vehicles[0] = new Hatchback(Make.TOYOTA, "Yaris T Sport", 2005, Gearbox.MANUAL, CarColour.RED, 15000);
        vehicles[0].addOption(Option.SAT_NAV);

        vehicles[1] = new Motorbike(Make.KAWASAKI, "Ninja", 2017, Gearbox.MANUAL, CarColour.GREEN, 5000);

        vehicles[2] = new Hatchback(Make.FORD, "Fiesta ST", 2015, Gearbox.AUTO, CarColour.BLUE, 20000);
        vehicles[2].addOption(Option.PARKING_SENSORS);
        vehicles[2].addOption(Option.ROOF_RACK);

        vehicles[3] = new Hatchback(Make.HONDA, "Civic", 2018, Gearbox.MANUAL, CarColour.BLACK, 12000);

        vehicles[4] = new Motorbike(Make.YAMAHA, "MT-07", 2020, Gearbox.MANUAL, CarColour.BLUE, 3000);

        vehicles[5] = new SUV(Make.BMW, "X5", 2021, Gearbox.AUTO, CarColour.WHITE, 10000);
        vehicles[5].addOption(Option.TOW_BAR);

        vehicles[6] = new Estate(Make.AUDI, "A4", 2019, Gearbox.AUTO, CarColour.SILVER, 15000);
        vehicles[6].addOption(Option.ROOF_RACK);
        vehicles[6].addOption(Option.THIRD_ROW_SEAT);

        vehicles[7] = new Motorbike(Make.DUCATI, "Panigale V4", 2022, Gearbox.MANUAL, CarColour.RED, 2000);

        vehicles[8] = new Saloon(Make.MERCEDES_BENZ, "C-Class", 2020, Gearbox.AUTO, CarColour.GREY, 8000);

        vehicles[9] = new Saloon(Make.TESLA, "Model 3", 2021, Gearbox.AUTO, CarColour.WHITE, 5000);
        vehicles[9].addOption(Option.PARKING_SENSORS);

        vehicles[10] = new Saloon(Make.BMW, "3 Series", 2005, Gearbox.MANUAL, CarColour.BLACK, 120000);

        vehicles[11] = new Estate(Make.BMW, "5 Series", 2008, Gearbox.AUTO, CarColour.SILVER, 90000);

        vehicles[12] = new Saloon(Make.BMW, "7 Series", 2003, Gearbox.AUTO, CarColour.BLUE, 150000);

        vehicles[13] = new Estate(Make.BMW, "330d", 2007, Gearbox.MANUAL, CarColour.WHITE, 80000);
        vehicles[13].addOption(Option.PARKING_SENSORS);
        vehicles[13].addOption(Option.TOW_BAR);

        vehicles[14] = new Saloon(Make.BMW, "M3 Competition", 2010, Gearbox.MANUAL, CarColour.RED, 60000);

        vehicles[15] = new Saloon(Make.VOLKSWAGEN, "Scirocco", 1990, Gearbox.MANUAL, CarColour.RED, 70000);

        vehicles[16] = new Estate(Make.VOLKSWAGEN, "Passat", 2009, Gearbox.AUTO, CarColour.GREY, 85000);
        vehicles[16].addOption(Option.ROOF_RACK);
        vehicles[16].addOption(Option.THIRD_ROW_SEAT);
        vehicles[16].addOption(Option.TOW_BAR);

        vehicles[17] = new SUV(Make.VOLKSWAGEN, "Touareg", 2008, Gearbox.AUTO, CarColour.GREEN, 95000);
        vehicles[17].addOption(Option.TOW_BAR);
        vehicles[17].addOption(Option.ROOF_RACK);
    }
}