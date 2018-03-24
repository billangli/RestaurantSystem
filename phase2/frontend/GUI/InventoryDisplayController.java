package frontend.GUI;

import backend.inventory.Inventory;
import backend.inventory.InventoryIngredient;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.HashMap;

public class InventoryDisplayController {
    @FXML private GridPane tableView;

    public void initialize(){
        HashMap<String,InventoryIngredient> ingredients = Inventory.getIngredientsInventory();
        int i = 0;
        for(String in: ingredients.keySet()){
            System.out.println("WHAT???");
            tableView.add(new Text(in),0,i);
            tableView.add(new Text(""+ingredients.get(in).getQuantity()),1, i);
        }
    }
}
