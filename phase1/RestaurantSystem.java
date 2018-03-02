import employee.Cook;
import employee.EmployeeManager;
import employee.Manager;
import employee.Server;
import event.EventManager;
import inventory.Ingredient;
import inventory.Inventory;
import inventory.Menu;
import table.TableManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RestaurantSystem {
    private static EmployeeManager employeeManager = new EmployeeManager();
    private static Inventory inventory = new Inventory();
    private static TableManager tableManager;
    private static EventManager eventManager;
    private static Menu menu;

    private static void start() throws IOException {

        try (BufferedReader fileReader = new BufferedReader(new FileReader("starter.txt"))) {

            // Print the lines from f prefaced with the line number,
            // starting at 1.
            String TableNum = fileReader.readLine();
            tableManager = new TableManager(Integer.parseInt(TableNum));
            String serverNum = fileReader.readLine();
            int id = 1;
            for (int i = 0; i < Integer.parseInt(serverNum); i++){
                employeeManager.add(new Server(id));
                id++;
            }
            String cookNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(cookNum); i++){
                employeeManager.add(new Cook(id));
                id++;
            }
            String managerNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(managerNum); i++){
                employeeManager.add(new Manager(id));
                id++;
            }
            String line = fileReader.readLine();
            while (line != null) {
                String[] item = line.split("//s");
                int[] limit = {Integer.parseInt(item[2]), Integer.parseInt(item[3])};
                Ingredient ingredient = new Ingredient(item[0], Integer.parseInt(item[1]), limit);
                inventory.add(ingredient);
                line = fileReader.readLine();
            }

        }

        menu = new Menu(inventory);

        // Bill Ang Li added this so it reads and processes events
        eventManager = new EventManager(employeeManager, inventory, tableManager);
        eventManager.readFile();
        eventManager.processEvents();
    }

    public static void main(String[] arg) throws IOException {
        start();
    }
}
