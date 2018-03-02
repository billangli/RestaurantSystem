package employee;

import inventory.Dish;

public class Cook extends Employee {
  private OrderQueue orderQueue;

  public Cook(int id) {
    super(id);
    orderQueue = new OrderQueue();
  }

  // This method should be able to set same OrderQueue for all cooks and servers.
  // This is a duplicate code in Cook and Server class.(Is it possible not to make it duplicated?)
  public void setOrderQueue(OrderQueue orderQueue) {
    this.orderQueue = orderQueue;
  }

  /** Cook confirms whether the order has been seen. */
  public void orderReceived() {
    if (orderQueue.queueIsEmpty()) {
      System.out.println("No orders are in the queue.");
    } else {
      orderQueue.emptyOrdersInQueue();
      System.out.println("Confirmed that the order has been seen.");
    }
  }

  /** Cook confirms whether the food is ready to be delivered by the server. */
  public void dishReady() {
    orderQueue.dishCompleted();
    System.out.println("Food is ready to be delivered.");
    // TODO: subtract the amount of ingredients used to cook the dish.
  }
}
