package frontend.client;

import backend.inventory.DishIngredient;
import backend.server.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


// Singleton pattern
public class Client implements Runnable {
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

  public void send(int type, Object object) {
    if (this.connected) {
      System.out.println("Sending " + object);
      try {
        Packet packet = new Packet(type, object);
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
            confirmLogIn((String) packet.getObject());
          } else if (packet.getType() == Packet.RECEIVENUMBEROFTABLES) {
            this.objectIsReady = true;
          } else if (packet.getType() == Packet.RECEIVEMENU) {
            this.objectIsReady = true;
          } else if (packet.getType() == Packet.RECEIVEINVENTORY) {
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
  public String logIn(String id) {
    this.send(Packet.LOGINREQUEST, Integer.parseInt(id));

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to log in request...");
    while (!this.objectIsReady) {
    }

    // Return the employee type to the GUI
    System.out.println("Employee Type is ready");
    this.objectIsReady = false;
    return (String) ((Packet) this.object).getObject();
  }

  private void confirmLogIn(String message) {
    switch (message) {
      case "Cook log in successful":
        this.objectIsReady = true;
        this.loggedIn = true;
        break;
      case "Manager log in successful":
        this.objectIsReady = true;
        this.loggedIn = true;
        break;
      case "Server log in successful":
        this.objectIsReady = true;
        this.loggedIn = true;
        break;
      case "Log in failed":
        this.objectIsReady = true;
        break;
    }
  }

  public Object request(String requestType) {
    switch (requestType) {
      case "menu":
        this.send(Packet.REQUESTMENU, null);
        break;
      case "inventory":
        this.send(Packet.REQUESTINVENTORY, null);
        break;
      case "table":
        this.send(Packet.REQUESTNUMBEROFTABLES, null);
        break;
      default:
        System.out.println("*** Something broke ***");
        break;
    }

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to request...");
    while (!this.objectIsReady) {
    }

    // Return the employee type to the GUI
    System.out.println("Object is ready");
    this.objectIsReady = false;
    return ((Packet) this.object).getObject();
  }

  public void adjustIngredient(ArrayList<DishIngredient> dishIngredients, boolean shouldSubtractQuantity) {
    this.send(Packet.ADJUSTINGREDIENT, new Object[]{dishIngredients, shouldSubtractQuantity});

    // Waiting for the Server to respond
    System.out.println("Waiting for ComputerServer to respond to ingredient adjustment...");
    while (!this.objectIsReady) {
    }

    //
  }

  // TODO: Remove this after testing
  public static void main(String[] args) throws IOException {
    Client client = Client.getInstance();
    client.send(Packet.EVENT, "Manager;6;checkInventory;()");
    client.send(Packet.EVENT, "Server;1;takeSeat;(1)");
    client.send(Packet.EVENT, "Server;1;enterMenu;(1,(hamburger)|(hamburger:lettuce+2_tomato-1))");
    client.send(Packet.EVENT, "Server;1;enterMenu;(1,(chicken nuggets(L)))");
  }
}
