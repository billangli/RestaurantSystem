import employee.*;
import inventory.Inventory;

import java.io.*;

public class RestaurantSystem {
    public EmployeeManager employeeManager = new EmployeeManager();
    public Inventory inventory = new Inventory();

    public void start() throws IOException {


        try (BufferedReader fileReader = new BufferedReader(new FileReader("starter.txt"))) {

            // Print the lines from f prefaced with the line number,
            // starting at 1.
            String serverNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(serverNum); i++){
                employeeManager.add(new Server("try", i));
            }
            String cookNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(cookNum); i++){
                employeeManager.add(new Cook("try", i));
            }
            String receiverNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(receiverNum); i++){
                employeeManager.add(new Receiver("try", i));
            }
            String managerNum = fileReader.readLine();
            for (int i = 0; i < Integer.parseInt(managerNum); i++){
                employeeManager.add(new Manager("try", i));
            }
            String line = fileReader.readLine();
            while (line != null) {
                String[] item = line.split("//s");

            }
            line = fileReader.readLine();
        }
    }
}
