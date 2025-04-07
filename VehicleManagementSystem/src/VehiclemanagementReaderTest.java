import java.util.ArrayList;
import java.util.List;

class VehicleManagementReaderTest {


    public static void main(String[] args) {
        testCreateCarWithValidAttributes();
        testCreateMotorbikeWithValidAttributes();
        testCreateVehicleWithInvalidYear();
        testCreateVehicleWithInvalidMake();
        testCreateVehicleWithInvalidMileage();
        testCreateVehicleWithInvalidColour();
        testChangeVehicleColour();
        testChangeVehicleColourToSameColour();
        testAddOptionsToVehicle();
        testAddClassSpecificModificationsToIncorrectVehicleType();
        testRemoveOptionsFromCar();
        testRemoveOptionsFromMotorbike();
        testVINGeneration();
        testValueEstimation();
        testSearchVehicleByMake();
        testSearchVehicleByModel();
        testSearchVehicleByColour();
        testSearchVehicleByYear();
        testSearchVehicleByVIN();
    }

    public static void testCreateCarWithValidAttributes() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        if (car != null &&
                car.getMake() == Make.BMW &&
                car.getModel().equals("3 Series") &&
                car.getYear() == 2010 &&
                car.getGearbox() == Gearbox.MANUAL &&
                car.getColour() == CarColour.BLACK &&
                car.getMileage() == 50000) {
            System.out.println("testCreateCarWithValidAttributes passed");
        } else {
            System.out.println("testCreateCarWithValidAttributes failed");
        }
    }

    public static void testCreateMotorbikeWithValidAttributes() {
        Motorbike motorbike = new Motorbike(Make.YAMAHA, "MT-07", 2020, Gearbox.MANUAL, CarColour.BLUE, 3000);
        if (motorbike != null &&
                motorbike.getMake() == Make.YAMAHA &&
                motorbike.getModel().equals("MT-07") &&
                motorbike.getYear() == 2020 &&
                motorbike.getGearbox() == Gearbox.MANUAL &&
                motorbike.getColour() == CarColour.BLUE &&
                motorbike.getMileage() == 3000) {
            System.out.println("testCreateMotorbikeWithValidAttributes passed");
        } else {
            System.out.println("testCreateMotorbikeWithValidAttributes failed");
        }
    }

    public static void testCreateVehicleWithInvalidYear() {
        try {
            new Saloon(Make.BMW, "3 Series", 2026, Gearbox.MANUAL, CarColour.BLACK, 50000);
            System.out.println("testCreateVehicleWithInvalidYear failed");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Invalid year. The vehicle cannot be newer than the current year.")) {
                System.out.println("testCreateVehicleWithInvalidYear passed");
            } else {
                System.out.println("testCreateVehicleWithInvalidYear failed");
            }
        }
    }

    public static void testCreateVehicleWithInvalidMake() {
        try {
            Make invalidMake = Make.searchMake("InvalidMake");
            System.out.println("testCreateVehicleWithInvalidMake failed");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Invalid make. Please enter a valid make.")) {
                System.out.println("testCreateVehicleWithInvalidMake passed");
            } else {
                System.out.println("testCreateVehicleWithInvalidMake failed");
            }
        }
    }

    public static void testCreateVehicleWithInvalidMileage() {
        try {
            new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, -50000);
            System.out.println("testCreateVehicleWithInvalidMileage failed");
        } catch (IllegalArgumentException e) {
            System.out.println("testCreateVehicleWithInvalidMileage passed");
        }
    }

    public static void testCreateVehicleWithInvalidColour() {
        try {
            CarColour invalidColour = CarColour.searchColour("InvalidColour");
            System.out.println("testCreateVehicleWithInvalidColour failed");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Invalid colour. Please enter a valid colour.")) {
                System.out.println("testCreateVehicleWithInvalidColour passed");
            } else {
                System.out.println("testCreateVehicleWithInvalidColour failed");
            }
        }
    }

    public static void testChangeVehicleColour() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        car.setColour(CarColour.RED);
        if (car.getColour() == CarColour.RED) {
            System.out.println("testChangeVehicleColour passed");
        } else {
            System.out.println("testChangeVehicleColour failed");
        }
    }

    public static void testChangeVehicleColourToSameColour() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        car.setColour(CarColour.BLACK);
        if (car.getColour() == CarColour.BLACK) {
            System.out.println("testChangeVehicleColourToSameColour passed");
        } else {
            System.out.println("testChangeVehicleColourToSameColour failed");
        }
    }

    public static void testAddOptionsToVehicle() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        car.addOption(Option.SAT_NAV);
        if (car.getOptions().contains(Option.SAT_NAV)) {
            System.out.println("testAddOptionsToVehicle passed");
        } else {
            System.out.println("testAddOptionsToVehicle failed");
        }
    }

    public static void testAddClassSpecificModificationsToIncorrectVehicleType() {
        Car car = new Hatchback(Make.BMW, "1 Series", 2015, Gearbox.AUTO, CarColour.WHITE, 30000);
        try {
            car.addOption(Option.THIRD_ROW_SEAT);
            System.out.println("testAddClassSpecificModificationsToIncorrectVehicleType failed");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Third Row Seat is only available for Estates.")) {
                System.out.println("testAddClassSpecificModificationsToIncorrectVehicleType passed");
            } else {
                System.out.println("testAddClassSpecificModificationsToIncorrectVehicleType failed");
            }
        }
    }



    public static void testRemoveOptionsFromCar() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        car.addOption(Option.SAT_NAV);
        if (car.getOptions().contains(Option.SAT_NAV)) {
            System.out.println("testRemoveOptionsFromCar passed");
        } else {
            System.out.println("testRemoveOptionsFromCar failed");
        }
    }

    public static void testRemoveOptionsFromMotorbike() {
        Motorbike motorbike = new Motorbike(Make.YAMAHA, "MT-07", 2020, Gearbox.MANUAL, CarColour.BLUE, 3000);
        motorbike.addOption(Option.LUGGAGE_BOX);
        motorbike.removeOption(Option.LUGGAGE_BOX);
        if (!motorbike.getOptions().contains(Option.LUGGAGE_BOX)) {
            System.out.println("testRemoveOptionsFromMotorbike passed");
        } else {
            System.out.println("testRemoveOptionsFromMotorbike failed");
        }
    }

    public static void testVINGeneration() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        if (car.getVIN() != null) {
            System.out.println("testVINGeneration passed");
        } else {
            System.out.println("testVINGeneration failed");
        }
    }

    public static void testValueEstimation() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        String estimatedValue = ValueEstimator.estimateValue(car);
        if (estimatedValue != null && estimatedValue.startsWith("£")) {
            System.out.println("testValueEstimation passed");
        } else {
            System.out.println("testValueEstimation failed");
        }
    }

    public static void testSearchVehicleByMake() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        Vehicle foundVehicle = vehicles.stream().filter(v -> v.getMake().equals(Make.BMW)).findFirst().orElse(null);
        if (foundVehicle != null) {
            System.out.println("testSearchVehicleByMake passed");
        } else {
            System.out.println("testSearchVehicleByMake failed");
        }
    }

    public static void testSearchVehicleByModel() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        Vehicle foundVehicle = vehicles.stream().filter(v -> v.getModel().equals("3 Series")).findFirst().orElse(null);
        if (foundVehicle != null) {
            System.out.println("testSearchVehicleByModel passed");
        } else {
            System.out.println("testSearchVehicleByModel failed");
        }
    }

    public static void testSearchVehicleByColour() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        Vehicle foundVehicle = vehicles.stream().filter(v -> v.getColour().equals(CarColour.BLACK)).findFirst().orElse(null);
        if (foundVehicle != null) {
            System.out.println("testSearchVehicleByColour passed");
        } else {
            System.out.println("testSearchVehicleByColour failed");
        }
    }

    public static void testSearchVehicleByYear() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        Vehicle foundVehicle = vehicles.stream().filter(v -> v.getYear() == 2010).findFirst().orElse(null);
        if (foundVehicle != null) {
            System.out.println("testSearchVehicleByYear passed");
        } else {
            System.out.println("testSearchVehicleByYear failed");
        }
    }

    public static void testSearchVehicleByVIN() {
        Car car = new Saloon(Make.BMW, "3 Series", 2010, Gearbox.MANUAL, CarColour.BLACK, 50000);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        Vehicle foundVehicle = vehicles.stream().filter(v -> v.getVIN().equals(car.getVIN())).findFirst().orElse(null);
        if (foundVehicle != null) {
            System.out.println("testSearchVehicleByVIN passed");
        } else {
            System.out.println("testSearchVehicleByVIN failed");
        }
    }
}