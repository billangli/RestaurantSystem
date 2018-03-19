package backend.inventory;

import java.util.HashMap;
import backend.table.Table;
import java.util.logging.Logger;
import backend.logger.RestaurantLogger;

/**
 * The Dish class represents the dish that the restaurant offers in its menu, and also the dish that
 * the customer orders, which may be different from the default dish listed in the menu if
 * adjustments are made in certain ingredients. There may be multiple Dish classes with the same
 * name, but every dish is assigned a unique dishNumber.
 *
 * <p>The class includes methods for adjusting the ingredients in the Dish, updating the ingredients
 * in the Restaurant backend.inventory, assigning the Dish to a Table that ordered it, and creating
 * a string with the name of its dish and its cost.
 */
public class Dish {
  private String name;
  private HashMap<String, DishIngredient> ingredientsRequired = new HashMap<>();
  private int dishNumber;
  private static int countDish = 0;
  private float cost;
  private Table table;
  private boolean isSent;
  private static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  /**
   * Constructor that takes the name of the dish, price and the list of names of the ingredients;
   * this constructor is used to create the dishes in the Menu
   *
   * @param dishName is the name of the Dish
   * @param dishPrice is the price of the Dish in dollars
   * @param ingredients is the list of names of the ingredients used for this dish
   */
  public Dish(String dishName, float dishPrice, String[] ingredients) {
    this.name = dishName;
    this.cost = dishPrice;
    isSent = false;
    for (String ingredient : ingredients) {
      String[] item = ingredient.split(":");
      int[] limit = {Integer.parseInt(item[2]), Integer.parseInt(item[3])};
      DishIngredient in =
          new DishIngredient(item[0], Integer.parseInt(item[1]), limit[0], limit[1]);
      ingredientsRequired.put(item[0], in);
    }
  }

  /**
   * A constructor that deep-copies the dish from the menu to create a dish for Order. <code>menuDish
   * </code> should not be mutated.
   *
   * @param menuDish is the dish in the menu
   */
  public Dish(Dish menuDish) {
    this.name = menuDish.getName();
    this.cost = menuDish.getCost();
    this.ingredientsRequired = new HashMap<>();
    // Deep Copy
    for (String dishName : menuDish.getIngredientsRequired().keySet()) {
      DishIngredient dishIngredient =
          new DishIngredient(menuDish.getIngredientsRequired().get(dishName));
      this.ingredientsRequired.put(dishName, dishIngredient);
    }
  }

  /**
   * Adjusts the ingredient in the dish that is to be added or subtracted to the Order
   *
   * @param ingredientName name of this Ingredient
   * @param amount the amount of ingredient being added to the Order
   */
  public void adjustIngredient(String ingredientName, int amount) {
    if (ingredientsRequired.get(ingredientName).allowed(amount)) {
      ingredientsRequired.get(ingredientName).modifyQuantity(amount);
    } else {
      logger.warning(
          "Adjusting " + amount + " " + ingredientName + " is not valid for dish " + name);
    }
  }

  /** Subtracts all the amounts of ingredients used in backend.inventory to make this dish. */
  public void updateIngredientsStock() {
    for (String ingredientName : ingredientsRequired.keySet()) {
      Inventory.modifyIngredientQuantity(
          ingredientName, -1 * ingredientsRequired.get(ingredientName).getQuantity());
    }
  }

  public boolean updateProjectedIngredientsStock() {
    for (String ingredientName : ingredientsRequired.keySet()) {

      Inventory.modifyIngredientMirrorQuantity(
          ingredientName, -1 * ingredientsRequired.get(ingredientName).getQuantity());
      if (Inventory.getIngredient(ingredientName).getWouldBeUnderThreshold()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Assigns this dish to the backend.table t
   *
   * @param t the backend.table that this dish was ordered from
   */
  public void assignDishToTable(Table t) {
    table = t;
  }

  /**
   * Checks if there is enough ingredient to cook this dish
   *
   * @return if there is enough ingredient in backend.inventory
   */
  public boolean ableToCook() {
    for (String ingredientName : ingredientsRequired.keySet()) {
      int inventoryQuantity = Inventory.getIngredient(ingredientName).getQuantity();
      int dishQuantity = ingredientsRequired.get(ingredientName).getQuantity();
      if (inventoryQuantity < dishQuantity) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the name of this Dish
   *
   * @return the name of this dish
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the cost of the dish
   *
   * @return the cost of the dish
   */
  public float getCost() {
    return cost;
  }

  /**
   * Returns the backend.table that this dish was ordered from
   *
   * @return the backend.table that this dish was ordered from
   */
  public Table getTable() {
    return table;
  }

  /**
   * Returns the name of the dish and its cost
   *
   * @return the name of the dish and its cost
   */
  public String toString() {
    //    float currentCost = isSent ? cost : 0;
    return String.format("%-20s: $%.2f", name, cost);
  }

  /** Adds the cost of this dish to the backend.table that ordered this dish */
  public void addCostToTable() {
    table.addCost(this);
  }

  /** Modifies the cost of this dish to 0; */
  public void isCancelled() {
    cost = 0;
  }

  /**
   * Returns the unique number that identifies this particular dish
   *
   * @return Return the number that identifies this particular dish
   */
  public int getDishNumber() {
    return dishNumber;
  }

  /** Assigns a unique number that identifies this dish */
  public void assignDishNumber() {
    dishNumber = ++Dish.countDish;
  }

  /** Acknowledges the dish is sent(=delivered) to its backend.table. */
  public void sent() {
    isSent = true;
  }

  public boolean isSent() {
    return isSent;
  }

  public HashMap<String, DishIngredient> getIngredientsRequired() {
    return ingredientsRequired;
  }
}
