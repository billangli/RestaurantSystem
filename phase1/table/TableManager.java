package table;

import employee.Server;

import java.util.ArrayList;

public class TableManager {
    public Table[] tables;

    public TableManager(int num){
        tables = new Table[num];
        for(int i = 0; i < num; i++){
            tables[i] = new Table(i);
        }
    }



    public void takeSeat(Table t, Server s){
        t.serve(s);
    }

    public Table getTable(int i){
        return tables[i];
    }

}
