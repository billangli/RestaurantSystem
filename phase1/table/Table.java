package table;

import employee.Server;
import inventory.Dish;

import java.util.ArrayList;

public class Table {
  private int tableNum;
  private float cost;
  private Server server;
  private ArrayList<Order> order;

  public Table(int tableNum) {
    this.tableNum = tableNum;
    order = new ArrayList<Order>();
    cost = 0;
  }

  public void clear(){
    server = null;
    cost = 0;
    order = new ArrayList<>();
  }


  public void serve(Server server) {
    this.server = server;
  }

  public void addCost(Dish d) {
    cost += d.getCost();
  }

  public void addOrder(Order newOrder) {
    order.add(newOrder);
    newOrder.assignDishToTable(this);
  }

  public int getTableNum() {
    return tableNum;
  }

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

  public int getServerId() {
    return (server == null) ? -1 : server.getId();
  }
}
