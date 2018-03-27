package frontend.GUI;

import backend.server.Packet;
import frontend.client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TakeSeatController {
    @FXML private TextField tf;
    @FXML private Button confirmButton, cancelButton;
    int tableNumber;
    private final Color COLOR_OCCUPIED = Color.BLUE;
    ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();

    private Client client = Client.getInstance();
    private int myId;

    public void setMyId(int id){
        this.myId = id;
    }

    @FXML private void confirmButtonClicked() {
        if (tf.getText().length()>0) {
            // Change the color of the table into COLOR_OCCUPIED
            rectangleArrayList.get(tableNumber - 1).setFill(COLOR_OCCUPIED);

            ArrayList<Object> parameters = new ArrayList<>();
            parameters.add(tableNumber);
            parameters.add(1); // TODO: SHould be number of customers
            client.sendEvent(Packet.TAKESEAT, parameters);// TODO: In backend, call takeSeat() method (need number of customers)
            //(serverObj).takeSeat(TableManager.getTable(tableNumber - 1), Integer.parseInt(tf.getText()));
            /* ------------------------------------------------------------------------------------------ */

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
