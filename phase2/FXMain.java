import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMain extends Application {
  Stage stage;
  Scene scene1, scene2;
  final String TITLE = "Welcome to Four Guys restaurant system";
  final int WIDTH = 800;
  final int HEIGHT = 600;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    scene1 = new Scene(root, WIDTH, HEIGHT);
    primaryStage.setTitle(TITLE);
    primaryStage.setScene(scene1);
    primaryStage.show();
  }
}
