package frontend.client;

import backend.inventory.Dish;
import backend.inventory.DishIngredient;
import backend.server.Packet;
import backend.table.Order;
import frontend.GUI.CookController;
import frontend.GUI.MenuController;
import frontend.GUI.ServerController;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Client.java
 * Created by Bill Ang Li
 * <p>
 * This class is responsible for communicating with the backend, requesting and receiving resources required by GUI
 */
public class Client implements Runnable {
  // Resources required for client-server communication
  private static final String IP = "127.0.0.1";
  private static final int PORT = 6000;
  private Socket socket;
  private boolean connected;
  private boolean loggedIn = false;
  private int employeeType;
  private ObjectInputStream input;
  private ObjectOutputStream output;
  private volatile boolean requestedPacketReceived = false; // The packet request has been received
  private volatile boolean otherUpdate = false; // Some other client updated something
  private volatile Object object;

  // Creating the singleton instance of this class
  private static Client instance = new Client();
  private HashMap<String, Object> stored = new HashMap<>();


  private Client() {
    this.connectAgain();
  }

  public static Client getInstance() {
    return instance;
  }

  /**
   * Connect this Client to the ComputerServer
   */
  private boolean connect() {
    try {
      this.socket = new Socket(IP, PORT);
      this.output = new ObjectOutputStream(this.socket.getOutputStream());
      this.input = new ObjectInputStream(this.socket.getInputStream());
    } catch (IOException ioe) {
      System.err.println("Error connecting to server");
      return false;
    }

    System.out.println("Connection successful");
    return true;
  }

