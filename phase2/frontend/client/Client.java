package frontend.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
  private String ip;
  private int port;
  private Socket socket;
  private BufferedReader input;
  private PrintWriter output;

  Client (String ip, int port) {
    this.ip = ip;
    this.port = port;
    this.connect();
  }

  private void connect() {
    try {
      System.out.println("Attempting connection");

      this.socket = new Socket(this.ip, this.port);
      this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      this.output = new PrintWriter(this.socket.getOutputStream());

      System.out.println("Connection successful");
    } catch (IOException e) {
      e.printStackTrace();
    }

    while(true) {

    }
  }

  // TODO: Remove this after testing
  public static void main(String[] args) {
    Client client = new Client("127.0.0.1", 6000);
  }
}
