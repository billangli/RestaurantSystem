package frontend.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
  @FXML GridPane tableView = new GridPane();
  @FXML Button signOut;

  private int myId;

  public void setMyId(int id) {
    myId = id;
  }

  @FXML
  private void request() {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    try {
      FXMLLoader numLoader =
          new FXMLLoader(this.getClass().getResource("/frontend/GUI/Request.fxml"));
      Parent scene = numLoader.load();
      window.setTitle("Ingredient request!");
      window.setScene(new Scene(scene, 600, 600));
      window.showAndWait();
    } catch (IOException e) {
      System.out.println("Request error");
    }
  }

  @FXML
  protected void ingredientAmount() {
    // TODO inventory to string
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    Parent root;
    try {
      FXMLLoader numLoader =
          new FXMLLoader(this.getClass().getResource("/frontend/GUI/InventoryDisplay.fxml"));
      Parent scene = numLoader.load();
      window.setTitle("Welcome!");
      window.setScene(new Scene(scene, 600, 600));
      window.showAndWait();
    } catch (IOException e) {
      System.out.println("Ingredient amount error");
    }
  }

  @FXML
  private void receiveItem() {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    try {
      FXMLLoader loader =
          new FXMLLoader(this.getClass().getResource("/frontend/GUI/ReceiveItem.fxml"));
      Parent root = loader.load();
      window.setTitle("Receive Item");
      window.setScene(new Scene(root, 400, 200));
      ReceiveItemController controller = loader.getController();
      controller.setMyId(myId);
      controller.start();
      window.showAndWait();
    } catch (IOException e) {
      System.out.println("receive item error");
    }
  }

  @FXML
  private void checkDishesNotDelivered() {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    try {
      FXMLLoader loader =
          new FXMLLoader(this.getClass().getResource("/frontend/GUI/DishesNotDelivered.fxml"));
      Parent root = loader.load();
      window.setTitle("Dishes not delivered");
      window.setScene(new Scene(root, 600, 600));
      window.showAndWait();
    } catch (IOException e) {
      System.out.println("receive item error");
    }
  }

  @FXML
  private void logOff() throws IOException {
    FXMLLoader startLoader =
        new FXMLLoader(this.getClass().getResource("/frontend/GUI/Start.fxml"));
    GridPane root = startLoader.load();
    Scene mainScene = new Scene(root, 600, 600);
    BackgroundImage mainImage =
        new BackgroundImage(
            new Image("hp.jpg", 600, 600, false, true),
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    root.setBackground(new Background(mainImage));

    StartSceneController paneController = startLoader.getController();
    paneController.start();

    Stage window = (Stage) (signOut.getScene().getWindow());

    window.setTitle("Welcome to Four Guys Restaurant System");
    window.setScene(mainScene);
    window.show();
  }
}
