package frontend.GUI;

import backend.inventory.Dish;
import backend.inventory.Inventory;
import frontend.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * the controller for ingredients
 */
public class IngredientController {
    @FXML
    GridPane tableView = new GridPane();

    private Dish dish;
    private Inventory inventory = Inventory.getInstance();
    private Client client = Client.getInstance();


    /**
     * generate the ingredient display for this dish
     * @param dish the dish is being used
     */
    public void getDish(Dish dish){
        this.dish = dish;
        int i = 0;
        for(String in: dish.getIngredientsRequired().keySet()){
            Text item = new Text(in);
            item.setId(in);
            tableView.add(item,0,i);
            Button add = new Button("add");
            Text amount = new Text(""+ dish.getIngredientsRequired().get(in).getQuantity());
            amount.setId(in+"Amount");
            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    //TODO receive the order, sendOrder()
                    if(inventory.isInventoryIngredientEnough(in,1)&& dish.ableToAdjustIngredient(in,1)){
                        client.sendAdjustIngredientRequest(dish.getIngredientsRequired().get(in),-1);
                        dish.adjustIngredient(in,1);
                        amount.setText(""+ dish.getIngredientsRequired().get(in).getQuantity());
                    }
                }
            });
            tableView.add(add,1,i);
            Button subtract = new Button("subtract");
            subtract.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    //TODO receive the order, sendOrder()
                    if( dish.ableToAdjustIngredient(in,-1)){
                        client.sendAdjustIngredientRequest(dish.getIngredientsRequired().get(in),1);
                        dish.adjustIngredient(in,-1);
                        amount.setText(""+ dish.getIngredientsRequired().get(in).getQuantity());
                    }
                }
            });
            tableView.add(subtract,2,i);
            tableView.add(amount,3,i);

            i++;
        }
    }
}
