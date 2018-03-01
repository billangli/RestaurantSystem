import employee.*;
import event.EventManager;
import inventory.Ingredient;
import inventory.Inventory;
import table.TableManager;

import java.io.*;

public class RestaurantSystem {
    public static EmployeeManager employeeManager = new EmployeeManager();
    public static Inventory inventory = new Inventory();
    public static TableManager tableManager;
    public static EventManager eventManager;

    public static void start() throws IOException {


        try (BufferedReader fileReader = new BufferedReader(new FileReader("starter.txt"))) {

            // Print the lines from f prefaced with the line number,
            // starting at 1.
            String TableNum = fileReader.readLine();
            tableManager = new TableManager(Integer.parseInt(TableNum));
            String serverNum = fileReader.readLine();
            int id = 0;
            for (int i = 0; i < Integer.parseInt(serverNum); i++){
                employeeManager.add(new Server("try", id));
                id++;
            }
            String cookNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(cookNum); i++){
                employeeManager.add(new Cook("try", id));
                id++;
            }
            String managerNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(managerNum); i++){
                employeeManager.add(new Manager("try", id));
                id++;
            }
            String line = fileReader.readLine();
            while (line != null) {
                String[] item = line.split("//s");
                Ingredient ind = new Ingredient(item[0], Integer.parseInt(item[1]), Integer.parseInt((item[2])));
                inventory.add(ind);

            }
            line = fileReader.readLine();
        }
    }

    public static void main(String[] arg) throws IOException{
        start();
    }

}
