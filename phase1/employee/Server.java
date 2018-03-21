package backend.employee;

import backend.inventory.Dish;
import backend.table.Order;
import backend.table.Table;

/** A ComputerServer class. This class represents a server. */
public class Server extends ServiceEmployee {

  /**
   * A constructor that takes an int as parameter.
   *
   * @param id id of this backend.employee.
   */
  public Server(int id) {
    super(id);
  }

  /**
   * Sets this server to serve the <code>backend.table</code>. This server is responsible for the customers
   * on that <code>backend.table</code>.
   *
   * @param table A backend.table where customers sat down, and where this server is now responsible for.
   */
  public void takeSeat(Table table) {
    table.serve(this);
    System.out.println("Customers take seat at backend.table " + table.getTableNum());
  }

  /**
   * Enters orders of customers on certain backend.table. The order is then sent to cook.
   *
   * <p>Customers should take seat before ordering food.
   */
  public void enterMenu(Table table, Order order) {
    // Add order to the backend.table and relate all the dish to the backend.table.
    if (table.getServerId() == -1) {
      System.err.println("ComputerServer hasn't been set up for backend.table " + table.getTableNum());
     // (new Exception()).printStackTrace();
    } else {
      order.assignDishNumber();
      table.addOrder(order);

      // Send order to cook
      orderQueue.addOrderInQueue(order);
      System.out.println("ComputerServer " + getId() + " Orders are sent to cook.");
    }
  }

  /** Successfully delivers dish to the backend.table. */
  public void deliverDishCompleted(int dishNumber) {
    Dish dish = orderQueue.dishDelivered(dishNumber);
    if (dish == null){
      System.err.println("the dish " +dishNumber + " does not exist");
    } else if (dish.getTable() == null) {
      System.out.println("there is no backend.table assigned to this dish " + dishNumber + ".");
    } else if(dish.getTable().getServerId() == -1){
      System.out.println("the backend.table is empty, the dish "+ dishNumber + " will not be delivered");
    }
    else{
      dish.sent();
      System.out.println(
              "ComputerServer "
                      + getId()
                      + "    "
                      + dish.getName()
                      + " (Dish #: "
                      + dish.getDishNumber()
                      + ") has been delivered successfully.");

      // This is when the price of the dish gets added to the backend.table.
      dish.addCostToTable();
    }
  }

  /** Delivers dish to the backend.table, but the customer requests to put the dish back. */
  public void deliverDishFailed(int dishNumber) {
    Dish dish = orderQueue.dishDelivered(dishNumber);
    System.out.println(
        "Dish #: "
            + dish.getDishNumber()
            + ", "
            + dish.getName()
            + " has been delivered but put back upon customer's request.");

    // Sets price of the dish to zero, while remaining the dish in the backend.table's order list.
    dish.isCancelled();
  }

  /**
   * Prints bill of the <code>backend.table</code>.
   *
   * @param table The backend.table which asked to have their bill.
   */
  public void printBill(Table table) {
    table.printBill();
  }

  /**
   * After customers leave, this method resets the backend.table.
   *
   * <p>This method removes - the server who was in change of the backend.table - all the orders that the
   * customers in <code>backend.table</code> ordered.
   *
   * @param table the Table that this ComputerServer is in charge of
   */
  public void clearTable(Table table) {
    table.clear();
  }

  /**
   * Returns a string representation of this backend.employee.
   *
   * <p>The string representation consists of its employ type (= ComputerServer) and its id.
   *
   * @return a string representation of this backend.employee.
   */
  public String toString() {
    return "ComputerServer, id:" + getId();
  }
}
