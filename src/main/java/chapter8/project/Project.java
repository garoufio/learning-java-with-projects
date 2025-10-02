package chapter8.project;

import java.util.Scanner;

public class Project {
  
  public static final int MAX_VISITORS = 100;
  public static final int MAX_DINOSAURS = 15;
  public static final int MAX_EMPLOYEES = 20;
  public static final int MAX_ENCLOSURES = 5;
  public static final int MAX_SPECIAL_EVENTS = 100;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private DinosaurController dinosaurController;
  private EmployeeController employeeController;
  private TicketController ticketController;
  private EnclosureController enclosureController;
  private SpecialEventsController specialEventsController;
  private Park park;
  // Use scanner for reading input from the user
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Project() {
    sc = new Scanner(System.in);
    // creation of Services
    DinosaurService dinosaurService = new DinosaurService(createDinosaurs());
    EmployeeService employeeService = new EmployeeService(createEmployees());
    TicketService ticketService = new TicketService(createTickets());
    EnclosureService enclosureService = new EnclosureService(createEnclosures(dinosaurService));
    SpecialEventsService eventsService = new SpecialEventsService();
    // creation of controllers
    dinosaurController = new DinosaurController(sc, dinosaurService, enclosureService);
    employeeController = new EmployeeController(sc, employeeService);
    ticketController = new TicketController(sc, ticketService);
    enclosureController = new EnclosureController(sc, enclosureService);
    specialEventsController = new SpecialEventsController(sc, eventsService);
    park = new Park();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args) {
    Project project = new Project();
    
    project.start();
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
        dinosaurController.manageDinosaurs();
        break;
      case 2:
        employeeController.manageEmployees();
        break;
      case 3:
        ticketController.manageTickets();
        break;
      case 4:
        enclosureController.manageEnclosures();
        break;
      case 5:
        checkParkStatus();
        break;
      case 6:
        specialEventsController.handleSpecialEvents();
        break;
      case 7:
        System.out.println("Exiting...");
        System.exit(0);
      default:
        System.out.println("Invalid choice. Please try again.");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Objects initialization
  //-------------------------------------------------------------------------------------------------------------------
  
  private Dinosaur[] createDinosaurs() {
    Dinosaur[] dinosaurs = new Dinosaur[Project.MAX_DINOSAURS];
    
    dinosaurs[0] = new Dinosaur("Claire", 5, DinosaurType.CARNIVORE, DinosaurSpecies.VELOCIRAPTOR);
    dinosaurs[1] = new Dinosaur("Jago", 9, DinosaurType.CARNIVORE, DinosaurSpecies.TYRANNOSAURUS);
    dinosaurs[2] = new Dinosaur("Margot", 12, DinosaurType.HERBIVORE, DinosaurSpecies.STEGOSAURUS);
    dinosaurs[3] = new Dinosaur("Hermes", 2, DinosaurType.CARNIVORE, DinosaurSpecies.SPINOSAURUS);
    
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Enclosure[] createEnclosures(DinosaurService dinosaurService) {
    Enclosure[] enclosures = new Enclosure[Project.MAX_ENCLOSURES];
    
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
  
  private Employee[] createEmployees() {
    Employee[]  employees = new Employee[Project.MAX_EMPLOYEES];
    
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
    Ticket[] tickets = new Ticket[Project.MAX_VISITORS];
    
    tickets[0] = new Ticket(TicketType.CHILD, new Visitor("Steve", "Cole", "6987898678"));
    tickets[1] = new Ticket(TicketType.CHILD, new Visitor("Moana", "Cole", "6987898678"));
    tickets[2] = new Ticket(TicketType.ADULT, new Visitor("Henry", "Cole", "6987898678"));
    tickets[3] = new Ticket(TicketType.ADULT, new Visitor("Ariana", "Harris", "6986564123"));
    tickets[4] = new Ticket(TicketType.ELDER, new Visitor("Louis", "Farrel", "6944455661"));
    tickets[5] = new Ticket(TicketType.DISABLED_PERSON, new Visitor("Sousan", "Ronald", "6955858578"));
    
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Park management
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkParkStatus() {
    if (park.getParkStatus()) System.out.println("The Park is open until " + Park.CLOSING_HOUR);
    else System.out.println("The Park will welcome its guests tomorrow from " + Park.OPENING_HOUR);
    System.out.println();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
