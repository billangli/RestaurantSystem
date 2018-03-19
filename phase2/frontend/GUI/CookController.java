package frontend.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CookController {
    @FXML
    GridPane tableView = new GridPane();
    public void initialize(){
        for(int i = 0; i < 6; i++){
            Label item = new Label("nothing");
            item.setId("" + i);
            tableView.add(item, 0, i);
        }
    }
}
