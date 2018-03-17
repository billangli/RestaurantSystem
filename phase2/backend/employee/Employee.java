package backend.employee;

import backend.inventory.Inventory;
import backend.logger.RestaurantLogger;
import java.util.logging.Logger;

/** An Employee class. This is parent class of all employees(Server, Cook, Manager). */
public class Employee {
  private final int ID;
  static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  /**
   * a backend.employee with id
   *
   * @param id the id of the backend.employee
   */
  public Employee(int id) {
    this.ID = id;
  }

  /**
   * Any backend.employee can receive ingredients that has been shipped to the restaurant.
   *
   * <p>This method updates information of amount of ingredients in the backend.inventory.
   *
   * @param receivedIngredientName The name of ingredient that has been shipped to the restaurant.
   * @param quantity The amount of ingredient that has been shipped to the restaurant.
   */
  public void receiveIngredient(String receivedIngredientName, int quantity) {
    Inventory.getIngredient(receivedIngredientName).addQuantity(quantity);
    logger.info(
        "Employee " + ID + " received " + receivedIngredientName + " by this amount " + quantity);
  }

  /**
   * Return the id of this backend.employee.
   *
   * @return the id of this backend.employee.
   */
  public int getId() {
    return ID;
  }
}
