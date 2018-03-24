package frontend.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class TakeSeatController {
    @FXML private javafx.scene.control.TextField tf;
    @FXML private Button confirmButton, cancelButton;
    private int tableNumber;

    @FXML private void confirmButtonClicked() {
        // TODO: Should call takeSeat() in backend.
        // the parameter for takeSeat() method
        // @param: tableNumber
        // @param: Server

        // close the window
        ((Stage) confirmButton.getScene().getWindow()).close();
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
