package backend.server;

import java.io.Serializable;

/**
 * Class for Packets being sent between client and server
 */
public class Packet implements Serializable {
  // Client to Server commands
  public static final int LOGINREQUEST = 1;
  public static final int REQUESTNUMBEROFTABLES = 2;
  public static final int REQUESTMENU = 3;
  public static final int REQUESTINVENTORY = 4;
  public static final int ADJUSTINGREDIENT = 5;
  public static final int EVENT = 6;

  // Server to Client commands
  public static final int LOGINCONFIRMATION = -1;
  public static final int RECEIVENUMBEROFTABLES = -2;
  public static final int RECEIVEMENU = -3;
  public static final int RECEIVEINVENTORY = -4;
  public static final int RECEIVEADJUSTMENT = -5;

  // Event Type
  public static final int RECEIVEINGREDIENT = 55;

  // Cook Events
  public static final int ORDERRECEIVED = 50;
  public static final int DISHREADY = 51;

  // Manager Events
  public static final int CHECKINVENTORY = 60;

  // Server Events
  public static final int TAKESEAT = 70;
  public static final int ENTERMENU = 71;
  public static final int DELIVERDISHCOMPLETED = 72;
  public static final int DELIVERDISHFAILED = 73;
  public static final int PRINTBILL = 74;
  public static final int CLEARTABLE = 75;

  // Employee Type
  public static final int LOGINFAILED = -100;
  public static final int COOKTYPE = 100;
  public static final int MANAGERTYPE = 101;
  public static final int SERVERTYPE = 102;

  private int type;
  private Object object;

  /**
   * Packet constructor
   *
   * @param type   is the type of the object
   * @param object is the information being sent
   */
  public Packet(int type, Object object) {
    this.type = type;
    this.object = object;
  }

  public int getType() {
    return type;
  }

  public Object getObject() {
    return object;
  }
}
