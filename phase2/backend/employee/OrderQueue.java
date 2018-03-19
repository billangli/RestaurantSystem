package backend.employee;

import backend.inventory.Dish;
import backend.table.Order;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;
import backend.logger.RestaurantLogger;

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

  private static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

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
    // TODO: This method is not used anymore(It was used in Cook.orderReceived()). Delete before
    // submission.
    return OrdersInQueue.isEmpty();
  }

  /**
   * This method is used by the cook to confirm that all the orders in queue are seen.
   *
   * @param cookId Id of cook who confirms the order.
   */
  public void confirmFirstOrderInQueue(int cookId) {
    if (OrdersInQueue.isEmpty()) {
      logger.info("There are no orders in the queue to be cooked.");
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
      } else if (!dish.ableToCook()) {
        logger.warning(
            "not enough ingredients to cook " + dish.getName() + " (Dish #: " + dishNumber + ")");
      } else {
        DishesCompleted.add(dish);
        dish.updateIngredientsStock();
        logger.info(
            "Cook "
                + cookId
                + "     calling "
                + "Server id("
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
      dish.sent();
    }
    return dish;
  }
}
