package backend.server;

import javafx.beans.InvalidationListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Server implements Observer {
  private Socket socket;
  private ServerSocket serverSocket;
  private ArrayList<Object> clients;
  private ClientThread clientThread;

  private int port;
  private boolean isListening;

  @Override
  public void update(Observable o, Object arg) {

  }
}
