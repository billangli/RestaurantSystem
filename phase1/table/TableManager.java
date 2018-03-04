package table;

import employee.Server;

import java.util.ArrayList;

public class TableManager {
  private static Table[] tables;

  public static void tableSetUp(int numOfTables) {
    tables = new Table[numOfTables];
    // Note that table number starts from 1.
    for (int i = 1; i <= numOfTables; i++) {
      tables[i - 1] = new Table(i);
    }
  }


  public static Table getTable(int i) {
    return tables[i];
  }
}
