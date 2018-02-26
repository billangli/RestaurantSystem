package table;

import employee.Server;

import java.util.ArrayList;

public class Table {
    private int tableNum;
    private int cost;
    private Server server;
    private ArrayList<Order> order;

    public Table(int tableNum) {
        this.tableNum = tableNum;
        order = new ArrayList<Order>();
        order.add(new Order(this));
        cost = 0;
    }

    public void serve(Server server){
        this.server = server;
    }

    public void addCost(Dish d){
        cost += d.getCost();
    }

    public void addOrder(Order newOrder) {
        order.add(newOrder);
    }

    public String printBill() {
        String bill = "" + tableNum;
        for(Order o: order){
            bill += o.toString();
        }
        return bill;
    }

    public Server getServer(){
        return server;
    }

    public int getTableNum(){
        return tableNum;
    }

}
