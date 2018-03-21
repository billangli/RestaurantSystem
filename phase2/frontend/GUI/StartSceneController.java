package frontend.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class StartSceneController{
    @FXML private TextField tf;
    private Scene scene;
    @FXML private Text actiontarget;


    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        SceneFactory factory  = new SceneFactory();
        actiontarget.setText("Sign in button pressed");
        //TODO IDidenify(int i)
        //client.send(new ProcessableEvent());
        System.out.println(tf.getText());
        int id = Integer.parseInt(tf.getText());
        scene = factory.createScene("server", id);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }


}