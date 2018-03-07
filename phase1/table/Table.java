package table;

import employee.Server;
import inventory.Dish;

import java.util.ArrayList;

/**
 * represent each table in the restaurant.
 * store the customer's orders and number number
 */
public class Table {
  private int tableNum;
  private float cost;
  private Server server;
  private ArrayList<Order> order;

  /**
   * initialize the table with its number
   * and cost of 0
   * @param tableNum the table number
   */

  public Table(int tableNum) {
    this.tableNum = tableNum;
    order = new ArrayList<Order>();
    cost = 0;
  }


  /**
   * clear the table for new customer
   * no server and empty order
   */
  public void clear(){
    server = null;
    cost = 0;
    order = new ArrayList<>();
  }

  /**
   * this server serves this table
   * @param server the server that serving this table
   */
  public void serve(Server server) {
    this.server = server;
  }

  /**
   * add the dish price into table's price
   * @param d a dish object
   */
  public void addCost(Dish d) {
    cost += d.getCost();
  }


  /**
   * add this order to this table
   * also add table to this order
   * @param newOrder the order that made by the customer
   */
  public void addOrder(Order newOrder) {
    order.add(newOrder);
    newOrder.assignDishToTable(this);
  }

  /**
   *
   * @return table number
   */
  public int getTableNum() {
    return tableNum;
  }

  /**
   *
   * @return orders in arraylist
   */
  public ArrayList<Order> getOrder() {
    return order;
  }


  /**
   * print bill for this table in format of Table number
   * and all dishes with its price
   * and the total price
   */
  public void printBill() {
    System.out.println("===== <BILL> =====");
    System.out.println("Table number: " + tableNum + "\nList of dishes ordered:");
    for (Order order : this.order) {
      System.out.println(order);
    }
    System.out.println("------------------");
    System.out.format("Total: $%.2f \n", cost);
    System.out.println("==================\n");
  }

  /**
   * return the server id if the server is not null
   * @return the server id, -1 if no server
   */
  public int getServerId() {
    return (server == null) ? -1 : server.getId();
  }
}
