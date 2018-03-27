package frontend.GUI;

import backend.inventory.Dish;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class IngredientController {
    @FXML
    GridPane tableView = new GridPane();

    Dish dish;

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
            add.setOnAction(e -> {
                //TODO receive the order, sendOrder()
                dish.adjustIngredient(in,1);
                amount.setText(""+ dish.getIngredientsRequired().get(in).getQuantity());
            });
            tableView.add(add,1,i);
            Button subtract = new Button("subtract");
            subtract.setOnAction(e -> {
                //TODO receive the order, sendOrder()
                dish.adjustIngredient(in,-1);
                amount.setText(""+ dish.getIngredientsRequired().get(in).getQuantity());
            });
            tableView.add(subtract,2,i);
            tableView.add(amount,3,i);

            i++;
        }
    }


    public void initialize(URL url, ResourceBundle rb){


    }
}
