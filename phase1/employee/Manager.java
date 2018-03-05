package employee;

import inventory.Inventory;

public class Manager extends Employee {

  public Manager(int id) {
    super(id);
  }

  /**
   *
   */
  public void checkInventory() {
    System.out.println(Inventory.inventoryToString());
  }

  // not sure
  //  public void sendRequeset(){
  //    try{
  //      new FileWriter("phase1/request.txt").close();
  //    }
  //    catch (IOException ioe) {
  //      ioe.printStackTrace();
  //    }

  //  }
  public String toString() {
    return "Manager, id:" + getId();
  }
}
