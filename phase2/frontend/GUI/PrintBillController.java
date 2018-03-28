package frontend.GUI;

import backend.server.Packet;
import backend.table.Table;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static frontend.GUI.FXMain.client;

public class PrintBillController {
  private int tableNumber;
  private Table table;
  private String billInfo;

  @FXML
  Label tableNumberLabel;
  @FXML
  Label billToString;
  @FXML
  Button oneBill, splitBill;

  @FXML
  private void initialize() {
    tableNumberLabel.setText("Print Bill for Table: " + Integer.toString(tableNumber));
    /* TODO: In backend, get table with table number <tableNumber> */

  }

  @FXML
  public void oneBillClicked() {
    if (table == null) {
      table = (Table) client.sendRequest(Packet.REQUESTTABLE, tableNumber - 1);
    }
    billInfo = table.printBill(false);
    billToString.setText(billInfo);
  }

  @FXML
  public void splitBillClicked() {
    if (table == null) {
      table = (Table) client.sendRequest(Packet.REQUESTTABLE, tableNumber - 1);
    }
    billInfo = table.printBill(true);
    billToString.setText(billInfo);
  }

  public void setTableNumber (int tableNumber){
    this.tableNumber = tableNumber;
  }
}
