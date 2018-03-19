package frontend.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartSceneController{
    @FXML private TextField tf;
    private Scene serverScene, cookScene, managerScene;
    @FXML private Text actiontarget;

    public void setServerScene(Scene scene1, Scene scene2, Scene scene3) {
        serverScene = scene1;
        cookScene = scene2;
        managerScene = scene3;
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
        //TODO IDidenify(int i)
        System.out.println(tf.getText());

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(managerScene);
    }


}