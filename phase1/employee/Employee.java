package employee;

import inventory.Inventory;

public class Employee {
  private final int id;

  public Employee(int id) {
    this.id = id;
  }

  public void receiveIngredient(String receivedIngredientName, int quantity) {
    Inventory.modifyIngredientQuantity(receivedIngredientName, quantity);
  }

  public int getId() {
    return id;
  }
}
