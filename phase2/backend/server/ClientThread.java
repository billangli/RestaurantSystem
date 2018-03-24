package backend.server;

import backend.RestaurantSystem;
import backend.event.EventManager;
import backend.event.ProcessableEvent;
import backend.inventory.Menu;
import backend.table.TableManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientThread implements Runnable {
  private Socket socket;
  private boolean connected;

  private ObjectInputStream input;
  private ObjectOutputStream output;

  private boolean loggedOn = false;
  private int employeeID = -1;

  /**
   * Constructor for Client Thread
   *
   * @param socket is the socket connected to the Client
   */
  ClientThread(Socket socket) throws IOException {
    this.socket = socket;
    this.connected = true;
    this.output = new ObjectOutputStream(socket.getOutputStream());
    this.input = new ObjectInputStream(socket.getInputStream());

    Thread thread = new Thread(this);
    thread.start();
  }

  // Receive messages
  @Override
  public void run() {
    System.out.println("Running this client thread");
    while (this.connected) {
      try {
        Object object = this.input.readObject();

        if (object != null) {
          Packet packet = (Packet) object;
          System.out.println("Received packet type " + packet.getType());

          if (packet.getType() == Packet.LOGINREQUEST) {
            // Log in request
            int id = (Integer) packet.getObject();
            this.send(Packet.LOGINCONFIRMATION, RestaurantSystem.logIn(id));
          } else if (packet.getType() == Packet.REQUESTNUMBEROFTABLES) {
            System.out.println("Sending number of tables");
            this.send(Packet.RECEIVENUMBEROFTABLES, TableManager.getNumberOfTables());
          } else if (packet.getType() == Packet.REQUESTMENU) {
            System.out.println("Sending menu");
            Menu menu = Menu.getInstance();
            menu.readFromFile();
            this.send(Packet.RECEIVEMENU, menu.getDishes());
          } else if (packet.getType() == Packet.REQUESTINVENTORY) {
            System.out.println("Sending inventory");
            // TODO: Send the inventory
          } else if (packet.getType() == Packet.EVENT) {
            // Just an event
            EventManager.addEvent(new ProcessableEvent((String) packet.getObject()));
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        this.connected = false;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    System.out.println("This client thread is closing");
    try {
      this.socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send a string to the client
   *
   * @param type   is the type of the message
   * @param object is what is being sent
   */
  void send(int type, Object object) {
    System.out.println("Sending \"" + object + "\"");

    Packet packet = new Packet(type, object);
    try {
      this.output.writeObject(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends a serialized object to the Client
   *
   * @param object is what is being sent
   */
  void sendObject(Object object) {
    System.out.println("Sending " + object.getClass());
    try {
      this.output.writeObject(object);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Getter for isLoggedOn
   *
   * @return if this user has logged on
   */
  boolean isLoggedOn() {
    return loggedOn;
  }

  /**
   * Getter for employeeID
   *
   * @return the employee's ID
   */
  public int getEmployeeID() {
    return employeeID;
  }
}
