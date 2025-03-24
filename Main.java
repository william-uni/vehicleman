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
        Vehicle.removeVehicle(vehicles.toArray(new Vehicle[0]));
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

}