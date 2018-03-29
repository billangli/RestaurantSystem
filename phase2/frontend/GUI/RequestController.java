
package frontend.GUI;

import backend.server.Packet;
import frontend.client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;


public class RequestController {
    @FXML
    Label requestListLabel;
    private Client client = Client.getInstance();

    @FXML
    private void initialize() {
        ArrayList request = (ArrayList) client.sendRequest(Packet.REQUESTREQUEST);
        requestListLabel.setText("");
        for (Object o : request) {
            String name = (String) o;
            requestListLabel.setText(requestListLabel.getText() + name + ": 20\n");
        }
    }
//  @FXML
//  private void initialize() throws IOException { TODO: CHeck if this is good
//    String fileName = "phase2/request.txt";
//    BufferedReader br = new BufferedReader(new FileReader(fileName));
//
//    requestListLabel.setText("");
//    String line;
//    while ((line = br.readLine()) != null) {
//      requestListLabel.setText(requestListLabel.getText() + "\n" + line);
//    }
//  }
}
