package inventory;

import java.util.HashMap;
        import table.Table;

public class Dish {
    private String name;
//    private HashMap<Ingredient, Integer> ingredients;
    private HashMap<String, Integer> ingredientsInventory = new HashMap<>();
    private int cost;
//    private int dishNumber;
    private boolean isReady;
    Table table;

    public Dish(String name, int price, String[] ingredients, String[] Changable){
        this.name = name;
        cost = price;
        for(String in: ingredients){
            String[] item = in.split(":");
            ingredientsInventory.put(item[0],Integer.parseInt(item[1]));
        }

        isReady = false;
    }

    public void adjustIngredient(String in, int amount){
        ingredientsInventory.put(in,ingredientsInventory.get(in)+amount);
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
