package inventory;

/** Ingredient class represents ingredients. */
public abstract class Ingredient {
  private String name;
  private int quantity;

  /**
   * Constructor for creating Ingredient with name and quantity of the ingredient
   *
   * @param name the name of this Ingredient
   * @param quantity the quantity of this Ingredient
   */
  public Ingredient(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  /**
   * returns the quantity of this Ingredient.
   *
   * @return the quantity of this Ingredient.
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   * sets the quantity of this Ingredient.
   *
   * @param quantityUnit the quantity that must be set for this Ingredient
   */
  public void setQuantity(int quantityUnit) {
    this.quantity += quantityUnit;
  }

  /**
   * returns the name of this Ingredient
   *
   * @return the name of this Ingredient
   */
  public String getName() {
    return this.name;
  }
}
