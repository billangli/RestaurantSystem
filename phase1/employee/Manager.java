package employee;

import java.io.FileWriter;
import java.io.IOException;

public class Manager extends Employee {

  public Manager(int id) {
    super(id);
  }

  public void checkInventory() {
    // TODO: print out all inventory items and their quentities.
  }

  //not sure
//  public void sendRequeset(){
//    try{
//      new FileWriter("phase1/request.txt").close();
//    }
//    catch (IOException ioe) {
//      ioe.printStackTrace();
//    }

  }
  public String toString() {
    return "Manager, id:" + getId();
  }
}
