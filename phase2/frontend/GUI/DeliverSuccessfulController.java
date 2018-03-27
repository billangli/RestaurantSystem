package frontend.GUI;

import backend.server.Packet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static frontend.GUI.FXMain.client;

public class DeliverSuccessfulController {
  @FXML private TextField tf;
  @FXML private Button confirmButton, cancelButton;
  private int myId;

  @FXML
  private void confirmButtonClicked() {
    if (tf.getText().length() > 0) {
      int dishNumber = Integer.parseInt(tf.getText());

      client.sendEvent(Packet.DELIVERDISHCOMPLETED, dishNumber); // TODO: In backend, (serverObject).deliverDishCompleted(dishNumber) should be called
      // (serverObject).deliverDishCompleted(dishNumber)
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

  public void setMyId(int myId) {
    this.myId = myId;
  }
}
