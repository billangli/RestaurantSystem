package inventory;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import table.Table;

public class Dish {
    private String name;

    private HashMap<String, Integer> ingredientsRequired = new HashMap<>();

    private int cost;
//    private int dishNumber;
    private boolean isReady;
    Table table;

    public Dish(String name, int price, String[] ingredients, String[] Changable){
        this.name = name;
        this.cost = price;
        for(String ingredient: ingredients){
            String[] item = ingredient.split(":");
            ingredientsRequired.put(item[0],Integer.parseInt(item[1]));
        }

        isReady = false;
    }

    // NEED MODIFICATION
    public void adjustIngredient(String ingredientName, int amount){
        ingredientsRequired.put(ingredientName,ingredientsRequired.get(ingredientName)+amount);
    }

    public void updateIngredientsStock(HashMap<String, Object> ingredientsInventory) {
        for (Ingredient ingredient : ingredientsRequired)


        Iterator iter = ingredientsInventory.keySet().iterator();


        for (String ingredient : ingredientsInventory.keySet()) {
            if
        }
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value <= ThresholdInventory.get(key)) {
                // create event to event.txt
                // create text request

            }

    }



    public void setTable(Table t){
        table = t;
    }

    public void ready(){
        isReady = true;
    }

    public void recook(){
        isReady = false;
    }

    public boolean isReady(){
        return isReady;
    }

    public String getName(){
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Table getTable() {
        return table;
    }


    public String toString(){
        return name +"    "+ cost;
    }
}
