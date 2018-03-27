package frontend.client;

import backend.inventory.DishIngredient;
import backend.inventory.InventoryIngredient;
import backend.server.Packet;
import frontend.GUI.MenuController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


// Singleton pattern
public class
Client implements Runnable {
  private static Client instance = new Client();

  private static final String IP = "127.0.0.1";
  private static final int PORT = 6000;

  private Socket socket;
  private boolean connected = false;
  private boolean loggedIn = false;
  private boolean numberOfTablesReceived = false;

  private ObjectInputStream input;
  private ObjectOutputStream output;

  private volatile boolean objectIsReady = false;
  private volatile Object object;

  private HashMap<String, Object> stored = new HashMap<>();


  private Client() {
    this.connected = this.connect();
    if (this.connected) {
      Thread t = new Thread(this);
      t.start();
    } else {
      System.err.println("Could not connect to server");
    }
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

          if (packet.getType() == Packet.LOGINCONFIRMATION) {
            // Confirm log in or not
            confirmLogIn((int) packet.getObject());
          } else if (Math.abs(packet.getType()) <= 10) {
            // Receive resource protocol
            this.objectIsReady = true;
          } else {
            System.out.println("*** Packet type invalid ***");
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        this.connected = false;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  // Methods for GUI to call
  public int sendLogInRequest(String id) {
    this.send(Packet.LOGINREQUEST, Integer.parseInt(id));

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to log in request...");
    while (!this.objectIsReady) ;

    // Return the employee type to the GUI
    System.out.println("Employee Type is ready");
    this.objectIsReady = false;
    return (int) ((Packet) this.object).getObject();
  }

  private void confirmLogIn(int confirmation) {
    switch (confirmation) {
      case Packet.LOGINFAILED:
        this.loggedIn = false;
        break;
      default:
        this.objectIsReady = true;
        this.loggedIn = true;
        break;
    }
  }

  public Object sendRequest(int requestType) {
    this.send(requestType);

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to request...");
    while (!this.objectIsReady) ;

    // Return the employee type to the GUI
    System.out.println("Object is ready");
    this.objectIsReady = false;
    System.out.println("Received " + ((Packet) this.object).getObject());
    return ((Packet) this.object).getObject();
  }

  public Object sendRequest(int requestType, Object message) {
    this.send(requestType, message);

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to request...");
    while (!this.objectIsReady) ;

    // Return the employee type to the GUI
    System.out.println("Object is ready");
    this.objectIsReady = false;
    System.out.println("Received " + ((Packet) this.object).getObject());
    return ((Packet) this.object).getObject();
  }

  public void sendAdjustIngredientRequest(ArrayList<DishIngredient> dishIngredients, boolean shouldSubtractQuantity) {
    this.send(Packet.ADJUSTINGREDIENT, new Object[]{dishIngredients, shouldSubtractQuantity});

    // Waiting for the Server to respond
    System.out.println("Waiting for ComputerServer to respond to ingredient adjustment...");
    while (!this.objectIsReady) ;

    this.objectIsReady = false;
    Packet packet = (Packet) this.object;

    System.out.println("Received " + packet.getObject());

    ArrayList<InventoryIngredient> newIngredientQuantities = (ArrayList<InventoryIngredient>) packet.getObject();
    MenuController menuController = (MenuController) stored.get("menuController");
    menuController.updateInventory(newIngredientQuantities);
  }

  public void sendEvent(int methodName, ArrayList parameters) {
    Packet packet = new Packet(methodName, parameters);
    this.send(methodName, parameters);
  }

  public void sendEvent(int methodName, Object parameter) {
    ArrayList<Object> parameters = new ArrayList<>();
    parameters.add(parameter);
    Packet packet = new Packet(methodName, parameters);
    this.send(methodName, parameters);
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
}
