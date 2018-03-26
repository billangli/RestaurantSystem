package frontend.GUI;

import backend.table.Table;
import backend.table.TableManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrintBillController {
  int tableNumber;
  Table table;
  String billInfo;

  @FXML Label tableNumberLabel;
  @FXML Label billToString;
  @FXML Button oneBill, splitBill;

  @FXML
  private void initialize() {
    tableNumberLabel.setText(Integer.toString(tableNumber));
    /* TODO: In backend, get number of customers in table <tableNumber> */
//    table = TableManager.getTable(tableNumber - 1);
    /* ----------------------------------------------------- */
  }

  @FXML
  public void oneBillClicked() {
    billInfo = table.printBill(false);
    billToString.setText(billInfo);
  }

  @FXML
  public void splitBillClicked() {
    billInfo = table.printBill(true);
    billToString.setText(billInfo);
  }
}
