package employee;

import table.Order;
import java.util.ArrayList;

public class Cook {
  private Server server;
  private ArrayList listOfReceivedOrders;

  void setServer(Server s) {
    server = s;
  }

  public boolean orderReceived(Order order) {}

  public boolean orderReady(Order order) {}
}
