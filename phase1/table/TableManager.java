package table;

import employee.Server;

import java.util.ArrayList;

public class TableManager {
  private static Table[] tables;

  public static void tableSetUp(int numOfTables) {
    tables = new Table[numOfTables];
    // Note that table number starts from 1.
    for (int i = 0; i < numOfTables; i++) {
      tables[i] = new Table(i+1);
    }
  }


  public static Table getTable(int i) {
    return tables[i];
  }
}
