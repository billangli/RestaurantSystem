package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerController {
    private Scene menuScene;

    public void setMenuScene(Scene scene) {
        menuScene = scene;
    }

    @FXML
    protected void order(ActionEvent event) {
        //TODO menu
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);
    }
    @FXML protected void bill(ActionEvent event) {
        //TODO print bill
    }
    @FXML protected void deliver(ActionEvent event) {
        //TODO deliver dish
    }
}
