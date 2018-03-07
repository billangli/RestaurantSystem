package table;

/**
 * the manager that manage all the tables
 */
public class TableManager {
  private static Table[] tables;

  /**
   * set up the manager with numOfTables tables
   * @param numOfTables the number of tables
   */
  public static void tableSetUp(int numOfTables) {
    tables = new Table[numOfTables];
    // Note that table number starts from 1.
    for (int i = 0; i < numOfTables; i++) {
      tables[i] = new Table(i+1);
    }
  }

  /**
   *
   * @param i the id of a table
   * @return the table with the id i
   */
  public static Table getTable(int i) {
    return tables[i];
  }
}
