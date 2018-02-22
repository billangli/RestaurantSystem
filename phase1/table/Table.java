package table;

public class Table {
    int tableNum;
    Server server;
    Order order;
    public Table(int  tableNum, Server server){
        this.tableNum = tableNum;
        this.server = server;
        order = new Order(this);
    }
    public void order(Dish dish){
        server.order(this, dish);
    }
    public String bill(){
        String bill = ""+tableNum;
        return bill + order.toString();
    }
    
}
