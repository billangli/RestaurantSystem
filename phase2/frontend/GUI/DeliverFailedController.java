package frontend.GUI;

import backend.server.Packet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static frontend.GUI.FXMain.client;

public class DeliverFailedController {
  @FXML private TextField tf;
  @FXML private Button confirmButton, cancelButton;
  private int tableNumber;

  @FXML
  private void confirmButtonClicked() {
      if (tf.getText().length() > 0) {
          int dishNumber = Integer.parseInt(tf.getText());

          client.sendEvent(Packet.DELIVERDISHFAILED, dishNumber);
          // (serverObject).deliverDishFailed(dishNumber)
          /* ------------------------------------------------------------------------------------------ */

          // close the window
          ((Stage) cancelButton.getScene().getWindow()).close();
      }
  }

  @FXML
  private void cancelButtonClicked() {
    // close the window
    ((Stage) cancelButton.getScene().getWindow()).close();
  }

  public void start() {
    tf.textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
              }
            });
  }

  public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }
}
