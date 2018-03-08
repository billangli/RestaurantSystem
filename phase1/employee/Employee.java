package employee;

import inventory.Inventory;

/** An Employee class. This is parent class of all employees(Server, Cook, Manager). */
public class Employee {
  private final int ID;

  /**
   * a employee with id
   * @param id the id of the employee
   */
  public Employee(int id) {
    this.ID = id;
  }

  /**
   * Any employee can receive ingredients that has been shipped to the restaurant.
   *
   * <p>This method updates information of amount of ingredients in the inventory.
   *
   * @param receivedIngredientName The name of ingredient that has been shipped to the restaurant.
   * @param quantity The amount of ingredient that has been shipped to the restaurant.
   */
  public void receiveIngredient(String receivedIngredientName, int quantity) {
    Inventory.modifyIngredientQuantity(receivedIngredientName, quantity);
    System.out.println("Employee "+ ID + " received " + receivedIngredientName + " by this amount " + quantity);
  }

  /**
   * Return the id of this employee.
   *
   * @return the id of this employee.
   */
  public int getId() {
    return ID;
  }
}
