package inventory;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    private HashMap<String, Dish> dishes = new HashMap<>();

    public Menu()throws IOException{
        create();
    }

    public void create()throws IOException{
        try (BufferedReader fileReader = new BufferedReader(new FileReader("menu.txt"))) {

            // Print the lines from f prefaced wiZZth the line number,
            // starting at 1.
            String line = fileReader.readLine();
            while (line != null) {
                String[] items = line.split(":");
                String name = items[0];
                int price = Integer.parseInt(items[1]);
                String[] ingredients = items[2].split(",");
                String[] changable = items[3].split(",");
                dishes.put(name, new Dish(name,price, ingredients, changable));

                }
                line = fileReader.readLine();
            }
    }

    public Dish getDish(String name){
        return dishes.get(name);
    }





}
