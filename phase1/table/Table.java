package table;

import employee.Server;
import inventory.Dish;

import java.util.ArrayList;

public class Table {
    private int tableNum;
    private int cost;
    private Server server;
    private ArrayList<Order> order;
    private boolean isPaid;
    public Table(int tableNum) {
        this.tableNum = tableNum;
        order = new ArrayList<Order>();
        order.add(new Order(this));
        cost = 0;
        isPaid = false;
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


    public Server getServer(){
        return server;
    }

    public int getTableNum(){
        return tableNum;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public boolean isPaid() {
        return isPaid;
    }
}
