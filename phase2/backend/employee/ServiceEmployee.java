package backend.employee;

/** A ServiceEmployee class. This class is parent class of Cook, Server class. */
class ServiceEmployee extends Employee {
  // A order queue that all the cooks and servers share together.
  static OrderQueue orderQueue = new OrderQueue();

  ServiceEmployee(int id) {
    super(id);
  }
}
