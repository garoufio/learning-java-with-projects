package chapter9.project;

import chapter9.project.entity.park.Park;
import chapter9.project.entity.dinosaur.*;
import chapter9.project.entity.employee.*;
import chapter9.project.entity.enclosure.*;
import chapter9.project.entity.ticket.*;
import chapter9.project.api.*;
import chapter9.project.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  
  public static final int MAX_DAILY_VISITORS = 300;
  public static final int MAX_DINOSAURS = 50;
  public static final int MAX_EMPLOYEES = 40;
  public static final int MAX_ENCLOSURES = 20;
  public static final int MAX_DAILY_SPECIAL_EVENTS = 5;
  
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
  
  public App() {
    sc = new Scanner(System.in);
    // creation of Services
    DinosaurService dinosaurService = new DinosaurService(createDinosaurs());
    EmployeeService employeeService = new EmployeeService(createEmployees());
    EnclosureService enclosureService = new EnclosureService(createEnclosures(dinosaurService, employeeService));
    TicketService ticketService = new TicketService(createTickets());
    SpecialEventsService eventsService = new SpecialEventsService();
    // creation of controllers
    dinosaurController = new DinosaurController(sc, dinosaurService, enclosureService);
    employeeController = new EmployeeController(sc, employeeService, enclosureService);
    ticketController = new TicketController(sc, ticketService);
    enclosureController = new EnclosureController(sc, enclosureService);
    specialEventsController = new SpecialEventsController(sc, eventsService, ticketService);
    park = new Park();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args) {
    App app = new App();
    app.start();
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
  
  private List<Dinosaur> createDinosaurs() {
    List<Dinosaur> dinosaurs = new ArrayList<>();
    
    dinosaurs.add(new Dinosaur(
        "Claire", 5, DinosaurType.CARNIVORE, DinosaurSpecies.VELOCIRAPTOR, DinosaurSize.MEDIUM)
    );
    dinosaurs.add(new Dinosaur(
        "Jago", 9, DinosaurType.CARNIVORE, DinosaurSpecies.TYRANNOSAURUS, DinosaurSize.BIG)
    );
    dinosaurs.add(new Dinosaur(
        "Margot", 12, DinosaurType.HERBIVORE, DinosaurSpecies.STEGOSAURUS, DinosaurSize.BIG)
    );
    dinosaurs.add(new Dinosaur(
        "Hermes", 2, DinosaurType.CARNIVORE, DinosaurSpecies.SPINOSAURUS, DinosaurSize.BIG)
    );
    
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Employee> getEmployeesByEnclosure(EmployeeService employeeService, EnclosureType enclosureType) {
    List<Employee> employees = new ArrayList<>();
    for (JobTitle jobTitle : enclosureType.getEmployeeJobTitles()) {
      for (Employee e : employeeService.getEmployees(jobTitle)) {
        employees.add(e);
      }
    }
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Dinosaur> getDinosaursByEnclosure(DinosaurService dinosaurService, EnclosureType enclosureType) {
    List<Dinosaur> dinosaurs = new ArrayList<>();
    for (DinosaurSpecies dinosaurSpecies : enclosureType.getDinosaurSpecies()) {
      for (Dinosaur d : dinosaurService.getDinosaurs(dinosaurSpecies)) {
        dinosaurs.add(d);
      }
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Enclosure> createEnclosures(DinosaurService dinosaurService, EmployeeService employeeService) {
    List<Enclosure> enclosures = new ArrayList<>();

    // add Raptors Park enclosure and the related dinosaurs and employees
    enclosures.add(new Enclosure(
        EnclosureType.RAPTORS_PARK,
        SafetyLevel.HIGH,
        getDinosaursByEnclosure(dinosaurService, EnclosureType.RAPTORS_PARK),
        getEmployeesByEnclosure(employeeService, EnclosureType.RAPTORS_PARK)
        )
    );
    // add Free Park enclosure and the related dinosaurs and employees
   enclosures.add(new Enclosure(
       EnclosureType.FREE_PARK,
       SafetyLevel.MEDIUM,
       getDinosaursByEnclosure(dinosaurService, EnclosureType.FREE_PARK),
       getEmployeesByEnclosure(employeeService, EnclosureType.FREE_PARK)
       )
   );
   // add Central Building enclosure and the related employees
   enclosures.add(new Enclosure(
           EnclosureType.CENTRAL_BUILDING,
           SafetyLevel.LOW,
           getDinosaursByEnclosure(dinosaurService, EnclosureType.CENTRAL_BUILDING),
           getEmployeesByEnclosure(employeeService, EnclosureType.CENTRAL_BUILDING)
       )
   );
   // add Tickets Kiosk enclosure and the related employees
   enclosures.add(new Enclosure(
       EnclosureType.TICKETS_KIOSK,
       SafetyLevel.LOW,
       getDinosaursByEnclosure(dinosaurService, EnclosureType.TICKETS_KIOSK),
       getEmployeesByEnclosure(employeeService, EnclosureType.TICKETS_KIOSK)
   ));
   // add Vet Center enclosure and the related employees
   enclosures.add(new Enclosure(
      EnclosureType.VET_CENTER,
      SafetyLevel.MEDIUM,
      getDinosaursByEnclosure(dinosaurService, EnclosureType.VET_CENTER),
      getEmployeesByEnclosure(employeeService, EnclosureType.VET_CENTER)
   ));
   // add Food Store enclosure and the related employees
   enclosures.add(new Enclosure(
      EnclosureType.FOOD_STORE,
      SafetyLevel.LOW,
      getDinosaursByEnclosure(dinosaurService, EnclosureType.FOOD_STORE),
      getEmployeesByEnclosure(employeeService, EnclosureType.FOOD_STORE)
   ));
   // add Parking Lot enclosure and the related employees
   enclosures.add(new Enclosure(
       EnclosureType.PARKING_LOT,
        SafetyLevel.LOW,
        getDinosaursByEnclosure(dinosaurService, EnclosureType.PARKING_LOT),
        getEmployeesByEnclosure(employeeService, EnclosureType.PARKING_LOT)
   ));
   // add Surveillance Building enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.SURVEILLANCE_BUILDING,
        SafetyLevel.MEDIUM,
        getDinosaursByEnclosure(dinosaurService, EnclosureType.SURVEILLANCE_BUILDING),
        getEmployeesByEnclosure(employeeService, EnclosureType.SURVEILLANCE_BUILDING)
    ));
   return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Employee> createEmployees() {
    List<Employee>  employees = new ArrayList<>();
    
    employees.add(new Employee("Mike", JobTitle.ZOOLOGIST, 7));
    employees.add(new Employee("Lisa", JobTitle.EVENTS_MANAGER, 12));
    employees.add(new Employee("Laura", JobTitle.CURATOR, 2));
    employees.add(new Employee("Philip", JobTitle.GENERAL_DIRECTOR, 11));
    employees.add(new Employee("Juan", JobTitle.VETERINARIAN, 9));
    employees.add(new Employee("Loren", JobTitle.CURATOR, 2));
    employees.add(new Employee("Oscar", JobTitle.VETERINARY_TECHNICIAN, 8));
    employees.add(new Employee("Maria", JobTitle.CURATOR, 7));
    employees.add(new Employee("Mathew", JobTitle.CURATOR, 10));
    employees.add(new Employee("David", JobTitle.HR_MANAGER, 15));
    employees.add(new Employee("Jimmy", JobTitle.HR_ASSISTANT, 3));
    employees.add(new Employee("Angie", JobTitle.PUBLIC_RELATIONS_MANAGER, 14));
    
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Ticket> createTickets() {
    List<Ticket> tickets = new ArrayList<>();
    
    tickets.add(
        new RegularTicket(
            TicketType.CHILD,
            new Visitor("Steve", "Cole", "6987898678"),
            true
        )
    );
    tickets.add(
        new RegularTicket(
            TicketType.CHILD,
            new Visitor("Moana", "Cole", "6987898678"),
            true
        )
    );
    tickets.add(
        new RegularTicket(
            TicketType.ADULT,
            new Visitor("Henry", "Cole", "6987898678"),
            true)
    );
    tickets.add(
        new Ticket(
            TicketType.ADULT,
            new Visitor("Ariana", "Harris", "6986564123")
        )
    );
    tickets.add(
        new RegularTicket(
            TicketType.ELDER,
            new Visitor("Louis", "Farrel", "6944455661"),
            false
        )
    );
    tickets.add(
        new SeasonTicket(
            TicketType.SEASON_6,
            new Visitor("Sousan", "Ronald", "6955858578"),
            LocalDate.of(2025, 11, 1)
        )
    );
    
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
