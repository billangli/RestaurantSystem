package table;

import inventory.Dish;

import java.util.ArrayList;

/**
 * order that is made by customer
 * store dishes and the table that is belongs to
 */
public class Order {
  private ArrayList<Dish> dishes;
  private Table table;

    /**
     * initialize the order with an empty arrayList of Dish
     */
  public Order() {
    dishes = new ArrayList<>();
  }

    /**
     * add this dish to dishes
     * @param d a dish
     */
  public void addDish(Dish d) {
    dishes.add(d);
  }

    /**
     *
     * @return all dish in the order
     */
  public String toString() {
    StringBuilder str = new StringBuilder();

    int i = 0;
    for (; i < dishes.size() - 1; i++) {
      str.append(dishes.get(i).toString()).append("\n");
    }
    str.append(dishes.get(i));

    return str.toString();
  }

    /**
     *
     * @return the table number
     */
  public int getTableNum() {
    return table.getTableNum();
  }


    /**
     * assign the order to this table
     * and assign all dishes in order to this table
     * @param table the table that made the order
     */
  public void assignDishToTable(Table table) {
    this.table = table;
    for (Dish dish : dishes) {
      dish.assignDishToTable(table);
    }
  }


  /**
   * @return all dish name with its price
   */
  public String dishesToString() {
    ArrayList<String> result = new ArrayList<>();
    for (Dish dish : dishes) {
      result.add(dish.getName() + "(Dish #: " + dish.getDishNumber() + ")");
    }
    return String.join(", ", result);
  }

    /**
     *
     * @return dishes in an arraylist
     */
  public ArrayList<Dish> getDishes() {
    return dishes;
  }

    /**
     * give an id to each dish in this order
     */
  public void assignDishNumber() {
    for (Dish d : dishes) {
      d.assignDishNumber();
    }
  }
}
