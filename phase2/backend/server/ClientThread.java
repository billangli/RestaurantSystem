package backend.server;

import backend.RestaurantSystem;
import backend.employee.ServiceEmployee;
import backend.event.EventManager;
import backend.event.ProcessableEvent;
import backend.inventory.*;
import backend.logger.RestaurantLogger;
import backend.table.TableManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import static backend.inventory.Inventory.getInstance;

class ClientThread implements Runnable {
  private Socket socket;
  private boolean connected;

  private ObjectInputStream input;
  private ObjectOutputStream output;

  private boolean loggedOn = false;
  private int employeeID = -1;
  private int employeeType = -1;

  private Inventory inventory = Inventory.getInstance();
  private ComputerServer computerServer = ComputerServer.getInstance();

  private static Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  /**
   * Constructor for Client Thread
   *
   * @param socket is the socket connected to the Client
   */
  ClientThread(Socket socket) throws IOException {
    this.socket = socket;
    this.connected = true;
    this.output = new ObjectOutputStream(socket.getOutputStream());
    this.input = new ObjectInputStream(socket.getInputStream());

    Thread thread = new Thread(this);
    thread.start();
  }

  // Receive messages
  @Override
  public void run() {
    System.out.println("Running this client thread");
    while (this.connected) {
      try {
        Object object = this.input.readObject();

        if (object != null) {
          Packet packet = (Packet) object;
          System.out.println("Received packet type " + packet.getType());

          if (packet.getType() == Packet.LOGINREQUEST) {
            // Log in sendRequest
            int id = (Integer) packet.getObject();
            int logInConfirmation = RestaurantSystem.logIn(id);
            if (logInConfirmation != Packet.LOGINFAILED) {
              this.loggedOn = true;
              this.employeeID = id;
              this.employeeType = logInConfirmation;
            }
            this.send(Packet.LOGINCONFIRMATION, logInConfirmation);
          } else if (packet.getType() == Packet.LOGOFF) {
            System.out.println("Employee type " + this.getEmployeeType() + " employee " + this.getEmployeeID() + " is logging off");
            this.loggedOn = false;
          } else if (packet.getType() == Packet.DISCONNECT) {
            System.out.println("Employee type " + this.getEmployeeType() + " employee " + this.getEmployeeID() + " is logging off");
            System.out.println("Disconnecting socket");
            this.loggedOn = false;
            this.connected = false;
          } else if (packet.getType() == Packet.REQUESTNUMBEROFTABLES) {
            System.out.println("Sending number of tables");
            this.send(Packet.RECEIVENUMBEROFTABLES, TableManager.getNumberOfTables());
          } else if (packet.getType() == Packet.REQUESTMENU) {
            System.out.println("Sending menu");
            Menu menu = Menu.getMenu();
            this.send(Packet.RECEIVEMENU, menu.getDishes());
          } else if (packet.getType() == Packet.REQUESTINVENTORY) {
            System.out.println("Sending inventory");
            Inventory inventory = getInstance();
            this.send(Packet.RECEIVEINVENTORY, inventory.getIngredientsInventory());
          } else if (packet.getType() == Packet.REQUESTTABLEOCCUPANCY) {
            System.out.println("Sending table occupancy");
            this.send(Packet.RECEIVETABLEOCCUPANCY, TableManager.getTableOccupancy());
          } else if (packet.getType() == Packet.REQUESTREQUEST) {
            System.out.println("Sending request");
           this.send(Packet.RECEIVEREQUEST, inventory.getRequests()); //TODO: REQUEST Uncomment this when it's done
          } else if (packet.getType() == Packet.REQUESTDISHESINPROGRESS) {
            System.out.println("Sending dishesInProgress");
            this.send(Packet.RECEIVEDISHESINPROGRESS, ServiceEmployee.getOrderQueue().getDishesInProgress());
          } else if (packet.getType() == Packet.REQUESTORDERSINQUEUE) {
            System.out.println("Sending ordersInQueue");
            this.send(Packet.RECEIVEORDERSINQUEUE, ServiceEmployee.getOrderQueue().getOrdersInQueue());
          } else if (packet.getType() == Packet.REQUESTTABLE) {
            System.out.println("Sending table");
            this.send(Packet.RECEIVETABLE, TableManager.getTable((int) packet.getObject()));
          } else if (packet.getType() == Packet.REQUESTDISHESCOMPLETED) {
            System.out.println("Sending dishes completed");
            this.send(Packet.RECEIVEDISHESCOMPLETED, ServiceEmployee.getOrderQueue().getDishesCompleted());
          } else if (packet.getType() == Packet.REQUESTQUANTITIES) {
            System.out.println("Sending ingredient quantities");
            HashMap<String, InventoryIngredient> inventoryIngredients = inventory.getIngredientsInventory();
            HashMap<String, Integer> quantities = new HashMap<>();
            for (String ingredientName : inventoryIngredients.keySet()) {
              quantities.put(ingredientName, inventoryIngredients.get(ingredientName).getQuantity());
            }
            this.send(Packet.RECEIVEQUANTITIES, quantities);
          } else if (packet.getType() == Packet.ADJUSTINGREDIENT) {
            System.out.println("Adjusting ingredient");
            Object[] infoArray = (Object[]) packet.getObject();
            ArrayList<DishIngredient> dishIngredients = (ArrayList<DishIngredient>) infoArray[0];
            boolean decrease = (Boolean) infoArray[1];
            Inventory inventory = Inventory.getInstance();
            ComputerServer computerServer = ComputerServer.getInstance();
            HashMap newIngredientQuantities = inventory.modifyIngredientRunningQuantity(dishIngredients, decrease);
            computerServer.broadcast(Packet.RECEIVERUNNINGQUANTITYADJUSTMENT, newIngredientQuantities);
          } else if (packet.getType() == Packet.ADJUSTINDIVIDUALINGREDIENT) {
            System.out.println("Adjusting individual ingredient");
            Object[] infoArray = (Object[]) packet.getObject();
            DishIngredient ingredient = (DishIngredient) infoArray[0];
            int quantity = (int) infoArray[1];
            HashMap newIngredientQuantities = inventory.modifyIngredientRunningQuantity(ingredient.getName(), quantity);
            computerServer.broadcast(Packet.SERVERTYPE, Packet.RECEIVERUNNINGQUANTITYADJUSTMENT, newIngredientQuantities);
          } else if (packet.isEventType()) {
            // Just an event
            EventManager.addEvent(createEvent(packet)); // TODO: Broadcast when Inventory is changed
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        this.connected = false;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    System.out.println("This client thread is closing");
    try {
      this.input.close();
      this.output.close();
      this.socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send an object to the client
   *
   * @param type   is the type of the message
   * @param object is what is being sent
   */
  void send(int type, Object object) {
    System.out.println("Sending " + object.getClass() + ": \"" + object + "\" to employee type " + this.employeeType + " employee " + this.employeeID);
    logger.info("Sending " + object.getClass() + ": \"" + object + "\" to employee type " + this.employeeType + "employee " + this.employeeID);

    Packet packet = new Packet(type, object);
    try {
      this.output.reset();
      this.output.writeObject(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send just the message type to the client
   *
   * @param type is the type of the message
   */
  void send(int type) {
    System.out.println("Sending " + type + " to employee type" + this.employeeType + " employee " + this.employeeID);
    logger.info("Sending " + type + " to employee type" + this.employeeType + " employee " + this.employeeID);

    Packet packet = new Packet(type);
    try {
      this.output.writeObject(packet);
      this.output.reset();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private ProcessableEvent createEvent(Packet packet) {
    int methodName = packet.getType();
    ArrayList parameters = (ArrayList) packet.getObject();
    return new ProcessableEvent(this.employeeType, this.employeeID, methodName, parameters);
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

  /**
   * Getter for employeeType
   * 100: Cook
   * 101: Manager
   * 102: Server
   * Outlined in Packet.java
   *
   * @return an integer representation of the employee type
   */
  public int getEmployeeType() {
    return employeeType;
  }
}
