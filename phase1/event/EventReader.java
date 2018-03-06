/*
 EventReader
 This is a helper class that reads the event.txt file

 Created by Bill Ang Li
 Feb. 25th, 2018
*/

package event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

public class EventReader {
  private String file;
  private ArrayList<String> lines = new ArrayList<>();

  /**
   * Constructor for EventReader
   *
   * @param file is the file to be read from
   */
  EventReader(String file) {
    this.file = file;
  }

  /**
   * Reads the event.txt file and creates events and adds them to the queue
   *
   * @param eventQueue is the queue that the events will be added to
   */
  void readFile(Queue<Event> eventQueue) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(this.file));
    String line;
    while ((line = br.readLine()) != null) {
      lines.add(line);
    }

    addLinesToQueue(lines, eventQueue);
  }

  /**
   * Creates Events from info in lines and adds the events to the eventQueue
   *
   * @param lines is a list of lines (read from event.txt) that represent events
   * @param eventQueue is the queue of events
   */
  private void addLinesToQueue(ArrayList<String> lines, Queue<Event> eventQueue) {
    for (String line : lines) {
      Event event = new Event(line);
      eventQueue.add(event);
    }
  }
}
