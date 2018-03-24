package backend.inventory;

import java.io.*;
import java.util.HashMap;

/**
 * The class represents the menu of this restaurant. It stores all the dishes offered by the
 * restaurant.
 */
public class Menu implements Serializable {
  private static Menu instance = new Menu();

  private HashMap<String, DefaultDish> defaultDishes = new HashMap<>();
  //    private static Inventory backend.inventory;
  //
  //    public Menu(Inventory backend.inventory)throws IOException{
  //        this.backend.inventory = backend.inventory;
  //        create();
  //    }

  private Menu() {
    try {
      this.create();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Menu getInstance() {
    return instance;
  }

  public Object readResolve() {
    return getInstance();
  }

  /**
   * Creates a menu using the provided phase1/menu.txt file
   */
  private void create() throws IOException {
    try (BufferedReader fileReader = new BufferedReader(new FileReader("phase1/menu.txt"))) {

      // Print the lines from f prefaced with the line number,
      // starting at 1.
      String line = fileReader.readLine();
      while (line != null) {
        String[] items = line.split(";");
        String name = items[0];
        float price = Integer.parseInt(items[1]);
        String[] ingredients = items[2].split(",");
        this.defaultDishes.put(name, new Dish(name, price, ingredients));
        line = fileReader.readLine();
      }
    }
  }

  /**
   * Returns a copy of the Dish dish
   *
   * @param name the name of the Dish dish
   * @return the copy of the Dish dish
   */
  public Dish makeDish(String name) {
    DefaultDish dish = instance.defaultDishes.get(name);
    return new Dish(dish);
  }

  public HashMap<String, DefaultDish> getDishes() {
    return instance.defaultDishes;
  }
}
