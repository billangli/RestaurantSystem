package backend.server;

import backend.logger.RestaurantLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * ComputerServer.java
 * Created by Bill Ang Li
 *
 * This is the class run in the back end that is responsible for communicating with the clients
 */
public class ComputerServer implements Runnable {
  // Defining variables

  // Singleton instance
  private static ComputerServer instance = new ComputerServer();

  // Logger
  private static Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  // Client-server variables
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
   * This runs a loop listening for new connections and adds the connections to the connectionPool
   */
  @Override
  public void run() {
    // Starting the Computer Server
    logger.info("Starting the ComputerServer");
    try {
      this.serverSocket = new ServerSocket(PORT);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Start the loop to listen for new connections while it is still running
    while (this.isRunning) {
      try {
        // Accepting the connection and adding to the existing client threads
        Socket connectionSocket = serverSocket.accept();
        logger.info("accepted");
        ClientThread clientThread = new ClientThread(connectionSocket);
        this.clients.add(clientThread);
      } catch (SocketException se) {
        System.err.println("*** serverSocket has closed ***");
      } catch (IOException e) {
        System.err.println("*** Error connecting client to server ***");
        e.printStackTrace();
      }
    }

    // Closing the socket when the ComputerServer is meant to be shut down
    logger.warning("This server is closing");
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
   * @param message     is the message
   */
  public void broadcast(int messageType, Object message) {
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
   * @param messageType  is the type of message being sent (protocol defined in Packet.java)
   * @param message      is the message being sent
   */
  public void broadcast(int employeeType, int messageType, Object message) {
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
   * @param employeeType is the employee type
   * @param messageType  is the type of message being sent (protocol defined in Packet.java)
   */
  public void broadcast(int employeeType, int messageType) {
    for (ClientThread client : clients) {
      if (client.isLoggedOn()) {
        if (client.getEmployeeType() == employeeType) {
          logger.info("Broadcasting to " + client);
          client.send(messageType);
        }
      }
    }
  }

  /**
   * Tell all clients that the Server is shutting down
   */
  public void shutDown() {
    // Telling clientThreads to shut down
    for (ClientThread client : clients) {
      logger.info("ComputerServer shutting down");
      client.send(Packet.SERVERSHUTDOWN);
      client.shutDown();
    }

    // Closing sockets
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.isRunning = false;
  }

  // TODO: Remove client thread
}
