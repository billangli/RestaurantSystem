package backend.server;

import backend.logger.RestaurantLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Logger;

// Singleton pattern
public class ComputerServer implements Runnable {
  private static ComputerServer instance = new ComputerServer();

  private Socket socket;
  private ServerSocket serverSocket;
  private ArrayList<ClientThread> clients;
  private ClientThread clientThread;

  private static final int PORT = 6000;
  private boolean isRunning;

  private static Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

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
   */
  void send(int employeeID, int type, String message) {
    for (ClientThread client : clients) { // TODO: A single person can sign on multiple times
      if (client.isLoggedOn()) {
        if (client.getEmployeeID() == employeeID) {
          client.send(type, message);
        }
      }
    }
  }

  /**
   * Sending a message to every client that is logged on
   *
   * @param messageType is the type of message being sent (protocol defined in Packet.java)
   * @param message is the message
   */
  void broadcast(int messageType, Object message) {
    logger.info("Broadcasting to all employees");
    for (ClientThread client : clients) {
      if (client.isLoggedOn()) {
        client.send(messageType, message);
      }
    }
  }

  /**
   * Sending a message to every client that is logged on and has the given employee type
   *
   * @param employeeType is the employee type
   * @param messageType is the type of message being sent (protocol defined in Packet.java)
   * @param message is the message being sent
   */
  void broadcast(int employeeType, int messageType, Object message) {
    logger.info("Broadcasting to employee type " + employeeType);
    for (ClientThread client : clients) {
      if (client.isLoggedOn()) {
        if (client.getEmployeeType() == employeeType) {
          client.send(messageType, message);
        }
      }
    }
  }

  /**
   * Broadcast by employee type
   *
   * @param employeeType is
   * @param type
   * @param message
   */
  void broadcast(String employeeType, int type, Object message) {
    for (ClientThread client : clients) {
      if (client.isLoggedOn()) {
        System.out.println("Broadcasting to " + client);
        client.send(type, message);
      }
    }
  }
}
