/*
 Event Processor
 This takes and event and calls the appropriate method with the parameters

 Created by Bill Ang Li
 Marc. 1st, 2018
 */

package event;

import employee.*;
import inventory.Inventory;
import table.Table;
import table.TableManager;

public class EventProcessor {
    private Event event;
    private EmployeeManager employeeManager;
    private Inventory inventory;
    private TableManager tableManager;

    /**
     * Constructor for EventProcessor
     *
     * @param event           is the event that this EventProcessor will process
     * @param employeeManager
     * @param inventory
     * @param tableManager
     */
    EventProcessor(Event event, EmployeeManager employeeManager, Inventory inventory, TableManager tableManager) {
        this.event = event;
        this.employeeManager = employeeManager;
        this.inventory = inventory;
        this.tableManager = tableManager;
    }

    /**
     * Find out which method to use based on the manager
     */
    void process() {
        switch (this.event.getManager()) {
            case "EmployeeManager":
                this.processEmployeeEvent();
                break;
            case "InventoryManager":
                this.processInventoryEvent();
                break;
            case "TableManager":
                this.processTableEvent();
                break;
            default:
                System.out.println("This is an invalid Manager"); // TODO: Do it properly
        }
    }

    private void processEmployeeEvent() {
        System.out.println("Processing Employee Event");

        Employee employee = this.employeeManager.getEmployeeById(this.event.getInstanceID());

        if (employee instanceof Cook) {
            this.processCookEvent((Cook) employee);
        } else if (employee instanceof Manager) {
            this.processManagerEvent((Manager) employee);
        } else if (employee instanceof Server) {
            this.processServerEvent((Server) employee);
        } else {
            System.out.println("*** Invalid Employee Type ***");
        }
    }

    private void processCookEvent(Cook cook) {
        switch (this.event.getMethod()) {
            case "orderReceived":
                cook.orderReceived();
                break;
            case "dishReady":
                cook.dishReady();
                break;
        }
    }

    private void processManagerEvent(Manager manager) {
        switch (this.event.getMethod()) {
            default:
                System.out.println("*** Manager has no " + this.event.getMethod() + " method ***");
                break;
        }
    }

    private void processServerEvent(Server server) {
        switch (this.event.getMethod()) {
            case "takeSeat": {
                int tableNumber = Integer.parseInt(this.event.getParameters().get(0));
                Table table = tableManager.getTable(tableNumber);
                server.takeSeat(table);
                break;
            }
            case "enterMenu": {
                int tableNumber = Integer.parseInt(this.event.getParameters().get(0));
                Table table = tableManager.getTable(tableNumber); // TODO: Make dish format and finish this
                break;
            }
            case "deliverDishCompleted": {
                break; // TODO: Finish this
            }
            case "deliverDishFailed": {
                break; // TODO: Finish this
            }
            case "printBill": {
                int tableNumber = Integer.parseInt(this.event.getParameters().get(0));
                Table table = tableManager.getTable(tableNumber);
                server.printBill(table);
                break;
            }
            case "checkIfPaid": {

                int tableNumber = Integer.parseInt(this.event.getParameters().get(0));
                Table table = tableManager.getTable(tableNumber);
                server.checkIfPaid(table);
                break;
            }
            default:
                System.out.println("*** Server has no " + this.event.getMethod() + " method ***");
                break;
        }
    }

    private void processInventoryEvent() {

    }

    private void processTableEvent() {
        switch (this.event.getMethod()) {
            default:
                System.out.println("*** Table has no methods currently ***");
        }
    }
}