  private void send(int type, Object object) {
    if (this.connected) {
      System.out.println("Sending " + type + " " + object);

      if (type == Packet.DISCONNECT) {
        this.connected = false;
      }

      try {
        Packet packet = new Packet(type, object);
        this.output.writeObject(packet);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void send(int type) {
    if (this.connected) {
      System.out.println("Sending " + type);

      if (type == Packet.DISCONNECT) {
        this.connected = false;
      }

      try {
        Packet packet = new Packet(type);
        this.output.writeObject(packet);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void run() {
    while (this.connected) {
      try {
        this.object = this.input.readObject();
        if (object != null) {
          Packet packet = (Packet) object;
          System.out.println("Packet type: " + packet.getType());

          if (packet.getType() == Packet.LOGINCONFIRMATION) {
            // Confirm log in or not
            confirmLogIn((int) packet.getItem());
          } else if (packet.isUpdateType()) {
            System.out.println("Received " + packet.getItem());

            if (otherUpdate) {
              if (packet.getType() == Packet.SERVERSHUTDOWN) {
                this.shutDown();
              } else if (packet.getType() == Packet.RECEIVEDISHESINPROGRESS) {
                LinkedList<Dish> dishesInProgress = (LinkedList<Dish>) packet.getItem();
                CookController cookController = (CookController) stored.get("cookController");
                cookController.updateDishesInProgressOnTableView(dishesInProgress);
              } else if (packet.getType() == Packet.RECEIVEORDERSINQUEUE) {
                LinkedList<Order> ordersInQueue = (LinkedList<Order>) packet.getItem();
                CookController cookController = (CookController) stored.get("cookController");
                cookController.updateOrdersInQueueOnTableView(ordersInQueue);
              } else if (packet.getType() == Packet.RECEIVEDISHESCOMPLETED) {
                LinkedList<Dish> dishesCompleted = (LinkedList<Dish>) packet.getItem();
                if (this.employeeType == Packet.SERVERTYPE) {
                  ServerController serverController = (ServerController) stored.get("serverController");
                  serverController.updateTableView(dishesCompleted);
                }
              } else if (packet.getType() == Packet.RECEIVERUNNINGQUANTITYADJUSTMENT) {
                HashMap newDisplayQuantity = (HashMap) packet.getItem();
                if (this.employeeType == Packet.SERVERTYPE) {
                  MenuController menuController = (MenuController) stored.get("menuController");
                  menuController.updateRunningQuantity(newDisplayQuantity);
                }
              } else if (packet.getType() == Packet.RECEIVETABLEOCCUPANCY) {
                ServerController serverController = (ServerController) stored.get("serverController");
                serverController.updateTableColor((ArrayList) packet.getItem());
              }
            } else {
              System.out.println("The object is ready");
              this.requestedPacketReceived = true;
            }
          } else if (Math.abs(packet.getType()) <= 12) { // TODO: Move to Packet
            // Receive resource protocol
            System.out.println("The object is ready");
            this.requestedPacketReceived = true;
          } else {
            System.out.println("*** Packet type invalid ***");
          }
        }
      } catch (IOException e) {
        System.out.println("*** IO Exception ***");
//        this.shutDown();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

    // Closing streams and socket
    try {
      this.input.close();
      this.output.close();
      this.socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Methods for GUI to call
  public int sendLogInRequest(String id) {
    this.send(Packet.LOGINREQUEST, Integer.parseInt(id));

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to log in request...");
    while (!this.requestedPacketReceived) ;

    // Return the employee type to the GUI
    System.out.println("Employee Type is ready");
    this.requestedPacketReceived = false;
    int employeeType = (int) ((Packet) this.object).getItem();
    if (employeeType != Packet.LOGINFAILED) {
      this.employeeType = employeeType;
    }
    return employeeType;
  }

  private void confirmLogIn(int confirmation) {
    switch (confirmation) {
      case Packet.LOGINFAILED:
        this.requestedPacketReceived = true;
        this.loggedIn = false;
        break;
      default:
        this.requestedPacketReceived = true;
        this.loggedIn = true;
        break;
    }
  }

  public Object sendRequest(int requestType) {
    this.otherUpdate = false;
    this.send(requestType);

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to request...");
    while (!this.requestedPacketReceived) ;

    // Return the employee type to the GUI
    System.out.println("Object is ready");
    this.requestedPacketReceived = false;
    System.out.println("Received " + ((Packet) this.object).getItem());
    this.otherUpdate = true;
    return ((Packet) this.object).getItem();
  }

  public Object sendRequest(int requestType, Object message) {

    this.otherUpdate = false;
    this.send(requestType, message);

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to request" + requestType + "...");
    while (!this.requestedPacketReceived) ;

    // Return the employee type to the GUI
    System.out.println("Object is ready");
    this.requestedPacketReceived = false;
    System.out.println("Received " + ((Packet) this.object).getItem());
    this.otherUpdate = true;
    return ((Packet) this.object).getItem();
  }

  public void sendAdjustIngredientRequest(ArrayList<DishIngredient> dishIngredients, boolean shouldSubtractQuantity) {
    this.otherUpdate = false;
    this.send(Packet.ADJUSTINGREDIENT, new Object[]{dishIngredients, shouldSubtractQuantity});

    // Waiting for the Server to respond
    System.out.println("Waiting for ComputerServer to respond to ingredient adjustment...");
    while (!this.requestedPacketReceived) ;

    this.requestedPacketReceived = false;
    Packet packet = (Packet) this.object;

    System.out.println("Received " + packet.getItem());

    HashMap newDisplayQuantity = (HashMap) packet.getItem();
    MenuController menuController = (MenuController) stored.get("menuController");
    menuController.updateRunningQuantity(newDisplayQuantity);
    this.otherUpdate = true;
  }

  public void sendAdjustIngredientRequest(DishIngredient ingredient, int quantity) {
    this.otherUpdate = false;
    this.send(Packet.ADJUSTINDIVIDUALINGREDIENT, new Object[]{ingredient, quantity});

    // Waiting for the Server to respond
    System.out.println("Waiting for ComputerServer to respond to ingredient adjustment...");
    while (!this.requestedPacketReceived) ;

    this.requestedPacketReceived = false;
    Packet packet = (Packet) this.object;

    System.out.println("Received " + packet.getItem());

    HashMap newDisplayQuantity = (HashMap) packet.getItem();
    MenuController menuController = (MenuController) stored.get("menuController");
    menuController.updateRunningQuantity(newDisplayQuantity);
    this.otherUpdate = true;
  }

  public void sendEvent(int methodName, ArrayList parameters) {
    Packet packet = new Packet(methodName, parameters);
    this.send(methodName, parameters);
  }

  public void sendEvent(int methodName, Object parameter) {
    ArrayList<Object> parameters = new ArrayList<>();
    parameters.add(parameter);
    Packet packet = new Packet(methodName, parameters);
    this.send(methodName, parameters); //TODO packet not used
  }

  public void sendEvent(int methodName) {
    Packet packet = new Packet(methodName);
    this.send(methodName);
  }

  public void store(String name, Object o) {
    stored.put(name, o);
  }

  public Object getStored(String name) {
    return stored.get(name);
  }

  public boolean isConnected() {
    return connected;
  }

  private void shutDown() {
    System.out.println("~~~ Shutting down in 3 seconds ~~~");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Setting logged in and shut down to be false
    this.connected = false;
    this.loggedIn = false;

    // Closing everything when server shuts down
    try {
      this.input.close();
      this.output.close();
      this.socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Shutting down GUI
    Platform.exit();
  }

  public void connectAgain() {
    if (!this.connected) {
      this.connected = this.connect();
      if (this.connected) {
        Thread t = new Thread(this);
        t.start();
      } else {
        System.err.println("Could not connect to server");
      }
    }
  }
}
