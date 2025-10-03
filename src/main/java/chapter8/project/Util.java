package chapter8.project;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.UUID;

public class Util {
  
  //-------------------------------------------------------------------------------------------------------------------
  // Dinosaurs
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readDinosaurName(Scanner sc) {
    System.out.print("Enter dinosaur name: ");
    String name = sc.next();
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static int readDinosaurAge(Scanner sc) {
    System.out.print("Enter dinosaur age: ");
    int age = sc.nextInt();
    return age;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static DinosaurType readDinosaurType(Scanner sc) {
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
  
  protected static DinosaurSpecies readDinosaurSpecies(Scanner sc) {
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
  // Tickets
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readVisitorField(Scanner sc, String field) {
    System.out.printf("Enter visitor's %s: ", field);
    return sc.next();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static Visitor readVisitor(Scanner sc) {
    String firstname = readVisitorField(sc, "firstname");
    sc.nextLine();
    String lastname = readVisitorField(sc, "lastname");
    sc.nextLine();
    String phoneNumber = readVisitorField(sc, "phoneNumber");
    
    return new Visitor(firstname, lastname, phoneNumber);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static TicketType readTicketType(Scanner sc) {
    TicketType ticketType;
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
  
  protected static LocalDate readTicketDate(Scanner sc) {
    for (;;) {
      System.out.print("Ticket for today (Y/N)? ");
      String answer = sc.next();
      if (answer.equalsIgnoreCase("Y")) {
        return LocalDate.now();
      }
      else if (answer.equalsIgnoreCase("N")) {
        System.out.print("Please enter a date: ");
        return LocalDate.parse(sc.next());
      }
      System.out.println("Invalid choice. Please try again.");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static UUID readTicketUUID(Scanner sc) {
    System.out.print("Enter ticket ID: ");
    String ticketID = sc.next();
    return UUID.fromString(ticketID);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Employees
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readEmployeeName(Scanner sc) {
    System.out.print("Enter employee name: ");
    return sc.next();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static int readEmployeeYearsOfExperience(Scanner sc) {
    System.out.print("Enter employee years of experience: ");
    return sc.nextInt();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static JobTitle readEmployeeJobTitle(Scanner sc) {
    JobTitle jobTitle;
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
  // Enclosures
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static EnclosureType readEnclosureType(Scanner sc) {
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
        case 1 -> EnclosureType.RAPTORS;
        case 2 -> EnclosureType.FLYING;
        case 3 -> EnclosureType.POOL;
        case 4 -> EnclosureType.FREE;
        default -> {
          System.out.println("Invalid choice for enclosure type. Please try again.");
          yield null;
        }
      };
    } while (enclosureType == null);
    
    return enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Special events
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static LocalTime readSpecialEventTime(Scanner sc) {
    System.out.print("Please enter a time: ");
    return LocalTime.parse(sc.next());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static LocalDate readSpecialEventDate(Scanner sc) {
    System.out.print("Please enter a date: ");
    return LocalDate.parse(sc.next());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
    protected static String readSpecialEventField(Scanner sc, String field) {
    System.out.printf("Enter event's %s: ", field);
    return sc.next();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static EventType readSpecialEventType(Scanner sc) {
    EventType eventType;
    do {
      System.out.println("Select the event's type: ");
      int count = 1;
      for (EventType t : EventType.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      eventType = switch (choice) {
        case 1 -> EventType.SCHOOL;
        case 2 -> EventType.GROUP;
        case 3 -> EventType.SUMMER_CAMP;
        case 4 -> EventType.VIP;
        case 5 -> EventType.EMERGENCY;
        default -> {
          System.out.println("Invalid choice for Event type. Please try again.");
          yield null;
        }
      };
    } while (eventType == null);
    
    return eventType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
