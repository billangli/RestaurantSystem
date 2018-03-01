package inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Inventory {
    private static HashMap<String, Ingredient> ingredientsInventory = new HashMap<>();
    private static HashMap<String, Ingredient> thresholdInventory = new HashMap<>();



    // read off the menu and just set arbitrary value for each ingredient
    public Inventory(Menu menu) {
        for (Dish dish : menu.Dishes) {
            ingredientsInventory.put(dish.getName(), 10000);

        }

    }
}
