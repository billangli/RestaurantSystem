package frontend.GUI;

import backend.inventory.Inventory;
import backend.inventory.InventoryIngredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

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
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Parent root;
        try {
            FXMLLoader numLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/InventoryDisplay.fxml"));
            Parent scene = numLoader.load();
            window.setTitle("Welcome!");
            window.setScene(new Scene(scene, 600, 600));
            window.setMinWidth(300);
            window.setMinHeight(200);
            window.showAndWait();
        } catch (IOException e) {
            System.out.println("request inventory error");
        }

    }
}
