package frontend.GUI_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerStageController {
  @FXML
  private Rectangle rectangle1,
      rectangle2,
      rectangle3,
      rectangle4,
      rectangle5,
      rectangle6,
      rectangle7,
      rectangle8,
      rectangle9,
      rectangle10,
      rectangle11,
      rectangle12;

  @FXML
  private void takeSeatClicked() {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    System.out.println(rectangle1);

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
}
