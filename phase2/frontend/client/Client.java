package frontend.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


// Singleton pattern
public class Client implements Runnable {
  private static Client instance = new Client();

  private static final String IP = "127.0.0.1";
  private static final int PORT = 6000;

  private Socket socket;
  private boolean isConnected = false;

  private BufferedReader input;
  private PrintWriter output;

  private boolean typeIsReady = false;
  private String employeeType;


  private Client() {
    this.isConnected = this.connect();
    if (this.isConnected) {
      Thread t = new Thread(this);
      t.start();
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
      this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      this.output = new PrintWriter(this.socket.getOutputStream());
    } catch (IOException ioe) {
      System.err.println("Error connecting to server");
      return false;
    }

    System.out.println("Connection successful");
    return true;
  }

  public void send(String message) {
    if (this.isConnected) {
      System.out.println("Sending " + message);
      this.output.println(message);
      this.output.flush();
    }
  }

  @Override
  public void run() {
    while (this.isConnected) {
      try {
        if (this.input.ready()) {
          // Do something with the input from the server
          String message = this.input.readLine();
          System.out.println(message);

          // Log in
          switch (message) {
            case "Cook log in successful":
              this.employeeType = "Cook";
              this.typeIsReady = true;
              break;
            case "Manager log in successful":
              this.employeeType = "Manager";
              this.typeIsReady = true;
              break;
            case "Server log in successful":
              this.employeeType = "Server";
              this.typeIsReady = true;
              break;
            case "Log in failed":
              this.employeeType = "Failed";
              this.typeIsReady = true;
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        this.isConnected = false;
      }
    }
  }

  // Methods for GUI to call
  public String logIn(int id) {
    this.send("#" + Integer.toString(id));

    // Waiting for Server to respond
    while (!this.typeIsReady) {
      ;
    }

    this.typeIsReady = false;
    this.typeIsReady = false;
    return this.employeeType;
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
