package backend.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ClientThread implements Runnable {
  private Socket socket;
  private boolean isRunning;
  private BufferedReader input;

  ClientThread(Socket socket) throws IOException {
    this.socket = socket;
    this.isRunning = true;
    this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

    this.run();
  }

  @Override
  public void run() {
    System.out.println("Running this client thread");
    while (this.isRunning) {
      try {
        if (this.input.ready()) {
          System.out.println(this.input.readLine());
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
}
