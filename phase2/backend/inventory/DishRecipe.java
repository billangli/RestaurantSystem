package backend.inventory;

import java.io.Serializable;
import java.util.HashMap;

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
public class DishRecipe implements Serializable{
    protected String name;
    protected HashMap<String, DishIngredient> ingredientsRequired = new HashMap<>();
    //private int dishNumber;
    //private static int countDish = 0;
    protected float cost;
    //private Table table;
    //private boolean isSent;
    //private static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

    /**
     * Constructor that takes the name of the dish, price and the list of names of the ingredients;
     * this constructor is used to create the dishes in the Menu
     *
     * @param dishName is the name of the Dish
     * @param dishPrice is the price of the Dish in dollars
     * @param ingredients is the list of names of the ingredients used for this dish
     */
    public DishRecipe(String dishName, float dishPrice, String[] ingredients) {
        this.name = dishName;
        this.cost = dishPrice;
        //isSent = false;
        for (String ingredient : ingredients) {
            String[] item = ingredient.split(":");
            int[] limit = {Integer.parseInt(item[2]), Integer.parseInt(item[3])};
            DishIngredient in =
                    new DishIngredient(item[0], Integer.parseInt(item[1]), limit[0], limit[1]);
            ingredientsRequired.put(item[0], in);
        }
    }

    public DishRecipe(String dishName, float dishPrice, HashMap<String, DishIngredient> ingredientsRequired) {
        this.name = dishName;
        this.cost = dishPrice;
        //isSent = false;
        for(String name: ingredientsRequired.keySet()){
            this.ingredientsRequired.put(name,new DishIngredient(ingredientsRequired.get(name)));
        }

    }

    /**
     * Returns the name of this DishRecipe
     *
     * @return the name of this DishRecipe
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the cost of the DishRecipe
     *
     * @return the cost of the dish
     */
    public float getCost() {
        return cost;
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

    /**
     * Return the HashMap of string of the dish name and the DishIngredient dish ingredient for this dish
     *
     * @return Return the HashMap of string of the dish name and the DishIngredient dish ingredient for this dish
     */
    public HashMap<String, DishIngredient> getIngredientsRequired() {
        return ingredientsRequired;
    }
}
