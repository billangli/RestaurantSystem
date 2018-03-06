package employee;

import inventory.Dish;
import table.Order;
import table.Table;

/** A Server class. This class represents a server. */
public class Server extends ServiceEmployee {

  /**
   * A constructor that takes an int as parameter.
   *
   * @param id id of this employee.
   */
  public Server(int id) {
    super(id);
  }

  /**
   * Sets this server to serve the <code>table</code>. This server is responsible for the customers
   * on that <code>table</code>.
   *
   * @param table A table where customers sat down, and where this server is now responsible for.
   */
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
    if (table.getServerId() == -1) {
      System.err.println("Server hasn't been set up for table " + table.getTableNum());
      (new Exception()).printStackTrace();
    } else {
      order.assignDishNumber();
      table.addOrder(order);

      // Send order to cook
      orderQueue.addOrderInQueue(order);
      System.out.println("Server " + getId() + " Orders are sent to cook.");
    }
  }

  /** Successfully delivers dish to the table. */
  public void deliverDishCompleted() {
    Dish dish = orderQueue.dishDelivered();
    System.out.println(
            "Server " + getId() + "    " +
        dish.getName()
            + " (Dish #: "
            + dish.getDishNumber()
            + ") has been delivered successfully.");

    // This is when the price of the dish gets added to the table.
    dish.addCostToTable();
  }

  /** Delivers dish to the table, but the customer requests to put the dish back. */
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

  /**
   * Prints bill of the <code>table</code>.
   *
   * @param table The table which asked to have their bill.
   */
  public void printBill(Table table) {
    table.printBill();
  }

  /**
   * Return true if the <code>table</code> has paid their bill, otherwise return false.
   *
   * @param table The table which we are curious to check.
   * @return true if the <code>table</code> has paid their bill, otherwise return false.
   */


  public void clearTable(Table table){
    table.clear();
  }

  /**
   * Returns a string representation of this employee.
   *
   * <p>The string representation consists of its employ type (= Server) and its id.
   *
   * @return a string representation of this employee.
   */
  public String toString() {
    return "Server, id:" + getId();
  }
}
