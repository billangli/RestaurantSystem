/*
  Event.java
  An abstract class to represent an event

  Created by Bill Ang Li
  Feb. 22nd, 2018
 */

package event;

import java.util.Date;

abstract class Event {
    private Date timeCreated;
    private String creator; // All eventCreators should have an id (e.g. Worker, Inventory)

    /**
     * Processes the event and makes changes to the RestaurantSystem by calling methods in other classes
     *
     * @return a String representing the output of processing this event
     */
    abstract public String process();
}
