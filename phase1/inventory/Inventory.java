package inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Inventory {
    private static HashMap<String, Integer> IngredientsInventory = new HashMap<>();
    private static HashMap<String, Integer> ThresholdInventory = new HashMap<>();



    // read off the menu and just set arbitrary value for each ingredient
    public Inventory(Menu menu) {
        for (Dish dish : menu.Dishes) {
            IngredientsInventory.put(dish.getName(), 10000);

        }

    }

    public void alertLowIngredient() {
        Iterator iter = IngredientsInventory.keySet().iterator();


        for (Map.Entry<String, Integer> entry : IngredientsInventory.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

//        while (iter.hasNext()) {
//            Map.Entry pair = (Map.Entry) iter.next();
//            if (pair.getValue() <= ThresholdInventory.get(pair)) {


        }
    }




}
