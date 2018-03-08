package event;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * EventManager - This class manages all the functionality of events
 *
 * <p>Created by Ang Li on Feb. 22nd, 2018
 */
public class EventManager {
  private static final String FILE = "phase1/event/event.txt";

  private Queue<Event> eventQueue = new LinkedList<>();

  /** Constructor for EventManager */
  public EventManager() {}

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
      event.process();
    }
  }
}
