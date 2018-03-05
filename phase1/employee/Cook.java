package employee;

import inventory.Dish;

public class Cook extends ServiceEmployee {

  public Cook(int id) {
    super(id);
  }

  /** Cook confirms whether the order has been seen. */
  public void orderReceived() {
    if (orderQueue.queueIsEmpty()) {
      System.out.println("No orders are in the queue.");
    } else {
      orderQueue.confirmOrdersInQueue();
      System.out.println("Cook confirmed that the order has been seen.");
    }
  }

  /** Cook confirms whether the food is ready to be delivered by the server. */
  public void dishReady(int dishNumber) {
    System.out.print("Dish made by Cook id(" + getId() + ")" + " calling ");
    // the print log is in orderQueue.dishCompleted() method.
    orderQueue.dishCompleted(dishNumber);
  }

  public String toString() {
    return "Cook, id:" + getId();
  }
}
