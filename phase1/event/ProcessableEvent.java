/*
EventProcessor
This takes and event and calls the appropriate method with the parameters

Created by Bill Ang Li
Mar. 1st, 2018
*/

package event;

import employee.*;
import table.Order;
import table.Table;
import table.TableManager;

class ProcessableEvent extends Event {

  /**
   * Constructor for ProcessableEvent
   *
   * @param line is the line of text containing the information to be parsed
   */
  ProcessableEvent(String line) {
    super(line);
  }

  /** Find which type of Employee to cast this employee to and call their methods */
  @Override
  void process() {
    Employee employee = EmployeeManager.getEmployeeById(this.employeeID);
    // TODO: ID invalid
    // TODO: dish not valid
    // TODO: Redo uml file

    switch (this.employeeType) {
      case "Cook":
        this.processCookEvent((Cook) employee);
        break;
      case "Manager":
        this.processManagerEvent((Manager) employee);
        break;
      case "Server":
        this.processServerEvent((Server) employee);
        break;
      default:
        System.out.println("*** " + this.employeeType + " is an invalid Employee type ***");
        break;
    }
  }

  /**
   * Call cook's method based on this event's method in event.txt
   *
   * @param cook is the Cook whose method will be called
   */
  private void processCookEvent(Cook cook) {
    switch (this.methodName) {
      case "orderReceived":
        cook.orderReceived();
        break;
      case "dishReady":
        {
          int dishNumber = Integer.parseInt(this.parameters.get(0));
          cook.dishReady(dishNumber);
          break;
        }
      case "receiveIngredient":
        {
          String ingredientName = this.parameters.get(0);
          int quantity = Integer.parseInt(this.parameters.get(1));
          cook.receiveIngredient(ingredientName, quantity);
        }
      default:
        System.out.println("*** Cook has no \" + this.event.getMethodName() + \" method ***");
    }
  }

  /**
   * Call manager's method based on this event's method in event.txt
   *
   * @param manager is the Manager whose method will be called
   */
  private void processManagerEvent(Manager manager) {
    switch (this.methodName) {
      case "checkInventory":
        manager.checkInventory();
        break;
      case "receiveIngredient":
        {
          String ingredientName = this.parameters.get(0);
          int quantity = Integer.parseInt(this.parameters.get(1));
          manager.receiveIngredient(ingredientName, quantity);
        }
      default:
        System.out.println("*** Manager has no " + this.methodName + " method ***");
        break;
    }
  }

  /**
   * Call server's method based on this event's method in event.txt
   *
   * @param server is the Server whose method will be called
   */
  private void processServerEvent(Server server) {
    switch (this.methodName) {
      case "takeSeat":
        {
          int tableNumber = Integer.parseInt(this.parameters.get(0)) - 1;
          Table table = TableManager.getTable(tableNumber);
          server.takeSeat(table);
          break;
        }
      case "enterMenu":
        {
          int tableNumber = Integer.parseInt(this.parameters.get(0)) - 1;
          Table table = TableManager.getTable(tableNumber);
          Order order = Event.parseOrder(this.parameters.get(1));
          server.enterMenu(table, order);
          break;
        }
      case "deliverDishCompleted":
        {
          int dishNumber = Integer.parseInt(this.parameters.get(0));
          server.deliverDishCompleted(dishNumber);
          break;
        }
      case "deliverDishFailed":
        {
          int dishNumber = Integer.parseInt(this.parameters.get(0));
          server.deliverDishFailed(dishNumber);
          break;
        }
      case "printBill":
        {
          int tableNumber = Integer.parseInt(this.parameters.get(0)) - 1;
          Table table = TableManager.getTable(tableNumber);
          server.printBill(table);
          break;
        }
      case "clearTable":
        {
          int tableNumber = Integer.parseInt(this.parameters.get(0)) - 1;
          Table table = TableManager.getTable(tableNumber);
          server.clearTable(table);
          break;
        }
      case "receiveIngredient":
        {
          String ingredientName = this.parameters.get(0);
          int quantity = Integer.parseInt(this.parameters.get(1));
          server.receiveIngredient(ingredientName, quantity);
        }
      default:
        System.out.println("*** Server has no " + this.methodName + " method ***");
        break;
    }
  }
}
