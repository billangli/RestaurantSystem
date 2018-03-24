package backend.server;

import backend.RestaurantSystem;
import backend.event.EventManager;
import backend.event.ProcessableEvent;

import java.io.*;
import java.net.Socket;

class ClientThread implements Runnable {
  private Socket socket;
  private boolean isRunning;

  private BufferedReader input;
  private PrintWriter output;
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
    this.isRunning = true;
    this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    this.output = new PrintWriter(this.socket.getOutputStream());
//    this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());

    Thread thread = new Thread(this);
    thread.start();
  }

  // Receive messages
  @Override
  public void run() {
    System.out.println("Running this client thread");
    while (this.isRunning) {
      try {
        if (this.input.ready()) {
          String message = this.input.readLine();
          System.out.println("Received " + message);

          // Processing incoming messages from Client
          if (message.substring(0, 1).equals("#")) {
            // Log in request
            int id = Integer.parseInt(message.substring(1));
            this.send(RestaurantSystem.logIn(id));
          } else {
            EventManager.addEvent(new ProcessableEvent(message));
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        this.isRunning = false;
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
    this.output.println(message);
    this.output.flush();
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
