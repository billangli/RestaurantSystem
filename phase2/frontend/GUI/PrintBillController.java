package frontend.GUI;

import backend.table.Table;
import backend.table.TableManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrintBillController {
  private int tableNumber;
  private Table table;
  private String billInfo;

  @FXML Label tableNumberLabel;
  @FXML Label billToString;
  @FXML Button oneBill, splitBill;

  @FXML
  private void initialize() {
    tableNumberLabel.setText(Integer.toString(tableNumber));
    /* TODO: In backend, get table with table number <tableNumber> */
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
