package event;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class EventManager {
    private static final String FILE = "event/event.txt";

    private Queue<Event> eventQueue = new LinkedList<>();

    void readFile() throws IOException {
        EventReader eventReader = new EventReader(FILE);
        eventReader.readFile(eventQueue);
    }
}
