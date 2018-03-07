package inventory;

/**
 * Ingredient class represents ingredients that are in the restaurant inventory, ingredient received
 * by a receiver, and also the ingredients required for each Dish. Different ingredient objects will
 * be used to represent inventory ingredient and dish ingredient, since the latter will have a lower
 * and upper threshold that limit the level of adjustment allowed for customization by the
 * customers, represented by the thresholdQuantity; thresholdQuantity[0] represents the lower
 * threshold and the thresholdQuantity[1]. The inventory ingredient will also have lower and upper
 * threshold, the lower threshold representing the quantity of ingredient below which will require a
 * restock order be made for the said ingredient. The upper threshold for the inventory ingredient
 * is the recommended maximum quantity for the ingredient set by the restaurant according to the
 * projected amount used for the given time.
 *
 * <p>Ingredient methods include, but are not limited to, indicating whether the ingredient in the
 * inventory is below the threshold and requires a restock order, indicating whether the given
 * quantity requested by a customer to modify the Dish is acceptable as per the limit set for this
 * ingredient used for this Dish.
 */
public class Ingredient {
  private String name;
  private int quantity;
  private int[] thresholdQuantity;
  //    private boolean lowStock; //not needed

  /**
   * Constructor for creating Ingredient that was received by the receiver from reStock order
   *
   * @param name the name of this Ingredient
   * @param quantity the quantity of this Ingredient
   */
  public Ingredient(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  /**
   * Constructor for creating Ingredient in the inventory, or the Ingredient in the Dish, including
   * both the default dish from the menu and customizable dish made to order for the customer
   *
   * @param name the name of this Ingredient
   * @param quantity the quantity of this Ingredient
   * @param thresholdQuantity the lower and upper threshold for the Ingredient
   */
  public Ingredient(String name, int quantity, int[] thresholdQuantity) {
    this.name = name;
    this.quantity = quantity;
    this.thresholdQuantity = thresholdQuantity;
  }

  /**
   * Return the quantity of this Ingredient.
   *
   * @return the quantity of this Ingredient.
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   * Set the quantity of this Ingredient.
   *
   * @param quantityUnit the quantity that must be set for this Ingredient
   */
  public void setQuantity(int quantityUnit) {
    this.quantity = quantityUnit;
  }

  /**
   * Return the name of this Ingredient
   *
   * @return the name of this Ingredient
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns true iff the given suggested new quantity for this Ingredient is within the acceptable
   * range according to its lower and upper threshold; this method is reserved for the Ingredient
   * objects in Dish objects that are made to order (not the Dish objects in the menu, which need
   * not customization)
   *
   * @param n the suggested quantity for this Ingredient
   * @param in the Ingredient whose quantity we are trying to modify
   * @return boolean statement
   */
  public boolean allowed(int n, Ingredient in) {
    if (n > thresholdQuantity[0] && n < thresholdQuantity[1] && in.getQuantity() >= n) {
      return true;
    }
    return false;
  }

  /**
   * Returns true iff the quantity of this Ingredient below the lower threshold; this method is
   * reserved for Ingredient objects in the inventory of the restaurant.
   *
   * @return boolean statement
   */
  public boolean isLowStock() {
    if (this.quantity < this.thresholdQuantity[0]) {
      return true;
    } else {
      return false;
    }
  }
}
