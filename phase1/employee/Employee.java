package employee;

import inventory.Inventory;

public class Employee {
  private final int id;
  static Inventory inventory;

  public Employee(int id) {
    this.id = id;
  }

  public void receiveItem() {
    // TODO
  }

  public int getId() {
    return id;
  }

  public static void setInventory(Inventory inventory) {
    Employee.inventory = inventory;
  }
}
