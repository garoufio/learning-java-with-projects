package chapter13.project.api;

import chapter13.project.collections.EmployeeComparator;
import chapter13.project.entity.employee.Employee;
import chapter13.project.entity.employee.JobTitle;
import chapter13.project.entity.enclosure.Enclosure;
import chapter13.project.service.EmployeeService;
import chapter13.project.service.EnclosureService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * The EmployeeController class is responsible for managing employees in the DinosaurCase system. It provides methods 
 * to add, edit, remove, and view employees, as well as manage their schedules.
 */
public class EmployeeController {
  
  private EmployeeService employeeService;
  private EnclosureService enclosureService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs an EmployeeController object. It initializes the controller with the Employee and Enclosure services and 
   * a Scanner object for user input.
   * @param sc Scanner for user input.
   * @param employeeService EmployeeService for managing employees.
   * @param enclosureService EnclosureService for managing enclosures.
   */
  public EmployeeController(Scanner sc, EmployeeService employeeService, EnclosureService enclosureService) {
    this.sc = sc;
    this.employeeService = employeeService;
    this.enclosureService = enclosureService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Manages the main menu for employee-related operations, allowing the user to view, add, find, edit, and remove 
   * employees, as well as manage their schedules.
   */
  public void manageEmployees() {
    for (;;) {
      System.out.print("""
      
      Employees service:
      1. Show all Employees
      2. Add Employee
      3. Find Employee
      4. Edit Employee
      5. Remove Employee
      6. Employees schedule
      7. Return to main menu
      Enter your choice:\t"""
      );
      int choice = sc.nextInt();
      sc.nextLine(); // Consume the newline character after nextInt()
      switch (choice) {
        case 1 -> printEmployees(sortEmployees(employeeService.getAllEmployees(), null));
        case 2 -> addEmployee();
        case 3 -> findEmployee();
        case 4 -> editEmployee();
        case 5 -> removeEmployee();
        case 6 -> manageSchedule();
        case 7 -> System.out.println("Returning to main menu...");
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 7) {
        System.out.println();
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the list of employees to the console. If the list is empty or null, it displays a message indicating that 
   * no employees were added.
   * @param employees Collection of Employee objects to be printed.
   */
  private void printEmployees(Collection<Employee> employees) {
    if (employees == null || employees.isEmpty()) {
      System.out.println("No employees were added");
      return;
    }
    
    for (Employee e : employees) {
      if (e != null) System.out.println(e);
    }
  }
 
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds a new employee by reading the necessary details from the user and assigning the employee to the appropriate 
   * enclosure(s).
   */
  private void addEmployee() {
    // read name
    String name = Util.readEmployeeName(sc);
    LocalDate birthDate = Util.readEmployeeBirthDate(sc);
    // read years of experience
    int yearsOfExperience = Util.readEmployeeIntField(sc, "employee years of experience: ");
    // read job title
    JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
    Employee employee = new Employee(name, jobTitle, yearsOfExperience, birthDate);
    
    // add employee to all the related enclosures
    Set<Enclosure> enclosures = enclosureService.getAllEnclosures();
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
  
  /**
   * Finds an employee based on user input, allowing searches by name, job title, or detailed criteria.
   * The method interacts with the user through the console and prints the search results.
   */
  private void findEmployee() {
    for (;;) {
      System.out.printf("""
      
      Search by:
      1. Employee name
      2. Employee job title
      3. Detailed search
      4. Return to employee menu
      System.out.print("Enter your choice:\t"""
      );
      int choice = sc.nextInt();
      sc.nextLine(); // Consume the newline character after nextInt()
      switch (choice) {
        case 1 -> {
          String name = Util.readEmployeeName(sc);
          Set<Employee> employees = employeeService.getEmployee(name);
          if (employees.isEmpty()) System.out.printf("Employee '%s' not found!\n", name);
          else printEmployees(employees);
        }
        case 2 -> {
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Set<Employee> employees = employeeService.getEmployees(jobTitle);
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
  
  /**
   * Edits the details of the given employee by prompting the user for changes to the name, job title, and years of 
   * experience.
   * @param employee the employee whose details are to be edited.
   */
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
  
  /**
   * Allows the user to edit an employee's details by choosing to search by name or using a detailed search.
   * The method interacts with the user through the console and updates the employee's information accordingly.
   */
  private void editEmployee() {
    for (;;) {
      System.out.print("""
      
      Edit by:
      1. Name
      2. Detailed search
      3. Return to employee menu
      System.out.print("Enter your choice:\t"""
      );
      int choice = sc.nextInt();
      sc.nextLine(); // Consume the newline character after nextInt()
      switch (choice) {
        case 1 -> {
          String name = Util.readEmployeeName(sc);
          Set<Employee> employees = employeeService.getEmployee(name);
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
  
  /**
   * Removes the specified employee from the given enclosure. If the enclosure does not exist or the removal fails, 
   * appropriate messages are displayed.
   * @param enclosure the enclosure from which the employee is to be removed.
   * @param employee the employee to be removed from the enclosure.
   */
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
  
  /**
   * Removes the specified employee from the given list of enclosures. If the list is empty or the removal fails, 
   * appropriate messages are displayed.
   * @param enclosure the set of enclosures from which the employee is to be removed.
   * @param employee the employee to be removed from the list of enclosures.
   */
  public void removeEmployeeFromEnclosure(Set<Enclosure> enclosure, Employee employee) {
    if (enclosure == null || enclosure.isEmpty()) {
      System.out.printf("The enclosure for employee '%s' does not exist in the park\n", employee.getName());
    } else {
      for (Enclosure e : enclosure) {
        removeEmployeeFromEnclosure(e, employee);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the employee matching the specified name, job title, and years of experience. If no such employee is found, 
   * an appropriate message is displayed.
   * @param name the name of the employee to be removed.
   * @param jobTitle the job title of the employee to be removed.
   * @param yearsOfExperience the years of experience of the employee to be removed.
   */
  private void removeEmployee(String name, JobTitle jobTitle, int yearsOfExperience) {
    removeEmployee(employeeService.getEmployee(name, jobTitle, yearsOfExperience));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified employee from the DinosaurCare system. If the employee is not found or the removal fails, 
   * appropriate messages are displayed.
   * @param employee the employee to be removed from the system.
   */
  private void removeEmployee(Employee employee) {
    if (employee == null) {
      System.out.println("Employee not found");
      return;
    }
    
    // remove employee from the list
    if (employeeService.removeEmployee(employee)) {
      System.out.println("Employee removed");
      // remove employee from the related enclosure
      Set<Enclosure> enclosures = enclosureService.getEnclosure(employee);
      removeEmployeeFromEnclosure(enclosures, employee);
    }
    else System.out.printf("Employee '%s' could not be removed\n", employee);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to remove an employee by various criteria such as name, job title, or detailed search.
   */
  private void removeEmployee() {
    for (;;) {
      System.out.print("""
      
      Remove by:
      1. Employee name
      2. Employee job title
      3. Detailed search
      4. Return to employee menu
      Enter your choice:\t"""
      );
      int choice = sc.nextInt();
      sc.nextLine(); // Consume the newline character after nextInt()
      switch (choice) {
        case 1 -> {
          String name = Util.readEmployeeName(sc);
          Set<Employee> employees = employeeService.getEmployee(name);
          for (Employee e : employees) {
            removeEmployee(e);
          }
        }
        case 2 -> {
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Set<Employee> employees = employeeService.getEmployees(jobTitle);
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
  
  /**
   * Manages the schedule of employees, allowing the user to view the weekly schedule and set days off for employees.
   */
  private void manageSchedule() {
    for (;;) {
      System.out.print("""
      
      Employees schedule management:
      1. Show employees schedule
      2. Set day off
      3. Return to employee menu
      Enter your choice:\t"""
      );
      int choice = sc.nextInt();
      sc.nextLine(); // Consume the newline character after nextInt()
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
  
  /**
   * Displays the weekly schedule of employees based on various criteria such as all employees, employee name, job title, 
   * or detailed search.
   */
  private void printEmployeesSchedule() {
      for (;;) {
        System.out.print("""
        
        Show weekly schedule by:
        1. All employees
        2. Employee name
        3. Employee job title
        4. Detailed search
        5. Return to employee menu
        Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> employeeService.printEmployeesWorkingSchedule(employeeService.getAllEmployees());
          case 2 -> {
            String name = Util.readEmployeeName(sc);
            Set<Employee> employees = employeeService.getEmployee(name);
            if (employees.isEmpty()) {
              System.out.printf("Employee with name '%s' not found\n", name);
            } else {
              employeeService.printEmployeesWorkingSchedule(employees);
            }
          }
          case 3 -> {
            JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
            Set<Employee> employees = employeeService.getEmployees(jobTitle);
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
  
  /**
   * Sets the days off for the specified employee by prompting the user for the day of the week and the number of days off.
   * The calculated days off are then applied to the employee's schedule.
   * @param employee the employee for whom to set the days off.
   */
  private void setEmployeeDaysOff(Employee employee) {
    DayOfWeek dayOfWeek = Util.readEmployeeDayOfWeek(sc);
    int numberOfDays = Util.readEmployeeIntField(sc, "number of days off: ");
    LocalDate[] daysOff = employee.daysOff(dayOfWeek, numberOfDays); // calculate days off (from -> to)
    employeeService.setDaysOff(employee, daysOff);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the days off for multiple employees based on user input, either by name or detailed search.
   * The user is prompted to select the method of search and then specify the days off for each employee found.
   */
  private void setEmployeesDaysOff() {
    for (;;) {
      System.out.print("""
      
      Set by:
      1. Name
      2. Detailed search
      3. Return to employee menu
      Enter your choice: \t"""
      );
      int choice = sc.nextInt();
      sc.nextLine(); // Consume the newline character after nextInt()
      switch (choice) {
        case 1 -> {
          String name = Util.readEmployeeName(sc);
          Set<Employee> employees = employeeService.getEmployee(name);
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
  
  /**
   * Sorts a collection of employees based on the provided comparator. If the comparator is null, the default comparator 
   * defined in the EmployeeComparator class is used. The method returns a sorted list of employees.
   * @param employees the collection of employees to be sorted.
   * @param comparator the comparator used to determine the order of the employees. If null, the default comparator 
   * is used.
   * @return a list of employees sorted according to the specified comparator. If the input collection is null or empty, 
   * an empty list is returned.
   */
  public List<Employee> sortEmployees(Collection<Employee> employees, Comparator<Employee> comparator) {
    if (employees == null || employees.isEmpty()) return List.of();
    
    List<Employee> sortedEmployees = new ArrayList<>(employees);
    Collections.sort(sortedEmployees, comparator == null ? EmployeeComparator.getDefaultComparator() : comparator);
    return sortedEmployees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
