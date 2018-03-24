package frontend.client;

import java.io.*;
import java.net.Socket;


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
  private PrintWriter output;

  private boolean objectIsReady = false;
  private Object object;


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
      this.input = new ObjectInputStream(this.socket.getInputStream());
      this.output = new PrintWriter(this.socket.getOutputStream());
    } catch (IOException ioe) {
      System.err.println("Error connecting to server");
      return false;
    }

    System.out.println("Connection successful");
    return true;
  }

  public void send(String message) {
    if (this.connected) {
      System.out.println("Sending " + message);
      this.output.println(message);
      this.output.flush();
    }
  }

  @Override
  public void run() {
    while (this.connected) {
      try {
        this.object = this.input.readObject();
        if (object != null) {

          if (!this.loggedIn) {
            // Receive log in feedback
            // Do something with the input from the server
            String message = (String) object;
            System.out.println(message);

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
          } else if (!this.numberOfTablesReceived) {
            this.objectIsReady = true;
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
    this.send("#" + id);

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to log in request...");
    while (!this.objectIsReady) {
    }

    // Return the employee type to the GUI
    System.out.println("Employee Type is ready");
    this.objectIsReady = false;
    return (String) this.object;
  }

  public Object request(String requestType) {
    this.send("%" + requestType);

    // Waiting for Server to respond
    System.out.println("Waiting for ComputerServer to respond to request...");
    while (!this.objectIsReady) {
    }

    // Return the employee type to the GUI
    System.out.println("Employee Type is ready");
    this.objectIsReady = false;
    return this.object;
  }

  // TODO: Remove this after testing
  public static void main(String[] args) throws IOException {
    Client client = Client.getInstance();
    client.send("Manager;6;checkInventory;()");
    client.send("Server;1;takeSeat;(1)");
    client.send("Server;1;enterMenu;(1,(hamburger)|(hamburger:lettuce+2_tomato-1))");
    client.send("Server;1;enterMenu;(1,(chicken nuggets(L)))");
  }
}
