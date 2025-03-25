import devtools.util.Reader;
import devtools.ui.Application;
import devtools.ui.Menu;
import devtools.io.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Main implements Serializable {
    @Data
    public static List<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        Application.start(Main.class);
    }

    @Menu(command = "1", description = "Add a new vehicle", id = 1)
    public void addVehicle() {
        int numVehicles = Reader.readInt("How many vehicles are you adding? ", 0, 100);
        vehicles = Vehicle.addVehicles(vehicles.toArray(new Vehicle[0]), numVehicles);
    }

    @Menu(command = "2", description = "Remove a vehicle", id = 2)
    public void removeVehicle() {
        Vehicle.removeVehicle(vehicles);
    }

    @Menu(command = "3", description = "Modify options of a vehicle", id = 3)
    public void modifyOptions() {
        Vehicle.modifyVehicleOptions(vehicles.toArray(new Vehicle[0]));
    }

    @Menu(command = "4", description = "Search for a vehicle", id = 4)
    public void searchVehicle() {
        Vehicle.searchVehicle(vehicles.toArray(new Vehicle[0]));
    }

    @Menu(command = "5", description = "Display all vehicles", id = 5)
    public void displayVehicles() {
        Vehicle.printAllVehiclesNoPrompt(vehicles.toArray(new Vehicle[0]));
    }

    @Menu(command = "6", description = "Set mileage of a vehicle", id = 6)
    public void setMileage() {
        Vehicle.setMileage(vehicles.toArray(new Vehicle[0]));
    }

    @Menu(command = "7", description = "Set colour of a vehicle", id = 7)
    public void setColour() {
        Vehicle.setColour(vehicles.toArray(new Vehicle[0]));
    }

    @Menu(command ="8", description ="Pre-populate vehicles", id =8)

    public void prePopulateVehicles() {
        vehicles.add(new Hatchback(Make.TOYOTA, "Yaris T Sport", 2005, Gearbox.MANUAL, CarColour.RED, 15000));
        vehicles.get(0).addOption(Option.SAT_NAV);
        vehicles.add(new Motorbike(Make.KAWASAKI, "Ninja", 2017, Gearbox.MANUAL, CarColour.GREEN, 5000));
        vehicles.add(new Hatchback(Make.FORD, "Fiesta ST", 2015, Gearbox.AUTO, CarColour.BLUE, 20000));
        vehicles.get(2).addOption(Option.PARKING_SENSORS);
        vehicles.get(2).addOption(Option.ROOF_RACK);
        vehicles.add(new Hatchback(Make.HONDA, "Civic", 2018, Gearbox.MANUAL, CarColour.BLACK, 12000));
        vehicles.add(new Motorbike(Make.YAMAHA, "MT-07", 2020, Gearbox.MANUAL, CarColour.BLUE, 3000));
        vehicles.add(new SUV(Make.BMW, "X5", 2021, Gearbox.AUTO, CarColour.WHITE, 10000));
        vehicles.get(5).addOption(Option.TOW_BAR);
        vehicles.add(new Estate(Make.AUDI, "A4", 2019, Gearbox.AUTO, CarColour.SILVER, 15000));
        vehicles.get(6).addOption(Option.ROOF_RACK);
        vehicles.get(6).addOption(Option.THIRD_ROW_SEAT);
        vehicles.add(new Motorbike(Make.DUCATI, "Panigale V4", 2022, Gearbox.MANUAL, CarColour.RED, 2000));
        vehicles.add(new Saloon(Make.MERCEDES_BENZ, "C-Class", 2020, Gearbox.AUTO, CarColour.GREY, 8000));
        vehicles.add(new Saloon(Make.TESLA, "Model 3", 2021, Gearbox.AUTO, CarColour.WHITE, 5000));
        vehicles.get(9).addOption(Option.PARKING_SENSORS);
        vehicles.add(new Saloon(Make.BMW, "3 Series", 2005, Gearbox.MANUAL, CarColour.BLACK, 120000));
        vehicles.add(new Estate(Make.BMW, "5 Series", 2008, Gearbox.AUTO, CarColour.SILVER, 90000));
        vehicles.add(new Saloon(Make.BMW, "7 Series", 2003, Gearbox.AUTO, CarColour.BLUE, 150000));
        vehicles.add(new Estate(Make.BMW, "330d", 2007, Gearbox.MANUAL, CarColour.WHITE, 80000));
        vehicles.get(13).addOption(Option.PARKING_SENSORS);
        vehicles.get(13).addOption(Option.TOW_BAR);
        vehicles.add(new Saloon(Make.BMW, "M3 Competition", 2010, Gearbox.MANUAL, CarColour.RED, 60000));
        vehicles.add(new Saloon(Make.VOLKSWAGEN, "Scirocco", 1990, Gearbox.MANUAL, CarColour.RED, 70000));
        vehicles.add(new Estate(Make.VOLKSWAGEN, "Passat", 2009, Gearbox.AUTO, CarColour.GREY, 85000));
        vehicles.get(16).addOption(Option.ROOF_RACK);
        vehicles.get(16).addOption(Option.THIRD_ROW_SEAT);
        vehicles.get(16).addOption(Option.TOW_BAR);
        vehicles.add(new SUV(Make.VOLKSWAGEN, "Touareg", 2008, Gearbox.AUTO, CarColour.GREEN, 95000));
        vehicles.get(17).addOption(Option.TOW_BAR);
        vehicles.get(17).addOption(Option.ROOF_RACK);
    }
}