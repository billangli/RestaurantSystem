package inventory;


/**
 * InventoryIngredient represents the Ingredient in inventory
 *
 * InventoryIngredient methods include checking whether the ingredient in the
 * restaurant inventory is below the lower threshold
 */
public class InventoryIngredient extends Ingredient{
    private int lowerThreshold;
    private int upperThreshold;

    /**
     *
     *
     * @param name the name of this InventoryIngredient
     * @param quantity the quantity of this InventoryIngredient in stock
     * @param lowerThreshold the lowerThreshold of this InventoryIngredient; if the quantity of this Ingredient goes
     *                       below this lowerThreshold, then a restock order will be made
     * @param upperThreshold the upperThreshold of this InventoryIngredient
     */
    public InventoryIngredient(String name, int quantity, int lowerThreshold, int upperThreshold) {
        super(name, quantity);
        this.lowerThreshold = lowerThreshold;
        this.upperThreshold = upperThreshold;
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
