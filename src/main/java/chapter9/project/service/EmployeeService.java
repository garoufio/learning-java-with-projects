package chapter9.project.service;

import chapter9.project.App;
import chapter9.project.entity.employee.*;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
  
  private List<Employee> employees;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EmployeeService(List<Employee> employees) {
    this.employees = (employees == null ? new ArrayList<>() : employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Employee> getAllEmployees() {
    return this.employees == null ? List.of() : List.copyOf(this.employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEmployees(Employee... employees) {
    if (this.employees == null || employees == null || employees.length == 0) {
      System.out.println("No employees were added");
      return;
    }
    
    if (this.employees.size() == App.MAX_EMPLOYEES) {
      System.out.println("No more employees can be added as maximum number of employees has been reached");
      return;
    }
    for (int i = 0; i < employees.length; i++) {
      if (employees[i] != null) {
        if (this.employees.size() < App.MAX_EMPLOYEES) {
          this.employees.add(employees[i]);
          System.out.printf("Employee added '%s'\n", employees[i]);
        } else {
          System.out.printf("Maximum number of employees has been reached. '%d' employees were added\n", i);
          break;
        }
      } else {
        System.out.printf("Invalid employee at index '%d'\n", i);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getEmployee(String name) {
    if (this.employees == null || name == null) return null;
    
    for (Employee e : this.employees) {
      if (e != null && e.getName().equals(name)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getEmployee(Employee employee) {
    if  (this.employees == null || employee == null) return null;
    
    for (Employee e : this.employees) {
      if (e != null && e.equals(employee)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Employee> getEmployees(JobTitle jobTitle) {
    if (this.employees == null || this.employees.isEmpty() || jobTitle == null) return List.of();
    
    List<Employee> employees = new ArrayList<>();
    for (Employee e : this.employees) {
      if (e != null && e.getJobTitle().equals(jobTitle)) employees.add(e);
    }
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEmployee(Employee employee) {
    if (this.employees == null || employee == null) return false;
    
    for (Employee e : this.employees) {
      if (e != null && e.equals(employee)) return this.employees.remove(employee);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
