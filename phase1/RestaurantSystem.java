import employee.*;
import event.EventManager;
import inventory.Ingredient;
import inventory.Inventory;
import inventory.Menu;
import table.TableManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RestaurantSystem {
  private static EventManager eventManager;

  private static void start() throws IOException {

    try (BufferedReader fileReader = new BufferedReader(new FileReader("phase1/starter.txt"))) {

      // Print the lines from f prefaced with the line number,
      // starting at 1.
      String TableNum = fileReader.readLine();
      TableManager.tableSetUp(Integer.parseInt(TableNum));
      String serverNum = fileReader.readLine();
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
        int[] limit = {Integer.parseInt(item[2]), Integer.parseInt(item[3])};
        Ingredient ingredient = new Ingredient(item[0], Integer.parseInt(item[1]), limit);
        Inventory.add(ingredient);
        line = fileReader.readLine();
      }

      try {
        File file = new File("phase1/request.txt");
        /*If file gets created then the createNewFile()
         * method would return true or if the file is
         * already present it would return false
         */
        boolean fvar = file.createNewFile();
        if (fvar) {
          System.out.println("File has been created successfully");
        } else {
          System.out.println("File already present at the specified location");
        }
      } catch (IOException e) {
        System.out.println("Exception Occurred:");
        e.printStackTrace();
      }
    }

    Menu.create();

    // Bill Ang Li added this so it reads and processes events
    eventManager = new EventManager();
    eventManager.readFile();
    eventManager.processEvents();
  }

  public static void main(String[] arg) throws IOException {
    start();
  }
}
