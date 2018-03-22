package frontend.GUI;

import backend.inventory.Menu;
import frontend.client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMain extends Application {
  Stage stage;
  Scene mainScene, serverScene, menuScene, cookScene, managerScene;
  final String TITLE = "Welcome to Four Guys restaurant system";
  public final int WIDTH = 600;
  public final int HEIGHT = 600;
  public Client client = Client.getInstance();

  //public static Client client = New Client()

  public static void main(String[] args)  throws IOException {

    Menu.create();
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    //load starter interface
    FXMLLoader startLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/Start.fxml"));
    GridPane startScene = startLoader.load();
    mainScene = new Scene(startScene, WIDTH, HEIGHT);
    BackgroundImage mainImage= new BackgroundImage(new Image("hp.jpg",600,600,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    startScene.setBackground(new Background(mainImage));

    StartSceneController paneController = startLoader.getController();
    paneController.start();


    primaryStage.setTitle(TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }
}
