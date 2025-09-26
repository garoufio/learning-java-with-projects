package chapter8;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Project {
  
  public static final int MAX_VISITORS = 100;
  public static final int MAX_DINOSAURS = 5;
  public static final int MAX_EMPLOYEES = 20;
  public static final int MAX_ENCLOSURES = 5;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private DinosaurService dinosaurService;
  private EmployeeService employeeService;
  private TicketService ticketService;
  private EnclosureService enclosureService;
  // Use scanner for reading input from the user
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Project() {
    sc = new Scanner(System.in);
    dinosaurService = new DinosaurService(createDinosaurs());
    employeeService = new EmployeeService(createEmployees());
    ticketService = new TicketService(createTickets());
    enclosureService = new EnclosureService(createEnclosures());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args) {
    Project project = new Project();
    
    project.start();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Dinosaur[] createDinosaurs() {
    Dinosaur[] dinosaurs = new Dinosaur[MAX_DINOSAURS];
    
    dinosaurs[0] = new Dinosaur("Claire", 5, DinosaurType.CARNIVORE, DinosaurSpecies.VELOCIRAPTOR);
    dinosaurs[1] = new Dinosaur("Jago", 9, DinosaurType.CARNIVORE, DinosaurSpecies.TYRANNOSAURUS);
    dinosaurs[2] = new Dinosaur("Margot", 12, DinosaurType.HERBIVORE, DinosaurSpecies.STEGOSAURUS);
    dinosaurs[3] = new Dinosaur("Hermes", 2, DinosaurType.CARNIVORE, DinosaurSpecies.SPINOSAURUS);
    
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Employee[] createEmployees() {
    Employee[]  employees = new Employee[MAX_EMPLOYEES];
    
    employees[0] = new Employee("Mike", JobTitle.ZOOLOGIST, 7);
    employees[1] = new Employee("Lisa", JobTitle.EVENTS_MANAGER, 12);
    employees[2] = new Employee("Laura", JobTitle.CURATOR, 2);
    employees[3] = new Employee("Philip", JobTitle.GENERAL_DIRECTOR, 11);
    employees[4] = new Employee("Juan", JobTitle.VETERINARIAN, 9);
    employees[5] = new Employee("Loren", JobTitle.CURATOR, 2);
    employees[6] = new Employee("Oscar", JobTitle.VETERINARY_TECHNICIAN, 8);
    employees[7] = new Employee("Maria", JobTitle.CURATOR, 7);
    employees[8] = new Employee("Mathew", JobTitle.CURATOR, 10);
    employees[9] = new Employee("David", JobTitle.HR_MANAGER, 15);
    employees[10] = new Employee("Jimmy", JobTitle.HR_ASSISTANT, 3);
    employees[11] = new Employee("Angie", JobTitle.PUBLIC_RELATIONS_MANAGER, 14);
    
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Ticket[] createTickets() {
    Ticket[] tickets = new Ticket[MAX_VISITORS];
    
    tickets[0] = new Ticket(TicketType.CHILD, new Visitor("Steve", "Cole", "6987898678"));
    tickets[1] = new Ticket(TicketType.CHILD, new Visitor("Moana", "Cole", "6987898678"));
    tickets[2] = new Ticket(TicketType.ADULT, new Visitor("Henry", "Cole", "6987898678"));
    tickets[3] = new Ticket(TicketType.ADULT, new Visitor("Ariana", "Harris", "6986564123"));
    tickets[4] = new Ticket(TicketType.ELDER, new Visitor("Louis", "Farrel", "6944455661"));
    tickets[5] = new Ticket(TicketType.DISABLED_PERSON, new Visitor("Sousan", "Ronald", "6955858578"));
    
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Enclosure[] createEnclosures() {
    Enclosure[] enclosures = new Enclosure[MAX_ENCLOSURES];
    
    enclosures[0] = new Enclosure(EnclosureType.RAPTORS);
    enclosures[0].setDinosaurs(
        dinosaurService.getDinosaurs(DinosaurSpecies.VELOCIRAPTOR)[0],
        dinosaurService.getDinosaurs(DinosaurSpecies.TYRANNOSAURUS)[0]
    );
    enclosures[1] = new Enclosure(EnclosureType.FREE);
    enclosures[1].setDinosaurs(
        dinosaurService.getDinosaurs(DinosaurType.HERBIVORE)[0],
        dinosaurService.getDinosaurs(DinosaurSpecies.SPINOSAURUS)[0]
    );
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void start() {
    /* this is the main loop of the application. It will keep running until the user decides to exit */
    while (true) {
      displayMenu();
      int choice = sc.nextInt();
      handleMenuChoice(choice);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void displayMenu() {
    System.out.println("Welcome to Mesozoic Eden Park Manager!");
    System.out.println("1. Manage Dinosaurs");
    System.out.println("2. Manage Park Employees");
    System.out.println("3. Manage Tickets");
    System.out.println("4. Manage Enclosures");
    System.out.println("5. Check Park Status");
    System.out.println("6. Handle Special Events");
    System.out.println("7. Exit");
    System.out.print("Enter your choice: ");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleMenuChoice(int choice) {
    switch (choice) {
      case 1:
        manageDinosaurs();
        break;
      case 2:
        manageEmployees();
        break;
      case 3:
        manageTickets();
        break;
      case 4:
        manageEnclosures();
        break;
      case 5:
        checkParkStatus();
        break;
      case 6:
        handleSpecialEvents();
        break;
      case 7:
        System.out.println("Exiting...");
        System.exit(0);
      default:
        System.out.println("Invalid choice. Please try again.");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Dinosaurs service
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageDinosaurs() {
    for (;;) {
      System.out.printf("\nDinosaurs service:\n");
      System.out.println("1. Show all Dinosaurs");
      System.out.println("2. Add Dinosaur");
      System.out.println("3. Find Dinosaur");
      System.out.println("4. Remove Dinosaur");
      System.out.println("5. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printDinosaurs(dinosaurService.getAllDinosaurs());
          break;
        case 2:
          addDinosaur();
          break;
        case 3:
          findDinosaur();
          break;
        case 4:
          removeDinosaur();
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
  
  private void printDinosaurs(Dinosaur... dinosaurs) {
    if (dinosaurs == null) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    for (Dinosaur d : dinosaurs) {
      if (d == null) continue;
      
      System.out.println(d);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private String readDinosaurName() {
    System.out.print("Enter dinosaur name: ");
    String name = sc.next();
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int readDinosaurAge() {
    System.out.print("Enter dinosaur age: ");
    int age = sc.nextInt();
    return age;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private DinosaurType readDinosaurType() {
    DinosaurType type = null;
    do {
      System.out.println("Select the dinosaur type: ");
      int count = 1;
      for (DinosaurType t : DinosaurType.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      type = switch (choice) {
        case 1 -> DinosaurType.CARNIVORE;
        case 2 -> DinosaurType.HERBIVORE;
        case 3 -> DinosaurType.OMNIVORE;
        default -> {
          System.out.println("Invalid choice for Dinosaur type. Please try again.");
          yield null;
        }
      };
    } while (type == null);
    
    return type;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private DinosaurSpecies readDinosaurSpecies() {
    DinosaurSpecies species = null;
    do {
      System.out.println("Select the dinosaur species: ");
      int count = 1;
      for (DinosaurSpecies s : DinosaurSpecies.values()) {
        System.out.printf("%d. %s\n", count++, s.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      species = switch (choice) {
        case 1 -> DinosaurSpecies.TYRANNOSAURUS;
        case 2 -> DinosaurSpecies.TRICERATOPS;
        case 3 -> DinosaurSpecies.VELOCIRAPTOR;
        case 4 -> DinosaurSpecies.STEGOSAURUS;
        case 5 -> DinosaurSpecies.BRACHIOSAURUS;
        case 6 -> DinosaurSpecies.SPINOSAURUS;
        case 7 -> DinosaurSpecies.PARASAUROLOPHUS;
        case 8 -> DinosaurSpecies.ANKYLOSAURUS;
        default -> {
          System.out.println("Invalid choice. Please try again.");
          yield null;
        }
      };
    } while (species == null);
    
    return species;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void addDinosaur() {
    // read name
    String name = readDinosaurName();
    // read age
    int age = readDinosaurAge();
    // read dinosaur type
    DinosaurType type = readDinosaurType();
    sc.nextLine();
    // read dinosaur species
    DinosaurSpecies species = readDinosaurSpecies();
    sc.nextLine();
    dinosaurService.addDinosaurs(new Dinosaur(name, age, type, species));
    System.out.println("Dinosaur added!");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void findDinosaur() {
    for (;;) {
      System.out.printf("\nSearch by:\n");
      System.out.println("1. Dinosaur name");
      System.out.println("2. Dinosaur type");
      System.out.println("3. Dinosaur species");
      System.out.println("4. Detailed search");
      System.out.println("5. Return to dinosaur menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = readDinosaurName();
          Dinosaur dinosaur = dinosaurService.getDinosaur(name);
          if (dinosaur == null) System.out.printf("Dinosaur '%s' not found!\n", name);
          else System.out.printf("Dinosaur found: '%s'\n", dinosaur.toString());
        }
        case 2 -> {
          DinosaurType type = readDinosaurType();
          Dinosaur[] dinosaurs = dinosaurService.getDinosaurs(type);
          if (dinosaurs == null) System.out.printf("No dinosaur(s) found for type '%s'!\n", type.name());
          else printDinosaurs(dinosaurs);
        }
        case 3 -> {
          DinosaurSpecies species = readDinosaurSpecies();
          Dinosaur[] dinosaurs = dinosaurService.getDinosaurs(species);
          if (dinosaurs == null) System.out.printf("No dinosaur(s) found for species '%s'!\n", species.name());
          else printDinosaurs(dinosaurs);
        }
        case 4 -> {
          String name = readDinosaurName();
          int age = readDinosaurAge();
          DinosaurType type = readDinosaurType();
          DinosaurSpecies species = readDinosaurSpecies();
          Dinosaur dinosaur = dinosaurService.getDinosaur(new Dinosaur(name, age, type, species));
          if (dinosaur == null) System.out.println("Dinosaur not found!");
          else System.out.printf("Dinosaur found: '%s'\n", dinosaur.toString());
        }
        case 5 -> { break; }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 6) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeDinosaur() {
    for (;;) {
      System.out.printf("\nRemove by:\n");
      System.out.println("1. Dinosaur name");
      System.out.println("2. Dinosaur type");
      System.out.println("3. Dinosaur species");
      System.out.println("4. Detailed search");
      System.out.println("5. Return to dinosaur menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = readDinosaurName();
          boolean isRemoved = dinosaurService.removeDinosaur(name);
          if (isRemoved) System.out.println("Dinosaur removed!");
          else System.out.printf("Dinosaur '%s' not found!\n", name);
        }
        case 2 -> {
          DinosaurType type = readDinosaurType();
          Dinosaur[] dinosaurs = dinosaurService.getDinosaurs(type);
          if (dinosaurs == null || dinosaurs.length == 0) {
            System.out.printf("No dinosaur(s) found for type '%s'!\n",  type.name());
          } else {
            for (Dinosaur dinosaur : dinosaurs) {
              if (dinosaurService.removeDinosaur(dinosaur)) {
                System.out.println("Dinosaur removed: " + dinosaur.toString());
              } else {
                System.out.printf("Failed to remove dinosaur: '%s'!\n", dinosaur.toString());
              }
            }
          }
        }
        case 3 -> {
          DinosaurSpecies species = readDinosaurSpecies();
          Dinosaur[] dinosaurs = dinosaurService.getDinosaurs(species);
          if (dinosaurs == null || dinosaurs.length == 0) {
            System.out.printf("No dinosaur(s) found for species '%s'!\n",  species.name());
          }
          else {
            for (Dinosaur dinosaur : dinosaurs) {
              if (dinosaurService.removeDinosaur(dinosaur)) {
                System.out.println("Dinosaur removed: " + dinosaur.toString());
              } else {
                System.out.printf("Failed to remove dinosaur: '%s'!\n", dinosaur.toString());
              }
            }
          }
        }
        case 4 -> {
          String name = readDinosaurName();
          int age = readDinosaurAge();
          DinosaurType type = readDinosaurType();
          DinosaurSpecies species = readDinosaurSpecies();
          Dinosaur dinosaur = new Dinosaur(name, age, type, species);
          if (dinosaurService.removeDinosaur(dinosaur)) {
            System.out.println("Dinosaur removed: " + dinosaur.toString());
          } else {
            System.out.printf("Failed to remove dinosaur: '%s'!\n'", dinosaur.toString());
          }
        }
        case 5 -> { break; }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 6) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Employees service
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
  
  private String readEmployeeName() {
    System.out.print("Enter employee name: ");
    String name = sc.next();
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int readEmployeeYearsOfExperience() {
    System.out.print("Enter employee years of experience: ");
    int yearsOfExperience = sc.nextInt();
    return yearsOfExperience;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private JobTitle readEmployeeJobTitle() {
    JobTitle jobTitle = null;
    do {
      System.out.println("Select the employee's job title: ");
      int count = 1;
      for (JobTitle t : JobTitle.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      jobTitle = switch (choice) {
        case 1 -> JobTitle.CURATOR;
        case 2 -> JobTitle.ZOOKEEPER;
        case 3 -> JobTitle.VETERINARIAN;
        case 4 -> JobTitle.VETERINARY_TECHNICIAN;
        case 5 -> JobTitle.GENERAL_DIRECTOR;
        case 6 -> JobTitle.OPERATIONS_DIRECTOR;
        case 7 -> JobTitle.HR_MANAGER;
        case 8 -> JobTitle.HR_ASSISTANT;
        case 9 -> JobTitle.TICKETS_MANAGER;
        case 10 -> JobTitle.EVENTS_MANAGER;
        case 11 -> JobTitle.PUBLIC_RELATIONS_MANAGER;
        case 12 -> JobTitle.FINANCE_MANAGER;
        case 13 -> JobTitle.EDUCATOR;
        case 14 -> JobTitle.ZOOLOGIST;
        default -> {
          System.out.println("Invalid choice for Job Title type. Please try again.");
          yield null;
        }
      };
    } while (jobTitle == null);
    
    return jobTitle;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void addEmployee() {
    // read name
    String name = readEmployeeName();
    sc.nextLine();
    // read years of experience
    int yearsOfExperience = readEmployeeYearsOfExperience();
    // read job title
    JobTitle jobTitle = readEmployeeJobTitle();
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
          String name = readEmployeeName();
          Employee employee = employeeService.getEmployee(name);
          if (employee == null) System.out.printf("Employee '%s' not found!\n", name);
          else System.out.printf("Employee found: '%s'\n", employee.toString());
        }
        case 2 -> {
          JobTitle jobTitle = readEmployeeJobTitle();
          Employee[] employees = employeeService.getEmployees(jobTitle);
          if (employees == null) System.out.printf("No employees(s) found for job title '%s'!\n", jobTitle.name());
          else printEmployees(employees);
        }
        case 3 -> {
          String name = readEmployeeName();
          int yearsOfExperience = readEmployeeYearsOfExperience();
          JobTitle jobTitle = readEmployeeJobTitle();
          Employee employee = employeeService.getEmployee(new Employee(name, jobTitle, yearsOfExperience));
          if (employee == null) System.out.println("Employee not found!");
          else System.out.printf("Employee found: '%s'\n", employee.toString());
        }
        case 4 -> { break; }
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
          String name = readEmployeeName();
          boolean isRemoved = employeeService.removeEmployee(name);
          if (isRemoved) System.out.println("Employee removed!");
          else System.out.printf("Employee '%s' not found!\n", name);
        }
        case 2 -> {
          JobTitle jobTitle = readEmployeeJobTitle();
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
          String name = readEmployeeName();
          int yearsOfExperience = readEmployeeYearsOfExperience();
          JobTitle jobTitle = readEmployeeJobTitle();
          Employee employee = employeeService.getEmployee(new Employee(name, jobTitle, yearsOfExperience));
          boolean isRemoved = employeeService.removeEmployee(employee);
          if (isRemoved) System.out.printf("Employee removed: '%s'\n" + employee.toString());
          else System.out.printf("Failed to remove employee: '%s'!\n", employee.toString());
        }
        case 4 -> { break; }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 5) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Tickets service
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageTickets() {
    for (;;) {
      System.out.printf("\nTickets service:\n");
      System.out.println("1. Show all Tickets");
      System.out.println("2. Add Ticket");
      System.out.println("3. Find Ticket");
      System.out.println("4. Remove Ticket");
      System.out.println("5. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printTickets(ticketService.getAllTickets());
          break;
        case 2:
          addTicket();
          break;
        case 3:
          findTicket();
          break;
        case 4:
          removeTicket();
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
  
  private void printTickets(Ticket... tickets) {
    if (tickets == null || tickets.length == 0) {
      System.out.println("No Tickets were added!");
      return;
    };
    
    for (Ticket ticket : tickets) {
      if (ticket == null) continue;
      
      System.out.println(ticket);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void addTicket() {
    // read visitor's firstname, lastname and phone number
    Visitor visitor = readVisitor();
    sc.nextLine();
    // read ticket type
    TicketType ticketType = readTicketType();
    // read date
    LocalDate date = readTicketDate();
    sc.nextLine();
    ticketService.addTickets(new Ticket(ticketType, visitor, date));
    System.out.println("Ticket added!");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private String readVisitorField(String field) {
    System.out.printf("Enter visitor's %s: ", field);
    String str =  sc.next();
    return str;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Visitor readVisitor() {
    String firstname = readVisitorField("firstname");
    sc.nextLine();
    String lastname = readVisitorField("lastname");
    sc.nextLine();
    String phoneNumber = readVisitorField("phoneNumber");
    //sc.nextLine();
    
    return new Visitor(firstname, lastname, phoneNumber);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private TicketType readTicketType() {
    TicketType ticketType = null;
    do {
      System.out.println("Select the ticket type: ");
      int count = 1;
      for (TicketType t : TicketType.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      ticketType = switch (choice) {
        case 1 -> TicketType.INFANT;
        case 2 -> TicketType.CHILD;
        case 3 -> TicketType.ADULT;
        case 4 -> TicketType.ELDER;
        case 5 -> TicketType.DISABLED_PERSON;
        case 6 -> TicketType.SCHOOL;
        case 7 -> TicketType.FORBIDDEN;
        case 8 -> TicketType.VIP;
        default -> {
          System.out.println("Invalid choice for ticket type. Please try again.");
          yield null;
        }
      };
    } while (ticketType == null);
    
    return ticketType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private LocalDate readTicketDate() {
    for (;;) {
      System.out.print("Ticket for today (Y/N)? ");
      String answer = sc.next();
      if (answer.equalsIgnoreCase("Y")) {
        return LocalDate.now();
      }
      else if (answer.equalsIgnoreCase("N")) {
        System.out.print("Please enter a date: ");
        LocalDate date = LocalDate.parse(sc.next());
        return date;
      }
      System.out.println("Invalid choice. Please try again.");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private UUID readTicketUUID() {
    System.out.print("Enter ticket ID: ");
    String ticketID = sc.next();
    return UUID.fromString(ticketID);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void findTicket() {
    for (;;) {
      System.out.printf("\nSearch by:\n");
      System.out.println("1. Ticket ID");
      System.out.println("2. Ticket Name");
      System.out.println("3. Ticket Phone");
      System.out.println("4. Ticket Date");
      System.out.println("5. Ticket Type");
      System.out.println("6. Detail search");
      System.out.println("7. Return to ticket menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          UUID id = readTicketUUID();
          sc.nextLine();
          Ticket ticket = ticketService.getTicket(id);
          if (ticket == null) System.out.printf("Ticket with ID '%s' not found!\n", id);
          else System.out.printf("Ticket found: '%s'\n", ticket);
        }
        case 2 -> {
          String firstname = readVisitorField("firstname");
          String lastname = readVisitorField("lastname");
          Visitor visitor = new Visitor(firstname, lastname, "");
          Ticket[] tickets = ticketService.getTickets(visitor);
          if (tickets == null) System.out.printf(
              "No ticket(s) found for visitor '%s'!\n",
              visitor.firstname() + " " + visitor.lastname()
          );
          else printTickets(tickets);
        }
        case 3 -> {
          String phone = readVisitorField("phone number");
          Ticket[] tickets = ticketService.getTickets(phone);
          if (tickets == null) System.out.printf("No ticket(s) found for phone number '%s'!\n", phone);
          else printTickets(tickets);
        }
        case 4 -> {
          LocalDate date = readTicketDate();
          Ticket[] tickets = ticketService.getTickets(date);
          if (tickets == null) System.out.printf("No ticket(s) found on '%s'!\n", date.toString());
          else printTickets(tickets);
        }
        case 5 -> {
          TicketType ticketType = readTicketType();
          Ticket[] tickets = ticketService.getTickets(ticketType);
          if (tickets == null) System.out.printf("No ticket(s) found for ticket type '%s'!\n", ticketType.name());
          else printTickets(tickets);
        }
        case 6 -> {
          Visitor visitor = readVisitor();
          TicketType ticketType = readTicketType();
          sc.nextLine();
          LocalDate date = readTicketDate();
          Ticket ticket = ticketService.getTicket(new Ticket(ticketType, visitor, date));
          if (ticket == null) System.out.println("No ticket found!");
          else System.out.println("Ticket found: " + ticket);
        }
        case 7 -> { break; }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 8) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeTicket() {
    for (;;) {
      System.out.printf("\nRemove by:\n");
      System.out.println("1. Ticket ID");
      System.out.println("2. Ticket Name");
      System.out.println("3. Ticket Phone");
      System.out.println("4. Ticket Date");
      System.out.println("5. Ticket Type");
      System.out.println("6. Detail search");
      System.out.println("7. Return to ticket menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          UUID id = readTicketUUID();
          sc.nextLine();
          Ticket ticket = ticketService.getTicket(id);
          if (ticketService.removeTicket(ticket)) System.out.println("Ticket removed");
          else System.out.println("Ticket not found");
        }
        case 2 -> {
          String firstname = readVisitorField("firstname");
          String lastname = readVisitorField("lastname");
          Visitor visitor = new Visitor(firstname, lastname, "");
          if (ticketService.removeTicket(visitor)) System.out.println("Ticket(s) removed");
          else System.out.printf("Ticket not found for  visitor '%s'\n", visitor);
        }
        case 3 -> {
          String phone = readVisitorField("phone number");
          Ticket[] tickets = ticketService.getTickets(phone);
          if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
          else System.out.printf("No ticket(s) found for phone number '%s'\n", phone);
        }
        case 4 -> {
          LocalDate date = readTicketDate();
          Ticket[] tickets = ticketService.getTickets(date);
          if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
          else System.out.printf("No ticket(s) found on '%s'\n", date.toString());
        }
        case 5 -> {
          TicketType ticketType = readTicketType();
          Ticket[] tickets = ticketService.getTickets(ticketType);
          if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
          else System.out.printf("No ticket(s) found for ticket type '%s'\n", ticketType.name());
        }
        case 6 -> {
          Visitor visitor = readVisitor();
          sc.nextLine();
          TicketType ticketType = readTicketType();
          sc.nextLine();
          LocalDate date = readTicketDate();
          sc.nextLine();
          Ticket ticket = ticketService.getTicket(new Ticket(ticketType, visitor, date));
          if (ticketService.removeTicket(ticket)) System.out.println("Ticket removed");
          else System.out.println("No ticket found\n");
        }
        case 7 -> { break; }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 8) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Enclosures service
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageEnclosures() {
    for (;;) {
      System.out.printf("\nEnclosures service:\n");
      System.out.println("1. Show all Enclosures");
      System.out.println("2. Add Enclosure");
      System.out.println("3. Find Enclosure");
      System.out.println("4. Remove Enclosure");
      System.out.println("5. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printEnclosures(enclosureService.getAllEnclosures());
          break;
        case 2:
          addEnclosure();
          break;
        case 3:
          findEnclosure();
          break;
        case 4:
          removeEnclosure();
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
  
  private void printEnclosures(Enclosure... enclosures) {
    if (enclosures == null) {
      System.out.println("No Enclosures were added!");
      return;
    }
    
    for (Enclosure e : enclosures) {
      if (e == null) continue;
      
      System.out.println(e);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void addEnclosure() {
    EnclosureType enclosureType = readEnclosureType();
    // TODO: add dinosaurs in the enclosure
    enclosureService.addEnclosure(new Enclosure(enclosureType));
    System.out.println("Enclosure added");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private EnclosureType readEnclosureType() {
    EnclosureType enclosureType = null;
    do {
      System.out.println("Select the enclosure type: ");
      int count = 1;
      for (EnclosureType e : EnclosureType.values()) {
        System.out.printf("%d. %s\n", count++, e.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      enclosureType = switch (choice) {
        case 1 -> EnclosureType.FREE;
        case 2 -> EnclosureType.RAPTORS;
        case 3 -> EnclosureType.FLYING;
        case 4 -> EnclosureType.POOL;
        default -> {
          System.out.println("Invalid choice for enclosure type. Please try again.");
          yield null;
        }
      };
    } while (enclosureType == null);
    
    return enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void findEnclosure() {
    for (;;) {
      System.out.printf("\nSearch by:\n");
      System.out.println("1. Enclosure Type");
      System.out.println("2. Dinosaur name");
      System.out.println("3. Dinosaur type");
      System.out.println("4. Dinosaur species");
      System.out.println("5. Detail search");
      System.out.println("6. Return to enclosure menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          EnclosureType enclosureType = readEnclosureType();
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType);
          if (enclosure == null) System.out.printf("No enclosure found for type '%s'\n", enclosureType);
          else System.out.println(enclosure);
        }
        case 2 -> {
          String dinosaurName = readDinosaurName();
          Enclosure enclosure = enclosureService.getEnclosure(dinosaurName);
          if (enclosure == null) System.out.printf("No enclosure found for dinosaur '%s'\n", dinosaurName);
          else System.out.println(enclosure);
        }
        case 3 -> {
          DinosaurType dinosaurType = readDinosaurType();
          Enclosure[] enclosures = enclosureService.getEnclosures(dinosaurType);
          if (enclosures == null) System.out.printf("No enclosure found for dinosaur type '%s'\n", dinosaurType.name());
          else printEnclosures(enclosures);
        }
        case 4 -> {
          DinosaurSpecies dinosaurSpecies = readDinosaurSpecies();
          Enclosure[] enclosures = enclosureService.getEnclosures(dinosaurSpecies);
          if (enclosures == null) System.out.printf("No enclosure found for dinosaur species '%s'\n", dinosaurSpecies.name());
          else printEnclosures(enclosures);
        }
        case 5 -> {
          EnclosureType enclosureType = readEnclosureType();
          String dinosaurName = readDinosaurName();
          DinosaurType dinosaurType = readDinosaurType();
          DinosaurSpecies dinosaurSpecies = readDinosaurSpecies();
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType, dinosaurType, dinosaurSpecies, dinosaurName);
          if (enclosure == null) System.out.println("No enclosure found");
          else System.out.println(enclosure);
        }
        case 6 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 7) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeEnclosure() {
    for (;;) {
      System.out.printf("\nRemove by:\n");
      System.out.println("1. Enclosure Type");
      System.out.println("2. Return to enclosure menu");
      System.out.println("Enter  your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          EnclosureType  enclosureType = readEnclosureType();
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType);
          if (enclosureService.removeEnclosure(enclosureType)) System.out.println("Enclosure removed");
          else System.out.printf("No enclosure found for type '%s'\n", enclosureType);
        }
        case 2 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 3) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Park management
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkParkStatus() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleSpecialEvents() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
