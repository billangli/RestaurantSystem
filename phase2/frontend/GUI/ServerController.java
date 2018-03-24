package frontend.GUI;

import frontend.client.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    @FXML GridPane tableView = new GridPane();

    private ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();
    private int selectedTableNumber;
    private int numOfTables;

    public void setmyId(int id){
        myId = id;
    }

    public void setMenuScene(Scene scene) {
        menuScene = scene;
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

        // Retrieve number of tables from backend.
        System.out.println("Requesting table");
        numOfTables = (Integer) client.request("table");
        int sideLength = (int) ceil(sqrt(numOfTables));

        for (int i = 0; i < numOfTables; i++) {
            Rectangle r = new Rectangle(50, 50);
            r.setFill(COLOR_EMPTY);
            r.setStroke(Color.BLACK);
            Label l = new Label(Integer.toString(i + 1));
            rectangleArrayList.add(r);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(r);
            stackPane.getChildren().add(l);
            stackPane.setOnMouseClicked(e -> {
                for(Rectangle rectangle: rectangleArrayList) {
                    rectangle.setStrokeWidth(1);
                    rectangle.setStroke(Color.BLACK);
                }
                r.setStroke(Color.LIGHTBLUE);
                r.setStrokeWidth(3);
                selectedTableNumber = Integer.parseInt(l.getText());
            });

            GridPane.setConstraints(stackPane, i % sideLength, i / sideLength);
            tableGrid.getChildren().add(stackPane);
        }

        gridParent.getChildren().add(tableGrid);

        tableView.setBackground(background);
    }

    @FXML
    protected void takeSeat() {
        //TODO link to server's takeseat method addTable()

        if (this.selectedTableNumber != 0){
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            Parent root;
            try {
                FXMLLoader numLoader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/TakeSeatAlertBox.fxml"));
                Parent scene = numLoader.load();
                window.setTitle("Welcome!");
                window.setScene(new Scene(scene, 300, 200));
                TakeSeatController paneController = numLoader.getController();
                paneController.start();
                paneController.setTableNumber(this.selectedTableNumber);
                window.setMinWidth(300);
                window.setMinHeight(200);
                window.showAndWait();
            } catch (IOException e) {
                System.out.println("take seat error");
            }
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
        if (this.selectedTableNumber != 0){
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/frontend/GUI/ClearTableAlertBox.fxml"));
                Parent root = loader.load();
                window.setScene(new Scene(root, 300, 200));
                ClearTableController controller = loader.getController();

                controller.setTableNumber(this.selectedTableNumber);
                controller.setText(Integer.toString(selectedTableNumber));
                window.setTitle("Welcome!");
                window.setMinWidth(300);
                window.setMinHeight(200);
                window.showAndWait();
            } catch (IOException e) {
                System.out.println("take seat error");
            }
        }
    }

    public void changeColorOfTable(int tableNumber, Color color) {
        if (1 <= tableNumber && tableNumber <= numOfTables) {
            rectangleArrayList.get(tableNumber-1).setFill(color);
        }
    }

}
