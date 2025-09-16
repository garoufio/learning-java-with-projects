package chapter8;

import java.util.Arrays;

public class EmployeeService {
  
  private Employee[] employees;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EmployeeService(Employee[] employees) {
    this.employees = employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee[] getAllEmployees() {
    return employees == null ? null : Arrays.copyOf(employees, employees.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEmployees(Employee... employees) {
    if (employees == null) {
      System.out.println("No employees were added");
      return;
    }
    
    if (this.employees == null) {
      this.employees = Arrays.copyOf(employees, employees.length);
    }
    else {
      Employee[] arr = new Employee[this.employees.length + employees.length];
      System.arraycopy(this.employees, 0, arr, 0, this.employees.length);
      System.arraycopy(employees, 0, arr, this.employees.length, employees.length);
      this.employees = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getEmployeeIndex(Employee employee) {
    if (employee == null) return -1;
    
    for (int i = 0; i < employees.length; i++) {
      if (employees[i].equals(employee)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getEmployee(String name) {
    if (name == null) return null;
    if  (employees == null) return null;
    
    for (Employee e : employees) {
      if (e.getName().equals(name)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getEmployee(Employee employee) {
    if  (employee == null) return null;
    if (this.employees == null) return null;
    
    for (Employee e : this.employees) {
      if (e.equals(employee)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  //public Employee getEmployee(int index) {}
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEmployee(Employee employee) {
    if (employee == null) return false;
    
    int index = getEmployeeIndex(employee);
    if (index < 0) return false;
    
    employees[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEmployee(String name) {
    return removeEmployee(getEmployee(name));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
