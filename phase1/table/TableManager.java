package table;

import employee.Server;

import java.util.ArrayList;

public class TableManager {
  private Table[] tables;
  private final int numOfTables = 15; // Assume that this restaurant has 15 tables.
  private ArrayList<Server> servers = new ArrayList<>();

  public TableManager() {
    tables = new Table[numOfTables];
    // Note that table number starts from 1.
    for (int i = 1; i <= numOfTables; i++) {
      tables[i] = new Table(i);
    }
  }

  public void registerServer(Server s) {
    if (!servers.contains(s)) {
      servers.add(s);
    }
  }

  public Table getTable(int i) {
    return tables[i];
  }
}
