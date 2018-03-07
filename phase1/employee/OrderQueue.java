package employee;

import inventory.Dish;
import table.Order;
import java.util.LinkedList;
import java.util.Queue;

/**
 * An OrderQueue class.
 *
 * <p>This class manages all the orders that are made from customers.
 */
class OrderQueue {
  // orders that are sent from server to cook and are not yet seen by cook.
  private Queue<Order> OrdersInQueue;

  // dishes that are seen by cook and are being cooked.
  private LinkedList<Dish> DishesInProgress;

  // dishes that are cooked and waiting to be delivered.
  private LinkedList<Dish> DishesCompleted;

  public OrderQueue() {
    OrdersInQueue = new LinkedList<>();
    DishesInProgress = new LinkedList<>();
    DishesCompleted = new LinkedList<>();
  }

  /**
   * This method is used by the server to send order from customer to cook.
   *
   * @param order order object that is being sent to cook.
   */
  public void addOrderInQueue(Order order) {
    OrdersInQueue.add(order);
  }

  /**
   * Return true if there is no order that should be seen by the cook, otherwise return false.
   *
   * @return true if there is no order that should be seen by the cook, otherwise return false.
   */
  public boolean queueIsEmpty() {
    return OrdersInQueue.isEmpty();
  }

  /** This method is used by the cook to confirm that all the orders in queue are seen. */
  public void confirmOrdersInQueue() {
    while (!OrdersInQueue.isEmpty()) {
      Order order = OrdersInQueue.remove();
      System.out.println("--- Order confirm message ---");
      System.out.println("Order for table number: " + order.getTableNum());
      System.out.println("List of Dishes: " + order.dishesToString() + "\n");
      DishesInProgress.addAll(order.getDishes());
    }
  }

  /**
   * This method is used by the cook to inform that a dish is cooked and waiting to be delivered to
   * customers.
   *
   * <p>Precondition: the dish that has 'dishNumber' is in the queue.
   *
   * @param dishNumber The dish number of dish that cook has finished cooking.
   */
  public void dishCompleted(int dishNumber) {
    Dish dish = null;
    for (int i = 0; i < DishesInProgress.size(); i++) {
      if (DishesInProgress.get(i).getDishNumber() == dishNumber) {
        dish = DishesInProgress.get(i);
        DishesInProgress.remove(i);
        break;
      }
    }

    Exception e = new Exception();
    if (dish == null) {
      System.err.print("OrderQueue.dishCompleted(int dishNumber): Not a valid dish number.");
      e.printStackTrace();
    } else {
      DishesCompleted.add(dish);
      int serverId = dish.getTable().getServerId();
      if (serverId == -1) {
        System.err.println("Not a valid server id.");
        e.printStackTrace();
      } else {
        System.out.println(
            "Server id("
                + serverId
                + ") | "
                + dish.getName()
                + " (Dish #: "
                + dishNumber
                + ") is ready to be delivered.");
      }
    }
  }

  /**
   * This method is used by the server to inform that a dish is delivered
   *
   * <p>Note that the dish can also be put back by customer's request.
   *
   * @return dish that is being delivered, null if there is no dish to be delivered.
   */
  public Dish dishDelivered(int dishNumber) {
    Dish dish = null;
    for (int i = 0; i < DishesCompleted.size(); i++) {
      if (DishesCompleted.get(i).getDishNumber() == dishNumber) {
        dish = DishesCompleted.get(i);
        DishesCompleted.remove(i);
        break;
      }
    }

    if (dish == null) {
      System.err.println("OrderQueue.dishDelivered(int dishNumber): Not a valid dish number.");
      (new Exception()).printStackTrace();
    } else {
      dish.updateIngredientsStock();
    }
    return dish;
  }
}
