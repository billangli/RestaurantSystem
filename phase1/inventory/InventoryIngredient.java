package inventory;

/**
 * InventoryIngredient represents the Ingredient in inventory
 *
 * <p>InventoryIngredient methods include checking whether the ingredient in the restaurant
 * inventory is below the lower threshold
 */
public class InventoryIngredient extends Ingredient {
  private int lowerThreshold;

  /**
   *
   * Constructor for InventoryIngredient that takes in the name, quantity and lower threshold for the ingredient
   *
   * @param name the name of this InventoryIngredient
   * @param quantity the quantity of this InventoryIngredient in stock
   * @param lowerThreshold the lowerThreshold of this InventoryIngredient; if the quantity of this
   *     Ingredient goes below this lowerThreshold, then a restock order will be made
   */
  public InventoryIngredient(String name, int quantity, int lowerThreshold) {
    super(name, quantity);
    this.lowerThreshold = lowerThreshold;
  }

  /**
   * Returns true iff the quantity of this InventoryIngredient is below the lower threshold
   *
   * @return boolean statement
   */
  public boolean isLowStock() {
    return this.lowerThreshold < this.getQuantity();
  }
}
