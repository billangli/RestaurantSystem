package backend.server;

import backend.RestaurantSystem;
import backend.event.EventManager;
import backend.event.ProcessableEvent;
import backend.inventory.Menu;
import backend.table.TableManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientThread implements Runnable {
  private Socket socket;
  private boolean connected;

  private BufferedReader input;
  private ObjectOutputStream outputStream;

  private boolean loggedOn = false;
  private int employeeID = -1;

  /**
   * Constructor for Client Thread
   *
   * @param socket is the socket connected to the Client
   * @throws IOException for initializing BufferedReader and PrintWriter
   */
  ClientThread(Socket socket) throws IOException {
    this.socket = socket;
    this.connected = true;
    this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());

    Thread thread = new Thread(this);
    thread.start();
  }

  // Receive messages
  @Override
  public void run() {
    System.out.println("Running this client thread");
    while (this.connected) {
      try {
        if (this.input.ready()) {
          String message = this.input.readLine();
          System.out.println("Received " + message);


          // Processing incoming messages from Client
          if (message.substring(0, 1).equals("#")) {
            // Log in request
            int id = Integer.parseInt(message.substring(1));
            this.send(RestaurantSystem.logIn(id));
          } else if (message.substring(0, 1).equals("%")) {
            // This is a request for information from the client
            String request = message.substring(1);
            if (request.equals("table")) {
              this.outputStream.writeObject(TableManager.getNumberOfTables());
            } else if (request.equals("menu")) {
              this.outputStream.writeObject(Menu.getInstance());
            } else if (request.equals("inventory")) {

            }
          } else {
            // Just an event
            EventManager.addEvent(new ProcessableEvent(message));
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        this.connected = false;
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
   * @param message is what is being sent
   */
  void send (String message) {
    System.out.println("Sending \"" + message + "\"");
    try {
      this.outputStream.writeObject(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends a serialized object to the Client
   *
   * @param object is what is being sent
   */
  void sendObject (Object object) {
    System.out.println("Sending " + object.getClass());
    try {
      this.outputStream.writeObject(object);
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
