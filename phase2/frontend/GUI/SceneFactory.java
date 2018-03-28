package frontend.GUI;

import backend.server.Packet;
import frontend.client.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


public class SceneFactory {
    public final int WIDTH = 600;
    public final int HEIGHT = 600;
    private Client client = Client.getInstance();

    public SceneFactory() {
    }

    public Scene createScene(int type,int id) throws IOException {
        Scene scene = null;
        if (type == Packet.COOKTYPE) {
            //load cook interface
            FXMLLoader cookLoader = new javafx.fxml.FXMLLoader(this.getClass().getResource("/frontend/GUI/Cook.fxml"));
            Parent cook = cookLoader.load();
            scene = new Scene(cook, WIDTH, HEIGHT);

            CookController paneController = cookLoader.getController();
            client.store("cookController", paneController);
            paneController.setmyId(id);
        }
        else if(type == Packet.SERVERTYPE){
            //load server interface
            FXMLLoader serverLoader = new javafx.fxml.FXMLLoader(this.getClass().getResource("/frontend/GUI/ServerStage.fxml"));
            Parent server = serverLoader.load();
            scene = new Scene(server, WIDTH, HEIGHT);

            //load menu interface
            FXMLLoader menuLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/MenuFx.fxml"));
            Parent menu = menuLoader.load();
            Scene menuScene = new Scene(menu, WIDTH, HEIGHT);


            //injecting menu scene into the controller of the server scene
            ServerController paneController = serverLoader.getController();
            client.store("serverController",paneController);
            paneController.setMenuScene(menuScene);

            //injecting server scene into the controller of the menu scene
            MenuController menuPaneController = menuLoader.getController();

            client.store("menuController", menuPaneController);
        }
        else if(type == Packet.MANAGERTYPE){
            //load manager interface
            FXMLLoader managerLoader = new javafx.fxml.FXMLLoader(this.getClass().getResource("/frontend/GUI/Manager.fxml"));
            Parent manager = managerLoader.load();
            scene = new Scene(manager, WIDTH, HEIGHT);

            ManagerController paneController = managerLoader.getController();
            paneController.setmyId(id);
        }
        return scene;
    }
}
