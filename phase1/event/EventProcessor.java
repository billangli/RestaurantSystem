/*
Event Processor
This takes and event and calls the appropriate method with the parameters

Created by Bill Ang Li
Marc. 1st, 2018
*/

package event;

import employee.*;
import table.Order;
import table.Table;
import table.TableManager;

public class EventProcessor {
  private Event event;

  /**
   * Constructor for EventProcessor
   *
   * @param event is the event that this EventProcessor will process
   */
  EventProcessor(Event event) {
    this.event = event;
  }

  //  /** Find out which method to use based on the manager */
  //  void process() {
  //    switch (this.event.getEmployeeType()) {
  //      case "EmployeeManager":
  //        this.processEmployeeEvent();
  //        break;
  //      case "InventoryManager":
  //        this.processInventoryEvent();
  //        break;
  //      case "TableManager":
  //        this.processTableEvent();
  //        break;
  //      default:
  //        System.out.println("This is an invalid Manager"); // TODO: Do it properly
  //    }
  //  }

  //  /**
  //   * Find out which type of Employee this employee is
  //   */
  //  private void processEmployeeEvent() {
  //    System.out.println("Processing Employee Event");
  //
  //    Employee employee = this.employeeManager.getEmployeeById(this.event.getEmployeeID());
  //
  //    if (employee instanceof Cook) {
  //      this.processCookEvent((Cook) employee);
  //    } else if (employee instanceof Manager) {
  //      this.processManagerEvent((Manager) employee);
  //    } else if (employee instanceof Server) {
  //      this.processServerEvent((Server) employee);
  //    } else {
  //      System.out.println("*** Invalid Employee Type ***");
  //    }
  //  }

  /** Find which type of Employee to cast this employee to and call their methods */
  void process() {
    Employee employee = EmployeeManager.getEmployeeById(this.event.getEmployeeID());

    switch (this.event.getEmployeeType()) {
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
        System.out.println("*** Invalid Employee Type ***");
        break;
    }
  }

  /**
   * Call cook's method based on this event's method in event.txt
   *
   * @param cook is the Cook whose method will be called
   */
  private void processCookEvent(Cook cook) {
    switch (this.event.getMethod()) {
      case "orderReceived":
        cook.orderReceived();
        break;
      case "dishReady":
        int dishNumber = Integer.parseInt(this.event.getParameters().get(0));
        cook.dishReady(dishNumber);
        break;
    }
  }

  /**
   * Call manager's method based on this event's method in event.txt
   *
   * @param manager is the Manager whose method will be called
   */
  private void processManagerEvent(Manager manager) {
    switch (this.event.getMethod()) {
      default:
        System.out.println("*** Manager has no " + this.event.getMethod() + " method ***");
        break;
    }
  }

  /**
   * Call server's method based on this event's method in event.txt
   *
   * @param server is the Server whose method will be called
   */
  private void processServerEvent(Server server) {
    switch (this.event.getMethod()) {
      case "takeSeat":
        {
          int tableNumber = Integer.parseInt(this.event.getParameters().get(0)) - 1;
          Table table = TableManager.getTable(tableNumber);
          server.takeSeat(table);
          break;
        }
      case "enterMenu":
        {
          int tableNumber = Integer.parseInt(this.event.getParameters().get(0)) - 1;
          Table table = TableManager.getTable(tableNumber);
          Order order = Event.parseOrder(this.event.getParameters().get(1));
          server.enterMenu(table, order);
          break;
        }
      case "deliverDishCompleted":
        {
          server.deliverDishCompleted(); // TODO: No need for dish?
          break;
        }
      case "deliverDishFailed":
        {
          server.deliverOrderFailed();
          break;
        }
      case "printBill":
        {
          int tableNumber = Integer.parseInt(this.event.getParameters().get(0)) - 1;
          Table table = TableManager.getTable(tableNumber);
          server.printBill(table);
          break;
        }
      case "checkIfPaid":
        {
          int tableNumber = Integer.parseInt(this.event.getParameters().get(0)) - 1;
          Table table = TableManager.getTable(tableNumber);
          server.checkIfPaid(table);
          break;
        }
      default:
        System.out.println("*** Server has no " + this.event.getMethod() + " method ***");
        break;
    }
  }

  /** Process the inventory's event */
  private void processInventoryEvent() {}

  /** Process a table's event */
  private void processTableEvent() {
    switch (this.event.getMethod()) {
      default:
        System.out.println("*** Table has no methods currently ***");
    }
  }
}
