package chapter12.project;

import chapter12.project.entity.employee.*;
import chapter12.project.entity.ticket.*;
import chapter12.project.entity.vehicle.*;
import chapter12.project.entity.dinosaur.*;
import chapter12.project.entity.enclosure.*;
import chapter12.project.exception.DinosaurIllException;
import chapter12.project.exception.EnclosureBreachException;
import chapter12.project.service.*;
import chapter12.project.api.*;
import chapter12.project.entity.park.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  
  public static final int MAX_DAILY_VISITORS = 300;
  public static final int MAX_DINOSAURS = 50;
  public static final int MAX_EMPLOYEES = 50;
  public static final int MAX_ENCLOSURES = 20;
  public static final int MAX_VEHICLES = 40;
  public static final int MAX_DAILY_SPECIAL_EVENTS = 5;
  
  private DinosaurCareSystemController dinosaurCareSystemController;
  private DinosaurController dinosaurController;
  private EmployeeController employeeController;
  private TicketController ticketController;
  private EnclosureController enclosureController;
  private SpecialEventsController specialEventsController;
  private VehicleController vehicleController;
  private Park park;
  // Use scanner for reading input from the user
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public App() {
    sc = new Scanner(System.in);
    
    // creation of Services
    DinosaurCareSystemService dinosaurCareSystemService = new DinosaurCareSystemService();
    DinosaurService dinosaurService = new DinosaurService(createDinosaurs());
    EmployeeService employeeService = new EmployeeService(createEmployees());
    EnclosureService enclosureService = new EnclosureService(createEnclosures(dinosaurService, employeeService));
    TicketService ticketService = new TicketService(createTickets());
    SpecialEventsService eventsService = new SpecialEventsService();
    VehicleService vehicleService = new VehicleService(createVehicles());
    // creation of controllers
    dinosaurCareSystemController = new DinosaurCareSystemController(sc, dinosaurCareSystemService);
    dinosaurController = new DinosaurController(sc, dinosaurService, enclosureService);
    employeeController = new EmployeeController(sc, employeeService, enclosureService);
    ticketController = new TicketController(sc, ticketService);
    enclosureController = new EnclosureController(sc, enclosureService);
    specialEventsController = new SpecialEventsController(sc, eventsService, ticketService);
    vehicleController = new VehicleController(sc, vehicleService);
    
    park = new Park();
    // TODO: create Activities
    // TODO: use InputException handling
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
    System.out.println("5. Manage Vehicles");
    System.out.println("6. Handle Special Events");
    System.out.println("7. Park Management");
    System.out.println("8. Exit");
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
        vehicleController.manageVehicles();
        break;
      case 6:
        specialEventsController.handleSpecialEvents();
        break;
      case 7:
        // TODO: park management
        // TODO: dinosaur activities management
        break;
      case 8:
        System.out.println("Exiting...");
        System.exit(0);
      default:
        System.out.println("Invalid choice. Please try again.");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Objects initialization
  //-------------------------------------------------------------------------------------------------------------------
  
  private static List<Dinosaur> createDinosaurs() {
    List<Dinosaur> dinosaurs = new ArrayList<>();
    
    dinosaurs.add(
        new TerrestrialDinosaur(
            "Claire", LocalDate.of(2020, 5, 15), DinosaurType.CARNIVORE, DinosaurSpecies.VELOCIRAPTOR, DinosaurSize.MEDIUM, 48,217, 65
        )
    );
    dinosaurs.add(
        new TerrestrialDinosaur(
            "Jago", LocalDate.of(1996, 10, 9), DinosaurType.CARNIVORE, DinosaurSpecies.TYRANNOSAURUS, DinosaurSize.BIG, 77,412, 48
        )
    );
    dinosaurs.add(
        new TerrestrialDinosaur(
            "Margot", LocalDate.of(1999, 4, 23), DinosaurType.HERBIVORE, DinosaurSpecies.BRACHIOSAURUS, DinosaurSize.BIG, 67,408, 35
        )
    );
    dinosaurs.add(
        new TerrestrialDinosaur(
            "Hermes", LocalDate.of(2021, 3, 30), DinosaurType.HERBIVORE, DinosaurSpecies.STEGOSAURUS, DinosaurSize.BIG, 51, 334, 27
        )
    );
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
            getEmployeesByEnclosure(employeeService, EnclosureType.RAPTORS_PARK),
            98
        )
    );
    // add Free Park enclosure and the related dinosaurs and employees
    enclosures.add(new Enclosure(
            EnclosureType.FREE_PARK,
            SafetyLevel.MEDIUM,
            getDinosaursByEnclosure(dinosaurService, EnclosureType.FREE_PARK),
            getEmployeesByEnclosure(employeeService, EnclosureType.FREE_PARK),
            95
        )
    );
    // add Central Building enclosure and the related employees
    enclosures.add(new Enclosure(
            EnclosureType.CENTRAL_BUILDING,
            SafetyLevel.LOW,
            getDinosaursByEnclosure(dinosaurService, EnclosureType.CENTRAL_BUILDING),
            getEmployeesByEnclosure(employeeService, EnclosureType.CENTRAL_BUILDING),
            90
        )
    );
    // add Tickets Kiosk enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.TICKETS_KIOSK,
        SafetyLevel.LOW,
        getDinosaursByEnclosure(dinosaurService, EnclosureType.TICKETS_KIOSK),
        getEmployeesByEnclosure(employeeService, EnclosureType.TICKETS_KIOSK),
        85
    ));
    // add Vet Center enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.VET_CENTER,
        SafetyLevel.MEDIUM,
        getDinosaursByEnclosure(dinosaurService, EnclosureType.VET_CENTER),
        getEmployeesByEnclosure(employeeService, EnclosureType.VET_CENTER),
        92
    ));
    // add Food Store enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.FOOD_STORE,
        SafetyLevel.LOW,
        getDinosaursByEnclosure(dinosaurService, EnclosureType.FOOD_STORE),
        getEmployeesByEnclosure(employeeService, EnclosureType.FOOD_STORE),
        85
    ));
    // add Parking Lot enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.PARKING_LOT,
        SafetyLevel.LOW,
        getDinosaursByEnclosure(dinosaurService, EnclosureType.PARKING_LOT),
        getEmployeesByEnclosure(employeeService, EnclosureType.PARKING_LOT),
        80
    ));
    // add Surveillance Building enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.SURVEILLANCE_BUILDING,
        SafetyLevel.MEDIUM,
        getDinosaursByEnclosure(dinosaurService, EnclosureType.SURVEILLANCE_BUILDING),
        getEmployeesByEnclosure(employeeService, EnclosureType.SURVEILLANCE_BUILDING),
        90
    ));
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Employee> createEmployees() {
    List<Employee>  employees = new ArrayList<>();
    
    employees.add(new Employee("Mike", JobTitle.ZOOLOGIST, 7, LocalDate.of(1986, 1, 10)));
    employees.add(new Employee("Elsa", JobTitle.ZOOLOGIST, 17, LocalDate.of(1979, 10, 17)));
    employees.add(new Employee("Laura", JobTitle.CURATOR, 2, LocalDate.of(1999, 5, 19)));
    employees.add(new Employee("Loren", JobTitle.CURATOR, 2, LocalDate.of(2001, 5, 3)));
    employees.add(new Employee("Maria", JobTitle.CURATOR, 7, LocalDate.of(1998, 2, 25)));
    employees.add(new Employee("Mathew", JobTitle.CURATOR, 10, LocalDate.of(1992, 8, 31)));
    employees.add(new Employee("Juan", JobTitle.VETERINARIAN, 9, LocalDate.of(1990, 11, 12)));
    employees.add(new Employee("Jessie", JobTitle.VETERINARIAN, 12, LocalDate.of(1990, 4, 21)));
    employees.add(new Employee("Oscar", JobTitle.VETERINARY_TECHNICIAN, 8, LocalDate.of(1992, 12, 1)));
    employees.add(new Employee("Philip", JobTitle.GENERAL_DIRECTOR, 11, LocalDate.of(1986, 7, 10)));
    employees.add(new Employee("Lisa", JobTitle.EVENTS_MANAGER, 12, LocalDate.of(1995, 11, 11)));
    employees.add(new Employee("David", JobTitle.HR_MANAGER, 15, LocalDate.of(1986, 9, 29)));
    employees.add(new Employee("Jimmy", JobTitle.HR_ASSISTANT, 3, LocalDate.of(1997, 7, 6)));
    employees.add(new Employee("Angie", JobTitle.PUBLIC_RELATIONS_MANAGER, 14, LocalDate.of(1996, 1, 14)));
    employees.add(new Employee("Antony", JobTitle.FINANCE_MANAGER, 17, LocalDate.of(1988, 9, 20)));
    employees.add(new Employee("Larry", JobTitle.SECURITY_MANAGER, 24, LocalDate.of(1980, 2, 11)));
    employees.add(new Employee("James", JobTitle.SECURITY_OFFICER, 12, LocalDate.of(1991, 1, 1)));
    employees.add(new Employee("Nina", JobTitle.SECURITY_OFFICER, 9, LocalDate.of(1994, 8, 25)));
    employees.add(new Employee("Henry", JobTitle.PARK_MANAGER, 7, LocalDate.of(1999, 12, 5)));
    employees.add(new Employee("Sophie", JobTitle.TICKETS_MANAGER, 4, LocalDate.of(2002, 6, 7)));
    employees.add(new Employee("Sousan", JobTitle.TICKETS_MANAGER, 8, LocalDate.of(2000, 3, 3)));
    employees.add(new Employee("Paula", JobTitle.CLEANING_STAFF, 12, LocalDate.of(1989, 6, 16)));
    employees.add(new Employee("Laura", JobTitle.CLEANING_STAFF, 10, LocalDate.of(1992, 4, 22)));
    employees.add(new Employee("Fenia", JobTitle.CLEANING_STAFF, 5, LocalDate.of(1998, 10, 30)));
    employees.add(new Employee("Robert", JobTitle.MAINTENANCE_STAFF, 6, LocalDate.of(1995, 5, 9)));
    employees.add(new Employee("Howard", JobTitle.MAINTENANCE_STAFF, 17, LocalDate.of(1983, 11, 19)));
    
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
  
  private List<Vehicle> createVehicles() {
    List<Vehicle> vehicles = new ArrayList<>();
    
    vehicles.add(
        new Car("Toyota", "RAV4", 2020, "White", 4, 180)
    );
    vehicles.add(
        new Car("Toyota", "RAV4", 2022, "White", 4, 200)
    );
    vehicles.add(
        new Car("Ford", "Raptor", 2024, "White", 4, 200)
    );
    vehicles.add(
        new Car("Ford", "Raptor", 2025, "White", 4, 200)
    );
    vehicles.add(
        new Car("Jeep", "Wrangler", 2021, "White", 4, 190)
    );
    vehicles.add(
        new Car("Jeep", "Wrangler", 2021, "White", 4, 190)
    );
    vehicles.add(
        new Truck("Volvo", "FMX", 2021, "White", 6, 5, 130, 15000)
    );
    vehicles.add(
        new Truck("Volvo", "FMX", 2024, "White", 6, 5, 130, 15000)
    );
    vehicles.add(
        new Truck("Volvo", "FH 16", 2023, "White", 6, 5, 130, 20000)
    );
    vehicles.add(
        new Truck("Volvo", "FH 16", 2025, "White", 6, 5, 130, 20000)
    );
    vehicles.add(
        new Motorcycle("Suzuki", "V-Strom 600", 2022, "White", 2, 180)
    );
    vehicles.add(
        new Motorcycle("Suzuki", "V-Strom 600", 2022, "White", 2, 180)
    );
    vehicles.add(
        new Helicopter("Agusta", "AW109 GrandNew", 2025, "White", 8, 283, 681, 3050, 3200)
    );
    vehicles.add(
        new Helicopter("Agusta", "AW09", 2025, "White", 8, 260, 800, 2900, 1500)
    );
    
    return vehicles;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Park management
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkParkStatus() {
    if (park.getParkStatus()) System.out.println("The Park is open until " + Park.CLOSING_HOUR.format(Util.TIME_FORMAT));
    else System.out.println("The Park will welcome its guests tomorrow from " + Park.OPENING_HOUR.format(Util.TIME_FORMAT));
    System.out.println();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void parkManagement() {
    for (;;) {
      System.out.println("Park Management Menu:");
      System.out.println("1. Dinosaur Healthcare Check");
      System.out.println("2. Enclosure Security Check");
      System.out.println("3. Back to Main Menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
//          try {
//            dinosaurCareSystem.checkDinosaursHealth();
//          } catch (DinosaurIllException e) {
//            System.out.println("ATTENTION: " + e.getMessage());
//          }
        }
        case 2 -> {
//          try {
//            dinosaurCareSystem.checkEnclosuresSecurity();
//          } catch (EnclosureBreachException e) {
//            System.out.println("ATTENTION: " + e.getMessage());
//          }
        }
        case 3 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice >= 1 && choice <= 3) break;
    }
    System.out.println();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Utility methods
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Employee> getEmployeesByEnclosure(EmployeeService employeeService, EnclosureType enclosureType) {
    List<Employee> employees = new ArrayList<>();
    for (JobTitle jobTitle : enclosureType.getEmployeeJobTitles()) {
      employees.addAll(employeeService.getEmployees(jobTitle));
    }
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Dinosaur> getDinosaursByEnclosure(DinosaurService dinosaurService, EnclosureType enclosureType) {
    List<Dinosaur> dinosaurs = new ArrayList<>();
    for (DinosaurSpecies dinosaurSpecies : enclosureType.getDinosaurSpecies()) {
      dinosaurs.addAll(dinosaurService.getDinosaurs(dinosaurSpecies));
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
