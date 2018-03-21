package frontend.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CookController {
    @FXML
    GridPane tableView = new GridPane();

    @FXML
    HBox personal = new HBox();

    private int myId;
    public void setmyId(int id){
        myId = id;
    }

    public void initialize(){
        for(int i = 0; i < 6; i++){
            Label item = new Label("nothing");
            item.setId("" + i);
            tableView.add(item, 0, i);
        }
        personal.setSpacing(20);
        personal.getChildren().add(new Text("nothing"));
        personal.getChildren().add(new Button("take"));
    }
}
