package event;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class EventManager {
    private static final String FILE = "event/event.txt";

    private Queue<Event> eventQueue = new LinkedList<>();

    /**
     * Read the specified file in this class and add the events to the eventQueue
     *
     * @throws IOException because it reads from a text file
     */
    void readFile() throws IOException {
        EventReader eventReader = new EventReader(FILE);
        eventReader.readFile(eventQueue);
    }
}
