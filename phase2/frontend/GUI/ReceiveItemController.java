package frontend.GUI;

import backend.server.Packet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

import static frontend.GUI.FXMain.client;

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


    HashMap ingredientHashMap = (HashMap) client.sendRequest(Packet.REQUESTINVENTORY);
    /* ---------------------------------------------------------- */
    ArrayList<String> ingredientNamesArrayList = new ArrayList<>();
    for (Object object : ingredientHashMap.keySet()) {
      ingredientNamesArrayList.add((String) object);
    }


    ingredientNames.addAll(ingredientNamesArrayList);
//    ingredientNames.addAll("banana", "apple"); // temporary values for testing
    choiceBox.setItems(ingredientNames);
  }

  @FXML
  private void confirmButtonClicked() {
    if (tf.getText().length() > 0 && choiceBox.getValue() != null) {
      String ingredientName = choiceBox.getValue();
      int quantity = Integer.parseInt(tf.getText());

      // TODO: In backend, call (employeeObj).receiveIngredient(ingredientName, quantity)

      ArrayList<Object> info = new ArrayList<Object>();
      info.add(ingredientName);
      info.add(quantity);
      client.sendRequest(Packet.RECEIVEINGREDIENT, info);

      //TODO: when this button is clicked, Manager's check inventory GUI should be updated
        //TODO: when dishes are ordered(it's not in this controller), Manager's check inventory GUI should be updated

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
