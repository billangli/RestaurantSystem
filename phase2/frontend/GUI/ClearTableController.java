package frontend.GUI;

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
  int tableNumber;
  private final Color COLOR_EMPTY = Color.WHITE;
  ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();

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

    rectangleArrayList.get(tableNumber-1).setFill(COLOR_EMPTY);

    // close the window
    ((Stage) yesButton.getScene().getWindow()).close();
  }

  @FXML
  private void noButtonClicked() {
    // close the window
    ((Stage) noButton.getScene().getWindow()).close();
  }
}
