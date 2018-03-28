package frontend.GUI;

import backend.inventory.Dish;
import backend.server.Packet;
import backend.table.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import frontend.client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.LinkedList;

public class DishesNotDeliveredController {
  private Client client = Client.getInstance();

  @FXML private TableView tableView;

  public void updateDishesOnTableView() {
    tableView.setItems(getDishesInProgress());
    tableView.setItems(getDishesInFirstOrderQueue());
  }

  @FXML private void refreshButtonClicked() {
      updateDishesOnTableView();
  }

  @FXML
  private void initialize() {
    updateDishesOnTableView();
  }

  private ObservableList<Dish> getDishesInProgress() {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();

    LinkedList<Dish> dishesInProgress =
        (LinkedList<Dish>) client.sendRequest(Packet.REQUESTDISHESINPROGRESS);

    dishes.addAll(dishesInProgress);

    return dishes;
  }

  private ObservableList<Dish> getDishesInFirstOrderQueue() {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();

    LinkedList<Order> ordersInQueue =
        (LinkedList<Order>) client.sendRequest(Packet.REQUESTORDERSINQUEUE);

    if (!ordersInQueue.isEmpty()) {
      dishes.addAll(ordersInQueue.get(0).getDishes());
    }

    return dishes;
  }
}
