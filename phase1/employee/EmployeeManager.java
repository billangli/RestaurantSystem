package employee;

import java.util.ArrayList;

public class EmployeeManager {
  private ArrayList<Cook> cookList;
  private ArrayList<Server> serverList;
  private ArrayList<Employee> employeeList;

  public EmployeeManager() {
    cookList = new ArrayList<>();
    serverList = new ArrayList<>();
    employeeList = new ArrayList<>();
  }

  public void addEmployee(Employee person) {
    employeeList.add(person);
  }

  public void addCook(String name, int id) {
    cookList.add(new Cook(name, id));
  }

  public void addServer(String name, int id) {
    serverList.add(new Server(name, id));
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
