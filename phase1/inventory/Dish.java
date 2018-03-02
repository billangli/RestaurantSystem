package inventory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import table.Table;

public class Dish {
    private String name;
    private HashMap<String, Ingredient> ingredientsRequired = new HashMap<String, Ingredient>();

  private int cost;
  //    private int dishNumber;
  private boolean isReady;
  Table table;

    public Dish(String name, int price, String[] ingredients){
        this.name = name;
        this.cost = price;
        for(String ingredient: ingredients){
            String[] item = ingredient.split(":");
            int[] limit = {Integer.parseInt(item[2]),Integer.parseInt(item[3])};
            Ingredient in = new Ingredient(item[0],Integer.parseInt(item[1]),limit);
            ingredientsRequired.put(item[0],in);
        }

    isReady = false;
  }

  public Dish(Dish d){
        this.name = d.getName();
        this.cost = d.getCost();
        this.ingredientsRequired = d.ingredientsRequired;
        isReady = false;
  }

    // NEED MODIFICATION
    public void adjustIngredient(String ingredientName, int amount){
        if(ingredientsRequired.get(ingredientName).allowed(amount, Inventory.getIngredient(ingredientName))){
            ingredientsRequired.get(ingredientName).adjust(amount);
        }
    }

    public void updateIngredientsStock(int amount) {
        for (String ingredientName : ingredientsRequired.keySet()) {
            Inventory.modifyIngredientQuantity(ingredientName, amount);
            Ingredient in = Inventory.getIngredient(ingredientName);
            if(in.isLowStock()){
                BufferedWriter bw = null;
                try {
                    String mycontent = ingredientName+" 20";
                    //Specify the file name and path here
                    File file = new File("phase/request.txt");

                    /* This logic will make sure that the file
                     * gets created if it is not present at the
                     * specified location*/


                    FileWriter fw = new FileWriter(file);
                    bw = new BufferedWriter(fw);
                    bw.write(mycontent);
                    System.out.println("File written Successfully");

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
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
