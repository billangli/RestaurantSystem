package frontend.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RequestController {
  @FXML Label requestListLabel;

  @FXML
  private void initialize() throws IOException {
    String fileName = "phase2/sendResourceRequest.txt";
    BufferedReader br = new BufferedReader(new FileReader(fileName));

    requestListLabel.setText("");
    String line;
    while ((line = br.readLine()) != null) {
        requestListLabel.setText(requestListLabel.getText() + "\n" + line);
    }
  }
}
