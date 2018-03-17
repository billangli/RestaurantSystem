package backend.table;

/**
 * Table manager manages all the tables.
 *
 * Table manager methods include setting up the backend.table, and returning a backend.table by its id.
 */
public class TableManager {
  private static Table[] tables;

  /**
   * sets up the manager with numOfTables tables
   * @param numOfTables the number of tables
   */
  public static void tableSetUp(int numOfTables) {
    tables = new Table[numOfTables];
    // Note that backend.table number starts from 1.
    for (int i = 0; i < numOfTables; i++) {
      tables[i] = new Table(i+1);
    }
  }

  /**
   * returns the backend.table with id i
   * @param i the id of a backend.table
   * @return the backend.table with the id i
   */
  public static Table getTable(int i) {
    return tables[i];
  }
}
