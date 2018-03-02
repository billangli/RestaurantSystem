package employee;

public class Employee {
  private final int id;

  public Employee(int id) {
    this.id = id;
  }

  public void receiveItem() {
    // TODO
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "id: " + Integer.toString(id);
    // TODO:
    // Distinguish id between cook and server, and print whether this employee is cook or server.
  }
}
