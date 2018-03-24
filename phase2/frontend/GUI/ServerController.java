package frontend.GUI;

import frontend.client.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class ServerController  implements Initializable {
    private Scene menuScene;
    private int myId;
    private final Color COLOR_OCCUPIED = Color.BLUE;
    private final Color COLOR_EMPTY = Color.WHITE;
    public Client client = Client.getInstance();
    @FXML private VBox gridParent;
    @FXML private HBox hBox1;
    @FXML private HBox hBox2;

    private ArrayList<Rectangle> rectangles;
    @FXML
    GridPane tableView = new GridPane();

    public void setmyId(int id){
        myId = id;
    }

    public void setMenuScene(Scene scene) {
        menuScene = scene;
    }

    @FXML
    protected void takeSeat() {
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
//                int tableNumber = Integer.parseInt(tableNum.getText());
//                client.send("Server;"+myId+";takeSeat;("+tableNum+")");
//            }
//        });
        tableView.add(submit,1,5);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Parent root;
        try {
            FXMLLoader numLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/TakeSeatAlertBox.fxml"));
            Parent scene = numLoader.load();
            window.setTitle("Welcome!");
            window.setScene(new Scene(scene, 300, 200));
            NumberKeyBoardController paneController = numLoader.getController();
            paneController.start();
            window.setMinWidth(300);
            window.setMinHeight(200);
            window.showAndWait();
        } catch (IOException e) {
            System.out.println("take seat error");
        }
    }

    @FXML
    protected void order() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/frontend/GUI/MenuFx.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.setTitle("Welcome!");
        window.setScene(new Scene(root, 600, 600));
        window.setMinWidth(300);
        window.setMinHeight(200);
        window.showAndWait();

        //Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //primaryStage.setScene(menuScene);
    }
    @FXML
    protected void bill() {
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
//                int tableNumber = Integer.parseInt(tableNum.getText());
//                client.send("Server;"+myId+";printBill;("+tableNum+")");
//            }
//        });
        tableView.add(submit,1,5);
    }
    @FXML protected void deliver(ActionEvent event) {
        //TODO deliver dish
    }

    @FXML
    protected void clear() {
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
//               client.send("ComputerServer;"+myId+";clear;("+tableNum+")");
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
        int size = 15;
        Rectangle rec1 = new Rectangle(size, size);
        Label label1 = new Label(" : table is occupied");
        Rectangle rec2 = new Rectangle(size, size);
        Label label2 = new Label(" : table is empty");

        rec1.setFill(COLOR_OCCUPIED);
        rec1.setStroke(Color.BLACK);
        rec2.setFill(COLOR_EMPTY);
        rec2.setStroke(Color.BLACK);

        hBox1.getChildren().addAll(rec1, label1);
        hBox2.getChildren().addAll(rec2, label2);
        hBox1.setPadding(new Insets(10, 0, 0, 10));
        hBox2.setPadding(new Insets(10, 0, 0, 10));

        GridPane tableGrid = new GridPane();
        tableGrid.setHgap(10);
        tableGrid.setVgap(8);
        tableGrid.setPadding(new Insets(40, 0, 0, 40));

        // TODO: retrieve numOfTable value from backend.
        // now I will assume that we have certain number of tables.
        System.out.println("Requesting table");
        int numOfTables = (Integer) client.request("table");
        int sideLength = (int) ceil(sqrt(numOfTables));

        for (int i = 0; i < numOfTables; i++) {
            Rectangle r = new Rectangle(50, 50);
            r.setFill(COLOR_EMPTY);
            r.setStroke(Color.BLACK);
            Label l = new Label(Integer.toString(i + 1));
            r.setId(""+ i+1);
            GridPane.setConstraints(r, i % sideLength, i / sideLength);
            GridPane.setConstraints(l, i % sideLength, i / sideLength);
            tableGrid.getChildren().addAll(r, l);
        }

        gridParent.getChildren().add(tableGrid);

        tableView.setBackground(background);
    }
}
