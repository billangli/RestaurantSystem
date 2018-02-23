package table;

import java.util.ArrayList;

public class TableManager {
    public ArrayList<Table> tables;

    public TableManager(){
        tables = new ArrayList<Table>();
    }


    public void takeSeat(Table t, Server s){
        t.serve(s);
    }

}
