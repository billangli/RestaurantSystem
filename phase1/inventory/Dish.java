package inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import table.Table;

public class Dish {
    private String name;
    private Inventory inventory;
    private HashMap<String, Ingredient> ingredientsRequired = new HashMap<String, Ingredient>();

  private int cost;
  //    private int dishNumber;
  private boolean isReady;
  Table table;

    public Dish(String name, int price, String[] ingredients, Inventory inventory){
        this.name = name;
        this.cost = price;
        this.inventory = inventory;
        for(String ingredient: ingredients){
            String[] item = ingredient.split(":");
            int[] limit = {Integer.parseInt(item[2]),Integer.parseInt(item[3])};
            Ingredient in = new Ingredient(item[0],Integer.parseInt(item[1]),limit);
            ingredientsRequired.put(item[0],in);
        }

    isReady = false;
  }

    // NEED MODIFICATION
    public void adjustIngredient(String ingredientName, int amount){
        if(ingredientsRequired.get(ingredientName).allowed(amount, inventory.getIngredient(ingredientName))){
            ingredientsRequired.get(ingredientName).adjust(amount);
        }
    }

    public void updateIngredientsStock(int amount) {
        for (String ingredientName : ingredientsRequired.keySet()) {
            inventory.modifyIngredientQuantity(ingredientName, amount);
        }




//        for (Map.Entry me : ingredientsRequired.entrySet()) {
//            Ingredient item = (Ingredient)(me.getValue());
//            String name = (String)(me.getKey());
//            inventory.getIngredient(name).adjust(item.getQuantity());
//        }

    }

  public void setTable(Table t) {
    table = t;
  }

  public void ready() {
    isReady = true;
  }

  public void recook() {
    isReady = false;
  }

  public boolean isReady() {
    return isReady;
  }

  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  public Table getTable() {
    return table;
  }

  public String toString() {
    return name + ",   $" + cost;
  }

  public void addCostToTable() {
    table.addCost(this);
  }

  public void isCancelled(){
    cost = 0;
  }
}
