package employee;

class ServiceEmployee extends Employee {
  static OrderQueue orderQueue = new OrderQueue();

  ServiceEmployee(int id) {
    super(id);
  }
}
