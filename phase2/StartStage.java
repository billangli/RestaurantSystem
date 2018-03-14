import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StartStage extends Stage{
    StartStage(){
        VBox vbox = new VBox();

        Label userName = new Label("User ID:");
        userName.setPrefSize(100,30);

        TextField userTextField = new TextField();
        userTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    userTextField.setText(oldValue);
                }
            }
        });
        userTextField.setPrefSize(100,30);

        HBox userNames = new HBox(userName, userTextField);
        vbox.getChildren().add(userNames);

        Button button = new Button("Log in");
        button.setPrefSize(60,30);
        vbox.getChildren().add(button);

        vbox.setPadding(new Insets(0, 0, 0, 50));
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 400);
        this.setTitle("Welcome to My Kingdom");

        BackgroundImage myBI= new BackgroundImage(new Image("hp.jpg",400,400,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        vbox.setBackground(new Background(myBI));


        this.setScene(scene);
        this.show();
    }

}
