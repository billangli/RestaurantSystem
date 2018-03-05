package table;

import employee.Server;
import inventory.Dish;

import java.util.ArrayList;

public class Table {
  private int tableNum;
  private float cost;
  private Server server;
  private ArrayList<Order> order;
  private boolean hasPaid;

  public Table(int tableNum) {
    this.tableNum = tableNum;
    order = new ArrayList<Order>();
    cost = 0;
    hasPaid = false;
  }

  public void serve(Server server) {
    this.server = server;
  }

  public void addCost(Dish d) {
    cost += d.getCost();
  }

  public void addOrder(Order newOrder) {
    order.add(newOrder);
    newOrder.setTable(this);
    newOrder.setTableForDishes(this);
  }

  public int getTableNum() {
    return tableNum;
  }

  public ArrayList<Order> getOrder() {
    return order;
  }

  public boolean getHasPaid() {
    return hasPaid;
  }

  public void printBill() {
    System.out.println("===== <BILL> =====");
    System.out.println("Table number: " + tableNum + "\nList of dishes ordered:");
    for (Order order : this.order) {
      System.out.println(order);
    }
    System.out.println("------------------");
    System.out.format("Total: $%.2f \n", cost);
    System.out.println("==================");
  }

  public int getServerId() {
    return (server == null) ? -1 : server.getId();
  }
}
