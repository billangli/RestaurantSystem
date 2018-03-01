/*
  Event.java
  An abstract class to represent an event

  Created by Bill Ang Li
  Feb. 22nd, 2018
 */

package event;

import java.awt.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Event {
    private String manager;
    private String instanceID;
    private String method;
    private ArrayList<String> parameters;

    /**
     * Empty constructor TODO: Might want to remove this
     */
    Event () {}

//    /**
//     * Processes the event and makes changes to the RestaurantSystem by calling methods in other classes
//     *
//     * @return a String representing the output of processing this event
//     */
//    abstract public String process();

    /**
     * Parses a line (read from event.txt) into an event and returns it
     *
     * @param line is the line to be parsed
     */
    void parseEvent(String line) {
        StringTokenizer lineTokenizer = new StringTokenizer(line);
        try {
            // Parsing the text file according to our format TODO: Create a format in the README
            this.manager = lineTokenizer.nextToken();
            this.instanceID = lineTokenizer.nextToken();
            this.method = lineTokenizer.nextToken();

            // Creating an ArrayList of parameters from one token in the line
            String allParameters = lineTokenizer.nextToken();
            allParameters = allParameters.substring(0, allParameters.length() - 1);
            StringTokenizer parameterTokenizer = new StringTokenizer(allParameters, ",");
            while (parameterTokenizer.hasMoreTokens()) {
                this.parameters.add(parameterTokenizer.nextToken());
            }
        } catch (Exception e) {
            e.printStackTrace(); // TODO: Make my own exception
        }
    }
}
