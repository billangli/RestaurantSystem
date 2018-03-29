package frontend.GUI_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginMain extends Application {
  static final int WIDTH = 600;
  static final int HEIGHT = 400;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Parent root = null;
    try {
      root = FXMLLoader.load(getClass().getResource("/frontend/GUI_2/ServerStage.fxml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    primaryStage.setTitle("Welcome!");
    Scene scene = null;
    if (root != null) {
      scene = new Scene(root, WIDTH, HEIGHT);
      System.err.println("Null Pointer Exception: root cannnot be null");
    }
    primaryStage.setScene(scene);
    primaryStage.setMinHeight(HEIGHT);
    primaryStage.setMinWidth(WIDTH);
    primaryStage.show();
  }
}
