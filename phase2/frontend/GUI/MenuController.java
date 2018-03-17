package frontend.GUI;

import backend.inventory.Dish;
import backend.inventory.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MenuController  implements Initializable {
    @FXML
    GridPane tableView = new GridPane();
    int numoforder = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        HashMap<String, Dish> dishes = Menu.getDishes(); //TODO should get menu from web Server
        int i = 0;
        for(String di: dishes.keySet()){
            Button item = new Button(di);
            item.setId(di);
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                   Button ordered = new Button(di);
                   item.setId(""+numoforder);
                   numoforder++;
                   tableView.add(ordered,2,numoforder);
                }
            });
            tableView.add(item,0,i);
            i++;


        }
        Button submit = new Button("order");
        tableView.add(submit,1,5);

    }


}
