package employee;

import table.Dish;
import table.Order;
import java.util.ArrayList;

public class Cook extends Employee {

  // When an order is seen by cook, it means the cook started to make the dishes.
  public static ArrayList<Order> listOfOrdersToBeSeen = new ArrayList<>();
  public static ArrayList<Dish> listOfDishesBeingCooked = new ArrayList<>();

  public Cook(String name, int id) {
    super(name, id);
  }

  /** Cook confirms whether the order has been seen. */
  public void orderReceived() {
    if (!listOfOrdersToBeSeen.isEmpty()) {
      if (listOfDishesBeingCooked.isEmpty()) {
        System.out.println("Order has been received.");
        Order order = listOfOrdersToBeSeen.remove(0);

        listOfDishesBeingCooked.addAll(order.getDishes());
        System.out.println(
            "Order for table number " + order.getTable().getTableNum() + "is being cooked.");
      } else {
        System.out.println("There are dishes to be cooked. Finish them first.");
      }
    } else {
      System.out.println("No orders are in queue.");
    }
  }

  /** Cook confirms whether the food is ready to be delivered by the server. */
  public void dishReady(Dish dish) {
    System.out.println(
        "Table number: "
            + dish.getTable().getTableNum()
            + ", "
            + dish.getName()
            + " is ready to be delivered.");
    dish.getTable().getServer().dishesToBeDelivered.add(dish);

    //TODO: subtract the amount of ingredients used to cook the dish.
  }
}
