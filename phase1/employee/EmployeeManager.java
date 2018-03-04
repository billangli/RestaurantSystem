package employee;

import java.util.ArrayList;

public class EmployeeManager {
  private static ArrayList<Employee> employeeList  = new ArrayList<>();

  public static void add(Employee employee) {
    employeeList.add(employee);
  }

  public static String printAllEmployees() {
    StringBuilder result = new StringBuilder("List of all employees: \n");
    result.append("==================================");
    for (Employee e : employeeList) {
      result.append(e.toString()).append("\n");
    }
    result.append("==================================");

    return result.toString();
  }

  public static Employee getEmployeeById(int id) {
    return employeeList.get(id - 1);
  }
}
