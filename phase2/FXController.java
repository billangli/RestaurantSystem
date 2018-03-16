import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class FXController{
    @FXML private TextField tf;

    @FXML private Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
        String s=tf.getText().toString();
        System.out.println(s);
    }

    @FXML protected void handleNumber(ActionEvent event) {
        String s=tf.getText().toString();
        System.out.println(s);
    }


}