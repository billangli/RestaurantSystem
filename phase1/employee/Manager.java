package employee;

import inventory.Inventory;

public class Manager extends Employee {

  private static Inventory inventory;

  public Manager(int id) {
    super(id);
  }

  public static void setInventory(Inventory inventory) {
    Manager.inventory = inventory;
  }

  public void checkInventory() {
    // TODO: print out all inventory items and their quentities.
  }
}
