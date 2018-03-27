package frontend.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
    @FXML
    GridPane tableView = new GridPane();

    private int myId;
    public void setmyId(int id){
        myId = id;
    }

    @FXML protected void request(ActionEvent event) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Parent root;
        try {
            FXMLLoader numLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/Request.fxml"));
            Parent scene = numLoader.load();
            window.setTitle("Ingredient sendRequest!");
            window.setScene(new Scene(scene, 600, 600));
            window.showAndWait();
        } catch (IOException e) {
            System.out.println("sendRequest error");
        }

    }@FXML protected void ingredientAmount(ActionEvent event) {
        //TODO inventory to string
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Parent root;
        try {
            FXMLLoader numLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/InventoryDisplay.fxml"));
            Parent scene = numLoader.load();
            window.setTitle("Welcome!");
            window.setScene(new Scene(scene, 600, 600));
            window.showAndWait();
        } catch (IOException e) {
            System.out.println("Ingredient amount error");
        }

    }
}
