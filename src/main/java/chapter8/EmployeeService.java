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
      // find empty slots
      int countEmpty = 0;
      for (Employee e : this.employees) {
        if (e == null) countEmpty++;
      }
      // fill empty slots
      if (countEmpty >= employees.length) {
        for (int i = 0, j = 0; i < this.employees.length && j < employees.length; i++) {
          if (this.employees[i] == null) {
            this.employees[i] = employees[j];
            j++;
          }
        }
        return;
      }
      /* if the number of newly added employees is greater than the empty slots, increase the size of the array and
      add them */
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
      if (employees[i] != null && employees[i].equals(employee)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getEmployee(String name) {
    if (name == null) return null;
    if  (employees == null) return null;
    
    for (Employee e : employees) {
      if (e != null && e.getName().equals(name)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getEmployee(Employee employee) {
    if  (employee == null) return null;
    if (this.employees == null) return null;
    
    for (Employee e : this.employees) {
      if (e != null && e.equals(employee)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee[] getEmployees(JobTitle jobTitle) {
    if (jobTitle == null) return null;
    if  (employees == null) return null;
    
    int capacity = 0;
    for (Employee e : employees) {
      if (e != null && e.getJobTitle() == jobTitle) capacity++;
    }
    if (capacity == 0) return null;
    
    Employee[] arr = new Employee[capacity];
    for (int i = 0, j = 0; i < employees.length && j < arr.length; i++) {
      if (employees[i] != null && employees[i].getJobTitle() == jobTitle) {
        arr[j] = employees[i];
        j++;
      }
    }
    return arr;
  }
  
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
