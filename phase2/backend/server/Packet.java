package backend.server;

import java.io.Serializable;

/**
 * Class for Packets being sent between client and server
 */
public class Packet implements Serializable {
  public static final int LOGINREQUEST = 1;
  public static final int REQUESTNUMBEROFTABLES = 2;
  public static final int REQUESTMENU = 3;
  public static final int REQUESTINVENTORY = 4;
  public static final int ADJUSTINGREDIENT = 5;
  public static final int EVENT = 6;

  public static final int LOGINCONFIRMATION = -1;
  public static final int RECEIVENUMBEROFTABLES = -2;
  public static final int RECEIVEMENU = -3;
  public static final int RECEIVEINVENTORY = -4;
  public static final int RECEIVEADJUSTMENT = -5;

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
