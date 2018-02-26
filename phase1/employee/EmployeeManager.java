package employee;

import java.util.ArrayList;

public class EmployeeManager {
  private ArrayList<Cook> cookList;
  private ArrayList<Server> serverList;
  private ArrayList<Receiver> receiverList;

  public EmployeeManager() {
    cookList = new ArrayList<>();
    serverList = new ArrayList<>();
    receiverList = new ArrayList<>();
  }

  public void addCook(String name, int id) {
    cookList.add(new Cook(name, id));
  }

  public void addServer(String name, int id) {
    serverList.add(new Server(name, id));
  }

  public void addReceiver(String name, int id) {
    receiverList.add(new Receiver(name, id));
  }
}
