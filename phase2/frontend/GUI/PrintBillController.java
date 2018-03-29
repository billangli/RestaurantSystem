package frontend.GUI;

import backend.inventory.Dish;
import backend.server.Packet;
import backend.table.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static frontend.GUI.FXMain.client;

/** the controller for print bill GUI */
public class PrintBillController {
  private int tableNumber; // starts from 1
  ObservableList<Dish> dishes = FXCollections.observableArrayList();

  @FXML Label tableNumberLabel;
  @FXML Label billToString;
  @FXML Button oneBill, splitBill;

  /** initialize the GUI */
  @FXML
  private void initialize() {
    tableNumberLabel.setText("Print Bill for Table: " + Integer.toString(tableNumber));
  }

  /**
   * set which table is looking for the bill
   *
   * @param tableNumber the table that needs the bill
   */
  public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }

  public void updateDishes() {
      //TODO: In backend, get TableManager.getTable(tableNumber-1).getAllDishes()

  }
}
