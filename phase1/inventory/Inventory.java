package inventory;

import java.io.*;
import java.util.*;

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
        boolean isAlreadyLow = stockIngredient.isLowStock();
        stockIngredient.setQuantity(newQuantity);

        if (stockIngredient.isLowStock() && !isAlreadyLow){
            // create a request as text that is to be stored in requests.txt for the manager
            // to cut and paste into n email
            // Default amount to request is 20 units
            // The manager can manually change that amount when creating the email
            BufferedWriter bw = null;
            try (BufferedReader fileReader = new BufferedReader(new FileReader("phase1/request.txt"))){
                String mycontent = ingredientName+" 20";
                //Specify the file name and path here
                File file = new File("phase1/request.txt");

                /* This logic will make sure that the file
                 * gets created if it is not present at the
                 * specified location*/
                FileWriter fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                String line = fileReader.readLine();
                while (line != null) {
                    bw.write(line);
                    line = fileReader.readLine();
                }


                bw.write(mycontent);
                System.out.println("request updated: " + ingredientName);
                bw.close();

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }


    // would this method not also work for removing
    public static void add(Ingredient ingredient){
        ingredientsInventory.put(ingredient.getName(),ingredient);
    }

    public String toString(){
        ArrayList<String> listOfKeys = new ArrayList<>();

        for (String name : ingredientsInventory.keySet()) {
            listOfKeys.add(name);
        }

        Collections.sort(listOfKeys, String.CASE_INSENSITIVE_ORDER);

        String output = "List of ingredients in stock: \n";
        for (String ingredientName : listOfKeys) {
            output += "ingredientName: " + ingredientsInventory.get(ingredientName) + "\n";
        }

        return output;

    }


}
