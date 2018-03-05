package employee;

import inventory.Dish;
import table.Order;
import table.Table;

public class Server extends ServiceEmployee {

  public Server(int id) {
    super(id);
  }

  public void takeSeat(Table table) {
    table.serve(this);
  }

  /**
   * Enters orders of customers on certain table. The order is then sent to cook.
   *
   * <p>Customers should take seat before ordering food.
   */
  public void enterMenu(Table table, Order order) {
    // Add order to the table and relate all the dish to the table.
    order.assignDishNumber();
    table.addOrder(order);

    // Send order to cook
    orderQueue.addOrderInQueue(order);
    System.out.println("Orders are sent to cook.");
  }

  public void deliverDishCompleted() {
    Dish dish = orderQueue.dishDelivered();
    System.out.println(
        "Dish #: "
            + dish.getDishNumber()
            + ", "
            + dish.getName()
            + " has been delivered successfully.");
    dish.addCostToTable();
  }

  public void deliverOrderFailed() {
    Dish dish = orderQueue.dishDelivered();
    System.out.println(
        "Dish #: "
            + dish.getDishNumber()
            + ", "
            + dish.getName()
            + " has been delivered but put back upon customer's request.");

    // Sets price of the dish to zero, while remaining the dish in the table's order list.
    dish.isCancelled();
  }

  /** Print bill. */
  public void printBill(Table table) {
    table.printBill();
  }

  public boolean checkIfPaid(Table table) {
    return table.getHasPaid();
  }

  public String toString() {
    return "Server, id:" + getId();
  }
}
