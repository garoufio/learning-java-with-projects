package chapter13.project.api;

import chapter13.project.App;
import chapter13.project.entity.dinosaur.*;
import chapter13.project.entity.employee.Employee;
import chapter13.project.entity.employee.JobTitle;
import chapter13.project.entity.enclosure.*;
import chapter13.project.entity.event.EventType;
import chapter13.project.entity.ticket.TicketType;
import chapter13.project.entity.ticket.Visitor;
import chapter13.project.entity.vehicle.Vehicle;
import chapter13.project.entity.vehicle.VehicleType;
import chapter13.project.exception.AppException;
import chapter13.project.exception.InputException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

/**
 * Utility class for common operations and input handling related to dinosaurs, employees, enclosures, events, tickets, 
 * and vehicles. Provides methods for reading and validating user input for various entity fields. This class is 
 * intended to centralize common utility functions to reduce code duplication and improve maintainability.
 * @see Dinosaur
 * @see Employee
 * @see Enclosure
 * @see EventType
 * @see TicketType
 * @see Visitor
 * @see Vehicle
 * @see VehicleType
 */
public class Util {
  
  // constants
  public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy HH:mm");
  public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
  public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
  public static final DateTimeFormatter PARSING_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
  
  //-------------------------------------------------------------------------------------------------------------------
  // Dinosaurs
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to confirm editing a specific field of a dinosaur. Displays a question asking if the user wants 
   * to edit the specified field of the given dinosaur. Accepts "Y" or "N" as valid input and returns the uppercase 
   * version of the answer. If the input is invalid, it will prompt the user again until a valid response is provided. 
   * In case of an input error, an AppException will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @param dinosaur the Dinosaur object whose field is being edited, see {@link Dinosaur}.
   * @param field the name of the field being edited.
   * @param question the question to display to the user.
   * @return "Y" if the user confirms editing, "N" otherwise.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static String readEditDinosaur(Scanner sc, Dinosaur dinosaur, String field, String question) throws AppException {
    String answer;
    for (;;) {
      if (dinosaur == null) System.out.printf("%s dinosaur's %s? (Y/N) ", question, field);
      else System.out.printf("%s dinosaur '%s'? (Y/N) ", question, dinosaur);
      
      try {
        answer = sc.next();
        sc.nextLine();
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readEditDinosaur':" + e.getMessage());
      }
      
      if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
        return answer.toUpperCase();
      }
      System.out.println("Invalid choice. Please try again");
    }
}
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the name of a dinosaur. In case of an input error, an AppException will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @return the name of the dinosaur entered by the user.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static String readDinosaurName(Scanner sc) throws AppException {
    try {
      System.out.print("Enter dinosaur name: ");
      return sc.nextLine();
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readDinosaurName':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the birthdate of a dinosaur. In case of an input error, an InputException will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @return the birthdate of the dinosaur entered by the user.
   * @throws InputException if an error occurs while reading or parsing user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static LocalDate readDinosaurBirthDate(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Enter dinosaur birthdate (YYYY-MM-DD): ");
      LocalDate birthDate = LocalDate.parse(sc.next());
      sc.nextLine();
      return birthDate;
    } catch (DateTimeParseException e) {
      throw new InputException("Cannot read 'birthdate': " + e.getMessage());
    } catch (NoSuchElementException | IllegalArgumentException e) {
      throw new AppException("Critical error occurred in 'readDinosaurBirthDate':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter an integer field of a dinosaur. In case of an input error, an InputException will be 
   * thrown.
   * @param sc the Scanner object used for reading user input.
   * @param field the name of the field being entered.
   * @return the integer value entered by the user for the specified field.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static int readDinosaurIntField(Scanner sc, String field) throws InputException, AppException {
    try {
      System.out.printf("Enter dinosaur's %s: ", field);
      int value = sc.nextInt();
      sc.nextLine();
      return value;
    } catch (InputMismatchException e) {
      throw new InputException("Cannot read '" + field + "': " + e.getMessage());
    }  catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readDinosaurIntField':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter whether the dinosaur is amphibious. In case of an input error, an InputException will be 
   * thrown.
   * @param sc the Scanner object used for reading user input.
   * @return true if the dinosaur is amphibious, false otherwise.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static boolean readDinosaurAmphibiousCapability(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Is the dinosaur amphibious? (true/false):");
      boolean amphibious = sc.nextBoolean();
      sc.nextLine();
      return amphibious;
    } catch (InputMismatchException e) {
      throw new AppException("Cannot read 'amphibious capability': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readDinosaurAmphibiousCapability':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Prompts the user to select the type of dinosaur from the available options. In case of an input error, an 
   * InputException will be thrown. 
   * @param sc the Scanner object used for reading user input.
   * @return the selected DinosaurType, see {@link DinosaurType} for available options.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static DinosaurType readDinosaurType(Scanner sc) throws InputException, AppException {
    DinosaurType type;
    do {
      System.out.println("Select the dinosaur type: ");
      int count = 1;
      for (DinosaurType t : DinosaurType.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'dinosaur type': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readDinosaurType':" + e.getMessage());
      }
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
  
  /**
   * Prompts the user to select the species of a dinosaur from the available options. In case of an input error, an 
   * InputException will be thrown. 
   * @param sc the Scanner object used for reading user input.
   * @return the selected DinosaurSpecies, see {@link DinosaurSpecies} for available options.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static DinosaurSpecies readDinosaurSpecies(Scanner sc) throws InputException, AppException {
    DinosaurSpecies species;
    do {
      System.out.println("Select the dinosaur species: ");
      int count = 1;
      for (DinosaurSpecies s : DinosaurSpecies.values()) {
        System.out.printf("%d. %s\n", count++, s.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'dinosaur species': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readDinosaurSpecies':" + e.getMessage());
      }
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
  
  /**
   * Prompts the user to select the size of a dinosaur from the available options. In case of an input error, an 
   * InputException will be thrown. 
   * @param sc the Scanner object used for reading user input.
   * @return the selected DinosaurSize, see {@link DinosaurSize} for available options.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static DinosaurSize readDinosaurSize(Scanner sc) throws InputException, AppException {
    DinosaurSize size;
    do {
      System.out.println("Select the dinosaur size: ");
      int count = 1;
      for (DinosaurSize s : DinosaurSize.values()) {
        System.out.printf("%d. %s\n", count++, s.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'dinosaur size': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readDinosaurSize':" + e.getMessage());
      }
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
  
  /**
   * Prompts the user to enter a specific field for a visitor. In case of an input error, an InputException will be 
   * thrown.
   * @param sc the Scanner object used for reading user input.
   * @param field the name of the field to be entered (e.g., "firstname", "lastname", "phoneNumber").
   * @return the value entered by the user for the specified field.                                                                                                                                                                                                                               
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static String readVisitorField(Scanner sc, String field) throws AppException {
    try {
      System.out.printf("Enter visitor's %s: ", field);
      return sc.nextLine();
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readVisitorField':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the details of a visitor, including firstname, lastname, and phone number. In case of an 
   * input error, an InputException will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @return a Visitor object containing the entered details, see {@link Visitor}.
   * @throws AppException if a critical and unrecoverable error occurs while reading visitor details.
   */
  protected static Visitor readVisitor(Scanner sc) throws AppException {
    String firstname = readVisitorField(sc, "firstname");
    String lastname = readVisitorField(sc, "lastname");
    String phoneNumber = readVisitorField(sc, "phoneNumber");

    return new Visitor(firstname, lastname, phoneNumber);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to select a ticket type from the available options. In case of an input error, an InputException 
   * will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @return the selected TicketType, see {@link TicketType}.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static TicketType readTicketType(Scanner sc) throws InputException, AppException {
    TicketType ticketType;
    do {
      System.out.println("Select the ticket type: ");
      int count = 1;
      for (TicketType t : TicketType.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'ticket type': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new InputException("Critical error occurred in 'readTicketType':" + e.getMessage());
      }
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
  
  /**
   * Prompts the user to enter the date for the ticket, either for today or a specific date. In case of an input error, 
   * an InputException will be thrown. 
   * @param sc the Scanner object used for reading user input.
   * @return the selected date as a LocalDate object.
   * @throws InputException if an error occurs while reading or parsing user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static LocalDate readTicketDate(Scanner sc) throws InputException, AppException {
    String answer;
    
    for (;;) {
      try {
        System.out.print("Ticket for today (Y/N)? ");
        answer = sc.next();
        sc.nextLine();
      } catch (DateTimeParseException e) {
        throw new InputException("Cannot read 'ticket date': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readTicketDate':" + e.getMessage());
      }
      
      if (answer.equalsIgnoreCase("Y")) {
        return LocalDate.now();
      } else if (answer.equalsIgnoreCase("N")) {
        System.out.print("Please enter a date: ");
        LocalDate date = LocalDate.parse(sc.next());
        sc.nextLine();
        return date;
      }
      System.out.println("Invalid choice. Please try again.");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the ticket ID. In case of an input error, an InputException will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @return the entered ticket ID as a UUID object.
   * @throws InputException if an error occurs while reading or parsing user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static UUID readTicketUUID(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Enter ticket ID: ");
      String ticketID = sc.next();
      sc.nextLine();
      return UUID.fromString(ticketID);
    } catch (IllegalArgumentException e) {
      throw new InputException("Cannot read 'ticketID': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error in 'readTicketUUID': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to decide whether to reserve a parking spot.
   * @param sc the Scanner object used for reading user input.
   * @return true if the user wants to reserve a parking spot, false otherwise.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static boolean reserveParkingSpot(Scanner sc) throws AppException {
    String answer;

    for (;;) {
      try {
        System.out.print("Do you want to reserve a parking spot (Y/N)? ");
        answer = sc.next();
        sc.nextLine();

        if (answer.equalsIgnoreCase("Y")) {
          return true;
        } else if (answer.equalsIgnoreCase("N")) {
          return false;
        }
        System.out.println("Invalid choice. Please try again.");
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error in 'reserveParkingSpot': " + e.getMessage());
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Employees
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to decide whether to edit a specific field of an employee. In case of an input error, an 
   * InputException will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @param employee the Employee object being edited, can be null if creating a new employee.
   * @param field the name of the field being edited.
   * @return "Y" if the user wants to edit the field, "N" otherwise.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static String readEditEmployee(Scanner sc, Employee employee, String field) throws AppException {
    String answer;
    for (;;) {
      if (employee == null) System.out.printf("Edit employee's %s? (Y/N) ", field);
      else System.out.printf("Edit employee '%s'? (Y/N) ", employee);
      
      try {
        answer = sc.next();
        sc.nextLine();
        
        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
          return answer.toUpperCase();
        } else {
          System.out.println("Invalid choice. Please try again");
        }
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error in 'readEditEmployee': " + e.getMessage());
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the employee's name. In case of an input error, an InputException will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @return the entered employee name as a String.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static String readEmployeeName(Scanner sc) throws AppException {
    try {
      System.out.print("Enter employee name: ");
      return sc.nextLine();
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error in 'readEmployeeName': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter an integer value for a specific employee field. In case of an input error, an 
   * InputException will be thrown and an AppException will be thrown if a critical and unrecoverable error occurs
   * while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @param msg the message indicating which field the user is entering.
   * @return the entered integer value.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static int readEmployeeIntField(Scanner sc, String msg) throws InputException, AppException {
    try {
      System.out.printf("Enter %s ", msg);
      int value = sc.nextInt();
      sc.nextLine();
      return value;
    } catch (InputMismatchException e) {
      throw new InputException("Cannot read 'employee's " + msg + "': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error in 'readEmployeeIntField': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to select the employee's job title from a list of available job titles. In case of an input 
   * error, an InputException will be thrown and an AppException will be thrown if a critical and unrecoverable error
   * occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the selected JobTitle enum value, see {@link JobTitle} for available job titles.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static JobTitle readEmployeeJobTitle(Scanner sc) throws InputException, AppException {
    JobTitle jobTitle;
    do {
      System.out.println("Select the employee's job title: ");
      int count = 1;
      for (JobTitle t : JobTitle.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'employee's job title': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error in 'readEmployeeJobTitle': " + e.getMessage());
      }
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
  
  /**
   * Prompts the user to select the day of week the day of starts. In case of an input error, an InputException will be 
   * thrown and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the selected DayOfWeek enum value.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static DayOfWeek readEmployeeDayOfWeek(Scanner sc) throws InputException, AppException {
    DayOfWeek dayOfWeek;
    do {
      System.out.println("Select the day of the week the day off starts: ");
      int count = 1;
      for (DayOfWeek d : DayOfWeek.values()) {
        System.out.printf("%d. %s\n", count++, d.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'employee's day of the week': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error in 'readEmployeeDayOfWeek': " + e.getMessage());
      }
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
  
  /**
   * Prompts the user to enter the employee's birthdate in the format YYYY-MM-DD. In case of an input error, an
   * InputException will be thrown and an AppException will be thrown if a critical and unrecoverable error occurs
   * while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the entered birthdate as a LocalDate object.
   * @throws InputException if an error occurs while reading or parsing user input.
   * @throws AppException if a critical and unrecoverable error occurs.
   */
  protected static LocalDate readEmployeeBirthDate(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Enter employee birthdate (YYYY-MM-DD): ");
      LocalDate birthDate = LocalDate.parse(sc.next());
      sc.nextLine();
      return birthDate;
    } catch (DateTimeParseException e) {
      throw new InputException("Cannot read 'employee's birthdate': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readEmployeeBirthDate':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Enclosures
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to edit a specific field of an enclosure with a yes/no question. An AppException will be thrown
   * if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @param enclosure the Enclosure object being edited, can be null.
   * @param field the specific field of the enclosure to be edited.
   * @param question the question to ask the user.
   * @return the user's answer as a string ("Y" or "N").
   * @throws AppException if a critical and unrecoverable error occurs.
   */
  protected static String readEditEnclosure(Scanner sc, Enclosure enclosure, String field, String question) throws AppException {
    String answer;
    for (;;) {
      if (enclosure == null) System.out.printf("%s enclosure's %s? (Y/N) ", question, field);
      else System.out.printf("%s enclosure '%s'? (Y/N) ", question, enclosure);
      
      try {
        answer = sc.next();
        sc.nextLine();
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readEditEnclosure': " + e.getMessage());
      }

      if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
        return answer.toUpperCase();
      } else {
        System.out.println("Invalid choice. Please try again");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to select the type of enclosure. In case of an input error, an InputException will be thrown and
   * an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the selected EnclosureType enum value, see {@link EnclosureType} for available enclosure types.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static EnclosureType readEnclosureType(Scanner sc) throws InputException, AppException {
    EnclosureType enclosureType;
    do {
      System.out.println("Select the enclosure type: ");
      int count = 1;
      for (EnclosureType e : EnclosureType.values()) {
        System.out.printf("%d. %s\n", count++, e.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'enclosure type': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readEnclosureType':" + e.getMessage());
      }
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
  
  /**
   * Prompts the user to select the safety level of an enclosure. In case of an input error, an InputException will be 
   * thrown and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the selected SafetyLevel enum value, see {@link SafetyLevel} for available safety levels.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static SafetyLevel readSafetyLevel(Scanner sc) throws InputException, AppException {
    SafetyLevel safetyLevel;
    
    do {
      System.out.println("Select the safety level: ");
      int count = 1;
      for (SafetyLevel e : SafetyLevel.values()) {
        System.out.printf("%d. %s\n", count++, e.name());
      }
      System.out.println("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'safety level': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readSafetyLevel':" + e.getMessage());
      }
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
  
  /**
   * Prompts the user to enter the security level of an enclosure. In case of an input error, an InputException will be 
   * thrown and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the entered security level as an integer (0-100).
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static int readEnclosureSecurityLevel(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Enter enclosure security level (0-100): ");
      int securityLevel = sc.nextInt();
      sc.nextLine();
      return securityLevel;
    } catch (InputMismatchException e) {
      throw new InputException("Cannot read 'enclosure security level': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readEnclosureSecurityLevel':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Special events
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the time of a special event. In case of an input error, an InputException will be thrown
   * and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the entered time as a LocalTime object.
   * @throws InputException if an error occurs while reading or parsing user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static LocalTime readSpecialEventTime(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Please enter a time (HH:mm (24h format)): ");
      LocalTime time = LocalTime.parse(sc.next());
      sc.nextLine();
      return time;
    } catch (DateTimeParseException e) {
      throw new InputException("Cannot read 'special event time': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readSpecialEventTime':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the date of a special event. In case of an input error, an InputException will be thrown
   * and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the entered date as a LocalDate object.
   * @throws InputException if an error occurs while reading or parsing user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static LocalDate readSpecialEventDate(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Please enter a date (YYYY-MM-DD): ");
      LocalDate date = LocalDate.parse(sc.next());
      sc.nextLine();
      return date;
    } catch (DateTimeParseException e) {
      throw new InputException("Cannot read 'special event date': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readSpecialEventDate':" + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter a specific field of a special event. In case of an input error, an AppException will be
   * thrown.
   * @param sc the Scanner object used for reading user input.
   * @param field the name of the field to be entered.
   * @return the entered value as a String.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static String readSpecialEventField(Scanner sc, String field) throws AppException {
    try {
      System.out.printf("Enter event's %s: ", field);
      return sc.nextLine();
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readSpecialEventField': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to select the type of special event. In case of an input error, an InputException will be thrown
   * and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the selected EventType enum value, see {@link EventType} for available event types.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static EventType readSpecialEventType(Scanner sc) throws InputException, AppException {
    EventType eventType;
    do {
      System.out.println("Select the event's type: ");
      int count = 1;
      for (EventType t : EventType.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'event type': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readSpecialEventType':" + e.getMessage());
      }
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
  
  /**
   * Prompts the user to select the vehicle type. In case of an input error, an InputException will be thrown and an
   * AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the select vehicle type enum value, see {@link VehicleType} for available vehicle types.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  public static VehicleType readVehicleType(Scanner sc) throws InputException, AppException {
    VehicleType vehicleType;
    do {
      System.out.println("Select the vehicle type: ");
      int count = 1;
      for (VehicleType t : VehicleType.values()) {
        System.out.printf("%d. %s\n", count++, t.name());
      }
      System.out.print("Enter your choice: ");
      int choice;
      try {
        choice = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        throw new InputException("Cannot read 'vehicle type': " + e.getMessage());
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readVehicleType':" + e.getMessage());
      }
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
  
  /**
   * Prompts the user to enter a specific field of a vehicle. In case of an input error, an AppException will be
   * thrown.
   * @param sc the Scanner object used for reading user input.
   * @param field the name of the field to be entered.
   * @return the entered value as a String.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  public static String readVehicleField(Scanner sc, String field) throws AppException {
    try {
      System.out.printf("Enter vehicle's %s: ", field);
      return sc.nextLine();
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readVehicleField': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter a specific integer field of a vehicle. In case of an input error, an InputException will 
   * be thrown and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @param field the name of the field to be entered.
   * @return the entered value as an int.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  public static int readVehicleIntField(Scanner sc, String field) throws InputException, AppException {
    try {
      System.out.printf("Enter vehicle's %s: ", field);
      int value = sc.nextInt();
      sc.nextLine();
      return value;
    } catch (InputMismatchException e) {
      throw new InputException("Cannot read vehicle's '" + field + "': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readVehicleIntField': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter a specific double field of a vehicle. In case of an input error, an InputException will
   * be thrown and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @param field the name of the field to be entered.
   * @return the entered value as a double.
   * @throws InputException if an error occurs while reading user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  public static double readVehicleDoubleField(Scanner sc, String field) throws InputException, AppException {
    try {
      System.out.printf("Enter vehicle's %s: ", field);
      double value = sc.nextDouble();
      sc.nextLine();
      return value;
    } catch (InputMismatchException e) {
      throw new InputException("Cannot read vehicle's '" + field + "': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readVehicleDoubleField': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to decide whether to edit a specific field of a vehicle. In case of an input error, an 
   * AppException will be thrown.
   * @param sc the Scanner object used for reading user input.
   * @param vehicle the vehicle object to be edited.
   * @param field the name of the field to be edited.
   * @return the user's choice as a String ("Y" or "N").
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static String readEditVehicle(Scanner sc, Vehicle vehicle, String field) throws AppException {
    String answer;
    for (;;) {
      if (vehicle == null) System.out.printf("Edit vehicle's %s? (Y/N) ", field);
      else System.out.printf("Edit vehicle '%s'? (Y/N) ", vehicle);
      
      try {
        answer = sc.next();
        sc.nextLine();
      } catch (NoSuchElementException | IllegalStateException e) {
        throw new AppException("Critical error occurred in 'readEditVehicle': " + e.getMessage());
      }

      if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
        return answer.toUpperCase();
      } else {
        System.out.println("Invalid choice. Please try again");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Activities
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter a specific field of an activity. In case of an input error, an AppException will be
   * thrown.
   * @param sc the Scanner object used for reading user input.
   * @param field the name of the field to be entered.
   * @return the entered value as a String.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static String readActivityField(Scanner sc, String field) throws AppException {
    try {
      System.out.printf("Enter activity's %s: ", field);
      return sc.nextLine();
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readActivityField': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the date and time of an activity. In case of an input error, an InputException will be 
   * thrown and an AppException will be thrown if a critical and unrecoverable error occurs while reading user input.
   * @param sc the Scanner object used for reading user input.
   * @return the entered date and time as a LocalDateTime object.
   * @throws InputException if an error occurs while reading or parsing user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static LocalDateTime readActivityTms(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Please enter a date and time (yyyy-MM-dd HH:mm): ");
      return LocalDateTime.parse(sc.nextLine(), PARSING_DATE_TIME_FORMAT);
    } catch (DateTimeParseException e) {
      throw new InputException("Cannot read activity's 'date and time': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readActivityTms': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to enter the duration of an activity in minutes. In case of an input error, an InputException 
   * will be thrown and an AppException will be thrown if a critical and unrecoverable error occurs while reading
   * user input.
   * @param sc the Scanner object used for reading user input.
   * @return the entered duration as a Duration object.
   * @throws InputException if an error occurs while reading or converting user input.
   * @throws AppException if a critical and unrecoverable error occurs while reading user input.
   */
  protected static Duration readActivityDuration(Scanner sc) throws InputException, AppException {
    try {
      System.out.print("Please enter activity duration in minutes: ");
      Duration duration = Duration.ofMinutes(sc.nextLong());
      sc.nextLine();
      return duration;
    } catch (InputMismatchException | ArithmeticException e) {
      throw new InputException("Cannot read activity's 'duration': " + e.getMessage());
    } catch (NoSuchElementException | IllegalStateException e) {
      throw new AppException("Critical error occurred in 'readActivityDuration': " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
