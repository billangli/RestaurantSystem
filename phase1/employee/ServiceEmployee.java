package employee;

class ServiceEmployee extends Employee {
  static OrderQueue orderQueue = new OrderQueue();

  ServiceEmployee(int id) {
    super(id);
  }

  public static OrderQueue getOrderQueue() {
    return orderQueue;
  }
}
