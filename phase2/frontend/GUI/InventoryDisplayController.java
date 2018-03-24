package frontend.GUI;

import backend.inventory.Inventory;
import backend.inventory.InventoryIngredient;
import frontend.client.Client;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.HashMap;

public class InventoryDisplayController {
    @FXML private GridPane tableView;
    private Client client = Client.getInstance();
    volatile HashMap<String, InventoryIngredient> defaultInventory = (HashMap<String, InventoryIngredient>) client.request("inventory"); //TODO should get menu from web ComputerServer requestMenu()
    Inventory inventory = Inventory.getInstance();

    public void initialize(){
        inventory.setStock(defaultInventory);
        HashMap<String,InventoryIngredient> ingredients = inventory.getIngredientsInventory();
        int i = 0;
        for(String in: ingredients.keySet()){
            tableView.add(new Text(in),0,i);
            tableView.add(new Text(""+ingredients.get(in).getQuantity()),1, i);
            i++;
        }
    }
}
