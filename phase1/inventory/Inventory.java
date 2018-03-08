package inventory;

import java.io.*;
import java.util.*;

/**
 * Inventory class represents the inventory of ingredients in the Restaurant.
 *
 * <p>Inventory methods include, but are not limited to, modifying quantity of each ingredient in
 * the inventory, adding Ingredient to the inventory, and creating a String that lists each
 * ingredient in the inventory and its stock.
 */
public class Inventory {
  private static HashMap<String, InventoryIngredient> ingredientsInventory = new HashMap<>();

  /**
   * Returns the Ingredient ingredient stored in the inventory
   *
   * @param ingredientName the name of the Ingredient ingredient
   * @return the Ingredient ingredient that has the matching ingredientName
   */
  public static InventoryIngredient getIngredient(String ingredientName) {
    return ingredientsInventory.get(ingredientName);
  }

  /**
   * Modifies the quantity of the ingredient in the Inventory by quantityUnits; if quantityUnits is
   * negative, then subtract the quantity of the ingredient in the Inventory by quantityUnits
   *
   * @param ingredientName the name of the Ingredient to be added or subtracted
   * @param quantityUnits quantity of the Ingredient to be added or subtracted
   */
  public static void modifyIngredientQuantity(String ingredientName, int quantityUnits) {
    InventoryIngredient stockIngredient = ingredientsInventory.get(ingredientName);
    int newQuantity = stockIngredient.getQuantity() + quantityUnits;
    boolean isAlreadyLow = stockIngredient.isLowStock();
    stockIngredient.setQuantity(newQuantity);

    if (stockIngredient.isLowStock() && !isAlreadyLow) {
      // create a request as text that is to be stored in requests.txt for the manager
      // to cut and paste into n email
      // Default amount to request is 20 units
      // The manager can manually change that amount when creating the email
      BufferedWriter bw;
      try (BufferedReader fileReader = new BufferedReader(new FileReader("phase1/request.txt"))) {
        String myContent = ingredientName + " 20";
        // Specify the file name and path here

        String line = fileReader.readLine();
        String outPut = "";
        while (line != null) {
          outPut += line + "\n";
          line = fileReader.readLine();
        }

        File file = new File("phase1/request.txt");

        /* This logic will make sure that the file
         * gets created if it is not present at the
         * specified location*/
        FileWriter fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
        bw.write(outPut + myContent);
        System.out.println("request updated: " + ingredientName);
        bw.close();

      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  /**
   * Add the InventoryIngredient ingredient to the inventory
   *
   * @param ingredient The InventoryIngredient ingredient to be added to the inventory
   */
  public static void add(InventoryIngredient ingredient) {
    ingredientsInventory.put(ingredient.getName(), ingredient);
  }

  /** Returns the String of list of ingredients and its stock */
  public static void inventoryToString() {

    ArrayList<String> listOfKeys = new ArrayList<>(ingredientsInventory.keySet());

    listOfKeys.sort(String.CASE_INSENSITIVE_ORDER);

    System.out.println("List of ingredients in stock: ");
    for (String ingredientName : listOfKeys) {
      System.out.printf(
          "%-17s %d%n", ingredientName, ingredientsInventory.get(ingredientName).getQuantity());
    }
    System.out.println("\n");
  }
}
