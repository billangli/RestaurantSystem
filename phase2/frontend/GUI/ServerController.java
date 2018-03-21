package frontend.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController  implements Initializable {
    private Scene menuScene;
    private int myId;
    @FXML
    GridPane tableView = new GridPane();

    public void setmyId(int id){
        myId = id;
    }

    public void setMenuScene(Scene scene) {
        menuScene = scene;
    }

    @FXML
    protected void takeSeat(ActionEvent event) {
        //TODO link to server's takeseat method addTable()
        TextField tableNum = new TextField();
        tableNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tableNum.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        tableView.add(tableNum,0,5);

        //submit the order
        Button submit = new Button("take charge");
//        submit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
//                primaryStage.setScene(serverScene);
//            }
//        });
        tableView.add(submit,1,5);
    }

    @FXML
    protected void order(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(menuScene);
    }
    @FXML protected void bill(ActionEvent event) {
        //TODO print bill base on the table number requestbill()
        TextField tableNum = new TextField();
        tableNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tableNum.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        tableView.add(tableNum,0,5);

        //submit the order
        Button submit = new Button("print bill");
//        submit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
//                primaryStage.setScene(serverScene);
//            }
//        });
        tableView.add(submit,1,5);
    }
    @FXML protected void deliver(ActionEvent event) {
        //TODO deliver dish
    }

    @FXML
    protected void clear(ActionEvent event) {
        //TODO method clear()

        TextField tableNum = new TextField();
        tableNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tableNum.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        tableView.add(tableNum,0,5);

        //submit the order
        Button submit = new Button("clear this table");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(tableNum.getText());
                int tableNumber = Integer.parseInt(tableNum.getText());
//               client.send("Server;"+myId+";clear;("+tableNum+")");
            }
        });
        tableView.add(submit,1,5);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        //set up background
        BackgroundImage menuImage= new BackgroundImage(new Image("server.png",600,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background= new Background(menuImage);
        tableView.setBackground(background);
    }
}
