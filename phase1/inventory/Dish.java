package inventory;

import java.util.HashMap;

import table.Table;

public class Dish {
  private String name;
  private HashMap<String, Ingredient> ingredientsRequired = new HashMap<>();
  private int dishNumber;
  private static int countDish = 0;
  private float cost;
  private boolean isReady;
  Table table;

  public Dish(String name, float price, String[] ingredients) {
    this.name = name;
    this.cost = price;
    for (String ingredient : ingredients) {
      String[] item = ingredient.split(":");
      int[] limit = {Integer.parseInt(item[2]), Integer.parseInt(item[3])};
      Ingredient in = new Ingredient(item[0], Integer.parseInt(item[1]), limit);
      ingredientsRequired.put(item[0], in);
    }

    isReady = false;
  }

  public Dish(Dish d) {
    this.name = d.getName();
    this.cost = d.getCost();
    this.ingredientsRequired = d.ingredientsRequired;
    isReady = false;
  }

  // NEED MODIFICATION
  public void adjustIngredient(String ingredientName, int amount) {
    if (ingredientsRequired
        .get(ingredientName)
        .allowed(amount, Inventory.getIngredient(ingredientName))) {
      ingredientsRequired.get(ingredientName).adjust(amount);
    }
  }

  /** Subtracts all the amounts of ingredients used in inventory to make this dish. */
  public void updateIngredientsStock() {
    for (String ingredientName : ingredientsRequired.keySet()) {
      Inventory.modifyIngredientQuantity(
          ingredientName, -1 * ingredientsRequired.get(ingredientName).getQuantity());
    }
  }

  public void setTable(Table t) {
    table = t;
  }

  public void ready() {
    isReady = true;
  }

  public void recook() {
    isReady = false;
  }

  public boolean isReady() {
    return isReady;
  }

  public String getName() {
    return name;
  }

  public float getCost() {
    return cost;
  }

  public Table getTable() {
    return table;
  }

  public String toString() {
    return name + ": $" + String.format("%.2f", cost);
  }

  public void addCostToTable() {
    table.addCost(this);
  }

  public void isCancelled() {
    cost = 0;
  }

  public int getDishNumber() {
    return dishNumber;
  }

  public void assignDishNumber(){
    dishNumber = ++Dish.countDish;
  }
}
