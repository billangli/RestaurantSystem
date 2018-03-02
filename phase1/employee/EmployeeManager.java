package employee;

import java.util.ArrayList;

public class EmployeeManager {
  private ArrayList<Employee> employeeList;

  public EmployeeManager() {
    employeeList = new ArrayList<>();
  }

  public void add(Employee employee) {
    employeeList.add(employee);
  }

  public String toString() {
    // TODO:
    StringBuilder result = new StringBuilder("List of all employees: \n");
    result.append("==================================");
    for (Employee e : employeeList) {
      result.append(e.toString()).append("\n");
    }
    result.append("==================================");

    return result.toString();
  }
}
