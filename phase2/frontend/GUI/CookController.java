package frontend.GUI;

import backend.inventory.Dish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CookController {
  @FXML HBox hBox;
  @FXML Button getOrderButton;
  @FXML Label numOfOrderLabel;
  private int myId;
  /* table view of dishes in progress */
  @FXML private TableView<Dish> tableViewDishesInProgress = new TableView<>();
  @FXML private TableColumn<Dish, String> nameColumn1;
  @FXML private TableColumn<Dish, Integer> numberColumn1;

  /* table view of dishes to be cooked */
  @FXML private TableView<Dish> tableViewDishesInQueue = new TableView<>();
  @FXML private TableColumn<Dish, String> nameColumn2;
  @FXML private TableColumn<Dish, Integer> numberColumn2;

  public void setmyId(int id) {
    myId = id;
  }

  @FXML
  private void initialize() {
//    // Dish Name Column 1
//    nameColumn1 = new TableColumn<>("Dish name");
//    nameColumn1.setMinWidth(175);
//    nameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//    // Dish Number Column 1
//    numberColumn1 = new TableColumn<>("Dish number");
//    numberColumn1.setMinWidth(125);
//    numberColumn1.setCellValueFactory(new PropertyValueFactory<>("dishNumber"));
//
//    // Dish Name Column 2
//    nameColumn2 = new TableColumn<>("Dish name");
//    nameColumn2.setMinWidth(175);
//    nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//    // Dish Number Column 2
//    numberColumn2 = new TableColumn<>("Dish number");
//    numberColumn2.setMinWidth(125);
//    numberColumn2.setCellValueFactory(new PropertyValueFactory<>("dishNumber"));
//
//    tableViewDishesInProgress = new TableView<>();
    tableViewDishesInProgress.setItems(getDishesInProgress());
//    tableViewDishesInProgress.getColumns().addAll(nameColumn1, numberColumn1);
//
//    tableViewDishesInQueue = new TableView<>();
    tableViewDishesInQueue.setItems(getDishesInFirstOrderQueue());
//    tableViewDishesInQueue.getColumns().addAll(nameColumn2, numberColumn2);
//
//    hBox.getChildren().add(tableViewDishesInProgress);
//    hBox.getChildren().add(tableViewDishesInQueue);
  }

  private ObservableList<Dish> getDishesInProgress() {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();
    // TODO: Retrieve current dishes from backend. Data should be from OrderQueue.DishesInProgress
    dishes.add(new Dish("Dish bbq", 5, new String[0]));

    return dishes;
  }

  private ObservableList<Dish> getDishesInFirstOrderQueue() {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();
    // TODO 1: Retrieve first order in queue, and get dishes in the first order.
    // TODO 2: Get number of orders in queue, and set it on label <numOfOrderLabel>
    dishes.add(new Dish("Dish to be cooked", 6, new String[0]));

    return dishes;
  }

  public void updateDishesInProgress() {
    tableViewDishesInProgress.setItems(getDishesInProgress());
    tableViewDishesInQueue.setItems(getDishesInFirstOrderQueue());
    tableViewDishesInProgress.getColumns().addAll(nameColumn1, numberColumn1);
    tableViewDishesInQueue.getColumns().addAll(nameColumn2, numberColumn2);
  }

  @FXML private void getOrderButtonClicked() {
      ObservableList<Dish> dishes = tableViewDishesInProgress.getItems();
      dishes.addAll(tableViewDishesInQueue.getItems());
      tableViewDishesInProgress.setItems(dishes);
  }

  @FXML private void finishedButtonClicked() {
      Dish dish = tableViewDishesInProgress.getSelectionModel().getSelectedItem();
      //TODO: remove dish in progress queue, and update the tableview
  }
}
