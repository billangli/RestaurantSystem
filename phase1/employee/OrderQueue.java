package employee;

import inventory.Dish;
import table.Order;
import java.util.LinkedList;
import java.util.Queue;

// I wonder where this file should be located.
public class OrderQueue {
  private Queue<Order> OrdersInQueue;
  private Queue<Dish> DishesInProgress;
  private Queue<Dish> DishesCompleted;

  public OrderQueue() {
    OrdersInQueue = new LinkedList<>();
    DishesInProgress = new LinkedList<>();
    DishesCompleted = new LinkedList<>();
  }

  public void addOrderInQueue(Order order) {
    OrdersInQueue.add(order);
  }

  public boolean queueIsEmpty() {
    return OrdersInQueue.isEmpty();
  }

  public void emptyOrdersInQueue() {
    while (!OrdersInQueue.isEmpty()) {
      Order order = OrdersInQueue.remove();
      System.out.println("Order for table number: " + order.getTableNum());
      System.out.println("List of Dishes: " + order.dishesToString() + "\n");
      DishesInProgress.addAll(order.getDishes());
    }
  }

  public void dishCompleted() {
    DishesCompleted.add(DishesInProgress.remove());
  }

  public Dish dishDelivered() {
    return DishesCompleted.remove();
  }
}
