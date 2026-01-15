package chapter12.project.service;

import chapter12.project.App;
import chapter12.project.api.Util;
import chapter12.project.entity.employee.Employee;
import chapter12.project.entity.employee.JobTitle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
  
  public List<Employee> getEmployee(String name) {
    if (this.employees == null || this.employees.isEmpty() || name == null) return List.of();
    
    List<Employee> employees = new ArrayList<>();
    for (Employee e : this.employees) {
      if (e != null && e.getName().equals(name)) employees.add(e);
    }
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getEmployee(String name, JobTitle jobTitle, int yearsOfExperience) {
    if (this.employees == null || name == null || jobTitle == null || yearsOfExperience < 0) return null;
    
    for (Employee e : this.employees) {
      if (e != null &&
          e.getName().equals(name) &&
          e.getJobTitle().equals(jobTitle) &&
          e.getYearsOfExperience() == yearsOfExperience) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getEmployee(Employee employee) {
    if (this.employees == null || employee == null) return null;
    
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
  
  private String getPrintableWorkingDays(LocalDate[] dates) {
    if (dates == null || dates.length == 0) return "[]";
    
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < dates.length; i++) {
      if (dates[i] == null) continue;
      
      sb.append(dates[i].format(Util.DATE_FORMAT));
      if (i < dates.length - 1) sb.append(", ");
    }
    sb.append("]");
    
    return sb.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void printEmployeeWorkingSchedule(Employee employee) {
    if (employee == null) return;
    
    int len = employee.getJobTitle().getWorkingDays().length;
    LocalDate[] dates = employee.getWeeklyWorkDays();
    System.out.printf(
        "%s '%s' works on %s, during %s to %s\n",
        employee.getJobTitle().getTitle(),
        employee.getName(),
        getPrintableWorkingDays(dates),
        employee.getJobTitle().getStartTime().format(Util.TIME_FORMAT),
        employee.getJobTitle().getEndTime().format(Util.TIME_FORMAT)
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void printEmployeesWorkingSchedule(List<Employee> employees) {
    if (employees == null || employees.isEmpty()) {
      System.out.println("No employees found");
      return;
    };
    
    for (Employee e : employees) {
      if (e != null) printEmployeeWorkingSchedule(e);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDaysOff(Employee employee, LocalDate[] daysOff) {
    if (employee == null || daysOff == null || daysOff.length == 0) return;
    
    LocalDate[] workDays = employee.getWeeklyWorkDays();
    for  (int i = 0; i < workDays.length; i++) {
      if (workDays[i] == null) continue;
      
      if (
          (workDays[i].isEqual(daysOff[0]) || workDays[i].isAfter(daysOff[0])) &&
          (workDays[i].isEqual(daysOff[1]) || workDays[i].isBefore(daysOff[1]))
          ) workDays[i] = null;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
