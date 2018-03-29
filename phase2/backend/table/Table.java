package backend.table;

import backend.employee.Server;
import backend.inventory.Dish;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Table class represents each backend.table in the restaurant.
 *
 * <p>Table class methods include storing the customer's orders, adding orders, adding price of the
 * order.
 */
public class Table implements Serializable {
  private int tableNum;
  private int numOfCustomer;
  private float cost;
  private boolean occupied;
  private Server server;
  private ArrayList<Order> order;

  /**
   * initializes the backend.table with its number and price of 0
   *
   * @param tableNum the backend.table number
   */
  public Table(int tableNum) {
    this.tableNum = tableNum;
    this.occupied = false;
    order = new ArrayList<>();
    cost = 0;
    numOfCustomer = 0;
  }

  /** clears the backend.table for new customer no server and empty order */
  public void clear() {
    server = null;
    cost = 0;
    order = new ArrayList<>();
    this.occupied = false;
  }

  /**
   * serves this backend.table
   *
   * @param server the server that serving this backend.table
   */
  public void serve(Server server, int numOfCustomer) {
    this.server = server;
    this.numOfCustomer = numOfCustomer;
    this.occupied = true;
  }

  /**
   * adds the dish price to backend.table's price
   *
   * @param d a dish object
   */
  public void addCost(Dish d) {
    cost += d.getPrice();
  }

  /**
   * adds this order to this backend.table also add backend.table to this order
   *
   * @param newOrder the order that made by the customer
   */
  public void addOrder(Order newOrder) {
    order.add(newOrder);
    newOrder.assignDishToTable(this);
  }

  /**
   * returns the backend.table number
   *
   * @return backend.table number
   */
  public int getTableNum() {
    return tableNum;
  }

  /**
   * prints bill for this backend.table in format of Table number and all dishes with its price and
   * the total price
   */
  public ArrayList<Dish> printBill() {
    // TODO: Phase 1, Bill class. Bill should be printed on frontend.GUI as text (maybe this method
    // should
    // return bill in string type.)
    //TODO OBject type??
    ArrayList<Dish> dishes = new ArrayList<>();
    for (Order order : this.order) {
      for(Dish dish: order.getDishes()){
        dish.taxed();
        if(numOfCustomer >= 8){
          dish.tiped();
        }
        dishes.add(dish);
      }
    }

    return dishes;
  }

  /**
   * returns the server id if the server is not null
   *
   * @return the server id, -1 if no server
   */
  public int getServerId() {
    return (server == null) ? -1 : server.getId();
  }

  public int getNumOfCustomer() {
    return numOfCustomer;
  }

  /**
   * Returns true if table is occupied, false if not
   *
   * @return the boolean
   */
  public boolean isOccupied() {
    return occupied;
  }
}
