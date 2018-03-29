package frontend.GUI;

import backend.server.Packet;
import frontend.client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * the controller for main GUI
 */
public class StartSceneController {
  @FXML
  private TextField tf;
  private Scene scene;
  private Client client = Client.getInstance();
  @FXML
  private Text actiontarget;

  @FXML
  GridPane tableView = new GridPane();

  /**
   * submit your id and confirm your identity
   *
   * @param event
   * @throws IOException
   */
  @FXML
  protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
    client.connectAgain();
    if (client.isConnected()) {
      SceneFactory factory = new SceneFactory();

      System.out.println(tf.getText());
      String id = tf.getText();
      int type = client.sendLogInRequest(id); //TODO what happens if it fails?
      if (type == Packet.LOGINFAILED) {
        actiontarget.setText("Log in failed");
      } else {
        scene = factory.createScene(type, Integer.parseInt(id));

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
      }
    } else {
      actiontarget.setText("Could not connect to ComputerServer");
    }
  }

  /*
  start up the program
   */
  public void start() {
    tf.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        tf.setText(newValue.replaceAll("[^\\d]", ""));
      }
    });
  }


}