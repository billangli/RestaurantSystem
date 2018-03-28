package frontend.GUI;

import backend.inventory.InventoryIngredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class ReceiveItemController {
  @FXML Button confirmButton, cancelButton;
  @FXML TextField tf;
  @FXML ChoiceBox<String> choiceBox;

  private int myId;

  public void setMyId(int myId) {
    this.myId = myId;
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

  @FXML
  private void initialize() {
    ObservableList<String> ingredientNames = FXCollections.observableArrayList();

    // TODO: get Inventory.inventoryIngredient from backend
//    HashMap<String, InventoryIngredient> ingredientHashMap = Inventory.inventoryIngredient
    /* ---------------------------------------------------------- */

    //    ingredientNames.addAll(ingredientHashMap.keySet());
    ingredientNames.addAll("banana", "apple"); // temporary values for testing
    choiceBox.setItems(ingredientNames);
  }

  @FXML
  private void confirmButtonClicked() {
    if (tf.getText().length() > 0 && choiceBox.getValue() != null) {
      String ingredientName = choiceBox.getValue();
      int quantity = Integer.parseInt(tf.getText());

      // TODO: In backend, call (employeeObj).receiveIngredient(ingredientName, quantity)

      // close the window
      ((Stage) confirmButton.getScene().getWindow()).close();
    }
  }

  @FXML
  private void cancelButtonClicked() {
    // close the window
    ((Stage) cancelButton.getScene().getWindow()).close();
  }
}
