package frontend.GUI;

import backend.inventory.Menu;
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
  Scene mainScene, serverScene, menuScene, cookScene;
  final String TITLE = "Welcome to Four Guys restaurant system";
  public final int WIDTH = 600;
  public final int HEIGHT = 600;

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

    //load server interface
    FXMLLoader serverLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/Server.fxml"));
    Parent server = serverLoader.load();
    serverScene = new Scene(server, WIDTH, HEIGHT);

    //load menu interface
    FXMLLoader menuLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/MenuFx.fxml"));
    Parent menu = menuLoader.load();
    menuScene = new Scene(menu, WIDTH, HEIGHT);

    //load cook interface
    FXMLLoader cookLoader = new javafx.fxml.FXMLLoader(this.getClass().getResource("/frontend/GUI/Cook.fxml"));
    Parent cook = cookLoader.load();
    cookScene = new Scene(cook, WIDTH, HEIGHT);

    //injecting server,cook scene into the controller of the start scene
    StartSceneController startController = startLoader.getController();
    startController.setServerScene(serverScene,cookScene);

    //injecting menu scene into the controller of the server scene
    ServerController serverPaneController = serverLoader.getController();
    serverPaneController.setMenuScene(menuScene);

    //injecting menu scene into the controller of the server scene
    MenuController menuPaneController = menuLoader.getController();
    menuPaneController.setServerScene(serverScene);




    primaryStage.setTitle(TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }
}
