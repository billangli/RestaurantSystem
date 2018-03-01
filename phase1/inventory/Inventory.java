package inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Inventory {
    private static HashMap<String, Integer> ingredientsInventory = new HashMap<>();
    private static HashMap<String, Integer> thresholdInventory = new HashMap<>();



    // read off the menu and just set arbitrary value for each ingredient
    public Inventory(Menu menu) {
        for (Dish dish : menu.Dishes) {
            ingredientsInventory.put(dish.getName(), 10000);

        }

    }

    public void alertLowIngredient() {
        Iterator iter = ingredientsInventory.keySet().iterator();

        for (String ingredient: ingredientsInventory.keySet()) {
            if (ingredientsInventory.get(ingredient) < thresholdInventory.get(ingredient)) {
                // create event to event.txt
                // create text request
            }
        }




        for (Map.Entry<String, Integer> entry : IngredientsInventory.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value <= ThresholdInventory.get(key)) {
                // create event to event.txt
                // create text request

            }




//        while (iter.hasNext()) {
//            Map.Entry pair = (Map.Entry) iter.next();
//            if (pair.getValue() <= ThresholdInventory.get(pair)) {


        }
    }




}
