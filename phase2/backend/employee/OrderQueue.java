package backend.employee;

import backend.inventory.Dish;
import backend.logger.RestaurantLogger;
import backend.table.Order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * An OrderQueue class.
 *
 * <p>This class manages all the orders that are made from customers.
 */
public class OrderQueue {
  // orders that are sent from server to cook and are not yet seen by cook.
  private LinkedList<Order> OrdersInQueue;

  // dishes that are seen by cook and are being cooked.
  private LinkedList<Dish> DishesInProgress;

  // dishes that are cooked and waiting to be delivered.
  private LinkedList<Dish> DishesCompleted;

  private ArrayList<Order> orderBeingCooked;

  private static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  public OrderQueue() {
    OrdersInQueue = new LinkedList<>();
    DishesInProgress = new LinkedList<>();
    DishesCompleted = new LinkedList<>();
    orderBeingCooked = new ArrayList<>();
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
   * This method is used by the cook to confirm that all the orders in queue are seen.
   *
   * @param cookId Id of cook who confirms the order.
   */
  public void confirmFirstOrderInQueue(int cookId) {
    if (OrdersInQueue.isEmpty()) {
      logger.info("There are no orders in the queue to be cooked.");
    } else if (!DishesInProgress.isEmpty()) {
      logger.info(
          "There are dishes that are in progress. Those should be finished first before confirming orders.");
    } else {
      Order order = OrdersInQueue.remove();
      logger.info(
          "Cook "
              + cookId
              + " confirmed order for backend.table number: "
              + order.getTableNum()
              + ", list of dishes: "
              + order.dishesToString());
      DishesInProgress.addAll(order.getDishes());
      orderBeingCooked.add(order);
    }
  }

  /**
   * This method is used by the cook to inform that a dish is cooked and waiting to be delivered to
   * customers.
   *
   * <p>Precondition: the dish that has 'dishNumber' is in the queue.
   *
   * @param dishNumber The dish number of dish that cook has finished cooking.
   * @param cookId Id of cook who completes the dish.
   */
  public void dishCompleted(int dishNumber, int cookId) {
    Dish dish = null;
    for (int i = 0; i < DishesInProgress.size(); i++) {
      if (DishesInProgress.get(i).getDishNumber() == dishNumber) {
        dish = DishesInProgress.get(i);
        DishesInProgress.remove(i);
        break;
      }
    }

    if (dish == null) {
      logger.warning("Not a valid dish number.");
    } else {
      int serverId = dish.getTable().getServerId();
      if (serverId == -1) {
        logger.warning("Not a valid server id.");
        //      }else if (!dish.ableToCook(inventory)) { TODO if needed
        //        logger.warning(
        //            "not enough ingredients to cook " + dish.getName() + " (D12ish #: " +
        // dishNumber + ")");
      } else {
        DishesCompleted.add(dish);
        dish.updateIngredientsStock();
        logger.info(
            "Cook "
                + cookId
                + "     calling "
                + "ComputerServer id("
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
      logger.warning("the dish " + dishNumber + " does not exist");
    } else if (dish.getTable() == null) {
      logger.warning("there is no backend.table assigned to this dish " + dishNumber + ".");
    } else if (dish.getTable().getServerId() == -1) {
      logger.warning(
          "the backend.table is empty, the dish " + dishNumber + " will not be delivered");
    } else {
      dish.delivered();
      Order orderTarget = null;
      outerloop:
      for (Order o : orderBeingCooked) {
        for (Dish d : o.getDishes()) {
          if (d == dish) {
            orderTarget = o;
            break outerloop;
          }
        }
      }
      if (orderTarget != null) {
        orderTarget.remove(dish);
        if (orderTarget.isEmpty()) {
          orderBeingCooked.remove(orderTarget);
        }
      }
    }

    return dish;
  }

  /**
   * Returns linked list of orders that are in queue.
   *
   * @return linked list of orders that are in queue.
   */
  public LinkedList<Order> getOrdersInQueue() {
    return OrdersInQueue;
  }

  /**
   * Returns linked list of dishes that are in progress.
   *
   * @return linked list of dishes that are in progress.
   */
  public LinkedList<Dish> getDishesInProgress() {
    return DishesInProgress;
  }

  /**
   * Returns linked list of dishes that are ready to be delivered.
   *
   * @return linked list of dishes that are ready to be delivered.
   */
  public LinkedList<Dish> getDishesCompleted() {
    return DishesCompleted;
  }

  /**
   * Returns linked list of orders that are not delivered yet.
   *
   * @return linked list of orders that are not delivered yet.
   */
  public ArrayList<Order> getOrdersNotDelivered() {
    ArrayList<Order> result = new ArrayList<>();
    result.addAll(orderBeingCooked);
    result.addAll(OrdersInQueue);

    return result;
  }
}
