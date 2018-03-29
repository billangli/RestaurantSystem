package frontend.GUI;

import backend.server.Packet;
import frontend.client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ClearTableController {
  @FXML Button yesButton, noButton;
  @FXML Label tableNumberLabel;
  private int tableNumber;
  ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();
  private int myId;

  public void setMyId(int id) {
    this.myId = id;
  }

    public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }
  public void setText(String s) {
      tableNumberLabel.setText(s);
  }

  private Client client = Client.getInstance();

  @FXML
  private void yesButtonClicked() {
    // In backend, clearTable(Table table) should be called where <table> has table number of
    // <tableNumber>.

    client.sendEvent(Packet.CLEARTABLE, tableNumber);
//      (serverObj).clearTable(TableManager.getTable(tableNumber-1));
    /* ------------------------------------------------------------------------------------------ */
    // close the window
    ((Stage) yesButton.getScene().getWindow()).close();
  }

  @FXML
  private void noButtonClicked() {
    // close the window
    ((Stage) noButton.getScene().getWindow()).close();
  }
}
