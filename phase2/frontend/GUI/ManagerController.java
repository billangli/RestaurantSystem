package frontend.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class ManagerController {
    @FXML
    GridPane tableView = new GridPane();

    private int myId;
    public void setmyId(int id){
        myId = id;
    }

    @FXML protected void request(ActionEvent event) {
        //TODO request.txt output

    }@FXML protected void ingredientAmount(ActionEvent event) {
        //TODO inventory to string
    }
}
