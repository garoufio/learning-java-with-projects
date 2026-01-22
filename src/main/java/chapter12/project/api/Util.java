package chapter12.project.api;

import chapter12.project.entity.dinosaur.*;
import chapter12.project.entity.employee.Employee;
import chapter12.project.entity.employee.JobTitle;
import chapter12.project.entity.enclosure.*;
import chapter12.project.entity.event.EventType;
import chapter12.project.entity.ticket.TicketType;
import chapter12.project.entity.ticket.Visitor;
import chapter12.project.entity.vehicle.Vehicle;
import chapter12.project.entity.vehicle.VehicleType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class Util {
  
  public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy HH:mm");
  public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
  public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
  
  //-------------------------------------------------------------------------------------------------------------------
  // Dinosaurs
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readEditDinosaur(Scanner sc, Dinosaur dinosaur, String field) {
    String answer;
    for (;;) {
      if (dinosaur == null) System.out.printf("Edit dinosaur's %s? (Y/N) ", field);
      else System.out.printf("Edit dinosaur '%s'? (Y/N) ", dinosaur);
      
      answer = sc.next();
      if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
        sc.nextLine();
        return answer.toUpperCase();
      } else {
        sc.nextLine();
        System.out.println("Invalid choice. Please try again");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readDinosaurName(Scanner sc) {
    sc.nextLine();
    System.out.print("Enter dinosaur name: ");
    return sc.nextLine();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static LocalDate readDinosaurBirthDate(Scanner sc) {
    sc.nextLine();
    System.out.print("Enter dinosaur birth date (YYYY-MM-DD): ");
    return LocalDate.parse(sc.next());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static int readDinosaurIntField(Scanner sc, String field) {
    sc.nextLine();
    System.out.printf("Enter dinosaur's %s: ", field);
    return sc.nextInt();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static boolean readDinosaurAmphibiousCapability(Scanner sc) {
    sc.nextLine();
    System.out.printf("Is the dinosaur amphibious? (true/false):");
    return sc.nextBoolean();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static DinosaurType readDinosaurType(Scanner sc) {
    sc.nextLine();
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
    sc.nextLine();
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
        case 9 -> DinosaurSpecies.PTEROSAUR;
        case 10 -> DinosaurSpecies.PLIOSAURS;
        default -> {
          System.out.println("Invalid choice. Please try again.");
          yield null;
        }
      };
    } while (species == null);
    
    return species;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static DinosaurSize readDinosaurSize(Scanner sc) {
    sc.nextLine();
    DinosaurSize size = null;
    do {
      System.out.println("Select the dinosaur size: ");
      int count = 1;
      for (DinosaurSize s : DinosaurSize.values()) {
        System.out.printf("%d. %s\n", count++, s.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      size = switch (choice) {
        case 1 -> DinosaurSize.SMALL;
        case 2 -> DinosaurSize.MEDIUM;
        case 3 -> DinosaurSize.BIG;
        default -> {
          System.out.println("Invalid choice. Please try again.");
          yield null;
        }
      };
    } while (size == null);
    
    return size;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Tickets
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readVisitorField(Scanner sc, String field) {
    System.out.printf("Enter visitor's %s: ", field);
    return sc.nextLine();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static Visitor readVisitor(Scanner sc) {
    sc.nextLine();
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
        case 9 -> TicketType.ALL_DAY;
        case 10 -> TicketType.SEASON_3;
        case 11 -> TicketType.SEASON_6;
        case 12 -> TicketType.SEASON_8;
        case 13 -> TicketType.SEASON_12;
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
      } else if (answer.equalsIgnoreCase("N")) {
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
  
  protected static boolean reserveParkingSpot(Scanner sc) {
    for (;;) {
      System.out.print("Do you want to reserve a parking spot (Y/N)? ");
      String answer = sc.next();
      if (answer.equalsIgnoreCase("Y")) {
        return true;
      } else if (answer.equalsIgnoreCase("N")) {
        return false;
      }
      System.out.println("Invalid choice. Please try again.");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Employees
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readEditEmployee(Scanner sc, Employee employee, String field) {
    String answer;
    for (;;) {
      if (employee == null) System.out.printf("Edit employee's %s? (Y/N) ", field);
      else System.out.printf("Edit employee '%s'? (Y/N) ", employee);
      
      answer = sc.next();
      if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
        sc.nextLine();
        return answer.toUpperCase();
      } else {
        sc.nextLine();
        System.out.println("Invalid choice. Please try again");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readEmployeeName(Scanner sc) {
    sc.nextLine();
    System.out.print("Enter employee name: ");
    return sc.nextLine();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static int readEmployeeIntField(Scanner sc, String msg) {
    System.out.printf("Enter %s\n", msg);
    //System.out.print("Enter employee years of experience: ");
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
        case 15 -> JobTitle.SECURITY_OFFICER;
        case 16 -> JobTitle.SECURITY_MANAGER;
        case 17 -> JobTitle.PARK_MANAGER;
        case 18 -> JobTitle.CLEANING_STAFF;
        case 19 -> JobTitle.MAINTENANCE_STAFF;
        default -> {
          System.out.println("Invalid choice for Job Title type. Please try again.");
          yield null;
        }
      };
    } while (jobTitle == null);
    
    return jobTitle;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static DayOfWeek readEmployeeDayOfWeek(Scanner sc) {
    DayOfWeek dayOfWeek = null;
    do {
      System.out.println("Select the day of the week the day off starts: ");
      int count = 1;
      for (DayOfWeek d : DayOfWeek.values()) {
        System.out.printf("%d. %s\n", count++, d.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      dayOfWeek = switch (choice) {
        case 1 -> DayOfWeek.MONDAY;
        case 2 -> DayOfWeek.TUESDAY;
        case 3 -> DayOfWeek.WEDNESDAY;
        case 4 -> DayOfWeek.THURSDAY;
        case 5 -> DayOfWeek.FRIDAY;
        case 6 -> DayOfWeek.SATURDAY;
        case 7 -> DayOfWeek.SUNDAY;
        default -> {
          System.out.println("Invalid choice for day of the week. Please try again.");
          yield null;
        }
      };
    } while (dayOfWeek == null);
    
    return dayOfWeek;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static LocalDate readEmployeeBirthDate(Scanner sc) {
    sc.nextLine();
    System.out.print("Enter employee birth date (YYYY-MM-DD): ");
    return LocalDate.parse(sc.next());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Enclosures
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readEditEnclosure(Scanner sc, Enclosure enclosure, String field) {
    String answer;
    for (;;) {
      if (enclosure == null) System.out.printf("Edit enclosure's %s? (Y/N) ", field);
      else System.out.printf("Edit enclosure '%s'? (Y/N) ", enclosure);
      
      answer = sc.next();
      if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
        sc.nextLine();
        return answer.toUpperCase();
      } else {
        sc.nextLine();
        System.out.println("Invalid choice. Please try again");
      }
    }
  }
  
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
        case 1 -> EnclosureType.RAPTORS_PARK;
        case 2 -> EnclosureType.FLYING_CAGE;
        case 3 -> EnclosureType.POOL_PARK;
        case 4 -> EnclosureType.FREE_PARK;
        case 5 -> EnclosureType.CENTRAL_BUILDING;
        case 6 -> EnclosureType.TICKETS_KIOSK;
        case 7 -> EnclosureType.VET_CENTER;
        case 8 -> EnclosureType.FOOD_STORE;
        case 9 -> EnclosureType.PARKING_LOT;
        case 10 -> EnclosureType.SURVEILLANCE_BUILDING;
        default -> {
          System.out.println("Invalid choice for enclosure type. Please try again.");
          yield null;
        }
      };
    } while (enclosureType == null);
    
    return enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static SafetyLevel readSafetyLevel(Scanner sc) {
    SafetyLevel safetyLevel = null;
    
    do {
      System.out.println("Select the safety level: ");
      int count = 1;
      for (SafetyLevel e : SafetyLevel.values()) {
        System.out.printf("%d. %s\n", count++, e.name());
      }
      System.out.println("Enter your choice: ");
      int choice = sc.nextInt();
      safetyLevel = switch (choice) {
        case 1 -> SafetyLevel.LOW;
        case 2 -> SafetyLevel.MEDIUM;
        case 3 -> SafetyLevel.HIGH;
        case 4 -> SafetyLevel.EMERGENCY;
        default -> {
          System.out.println("Invalid choice for safety level. Please try again.");
          yield null;
        }
      };
    } while (safetyLevel == null);
    
    return safetyLevel;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static int readEnclosureSecurityLevel(Scanner sc) {
    sc.nextLine();
    System.out.print("Enter enclosure security level (0-100): ");
    return sc.nextInt();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Special events
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static LocalTime readSpecialEventTime(Scanner sc) {
    System.out.print("Please enter a time (HH:mm (24h format)): ");
    return LocalTime.parse(sc.next());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static LocalDate readSpecialEventDate(Scanner sc) {
    System.out.print("Please enter a date (YYYY-MM-DD): ");
    return LocalDate.parse(sc.next());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readSpecialEventField(Scanner sc, String field) {
    sc.nextLine();
    System.out.printf("Enter event's %s: ", field);
    return sc.nextLine();
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
  // Vehicles
  //-------------------------------------------------------------------------------------------------------------------
  
  public static VehicleType readVehicleType(Scanner sc) {
    VehicleType vehicleType;
    do {
      System.out.println("Select the vehicle type: ");
      int count = 1;
      for (VehicleType t : VehicleType.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      vehicleType = switch (choice) {
        case 1 -> VehicleType.CAR;
        case 2 -> VehicleType.MOTORCYCLE;
        case 3 -> VehicleType.TRUCK;
        case 4 -> VehicleType.HELICOPTER;
        default -> {
          System.out.println("Invalid choice for Vehicle type. Please try again.");
          yield null;
        }
      };
    } while (vehicleType == null);
    
    return vehicleType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static String readVehicleField(Scanner sc, String field) {
    sc.nextLine();
    System.out.printf("Enter vehicle's %s: ", field);
    return sc.nextLine();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static int readVehicleIntField(Scanner sc, String field) {
    sc.nextLine();
    System.out.printf("Enter vehicle's %s: ", field);
    return sc.nextInt();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static double readVehicleDoubleField(Scanner sc, String field) {
    sc.nextLine();
    System.out.printf("Enter vehicle's %s: ", field);
    return sc.nextDouble();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  protected static String readEditVehicle(Scanner sc, Vehicle vehicle, String field) {
    String answer;
    for (;;) {
      if (vehicle == null) System.out.printf("Edit vehicle's %s? (Y/N) ", field);
      else System.out.printf("Edit vehicle '%s'? (Y/N) ", vehicle);
      
      answer = sc.next();
      if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
        sc.nextLine();
        return answer.toUpperCase();
      } else {
        sc.nextLine();
        System.out.println("Invalid choice. Please try again");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
