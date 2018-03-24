package backend;

import backend.employee.*;
import backend.event.EventManager;
import backend.inventory.Inventory;
import backend.inventory.InventoryIngredient;
import backend.inventory.Menu;
import backend.logger.RestaurantLogger;
import backend.server.ComputerServer;
import backend.table.TableManager;

import java.io.*;
import java.util.logging.Logger;

public class  RestaurantSystem {
  private static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  /**
   * Read and parse the config files for employees, tables, menu and backend.inventory
   *
   * @throws IOException for reading files
   */
  private static void start() throws IOException {
    logger.config("------initializing restaurant system------");

    // check the existence of starter.txt and menu.txt
    try {
      File file = new File("phase2/starter.txt");
      /*If file gets created then the createNewFile()
       * method would return true or if the file is
       * already present it would return false
       */
      boolean fvar = file.createNewFile();
      if (fvar) {
        BufferedWriter bw;

        logger.config(
            "starter.txt has been created successfully with default 10 backend.table, 1 server, 1 cook, and 1 manager");

        FileWriter fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
        bw.write("10\n1\n1\n1\n");
        bw.close();
      } else {
        logger.config("starter.txt already present at the specified location");
      }

      file = new File("phase2/menu.txt");
      fvar = file.createNewFile();
      if (fvar) {
        logger.config("menu.txt has been created successfully with no item");
      } else {
        logger.config("menu.txt already present at the specified location");
        logger.config("menu.txt already present at the specified location");
      }

    } catch (IOException e) {
      logger.warning("File Exception Occurred.");
      // TODO: Delete printStackTrace() before submission.
      e.printStackTrace();
    }

    // read the starter.txt
    try (BufferedReader fileReader = new BufferedReader(new FileReader("phase2/starter.txt"))) {

      // Print the lines from f prefaced with the line number,
      // starting at 1.
      String TableNum = fileReader.readLine();
      TableManager.tableSetUp(Integer.parseInt(TableNum));
      String serverNum = fileReader.readLine();

      // There are three types of employees(ComputerServer/Cook/Manager). The id of each backend.employee
      // starts
      // from 1 and increments by 1, starting from server, cook then manager.
      // For example, if we have 3 servers, 2 cook, 1 manager in this restaurant system, then the id
      // of these employees are: server(1), server(2), server(3), cook(4), cook(5), manager(6).*/
      int id = 0;
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
            new InventoryIngredient(item[0], Integer.parseInt(item[1]), threshold);
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
          logger.config("request.txt has been created successfully");
        } else {
          logger.config("request.txt already present at the specified location");
          // Clearing the file
          PrintWriter output = new PrintWriter(file);
          output.print("");
          output.close();
        }
      } catch (IOException e) {
        logger.warning("File Exception Occurred.");
        // TODO: Delete printStackTrace() before submission.
        e.printStackTrace();
      }
    }

    Menu menu = Menu.getInstance();
    menu.readFromFile();
    logger.config("---------initialization over---------\n\n");
  }

  // TODO: Move this somewhere else where it makes more sense
  public static String logIn(int id) {
    Employee employee = EmployeeManager.getEmployeeById(id);
    if (employee == null) {
      return "Failed";
    } else if (employee instanceof Cook) {
      return "Cook log in successful";
    } else if (employee instanceof Manager) {
      return "Manager log in successful";
    } else if (employee instanceof Server) {
      return "Server log in successful";
    }
    return "Failed";
  }

  public static void main(String[] args) throws IOException {
    RestaurantLogger.init();
    start();
    EventManager.setRunning(true);
    Thread eventThread = new Thread(new EventManager());
    eventThread.start();
    ComputerServer computerServer = ComputerServer.getInstance();

    // Initializing Logger.

    //        Application.launch(args);
  }
}
