package employee;

import java.util.ArrayList;
import logger.RestaurantLogger;
import java.util.logging.Logger;

/** An EmployeeManager class. This class manages all the employees working in the restaurant. */
public class EmployeeManager {
  // ArrayList of all employees.
  private static ArrayList<Employee> employeeList = new ArrayList<>();

  private static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  /**
   * An instance of employee is added to employee manager.
   *
   * @param employee The employee instance that is added to this employee manager.
   */
  public static void add(Employee employee) {
    employeeList.add(employee);
  }

  /**
   * Returns a string representation of all the employees.
   *
   * @return a string representation of all the employees.
   */
  public static String allEmployeesToString() {
    StringBuilder result = new StringBuilder("List of all employees: \n");
    result.append("==================================");
    for (Employee e : employeeList) {
      result.append(e.toString()).append("\n");
    }
    result.append("==================================");

    return result.toString();
  }

  /**
   * Return an employee whose id is equal to <code>id</code>.
   *
   * @param id Id of the employee this method returns.
   * @return An Employee instance whose id is equal to <code>id</code>.
   */
  public static Employee getEmployeeById(int id) {
    if (id < 1 || id > employeeList.size()) {
      logger.warning("Not a valid employee id.");
      return null;
    } else {
      return employeeList.get(id - 1);
    }
  }
}
