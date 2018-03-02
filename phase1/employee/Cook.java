package employee;

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
      System.out.println("Confirmed that the order has been seen.");
    }
  }

  /** Cook confirms whether the food is ready to be delivered by the server. */
  public void dishReady() {
    orderQueue.dishCompleted();
    System.out.println("Food is ready to be delivered.");
  }
}
