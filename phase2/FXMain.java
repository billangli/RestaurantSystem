import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMain extends Application {
  Stage stage;
  Scene scene1, scene2;
  final String TITLE = "Welcome to Four Guys restaurant system";
  final int WIDTH = 600;
  final int HEIGHT = 600;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    GridPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    scene1 = new Scene(root, WIDTH, HEIGHT);
    BackgroundImage myBI= new BackgroundImage(new Image("hp.jpg",600,600,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    root.setBackground(new Background(myBI));
    primaryStage.setTitle(TITLE);
    primaryStage.setScene(scene1);
    primaryStage.show();
  }
}
