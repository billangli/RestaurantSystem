package table;

import java.util.ArrayList;

public class Order {
    private ArrayList<Dish> dishes;
    private boolean sent;
    private Table table;

    public Order(Table t){
        table = t;
        sent = false;
        dishes = new ArrayList<Dish>();

    }
    public void addDish(Dish d){
        dishes.add(d);
    }

    public void sent(){
        sent = true;
    }

    public String toString(){
        String str = "";
        for(Dish d:dishes){
            str += d.toString();
        }
        return str;
    }

    public boolean isSent(){
        return sent;
    }

    public void recook(Dish d){
        d.recook();
    }

    public Table getTable() {
        return table;
    }
}
