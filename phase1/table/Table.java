package table;

public class Table {
    private int tableNum;
    private Server server;
    private Order order;

    public Table(int tableNum) {
        this.tableNum = tableNum;
        order = new Order(this);
    }

    public void serve(Server server){
        this.server = server;
    }

    public void order(Dish dish) {
        server.order(this, dish);
    }

    public String bill() {
        String bill = "" + tableNum;
        return bill + order.toString();
    }

}
