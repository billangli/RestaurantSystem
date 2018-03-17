package GUI;

import GUI.StartSceneController;
import inventory.Menu;
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
  Scene mainScene, serverScene, menuScene;
  final String TITLE = "Welcome to Four Guys restaurant system";
  final int WIDTH = 600;
  final int HEIGHT = 600;

  public static void main(String[] args)  throws IOException {

    Menu.create();
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    //load starter interface
    FXMLLoader startLoader = new FXMLLoader(this.getClass().getResource("/GUI/Start.fxml"));
    GridPane startScene = startLoader.load();
    mainScene = new Scene(startScene, WIDTH, HEIGHT);
    BackgroundImage myBI= new BackgroundImage(new Image("hp.jpg",600,600,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    //load server interface
    FXMLLoader serverLoader = new FXMLLoader(this.getClass().getResource("/GUI/Server.fxml"));
    Parent server = serverLoader.load();
    serverScene = new Scene(server, WIDTH, HEIGHT);

    //load menu interface
    Parent Menu = FXMLLoader.load(getClass().getResource("/GUI/MenuFx.fxml"));
    menuScene = new Scene(Menu, WIDTH, HEIGHT);

    //injecting server scene into the controller of the start scene
    StartSceneController startController = startLoader.getController();
    startController.setServerScene(serverScene);

    //injecting server scene into the controller of the start scene
    ServerController serverPaneController = serverLoader.getController();
    serverPaneController.setMenuScene(menuScene);



    startScene.setBackground(new Background(myBI));
    primaryStage.setTitle(TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }
}
