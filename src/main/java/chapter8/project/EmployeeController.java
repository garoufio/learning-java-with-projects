package chapter8.project;

import java.util.Scanner;

public class EmployeeController {
  
  private EmployeeService employeeService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EmployeeController(Scanner sc, EmployeeService employeeService) {
    this.sc = sc;
    this.employeeService = employeeService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageEmployees() {
    for (;;) {
      System.out.printf("\nEmployees service:\n");
      System.out.println("1. Show all Employees");
      System.out.println("2. Add Employee");
      System.out.println("3. Find Employee");
      System.out.println("4. Remove Employee");
      System.out.println("5. Return to main menu");
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
          removeEmployee();
          break;
        case 5:
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 5) {
        System.out.println();
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void printEmployees(Employee... employees) {
    if (employees == null) {
      System.out.println("No employees were added");
      return;
    }
    
    for (Employee e : employees) {
      if (e == null) continue;
      
      System.out.println(e);
    }
  }
 
  //-------------------------------------------------------------------------------------------------------------------
  
  private void addEmployee() {
    // read name
    String name = Util.readEmployeeName(sc);
    sc.nextLine();
    // read years of experience
    int yearsOfExperience = Util.readEmployeeYearsOfExperience(sc);
    // read job title
    JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
    sc.nextLine();
    employeeService.addEmployees(new Employee(name, jobTitle, yearsOfExperience));
    System.out.println("Employee added!");
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
          Employee employee = employeeService.getEmployee(name);
          if (employee == null) System.out.printf("Employee '%s' not found!\n", name);
          else System.out.printf("Employee found: '%s'\n", employee);
        }
        case 2 -> {
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Employee[] employees = employeeService.getEmployees(jobTitle);
          if (employees == null) System.out.printf("No employees(s) found for job title '%s'!\n", jobTitle.name());
          else printEmployees(employees);
        }
        case 3 -> {
          String name = Util.readEmployeeName(sc);
          int yearsOfExperience = Util.readEmployeeYearsOfExperience(sc);
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Employee employee = employeeService.getEmployee(new Employee(name, jobTitle, yearsOfExperience));
          if (employee == null) System.out.println("Employee not found!");
          else System.out.printf("Employee found: '%s'\n", employee);
        }
        case 4 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 5) break;
    }
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
          boolean isRemoved = employeeService.removeEmployee(name);
          if (isRemoved) System.out.println("Employee removed!");
          else System.out.printf("Employee '%s' not found!\n", name);
        }
        case 2 -> {
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Employee[] employees = employeeService.getEmployees(jobTitle);
          if (employees == null || employees.length == 0) {
            System.out.printf("No employees(s) found for job title '%s'!\n",  jobTitle.name());
          } else {
            for (Employee e : employees) {
              if (employeeService.removeEmployee(e)) {
                System.out.println("Employee removed: " + e.toString());
              } else {
                System.out.printf("Failed to remove employee: '%s'!\n", e.toString());
              }
            }
          }
        }
        case 3 -> {
          String name = Util.readEmployeeName(sc);
          int yearsOfExperience = Util.readEmployeeYearsOfExperience(sc);
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Employee employee = employeeService.getEmployee(new Employee(name, jobTitle, yearsOfExperience));
          boolean isRemoved = employeeService.removeEmployee(employee);
          if (isRemoved) System.out.printf("Employee removed: '%s'\n", employee);
          else System.out.printf("Failed to remove employee: '%s'!\n", employee);
        }
        case 4 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 5) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
