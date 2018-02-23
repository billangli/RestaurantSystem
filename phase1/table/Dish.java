package table;

import java.util.HashMap;

public class Dish {
    private String name;
    private HashMap<Ingredient, Integer> ingredients;
    private int cost;
    private boolean isReady;
    Table table;
    public Dish(Table t, String name){
        this.name = name;
        table = t;
        isReady = false;
        ingredients = new HashMap<Ingredient, Integer>();
    }

    public void adjustIngredient(Ingredient in, int amount){
        ingredients.put(in,ingredients.get(in)+amount);
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

}
