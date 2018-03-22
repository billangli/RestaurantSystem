package backend.inventory;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import backend.logger.RestaurantLogger;

/**
 * Inventory class represents the backend.inventory of ingredients in the Restaurant.
 *
 * <p>Inventory methods include, but are not limited to, modifying quantity of each ingredient in
 * the backend.inventory, adding Ingredient to the backend.inventory, and creating a String that
 * lists each ingredient in the backend.inventory and its stock.
 */
public class Inventory {
  private static HashMap<String, InventoryIngredient> ingredientsInventory = new HashMap<>();
  private static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  /**
   * Returns the Ingredient ingredient stored in the backend.inventory
   *
   * @param ingredientName the name of the Ingredient ingredient
   * @return the Ingredient ingredient that has the matching ingredientName
   */
  public static InventoryIngredient getIngredient(String ingredientName) {
    return ingredientsInventory.get(ingredientName);
  }

  public static void modifyIngredientMirrorQuantity(String ingredientName, int quantityUnits) {
    InventoryIngredient stockIngredient = ingredientsInventory.get(ingredientName);
    stockIngredient.modifyMirrorQuantity(quantityUnits);
  }

  public static boolean isInventoryIngredientEnough(String ingredientName, int quantityUnits) {
    InventoryIngredient stockIngredient = ingredientsInventory.get(ingredientName);
    if (stockIngredient.getMirrorQuantity() > quantityUnits) {
      return true;
    } else {
      return false;
    }

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
    boolean isAlreadyLow = stockIngredient.getIsUnderThreshold();
    stockIngredient.modifyQuantity(quantityUnits);
    boolean isCurrentlyLow = stockIngredient.getIsUnderThreshold();
    // if this InventoryIngredient was not already below the threshold, then
    // execute createRequest
    if (!isAlreadyLow && !isCurrentlyLow) {
      createRequest(ingredientName);
    }
  }

  /**
   * Create a text request to restock the Inventory Ingredient that has the same name as ingredientName
   *
   * @param ingredientName the name of the InventoryIngredient that needs to be requested for
   *     restock
   */
  private static void createRequest(String ingredientName) {
    // create a request as text that is to be stored in requests.txt for the manager
    // to cut and paste into n email
    // Default amount to request is 20 units
    // The manager can manually change that amount when creating the email
    BufferedWriter bw;
    try (BufferedReader fileReader = new BufferedReader(new FileReader("phase1/request.txt"))) {
      String myContent = ingredientName + " 20";
      // Specify the file name and path here

      String line = fileReader.readLine();
      StringBuilder outPut = new StringBuilder();
      while (line != null) {
        outPut.append(line).append("\n");
        line = fileReader.readLine();
      }

      File file = new File("phase1/request.txt");

      /* This logic will make sure that the file
       * gets created if it is not present at the
       * specified location*/
      FileWriter fw = new FileWriter(file);
      bw = new BufferedWriter(fw);
      bw.write(outPut + myContent);
      logger.info("Request updated: " + ingredientName);
      bw.close();

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  /**
   * Adds the InventoryIngredient ingredient to the backend.inventory
   *
   * @param ingredient The InventoryIngredient ingredient to be added to the backend.inventory
   */
  public static void add(InventoryIngredient ingredient) {
    ingredientsInventory.put(ingredient.getName(), ingredient);
  }

  /** Returns the String of list of ingredients and its stock */
  public static void inventoryToString() {

    ArrayList<String> listOfKeys = new ArrayList<>(ingredientsInventory.keySet());

    listOfKeys.sort(String.CASE_INSENSITIVE_ORDER);

    StringBuilder logString = new StringBuilder("List of ingredients in stock: \n");
    for (String ingredientName : listOfKeys) {
      logString.append(
              String.format(
                      "%-17s %d%n",
                      ingredientName, ingredientsInventory.get(ingredientName).getQuantity()));
    }
    logger.info(logString.toString());
  }
}
