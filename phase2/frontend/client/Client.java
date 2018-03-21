package frontend.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
  private String ip;
  private int port;
  private Socket socket;
  private boolean isConnected = false;

  private BufferedReader input;
  private PrintWriter output;

  Client(String ip, int port) throws IOException {
    this.ip = ip;
    this.port = port;
    this.isConnected = this.connect();
    Thread t = new Thread(this);
    t.start();
  }

  /**
   * Connect this Client to the ComputerServer
   */
  private boolean connect() throws IOException {
    this.socket = new Socket(this.ip, this.port);
    this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    this.output = new PrintWriter(this.socket.getOutputStream());

    System.out.println("Connection successful");
    return true;
  }

  void send(String message) {
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
          System.out.println(this.input.readLine());
        }
      } catch (IOException e) {
        e.printStackTrace();
        this.isConnected = false;
      }
    }
  }

  // TODO: Remove this after testing
  public static void main(String[] args) throws IOException {
    Client client = new Client("127.0.0.1", 6000);
    client.send("Manager;6;checkInventory;()");
    client.send("Server;1;takeSeat;(1)");
    client.send("Server;1;enterMenu;(1,(hamburger)|(hamburger:lettuce+2_tomato-1))");
    client.send("Server;1;enterMenu;(1,(chicken nuggets(L)))");
  }
}
