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


    public Ingredient getIngredient(String ingredientName){
        return ingredientsInventory.get(ingredientName);        
    }


//    public void addQuantity(Ingredient receivedIngredient) {
//        String ingredientName = dishIngredient.getName();
//        int dishIngredientQuantity = dishIngredient.getQuantity();
//        int ingredientStock = ingredientsInventory.get(ingredientName).getQuantity();
//
//        int updatedQuantity = ingredientStock + dishIngredientQuantity;
//
//        ingredientsInventory.get(ingredientName).setQuantity(updatedQuantity);
//    }
//
//    public void reduceQuantity(Ingredient dishIngredient) {
//        String ingredientName = dishIngredient.getName();
//        int dishIngredientQuantity = dishIngredient.getQuantity();
//        int ingredientStock = ingredientsInventory.get(ingredientName).getQuantity();
//
//        int updatedQuantity = ingredientStock - dishIngredientQuantity ;
//
//        ingredientsInventory.get(ingredientName).setQuantity(updatedQuantity);
//    }


}
