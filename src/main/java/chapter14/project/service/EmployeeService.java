package chapter14.project.service;

import chapter14.project.App;
import chapter14.project.api.Util;
import chapter14.project.entity.employee.Employee;
import chapter14.project.entity.employee.JobTitle;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class for managing employees.
 */
public class EmployeeService {
  
  private Set<Employee> employees;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs an EmployeeService with the given set of employees. If the provided set is null, an empty set is 
   * created instead.
   * @param employees the set of employees to be managed by this service, and can be null.
   */
  public EmployeeService(Set<Employee> employees) {
    this.employees = (employees == null ? new HashSet<>() : employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a set of all employees managed by this service. If no employees are present, an empty set is returned.
   * @return a set of all employees, or an empty set if none are present.
   */
  public Set<Employee> getAllEmployees() {
    return this.employees == null ? Set.of() : Set.copyOf(this.employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds one or more employees to the service. If the maximum number of employees has been reached, no more employees 
   * will be added.
   * @param employees the employees to be added, can be null or empty.
   */
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
  
  /**
   * Returns a set of employees whose names match the given name. If no matching employees are found, an empty set is returned.
   * @param name the name to search for, can be null.
   * @return a set of employees with the given name, or an empty set if none are found.
   */
  public Set<Employee> getEmployee(String name) {
    if (this.employees == null || this.employees.isEmpty() || name == null) return Set.of();
    
    Set<Employee> employees = new HashSet<>();
    for (Employee e : this.employees) {
      if (e != null && e.getName().equals(name)) employees.add(e);
    }
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the employee whose name, job title, and years of experience match the given parameters. If no matching 
   * employee is found, null is returned.
   * @param name the name of the employee to search for, can be null.
   * @param jobTitle the job title of the employee to search for, can be null. See {@link JobTitle} for available job titles.
   * @param yearsOfExperience the years of experience of the employee to search for, must be non-negative.
   * @return the matching employee, or null if none is found.
   */
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
  
  /**
   * Returns the employee that matches the given employee object. If no matching employee is found, null is returned.
   * @param employee the employee to search for, can be null.
   * @return the matching employee, or null if none is found.
   */
  public Employee getEmployee(Employee employee) {
    if (this.employees == null || employee == null) return null;
    
    for (Employee e : this.employees) {
      if (e != null && e.equals(employee)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a set of employees that have the specified job title. If no matching employees are found, an empty set 
   * is returned.
   * @param jobTitle the job title to search for, can be null. See {@link JobTitle} for available job titles.
   * @return a set of employees with the specified job title, or an empty set if none are found.
   */
  public Set<Employee> getEmployees(JobTitle jobTitle) {
    if (this.employees == null || this.employees.isEmpty() || jobTitle == null) return Set.of();
    
    Set<Employee> employees = new HashSet<>();
    for (Employee e : this.employees) {
      if (e != null && e.getJobTitle().equals(jobTitle)) employees.add(e);
    }
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified employee from the set of employees. If the employee is successfully removed, true is returned.
   * If the employee is not found or the input is invalid, false is returned.
   * @param employee the employee to remove, can be null.
   * @return true if the employee was removed, false otherwise.
   */
  public boolean removeEmployee(Employee employee) {
    if (this.employees == null || employee == null) return false;
    
    return this.employees.remove(employee);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the given array of working days. Null or empty arrays are represented as "[]".
   * @param dates the array of working days, can be null or contain null elements.
   * @return a string representation of the working days array.
   */
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
  
  /**
   * Prints the working schedule of the specified employee. If the employee is null, no output is produced.
   * @param employee the employee whose working schedule is to be printed, can be null.
   */
  public void printEmployeeWorkingSchedule(Employee employee) {
    if (employee == null) return;
    
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
  
  /**
   * Prints the working schedules of the specified set of employees. If the set is null or empty, a message is printed 
   * indicating no employees were found.
   * @param employees the set of employees whose working schedules are to be printed, can be null or empty.
   */
  public void printEmployeesWorkingSchedule(Set<Employee> employees) {
    if (employees == null || employees.isEmpty()) {
      System.out.println("No employees found");
      return;
    };
    
    for (Employee e : employees) {
      if (e != null) printEmployeeWorkingSchedule(e);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the specified days off for the given employee. The days off are represented as a range from the first to the 
   * second element in the array. If the employee is null, the days off array is null or empty, or the work days array 
   * is null, no changes are made.
   * @param employee the employee whose days off are to be set, can be null.
   * @param daysOff an array containing the start and end dates of the days off, can be null or empty.
   */
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
