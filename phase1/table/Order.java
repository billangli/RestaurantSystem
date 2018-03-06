package table;

import inventory.Dish;

import java.util.ArrayList;

public class Order {
  private ArrayList<Dish> dishes;
  private boolean sent;
  private Table table;

  public Order() {
    sent = false;
    dishes = new ArrayList<>();
  }

  public void addDish(Dish d) {
    dishes.add(d);
  }

  public void sent() {
    sent = true;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();

    int i = 0;
    for (; i < dishes.size() - 1; i++) {
      str.append(dishes.get(i).toString()).append("\n");
    }
    str.append(dishes.get(i));

    return str.toString();
  }

  public boolean isSent() {
    return sent;
  }

  public void recook(Dish d) {
    d.recook();
  }

  public int getTableNum() {
    return table.getTableNum();
  }

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

  public ArrayList<Dish> getDishes() {
    return dishes;
  }

  public void assignDishNumber() {
    for (Dish d : dishes) {
      d.assignDishNumber();
    }
  }
}
