package chapter12.project.api;

import chapter12.project.entity.employee.Employee;
import chapter12.project.entity.employee.JobTitle;
import chapter12.project.entity.enclosure.Enclosure;
import chapter12.project.service.EmployeeService;
import chapter12.project.service.EnclosureService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
  
  private EmployeeService employeeService;
  private EnclosureService enclosureService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EmployeeController(Scanner sc, EmployeeService employeeService, EnclosureService enclosureService) {
    this.sc = sc;
    this.employeeService = employeeService;
    this.enclosureService = enclosureService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageEmployees() {
    for (;;) {
      System.out.printf("\nEmployees service:\n");
      System.out.println("1. Show all Employees");
      System.out.println("2. Add Employee");
      System.out.println("3. Find Employee");
      System.out.println("4. Edit Employee");
      System.out.println("5. Remove Employee");
      System.out.println("6. Employees schedule");
      System.out.println("7. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printEmployees(employeeService.getAllEmployees());
          break;
        case 2:
          addEmployee();
          break;
        case 3:
          findEmployee();
          break;
        case 4:
          editEmployee();
          break;
        case 5:
          removeEmployee();
          break;
        case 6:
          manageSchedule();
          break;
        case 7:
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 7) {
        System.out.println();
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void printEmployees(List<Employee> employees) {
    if (employees == null || employees.isEmpty()) {
      System.out.println("No employees were added");
      return;
    }
    
    for (Employee e : employees) {
      if (e != null) System.out.println(e);
    }
  }
 
  //-------------------------------------------------------------------------------------------------------------------
  
  private void addEmployee() {
    // read name
    String name = Util.readEmployeeName(sc);
    sc.nextLine();
    LocalDate birthDate = Util.readEmployeeBirthDate(sc);
    // read years of experience
    int yearsOfExperience = Util.readEmployeeIntField(sc, "employee years of experience: ");
    // read job title
    JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
    sc.nextLine();
    Employee employee = new Employee(name, jobTitle, yearsOfExperience, birthDate);
    
    // add employee to all the related enclosures
    List<Enclosure> enclosures = enclosureService.getAllEnclosures();
    if (enclosures.isEmpty()) {
      System.out.printf("No enclosures found for employee job title '%s'. Employee cannot be added\n", jobTitle);
      return;
    }
    for (Enclosure e : enclosures) {
      if (e.getEnclosureType().getEmployeeJobTitles().contains(jobTitle)) {
        if (e.addEmployee(employee)) {
          System.out.printf("Employee '%s' added to enclosure '%s'\n", employee, e.getEnclosureType());
        } else {
          System.out.printf("Employee '%s' could not be added to enclosure '%s'\n", employee, e.getEnclosureType());
        }
      }
    }
    employeeService.addEmployees(employee);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void findEmployee() {
    for (;;) {
      System.out.printf("\nSearch by:\n");
      System.out.println("1. Employee name");
      System.out.println("2. Employee job title");
      System.out.println("3. Detailed search");
      System.out.println("4. Return to employee menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = Util.readEmployeeName(sc);
          List<Employee> employees = employeeService.getEmployee(name);
          if (employees.isEmpty()) System.out.printf("Employee '%s' not found!\n", name);
          else printEmployees(employees);
        }
        case 2 -> {
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          List<Employee> employees = employeeService.getEmployees(jobTitle);
          if (employees.isEmpty()) System.out.printf("No employees(s) found for job title '%s'!\n", jobTitle);
          else printEmployees(employees);
        }
        case 3 -> {
          String name = Util.readEmployeeName(sc);
          int yearsOfExperience = Util.readEmployeeIntField(sc, "employee years of experience: ");
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Employee employee = employeeService.getEmployee(name, jobTitle, yearsOfExperience);
          if (employee == null) System.out.println("Employee not found");
          else System.out.printf("Employee found: '%s'\n", employee);
        }
        case 4 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice > 0 && choice < 5) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void editEmployeeDetails(Employee employee) {
    // change name
    if (Util.readEditEmployee(sc, null, "name").equals("Y")) {
      String newName = Util.readEmployeeName(sc);
      employee.setName(newName);
    }
    // change jobTitle
    if (Util.readEditEmployee(sc, null, "job title").equals("Y")) {
      JobTitle newJobTitle = Util.readEmployeeJobTitle(sc);
      employee.setJobTitle(newJobTitle);
    }
    // change yearsOfExperience
    if (Util.readEditEmployee(sc, null, "years of experience").equals("Y")) {
      int newYearsOfExperience = Util.readEmployeeIntField(sc, "employee years of experience: ");
      employee.setYearsOfExperience(newYearsOfExperience);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void editEmployee() {
    for (;;) {
      System.out.printf("\nEdit by:\n");
      System.out.println("1. Name");
      System.out.println("2. Detailed search");
      System.out.println("3. Return to employee menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = Util.readEmployeeName(sc);
          List<Employee> employees = employeeService.getEmployee(name);
          if (!employees.isEmpty()) {
            System.out.printf("'%d' employees found with name '%s'\n", employees.size(), name);
            for (Employee e : employees) {
              if (Util.readEditEmployee(sc, e, null).equals("Y")) editEmployeeDetails(e);
            }
          } else System.out.printf("Employee with name '%s' not found\n", name);
        }
        case 2 -> {
          String name = Util.readEmployeeName(sc);
          int yearsOfExperience = Util.readEmployeeIntField(sc, "employee years of experience: ");
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Employee employee = employeeService.getEmployee(name, jobTitle, yearsOfExperience);
          if (employee != null) {
            editEmployeeDetails(employee);
          } else System.out.println("Employee not found");
        }
        case 3 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice > 0 && choice < 4) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeEmployeeFromEnclosure(Enclosure enclosure, Employee employee) {
    if (enclosure == null) {
      System.out.printf("The enclosure for employee '%s' does not exist in the park\n", employee.getName());
    } else {
      if (enclosureService.removeEmployeeFromEnclosure(enclosure, employee)) {
        System.out.printf("Employee '%s' removed from the enclosure '%s'\n", employee, enclosure.getEnclosureType());
      } else {
        System.out.println("Employee could not be removed from the enclosure");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeEmployeeFromEnclosure(List<Enclosure> enclosure, Employee employee) {
    if (enclosure == null || enclosure.isEmpty()) {
      System.out.printf("The enclosure for employee '%s' does not exist in the park\n", employee.getName());
    } else {
      for (Enclosure e : enclosure) {
        removeEmployeeFromEnclosure(e, employee);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeEmployee(String name, JobTitle jobTitle, int yearsOfExperience) {
    removeEmployee(employeeService.getEmployee(name, jobTitle, yearsOfExperience));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeEmployee(Employee employee) {
    if (employee == null) {
      System.out.println("Employee not found");
      return;
    }
    
    // remove employee from the list
    if (employeeService.removeEmployee(employee)) {
      System.out.println("Employee removed");
      // remove employee from the related enclosure
      List<Enclosure> enclosures = enclosureService.getEnclosure(employee);
      removeEmployeeFromEnclosure(enclosures, employee);
    }
    else System.out.printf("Employee '%s' could not be removed\n", employee);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeEmployee() {
    for (;;) {
      System.out.printf("\nRemove by:\n");
      System.out.println("1. Employee name");
      System.out.println("2. Employee job title");
      System.out.println("3. Detailed search");
      System.out.println("5. Return to employee menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = Util.readEmployeeName(sc);
          List<Employee> employees = employeeService.getEmployee(name);
          for (Employee e : employees) {
            removeEmployee(e);
          }
        }
        case 2 -> {
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          List<Employee> employees = employeeService.getEmployees(jobTitle);
          if (employees.isEmpty()) {
            System.out.printf("No employees(s) found for job title '%s'\n",  jobTitle);
          } else {
            for (Employee e : employees) {
              removeEmployee(e);
            }
          }
        }
        case 3 -> {
          String name = Util.readEmployeeName(sc);
          int yearsOfExperience = Util.readEmployeeIntField(sc, "employee years of experience: ");
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          removeEmployee(name, jobTitle, yearsOfExperience);
        }
        case 4 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice > 0 && choice < 5) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void manageSchedule() {
    for (;;) {
      System.out.printf("\nEmployees schedule management:\n");
      System.out.println("1. Show employees schedule");
      System.out.println("2. Set day off");
      System.out.println("3. Return to employee menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> printEmployeesSchedule();
        case 2 -> setEmployeesDaysOff();
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 3) {
        System.out.println();
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void printEmployeesSchedule() {
      for (;;) {
        System.out.printf("\nShow weekly schedule by:\n");
        System.out.println("1. All employees");
        System.out.println("2. Employee name");
        System.out.println("3. Employee job title");
        System.out.println("4. Detailed search");
        System.out.println("5. Return to employee menu");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
          case 1 -> employeeService.printEmployeesWorkingSchedule(employeeService.getAllEmployees());
          case 2 -> {
            String name = Util.readEmployeeName(sc);
            List<Employee> employees = employeeService.getEmployee(name);
            if (employees.isEmpty()) {
              System.out.printf("Employee with name '%s' not found\n", name);
            } else {
              employeeService.printEmployeesWorkingSchedule(employees);
            }
          }
          case 3 -> {
            JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
            List<Employee> employees = employeeService.getEmployees(jobTitle);
            if (employees.isEmpty()) {
              System.out.printf("No employees(s) found for job title '%s'!\n", jobTitle);
            } else {
              employeeService.printEmployeesWorkingSchedule(employees);
            }
          }
          case 4 -> {
            String name = Util.readEmployeeName(sc);
            int yearsOfExperience = Util.readEmployeeIntField(sc, "employee years of experience: ");
            JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
            Employee employee = employeeService.getEmployee(name, jobTitle, yearsOfExperience);
            if (employee == null) {
              System.out.println("Employee not found");
            } else {
              employeeService.printEmployeeWorkingSchedule(employee);
            }
          }
          case 5 -> { }
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice > 0 && choice < 6) break;
      }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void setEmployeeDaysOff(Employee employee) {
    DayOfWeek dayOfWeek = Util.readEmployeeDayOfWeek(sc);
    int numberOfDays = Util.readEmployeeIntField(sc, "number of days off: ");
    LocalDate[] daysOff = employee.daysOff(dayOfWeek, numberOfDays); // calculate days off (from -> to)
    employeeService.setDaysOff(employee, daysOff);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void setEmployeesDaysOff() {
    for (;;) {
      System.out.printf("\nSet by:\n");
      System.out.println("1. Name");
      System.out.println("2. Detailed search");
      System.out.println("3. Return to employee menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = Util.readEmployeeName(sc);
          List<Employee> employees = employeeService.getEmployee(name);
          if (employees.isEmpty()) {
            System.out.printf("Employee with name '%s' not found\n", name);
          } else {
            System.out.printf("'%d' employees found with name '%s'\n", employees.size(), name);
            for (Employee e : employees) {
              if (Util.readEditEmployee(sc, e, "schedule").equals("Y")) setEmployeeDaysOff(e);
            }
          }
        }
        case 2 -> {
          String name = Util.readEmployeeName(sc);
          int yearsOfExperience = Util.readEmployeeIntField(sc, "employee years of experience: ");
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Employee employee = employeeService.getEmployee(name, jobTitle, yearsOfExperience);
          if (employee == null) System.out.println("Employee not found");
          else setEmployeeDaysOff(employee);
        }
        case 3 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice > 0 && choice < 4) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
