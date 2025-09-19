package chapter8;

import java.util.Arrays;
import java.util.Scanner;

public class Project {
  
  public static final int MAX_VISITORS = 100;
  public static final int MAX_DINOSAURS = 5;
  public static final int MAX_EMPLOYEES = 10;
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
    
    employees[0] = new Employee("Mike", null, 7);
    employees[1] = new Employee("Lisa", null, 12);
    employees[2] = new Employee("Laura", null, 2);
    employees[3] = new Employee("Philip", null, 11);
    employees[4] = new Employee("Juan", null, 9);
    
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
    enclosures[1] = new Enclosure(EnclosureType.FREE);
    
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
    System.out.println("4. Check Park Status");
    System.out.println("5. Handle Special Events");
    System.out.println("6. Exit");
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
        checkParkStatus();
        break;
      case 5:
        handleSpecialEvents();
        break;
      case 6:
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
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Tickets service
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageTickets() {
    // TODO
  }
  
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
