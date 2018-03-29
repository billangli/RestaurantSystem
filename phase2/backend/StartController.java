package backend;

import backend.inventory.Dish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StartController {
    @FXML
    private TableView<String> events = new TableView<>();
    @FXML
    private TableColumn<String,String> event;

    private ObservableList<String> messages = FXCollections.observableArrayList();

    public void receiveMessage(String message){
        messages.add(message);
        events.setItems(messages);
    }
}
