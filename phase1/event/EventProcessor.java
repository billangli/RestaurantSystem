/*
 Event Processor
 This takes and event and calls the appropriate method with the parameters

 Created by Bill Ang Li
 Marc. 1st, 2018
 */

package event;

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

    }

    private void processCookEvent() {

    }

    private void processManagerEvent() {

    }

    private void processServerEvent() {

    }

    private void processInventoryEvent() {

    }

    private void processTableEvent() {

    }
}
