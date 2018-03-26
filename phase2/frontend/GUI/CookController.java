package frontend.GUI;

import backend.employee.OrderQueue;
import backend.employee.ServiceEmployee;
import backend.inventory.Dish;
import backend.table.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

import java.util.LinkedList;

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
  private int numOfOrdersInQueue;

  public void setmyId(int id) {
    myId = id;
  }

  @FXML
  private void initialize() {
    updateDishesOnTableView();
  }

  private ObservableList<Dish> getDishesInProgress() {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();

    /* TODO: In backend, get ServiceEmployee.getOrderQueue().DishesInProgress */
    LinkedList<Dish> dishesInProgress = ServiceEmployee.getOrderQueue().getDishesInProgress();
    /* ------------------------------------------------------------------------------------------ */

    dishes.addAll(dishesInProgress);

    return dishes;
  }

  private ObservableList<Dish> getDishesInFirstOrderQueue() {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();

    /* TODO: In backend, get ServiceEmployee.getOrderQueue().ordersInQueue  */
    LinkedList<Order> ordersInQueue = ServiceEmployee.getOrderQueue().getOrdersInQueue();
    /* ------------------------------------------------------------------------------------------ */

    numOfOrdersInQueue = ordersInQueue.size();
    numOfOrderLabel.setText(Integer.toString(numOfOrdersInQueue));

    if (!ordersInQueue.isEmpty()) {
      dishes.addAll(ordersInQueue.get(0).getDishes());
    }

    return dishes;
  }

  private void updateDishesOnTableView() {
    tableViewDishesInProgress.setItems(getDishesInProgress());
    tableViewDishesInQueue.setItems(getDishesInFirstOrderQueue());
  }

  @FXML
  private void getOrderButtonClicked() {
    /* TODO: In backend, cookObject.orderReceived() has to be called */
    /* (cookObject).orderReceived(); */
    /* ------------------------------------------------------------------------------------------ */

    updateDishesOnTableView();
  }

  @FXML
  private void finishedButtonClicked() {
    Dish selectedDish = tableViewDishesInProgress.getSelectionModel().getSelectedItem();
    int dishNumber;

    if (selectedDish != null) {
      dishNumber = selectedDish.getDishNumber();
    }

    /* TODO: In backend, cookObject.dishReady(dishNumber) has to be called */
    /* (cookObject).dishReady(dishNumber); */
    /* ------------------------------------------------------------------------------------------ */

    updateDishesOnTableView();
  }
}
