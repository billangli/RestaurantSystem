package frontend.GUI_2;

import backend.server.Packet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static frontend.GUI.FXMain.client;
import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class ServerStageController {
  /*final variables*/
  private final Color COLOR_OCCUPIED = Color.BLUE;
  private final Color COLOR_EMPTY = Color.WHITE;

  /*FXML variables*/
  @FXML Button confirmButtonTakeSeat;
  @FXML Button confirmButtonClearTable;
  @FXML Button cancelButton;
  @FXML private ChoiceBox<String> tableNumberBoxTakeSeat;
  @FXML private ChoiceBox<String> tableNumberBoxClearTable;
  @FXML private VBox gridParent;
  @FXML private HBox hBox1;
  @FXML private HBox hBox2;

  private int numOfTables;
  /* Methods */
  @FXML
  private void initialize() {
    int size = 15;
    Rectangle rec1 = new Rectangle(size, size);
    Label label1 = new Label(" : table is occupied");
    Rectangle rec2 = new Rectangle(size, size);
    Label label2 = new Label(" : table is empty");

    rec1.setFill(COLOR_OCCUPIED);
    rec1.setStroke(Color.BLACK);
    rec2.setFill(COLOR_EMPTY);
    rec2.setStroke(Color.BLACK);

    hBox1.getChildren().addAll(rec1, label1);
    hBox2.getChildren().addAll(rec2, label2);
    hBox1.setPadding(new Insets(10, 0, 0, 10));
    hBox2.setPadding(new Insets(10, 0, 0, 10));

    GridPane tableGrid = new GridPane();
    tableGrid.setHgap(10);
    tableGrid.setVgap(8);
    tableGrid.setPadding(new Insets(40, 0, 0, 40));

    // now I will assume that we have certain number of tables.
    numOfTables = (int) client.sendRequest(Packet.REQUESTNUMBEROFTABLES); // TODO: retrieve numOfTable value from backend.
    int sideLength = (int) ceil(sqrt(numOfTables));

    for (int i = 0; i < numOfTables; i++) {
      Rectangle r = new Rectangle(50, 50);
      r.setFill(COLOR_EMPTY);
      r.setStroke(Color.BLACK);
      Label l = new Label(Integer.toString(i + 1));
      GridPane.setConstraints(r, i % sideLength, i / sideLength);
      GridPane.setConstraints(l, i % sideLength, i / sideLength);
      tableGrid.getChildren().addAll(r, l);
    }

    gridParent.getChildren().add(tableGrid);
  }

  @FXML
  private void takeSeatClicked() {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Parent root = null;
    try {
      root = FXMLLoader.load(getClass().getResource("/frontend/GUI_2/TakeSeatAlertBox.fxml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    window.setTitle("Welcome!");
    window.setScene(new Scene(root, 300, 200));
    window.setMinWidth(300);
    window.setMinHeight(200);
    window.showAndWait();
  }

  @FXML
  private void confirmButtonClickedTakeSeat() {
    // TODO 1: change color of table. Data should come from backend.
    // TODO 2: should I use Thread?

    // close the window
    ((Stage) confirmButtonTakeSeat.getScene().getWindow()).close();
  }

  @FXML
  private void confirmButtonClickedClearTable() {
    // TODO 1: change color of table. Data should come from backend.
    // TODO 2: should I use Thread?

    // close the window
    ((Stage) confirmButtonClearTable.getScene().getWindow()).close();
  }

  @FXML
  private void cancelButtonClicked() {
    // close the window
    ((Stage) cancelButton.getScene().getWindow()).close();
  }

  @FXML
  private void clearTableClicked() {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Parent root = null;
    try {
      root = FXMLLoader.load(getClass().getResource("/frontend/GUI_2/ClearTableAlertBox.fxml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    window.setTitle("Welcome!");
    window.setScene(new Scene(root, 300, 200));
    window.setMinWidth(300);
    window.setMinHeight(200);
    window.showAndWait();
  }
}
