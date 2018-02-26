package employee;

import table.Dish;
import table.Order;
import table.Table;
import java.util.ArrayList;

public class Server extends Employee {
  ArrayList<Dish> dishesToBeDelivered;

  public Server(String name, int id) {
    super(name, id);
  }

  /**
   * Enters each dish that customer ordered. This method is responsible for
   *
   * <p>1. Entering dish(including addition and subtraction from each dish.
   *
   * <p>2. Sending list of dishes to the Cook.
   */
  public void enterMenu(Order order) {
    Cook.listOfOrdersToBeSeen.add(order);
  }

  public void deliverOrderCompleted() {
    if (!dishesToBeDelivered.isEmpty()) {
      Dish dish = dishesToBeDelivered.remove(0);
      dish.getTable().totalCost += dish.getCost();
    }
  }

  public void deliverOrderFailed() {
    if (!dishesToBeDelivered.isEmpty()) {
      return;
    }
  }

  /** Print bill. */
  public void printBill(Table table) {
    ArrayList<Dish> dishes = table.getOrder().getDishes();
    int costSum = 0;
    for (Dish dish : dishes) {
      System.out.println(dish.getName() + ": " + dish.getCost());
      costSum += dish.getCost();
    }
    System.out.println("Total: " + costSum);
  }

  public boolean checkIfPaid(Table table) {
    return table.isPaid; // TODO: do we need isPaid variable in table?
  }
}
