package employee;

public class Manager extends Employee {

  public Manager(int id) {
    super(id);
  }

  public void checkInventory() {
    // TODO: print out all inventory items and their quentities.
  }

  public String toString() {
    return "Manager, id:" + getId();
  }
}
