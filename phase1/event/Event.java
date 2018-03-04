/*
 Event.java
 An abstract class to represent an event

 Created by Bill Ang Li
 Feb. 22nd, 2018
*/

package event;

import inventory.Dish;
import inventory.Menu;
import table.Order;

import java.util.ArrayList;
import java.util.StringTokenizer;

class Event {
  private String employeeType;
  private String employeeID;
  private String method;
  private ArrayList<String> parameters = new ArrayList<>();

  /**
   * Constructor that takes a line and uses the information in the line to create the event This
   * uses the parseEvent method
   *
   * @param line is the line (in event.txt) that contains the information
   */
  Event(String line) {
    this.parseEvent(line);
  }

  /**
   * Parses a line (read from event.txt) into an event and returns it
   *
   * @param line is the line to be parsed
   */
  private void parseEvent(String line) {
    StringTokenizer lineTokenizer = new StringTokenizer(line);
    try {
      // Parsing the text file according to our format TODO: Create a format in the README
      this.employeeType = lineTokenizer.nextToken();
      this.employeeID = lineTokenizer.nextToken();
      this.method = lineTokenizer.nextToken();

      // Creating an ArrayList of parameters from one token in the line
      String allParameters = lineTokenizer.nextToken();
      allParameters = allParameters.substring(1, allParameters.length() - 1);
      StringTokenizer parameterTokenizer = new StringTokenizer(allParameters, ",");
      while (parameterTokenizer.hasMoreTokens()) {
        this.parameters.add(parameterTokenizer.nextToken());
      }
    } catch (Exception e) {
      e.printStackTrace(); // TODO: Make my own exception
    }
  }

  /**
   * Reads a str of text and returns the Order
   *
   * @param str is the string to read from
   * @return the Order from that string
   */
  static Order parseOrder(String str) {
    StringTokenizer orderTokenizer = new StringTokenizer(str, "|");
    Order order = new Order();
    while (orderTokenizer.hasMoreTokens()) {
      String dishString = orderTokenizer.nextToken();
      dishString = dishString.substring(1, dishString.length() - 1); // Get rid of the brackets
      Dish dish = parseDish(dishString);
      order.addDish(dish);
    }
    return order;
  }

  /**
   * Reads a str of text and returns the Dish
   *
   * @param str is the string to read from
   * @return the Dish from that string
   */
  private static Dish parseDish(String str) {
    // Parsing the dish name and removing the dish name from str to make a string of ingredient
    // adjustments
    String dishName = str;
    if (str.contains(":")) {
      dishName = str.substring(0, str.indexOf(":"));
    }
    String ingredientAdjustments = str.substring(str.indexOf(":") + 1);
    Dish dish = Menu.makeDish(dishName);

    // Making adjustments to the dish one ingredient at a time
    StringTokenizer adjustmentTokenizer = new StringTokenizer(ingredientAdjustments, "_");
    while (adjustmentTokenizer.hasMoreTokens()) {
      String adjustment = adjustmentTokenizer.nextToken();

      // Checking if add or subtract ingredient
      if (adjustment.contains("+")) {
        // Add more ingredients
        String ingredientName = adjustment.substring(0, adjustment.indexOf("+"));
        int amount = Integer.parseInt(adjustment.substring(adjustment.indexOf("+") + 1));
        dish.adjustIngredient(ingredientName, amount);
      } else if (adjustment.contains("-")) {
        // Subtract ingredients
        String ingredientName = adjustment.substring(0, adjustment.indexOf("-"));
        int amount = Integer.parseInt(adjustment.substring(adjustment.indexOf("-") + 1));
        dish.adjustIngredient(ingredientName, (-1) * amount);
      } else {
        System.out.println("Dish adjustment invalid");
      }
    }

    return dish;
  }

  /**
   * Getter for the employeeType
   *
   * @return the employeeType
   */
  public String getEmployeeType() {
    return employeeType;
  }

  /**
   * Getter for employeeID
   *
   * @return the employeeID as an integer
   */
  public int getEmployeeID() {
    return Integer.parseInt(this.employeeID);
  }

  /**
   * Getter for method
   *
   * @return the method
   */
  public String getMethod() {
    return method;
  }

  /**
   * Getter for the parameters
   *
   * @return the ArrayList parameters
   */
  public ArrayList<String> getParameters() {
    return parameters;
  }

  // Test for Event
  public static void main(String[] args) {
    Event event = new Event("EmployeeManager 1 deliverOrderFailed (A13,B42,C23)");
    System.out.println(event.employeeType);
    System.out.println(event.employeeID);
    System.out.println(event.method);
    System.out.println("Parameters:");
    for (int i = 0; i < event.parameters.size(); i++) {
      System.out.println(event.parameters.get(i));
    }
  }
}
