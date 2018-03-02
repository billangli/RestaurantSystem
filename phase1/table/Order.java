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
    for (Dish d : dishes) {
      str.append(d.toString()).append("\n");
    }
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

  public void setTable(Table table) {
    this.table = table;
  }

  public void setTableForDishes(Table table) {
    for (Dish dish : dishes) {
      dish.setTable(table);
    }
  }

  public String dishesToString() {
    ArrayList<String> result = new ArrayList<>();
    for (Dish dish : dishes) {
      result.add(dish.getName());
    }
    return String.join("/", result);
  }

  public ArrayList<Dish> getDishes() {
    return dishes;
  }
}
