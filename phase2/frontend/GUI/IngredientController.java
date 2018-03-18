package frontend.GUI;

import backend.inventory.Dish;
import backend.inventory.DishIngredient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
        System.out.println(dish.getName());
        int i = 0;
        for(String in: dish.getIngredientsRequired().keySet()){
            Text item = new Text(in);
            item.setId(in);
            tableView.add(item,0,i);
            tableView.add(new Button("add"),1,i);
            tableView.add(new Button("subtract"),2,i);
            i++;
        }
    }


    public void initialize(URL url, ResourceBundle rb){


    }
}
