package frontend.GUI;

import frontend.client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class TakeSeatController {
    @FXML private TextField tf;
    @FXML private Button confirmButton, cancelButton;
    int tableNumber;
    private final Color COLOR_OCCUPIED = Color.BLUE;
    ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();

    private Client client = Client.getInstance();

    @FXML private void confirmButtonClicked() {
        if (tf.getText().length()>0) {
            // Change the color of the table into COLOR_OCCUPIED
            rectangleArrayList.get(tableNumber - 1).setFill(COLOR_OCCUPIED);
            //client.seatSeat(tableNumber-1, tf.getText()); //TODO creates it

            // close the window
            ((Stage) confirmButton.getScene().getWindow()).close();
        }
    }
    @FXML private void cancelButtonClicked() {
        // close the window
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
    public void start(){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    public void setTableNumber(int t) {
        tableNumber = t;
    }

}
