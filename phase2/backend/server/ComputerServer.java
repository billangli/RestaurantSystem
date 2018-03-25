package backend.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

// Singleton pattern
public class ComputerServer implements Runnable {
  private static ComputerServer instance = new ComputerServer();

  private Socket socket;
  private ServerSocket serverSocket;
  private ArrayList<ClientThread> clients;
  private ClientThread clientThread;

  private static final int PORT = 6000;
  private boolean isRunning;

  /**
   * ComputerServer constructor
   */
  private ComputerServer() {
    this.isRunning = true;
    this.clients = new ArrayList<>();

    // Starts to listen for new connections
    Thread thread = new Thread(this);
    thread.start();
  }

  /**
   * Return the single instance of this Computer Server
   *
   * @return the static ComputerServer Object
   */
  public static ComputerServer getInstance() {
    return instance;
  }

  /**
   * This runs a loop listening for new connections
   */
  @Override
  public void run() {
    System.out.println("Waiting for client connection...");
    try {
      this.serverSocket = new ServerSocket(PORT);
    } catch (IOException e) {
      e.printStackTrace();
    }

    while (this.isRunning) {
      try {
        // Accepting the connection and adding to the existing client threads
        Socket connectionSocket = serverSocket.accept();
        System.out.println("accepted");
        ClientThread clientThread = new ClientThread(connectionSocket);
        this.clients.add(clientThread);

      } catch (SocketException se) {
        System.err.println("*** serverSocket has closed ***");
      } catch (IOException e) {
        System.err.println("*** Error connecting client to server ***");
        e.printStackTrace();
      }
    }

    System.out.println("This server is closing");
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send a message to the desired employee
   *
   * @param employeeID is the employee's ID
   * @param message    is the message to be delivered
   * @return true if the employee is logged on and message delivered, false if not
   */
  boolean send(int employeeID, int type, String message) {
    boolean messageSent = false;

    for (ClientThread client : clients) { // TODO: A single person can sign on multiple times
      if (client.isLoggedOn()) {
        if (client.getEmployeeID() == employeeID) {
          client.send(type, message);
          messageSent = true;
        }
      }
    }

    return messageSent;
  }

  void broadcast(int type, Object message) {
    for (ClientThread client : clients) {
      if (client.isLoggedOn()) {
        client.send(type, message);
      }
    }
  }
}
