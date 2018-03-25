package frontend.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrintBillController {
  int tableNumber;
  String billInfo;

  @FXML Label tableNumberLabel;
  @FXML Label billToString;

  @FXML
  private void initialize() {
    tableNumberLabel.setText(Integer.toString(tableNumber));
  }
}
