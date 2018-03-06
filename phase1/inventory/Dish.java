package inventory;

import java.util.HashMap;

import table.Table;

/**
 * The Dish class represents the dish that the restaurant offers in its menu, and also the dish
 * that the customer orders, which may be different from the default dish listed in the menu if adjustments
 * are made in certain ingredients. There may be multiple Dish classes with the same name, but every dish is assigned
 * a unique dishNumber.
 *
 *
 * The class includes methods for adjusting the ingredients in the Dish, updating the ingredients in the Restaurant
 * inventory, assigning the Dish to a Table that ordered it, and creating a string with the name of its dish and its
 * cost.
 */
public class Dish {
  private String name;
  private HashMap<String, Ingredient> ingredientsRequired = new HashMap<>();
  private int dishNumber;
  private static int countDish = 0;
  private float cost;
  private boolean isReady;
  Table table;

  /**
     * Constructor that takes the name of the dish, price and the list of names of the ingredients;
     * this constructor is used to create the dishes in the Menu
     *
     * @param name is the name of the Dish
     * @param price is the price of the Dish in dollars
     * @param ingredients is the list of names of the ingredients used for this dish
     */
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

    /**
     * A constructor that copies the dish from the menu to create a dish for Order
     *
     * @param d is the dish in the menu
     */
  public Dish(Dish d) {
    this.name = d.getName();
    this.cost = d.getCost();
    this.ingredientsRequired = d.ingredientsRequired;
    isReady = false;
  }

    /**
     * Adjust the ingredient in the dish that is to be added to the Order
     *
     *
     * @param ingredientName
     * @param amount
     */
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

  /**
   * Assign this dish to the table t
   *
   * @param t the table that this dish was ordered from
   */
  public void assignDishToTable(Table t) {
    table = t;
  }

  /**
   *  Status of the dish describing whether it is cooked and ready to be delivered to the table
   */
  public void ready() {
    isReady = true;
  }

  /**
   * Recook the dish
   */
  public void recook() {
    isReady = false;
  }

  /**
   * Return whether dish is ready to be delivered
   *
   * @return
   */
  public boolean isReady() {
    return isReady;
  }

  /**
   * Return name of the dish
   * @return the name of the dish
   */
  public String getName() {
    return name;
  }

  /**
   * Return the cost of the dish
   * @return the cost of the dish
   */
  public float getCost() {
    return cost;
  }

  /**
   * Return the table that this dish was ordered from
   * @return the table that this dish was ordered from
   */
  public Table getTable() {
    return table;
  }

  /**
   * Return the name of the dish and its cost
   * @return Return the name of the dish and its cost
   */
  public String toString() {
    return name + ": $" + String.format("%.2f", cost);
  }

  /**
   * Add the cost of this dish to the table that ordered this dish
   */

  public void addCostToTable() {
    table.addCost(this);
  }

  /**
   * Modify the cost of this dish to 0;
   */
  public void isCancelled() {
    cost = 0;
  }

  /**
   * Return the unique number that identifies this particular dish
   * @return Return the number that identifies this particular dish
   */
  public int getDishNumber() {
    return dishNumber;
  }

  /**
   * Assign a unique number that identifies this dish
   */
  public void assignDishNumber(){
    dishNumber = ++Dish.countDish;
  }
}
