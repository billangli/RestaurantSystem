package backend.server;

import java.io.Serializable;

/**
 * Class for Packets being sent between client and server
 */
public class Packet implements Serializable {
  public static final int LOGOFF = 0;
  public static final int DISCONNECT = 1000;

  // Client to Server resource requests
  public static final int LOGINREQUEST = 1;
  public static final int REQUESTNUMBEROFTABLES = 2;
  public static final int REQUESTMENU = 3;
  public static final int REQUESTINVENTORY = 4;
  public static final int REQUESTDISHESINPROGRESS = 5;
  public static final int REQUESTORDERSINQUEUE = 6;
  public static final int REQUESTTABLE = 7;
  public static final int REQUESTQUANTITIES = 8;
  public static final int REQUESTDISHESCOMPLETED = 9;
  public static final int REQUESTTABLEOCCUPANCY = 10;
  public static final int REQUESTREQUEST = 11;

  // Server to Client receive resources
  public static final int LOGINCONFIRMATION = -1;
  public static final int RECEIVENUMBEROFTABLES = -2;
  public static final int RECEIVEMENU = -3;
  public static final int RECEIVEINVENTORY = -4;
  public static final int RECEIVEDISHESINPROGRESS = -5;
  public static final int RECEIVEORDERSINQUEUE = -6;
  public static final int RECEIVETABLE = -7;
  public static final int RECEIVEQUANTITIES = -8;
  public static final int RECEIVEDISHESCOMPLETED = -9;
  public static final int RECEIVETABLEOCCUPANCY = -10;
  public static final int RECEIVEREQUEST = -11;

  // Adjust ingredient
  public static final int ADJUSTINGREDIENT = 30;
  public static final int ADJUSTINDIVIDUALINGREDIENT = 31;
  public static final int RECEIVERUNNINGQUANTITYADJUSTMENT = -30;

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

  /**
   * Packet constructor without object
   *
   * @param type   is the type of the object
   */
  public Packet(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }

  public boolean isEventType() {
    return (this.type >= 50) && (this.type < 80);
  }

  public boolean isUpdateType() {
    return (this.type == RECEIVEDISHESINPROGRESS) ||
            (this.type == RECEIVEORDERSINQUEUE) ||
            (this.type == RECEIVEDISHESCOMPLETED) ||
            (this.type == RECEIVERUNNINGQUANTITYADJUSTMENT) ||
            (this.type == RECEIVETABLEOCCUPANCY);
  }

  public Object getObject() {
    return object;
  }
}
