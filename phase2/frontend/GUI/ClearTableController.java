package frontend.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ClearTableController {
  private int tableNumber;
  @FXML Button yesButton, noButton;
  @FXML Label tableNumberLabel;

  public void setTableNumber(int t) {
    this.tableNumber = t;
  }
  public void setText(String s) {
      tableNumberLabel.setText(s);
  }

  @FXML
  private void yesButtonClicked() {
    // TODO: clear table in the backend.
    // In backend, clearTable(Table table) should be called where <table> has table number of
    // <tableNumber>.

    // close the window
    ((Stage) yesButton.getScene().getWindow()).close();
  }

  @FXML
  private void noButtonClicked() {
    // close the window
    ((Stage) noButton.getScene().getWindow()).close();
  }
}
