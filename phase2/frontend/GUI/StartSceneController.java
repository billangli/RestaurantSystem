package frontend.GUI;

import frontend.client.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class StartSceneController{
    @FXML private TextField tf;
    private Scene scene;
    public Client client = Client.getInstance();
    @FXML private Text actiontarget;

    @FXML
    GridPane tableView = new GridPane();


    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        SceneFactory factory  = new SceneFactory();
        actiontarget.setText("Sign in button pressed");
        System.out.println(tf.getText());
        String id = tf.getText();
        int type = client.sendLogInRequest(id); //TODO what happens if it fails?
        scene = factory.createScene(type, Integer.parseInt(id));

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void start(){
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }


}