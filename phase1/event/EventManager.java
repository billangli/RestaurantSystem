package event;

import employee.EmployeeManager;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class EventManager {
  private static final String FILE = "phase1/event/event.txt";

  private Queue<Event> eventQueue = new LinkedList<>();
  private EmployeeManager employeeManager;

  /**
   * Constructor for EventManager
   *
   * @param employeeManager is the class to control employees
   */
  public EventManager(EmployeeManager employeeManager) {
    this.employeeManager = employeeManager;
  }

  /**
   * Read the specified file in this class and add the events to the eventQueue
   *
   * @throws IOException because it reads from a text file
   */
  public void readFile() throws IOException {
    EventReader eventReader = new EventReader(FILE);
    eventReader.readFile(eventQueue);
  }

  /** Processes the events from eventQueue as long as if there are more events */
  public void processEvents() {
    while (!this.eventQueue.isEmpty()) {
      Event event = this.eventQueue.remove();
      EventProcessor eventProcessor = new EventProcessor(event, this.employeeManager);
      eventProcessor.process();
    }
  }
}
