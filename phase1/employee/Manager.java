package employee;

import inventory.Inventory;

/** A manager class. This class represents a manager. */
public class Manager extends Employee {

  /**
   * A constructor. Takes an int type parameter.
   *
   * @param id id of this employee.
   */
  public Manager(int id) {
    super(id);
  }

  /** Prints out the amount of all ingredients left in inventory. */
  public void checkInventory() {
    System.out.println(Inventory.inventoryToString());
  }

  /**
   * Returns a string representation of this employee.
   *
   * <p>The string representation consists of its employ type (= Manager) and its id.
   *
   * @return a string representation of this employee.
   */
  public String toString() {
    return "Manager, id:" + getId();
  }
}
