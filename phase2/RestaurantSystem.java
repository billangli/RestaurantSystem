import employee.Cook;
import employee.EmployeeManager;
import employee.Manager;
import employee.Server;
import event.EventManager;
import inventory.Inventory;
import inventory.InventoryIngredient;
import inventory.Menu;
import javafx.application.Application;
import javafx.stage.Stage;
import table.TableManager;

import java.io.*;

public class RestaurantSystem extends Application{

    @Override
    public void start(Stage stage) {
        new StartStage();

    }

    /**
     * Read and parse the config files for employees, tables, menu and inventory
     *
     * @throws IOException for reading files
     */
    private static void start() throws IOException {
        System.out.println("------initializing restaurant system------");

        // check the existence of starter.txt and menu.txt
        try {
            File file = new File("phase1/starter.txt");
            /*If file gets created then the createNewFile()
             * method would return true or if the file is
             * already present it would return false
             */
            boolean fvar = file.createNewFile();
            if (fvar) {
                BufferedWriter bw;
                System.out.println(
                        "starter.txt has been created successfully with default 10 table, 1 server, 1 cook, and 1 manager");
                FileWriter fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                bw.write("10\n1\n1\n1\n");
                bw.close();
            } else {
                System.out.println("starter.txt already present at the specified location");
            }

            file = new File("phase1/menu.txt");
            fvar = file.createNewFile();
            if (fvar) {
                System.out.println("menu.txt has been created successfully with no item");
            } else {
                System.out.println("menu.txt already present at the specified location");
            }

        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }

        // read the starter.txt
        try (BufferedReader fileReader = new BufferedReader(new FileReader("phase1/starter.txt"))) {

            // Print the lines from f prefaced with the line number,
            // starting at 1.
            String TableNum = fileReader.readLine();
            TableManager.tableSetUp(Integer.parseInt(TableNum));
            String serverNum = fileReader.readLine();

            // There are three types of employees(Server/Cook/Manager). The id of each employee starts
            // from 1 and increments by 1, starting from server, cook then manager.
            // For example, if we have 3 servers, 2 cook, 1 manager in this restaurant system, then the id
            // of these employees are: server(1), server(2), server(3), cook(4), cook(5), manager(6).*/
            int id = 1;
            for (int i = 0; i < Integer.parseInt(serverNum); i++) {
                EmployeeManager.add(new Server(id));
                id++;
            }
            String cookNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(cookNum); i++) {
                EmployeeManager.add(new Cook(id));
                id++;
            }
            String managerNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(managerNum); i++) {
                EmployeeManager.add(new Manager(id));
                id++;
            }
            String line = fileReader.readLine();
            while (line != null) {
                String[] item = line.split(",");
                int threshold = Integer.parseInt(item[2]);
                InventoryIngredient inventoryIngredient =
                        new InventoryIngredient(
                                item[0], Integer.parseInt(item[1]), threshold);
                Inventory.add(inventoryIngredient);
                line = fileReader.readLine();
            }

            // creating request.txt
            try {
                File file = new File("phase1/request.txt");
                /*If file gets created then the createNewFile()
                 * method would return true or if the file is
                 * already present it would return false
                 */
                boolean fvar = file.createNewFile();
                if (fvar) {
                    System.out.println("request.txt has been created successfully");
                } else {
                    System.out.println("request.txt already present at the specified location");
                    // Clearing the file
                    PrintWriter output = new PrintWriter(file);
                    output.print("");
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("Exception Occurred:");
                e.printStackTrace();
            }
        }

        Menu.create();
        System.out.println("---------initialization over---------\n\n");
    }

    public static void main(String[] args) throws IOException {
//        start();
//        EventManager eventManager = new EventManager();
//        eventManager.readFile();
//        eventManager.processEvents();
        Application.launch(args);
    }
}
