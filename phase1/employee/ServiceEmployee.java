package employee;

class ServiceEmployee extends Employee {
  OrderQueue orderQueue;

  ServiceEmployee(int id) {
    super(id);
    orderQueue = new OrderQueue();
  }

  protected void setOrderQueue(OrderQueue orderQueue) {
    this.orderQueue = orderQueue;
  }
}
