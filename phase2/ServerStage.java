import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerStage extends Stage {
    ServerStage(){
        VBox vbox = new VBox();

        Button menu = new Button("EnterMenu");
        menu.setPrefSize(100,30);
        vbox.getChildren().add(menu);

        Button bill = new Button("PrintBill");
        bill.setPrefSize(100,30);
        vbox.getChildren().add(bill);

        Button deliverDish = new Button("DeliverDish");
        deliverDish.setPrefSize(100,30);
        vbox.getChildren().add(deliverDish);

        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 400);
        this.setTitle("Server");


//        button.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent e) {
//                if ((userTextField.getText() != null && !userTextField.getText().isEmpty())) {
//                    int id = Integer.parseInt(userTextField.getText());
//                    //TODO how to link this id to employee id?
//
//                    button.setText("Success");
//                } else {
//                    button.setText("again");
//                    userTextField.clear();
//                }
//            }
//        });

        this.setScene(scene);
        this.show();
    }
}
