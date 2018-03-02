package inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Inventory {
    private static HashMap<String, Ingredient> ingredientsInventory = new HashMap<>();;



    // read off the menu and just set arbitrary value for each ingredient
//    public Inventory() {
//        ingredientsInventory = new HashMap<>();
//    }


    public static Ingredient getIngredient(String ingredientName){
        return ingredientsInventory.get(ingredientName);
    }

    public static void modifyIngredientQuantity(String ingredientName, int quantityUnits) {
        Ingredient stockIngredient = ingredientsInventory.get(ingredientName);
        int newQuantity = stockIngredient.getQuantity() + quantityUnits;
        stockIngredient.setQuantity(newQuantity);

        if (stockIngredient.isLowStock()){
            // create a request as text that is to be stored in requests.txt for the manager
            // to cut and paste into n email
            // Default amount to request is 20 units
            // The manager can manually change that amount when creating the email
        }

    }


    // would this method not also work for removing
    public static void add(Ingredient ingredient){
        ingredientsInventory.put(ingredient.getName(),ingredient);
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
