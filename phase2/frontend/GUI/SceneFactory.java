package frontend.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


public class SceneFactory {
    public final int WIDTH = 600;
    public final int HEIGHT = 600;

    public SceneFactory() {
    }

    public Scene createScene(String type) throws IOException {
        Scene scene = null;
        if (type.equals("cook")) {
            //load cook interface
            FXMLLoader cookLoader = new javafx.fxml.FXMLLoader(this.getClass().getResource("/frontend/GUI/Cook.fxml"));
            Parent cook = cookLoader.load();
            scene = new Scene(cook, WIDTH, HEIGHT);
        }
        else if(type.equals("server")){
            //load server interface
            FXMLLoader serverLoader = new javafx.fxml.FXMLLoader(this.getClass().getResource("/frontend/GUI/Server.fxml"));
            Parent server = serverLoader.load();
            scene = new Scene(server, WIDTH, HEIGHT);
            //load menu interface
            FXMLLoader menuLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/MenuFx.fxml"));
            Parent menu = menuLoader.load();
            Scene menuScene = new Scene(menu, WIDTH, HEIGHT);


            //injecting menu scene into the controller of the server scene
            ServerController serverPaneController = serverLoader.getController();
            serverPaneController.setMenuScene(menuScene);

            //injecting server scene into the controller of the menu scene
            MenuController menuPaneController = menuLoader.getController();
            menuPaneController.setServerScene(scene);
        }
        else if(type.equals("manager")){
            //load manager interface
            FXMLLoader managerLoader = new javafx.fxml.FXMLLoader(this.getClass().getResource("/frontend/GUI/Manager.fxml"));
            Parent manager = managerLoader.load();
            scene = new Scene(manager, WIDTH, HEIGHT);
        }
        return scene;
    }
}
