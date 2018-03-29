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

      ObservableList<Dish> allDishes = FXCollections.observableArrayList();
      allDishes.addAll(getDishesCompleted());
      allDishes.addAll(getDishesInProgress());
      allDishes.addAll(getDishesInOrderQueue());

      tableView.setItems(allDishes);
  }

  @FXML private void refreshButtonClicked() {
      updateDishesOnTableView();
  }

  @FXML
  private void initialize() {
    updateDishesOnTableView();
  }

    private ObservableList<Dish> getDishesCompleted() {
        ObservableList<Dish> dishes = FXCollections.observableArrayList();

        LinkedList<Dish> dishesInProgress =
                (LinkedList<Dish>) client.sendRequest(Packet.REQUESTDISHESCOMPLETED);

        dishes.addAll(dishesInProgress);

        return dishes;
    }

  private ObservableList<Dish> getDishesInProgress() {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();

    LinkedList<Dish> dishesInProgress =
        (LinkedList<Dish>) client.sendRequest(Packet.REQUESTDISHESINPROGRESS);

    dishes.addAll(dishesInProgress);

    return dishes;
  }

  private ObservableList<Dish> getDishesInOrderQueue() {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();

    LinkedList<Order> ordersInQueue =
        (LinkedList<Order>) client.sendRequest(Packet.REQUESTORDERSINQUEUE);

    for (Order o: ordersInQueue) {
        dishes.addAll(o.getDishes());
    }

    return dishes;
  }
}
