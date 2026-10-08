package chapter13.project.api;

import chapter13.project.collections.EnclosureComparator;
import chapter13.project.entity.dinosaur.DinosaurSpecies;
import chapter13.project.entity.dinosaur.DinosaurType;
import chapter13.project.entity.employee.JobTitle;
import chapter13.project.entity.enclosure.Enclosure;
import chapter13.project.entity.enclosure.EnclosureType;
import chapter13.project.entity.enclosure.SafetyLevel;
import chapter13.project.service.DinosaurCareSystemService;
import chapter13.project.service.EnclosureService;

import java.util.*;

/**
 * The EnclosureController class is responsible for managing the enclosures in the dinosaur park. It provides methods to
 * add, find, edit, and remove enclosures, as well as to display all enclosures. It interacts with the EnclosureService
 * and DinosaurCareSystemService to perform these operations.
 */
public class EnclosureController {
  
  private EnclosureService enclosureService;
  private DinosaurCareSystemService dinosaurCareSystemService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructor for EnclosureController
   * @param sc Scanner object for user input.
   * @param enclosureService EnclosureService object for managing enclosures.
   * @param dinosaurCareSystemService DinosaurCareSystemService object for managing dinosaur care system.
   */
  public EnclosureController(
      Scanner sc,
      EnclosureService enclosureService,
      DinosaurCareSystemService dinosaurCareSystemService
  ) {
    this.sc = sc;
    this.enclosureService = enclosureService;
    this.dinosaurCareSystemService = dinosaurCareSystemService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Manages the enclosures by providing a menu for the user to choose from various options such as showing all
   * enclosures, adding an enclosure, finding an enclosure, editing an enclosure, removing an enclosure and returning
   * to the main menu.
   */
  public void manageEnclosures() {
    for (;;) {
      System.out.printf("\nEnclosures service:\n");
      System.out.println("1. Show all Enclosures");
      System.out.println("2. Add Enclosure");
      System.out.println("3. Find Enclosure");
      System.out.println("4. Edit Enclosure");
      System.out.println("5. Remove Enclosure");
      System.out.println("6. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printEnclosures(sortEnclosures(enclosureService.getAllEnclosures(), null));
          break;
        case 2:
          addEnclosure();
          break;
        case 3:
          findEnclosure();
          break;
        case 4:
          editEnclosure();
          break;
        case 5:
          removeEnclosure();
          break;
        case 6:
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 6) {
        System.out.println();
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the Enclosure(s) to the console.
   * @param enclosures Collection of Enclosure objects to be printed.
   */
  public void printEnclosures(Collection<Enclosure> enclosures) {
    if (enclosures == null) {
      System.out.println("No Enclosures were added!");
      return;
    }
    
    for (Enclosure e : enclosures) {
      if (e != null) System.out.println(e);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds a new Enclosure to the system by reading the EnclosureType, SafetyLevel and SecurityLevel from user input.
   * The new Enclosure is then added to the EnclosureService and DinosaurCareSystemService.
   */
  public void addEnclosure() {
    EnclosureType enclosureType = Util.readEnclosureType(sc);
    SafetyLevel  safetyLevel = Util.readSafetyLevel(sc);
    int securityLevel = 100;
    if (Util.readEditEnclosure(sc, null, "security level", "Add").equals("Y")) {
      securityLevel = Util.readEnclosureSecurityLevel(sc);
    }
    Enclosure enclosure = new Enclosure(
        enclosureType,
        safetyLevel,
        new HashSet<>(),
        new HashSet<>(),
        securityLevel
    );
    enclosureService.addEnclosure(enclosure);
    dinosaurCareSystemService.addEnclosures(enclosure);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Finds an Enclosure based on various search criteria such as EnclosureType, SafetyLevel, Dinosaur name,
   * Dinosaur type, Dinosaur species, Employee name, Employee job title, Dinosaur detail search, and Employee detail
   * search. The user is prompted to choose a search option, and the corresponding Enclosure(s) are retrieved from the
   * EnclosureService and printed to the console.
   */
  public void findEnclosure() {
    for (;;) {
      System.out.printf("\nSearch by:\n");
      System.out.println("1. Enclosure Type");
      System.out.println("2. SafetyLevel");
      System.out.println("3. Dinosaur name");
      System.out.println("4. Dinosaur type");
      System.out.println("5. Dinosaur species");
      System.out.println("6. Employee name");
      System.out.println("7. Employee job title");
      System.out.println("8. Dinosaur detail search");
      System.out.println("9. Employee detail search");
      System.out.println("10. Return to enclosure menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> { // by enclosure type
          EnclosureType enclosureType = Util.readEnclosureType(sc);
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType);
          if (enclosure == null) System.out.printf("No enclosure found for type '%s'\n", enclosureType);
          else System.out.println(enclosure);
        }
        case 2 -> { // by safety level
          SafetyLevel safetyLevel = Util.readSafetyLevel(sc);
          Set<Enclosure> enclosures = enclosureService.getEnclosure(safetyLevel);
          if (enclosures.isEmpty()) System.out.printf("No enclosures found with safety level '%s'\n", safetyLevel);
          else printEnclosures(enclosures);
        }
        case 3 -> { // by dinosaur name
          String dinosaurName = Util.readDinosaurName(sc);
          Set<Enclosure> enclosures = enclosureService.getEnclosure(dinosaurName, true);
          if (enclosures.isEmpty()) System.out.printf("No enclosure found for dinosaur '%s'\n", dinosaurName);
          else printEnclosures(enclosures);
        }
        case 4 -> { // by dinosaur type
          DinosaurType dinosaurType = Util.readDinosaurType(sc);
          Set<Enclosure> enclosures = enclosureService.getEnclosure(dinosaurType);
          if (enclosures.isEmpty()) System.out.printf("No enclosure found for dinosaur type '%s'\n", dinosaurType.name());
          else printEnclosures(enclosures);
        }
        case 5 -> { // by dinosaur species
          DinosaurSpecies dinosaurSpecies = Util.readDinosaurSpecies(sc);
          Set<Enclosure> enclosures = enclosureService.getEnclosure(dinosaurSpecies);
          if (enclosures == null) System.out.printf("No enclosure found for dinosaur species '%s'\n", dinosaurSpecies.name());
          else printEnclosures(enclosures);
        }
        case 6 -> { // by employee name
          String employeeName = Util.readEmployeeName(sc);
          Set<Enclosure> enclosures = enclosureService.getEnclosure(employeeName, false);
          if (enclosures.isEmpty()) System.out.printf("No enclosure found for employee '%s'\n", employeeName);
          else printEnclosures(enclosures);
        }
        case 7 -> { // by employee's job title
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Set<Enclosure> enclosures = enclosureService.getEnclosure(jobTitle);
          if (enclosures.isEmpty()) System.out.printf("No enclosures found for job title '%s'\n", jobTitle);
          else printEnclosures(enclosures);
        }
        case 8 -> { // by dinosaur detail search
          EnclosureType enclosureType = Util.readEnclosureType(sc);
          String dinosaurName = Util.readDinosaurName(sc);
          DinosaurType dinosaurType = Util.readDinosaurType(sc);
          DinosaurSpecies dinosaurSpecies = Util.readDinosaurSpecies(sc);
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType, dinosaurType, dinosaurSpecies, dinosaurName);
          if (enclosure == null) System.out.println("No enclosure found");
          else System.out.println(enclosure);
        }
        case 9 -> { // by employee detail search
          EnclosureType enclosureType = Util.readEnclosureType(sc);
          String employeeName = Util.readEmployeeName(sc);
          JobTitle jobTitle = Util.readEmployeeJobTitle(sc);
          Set<Enclosure> enclosures = enclosureService.getEnclosure(enclosureType, jobTitle, employeeName);
          if (enclosures.isEmpty()) System.out.println("No enclosure found");
          else printEnclosures(enclosures);
        }
        case 10 -> { } // return to enclosure menu
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice > 0 && choice < 11) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Edits the details of an existing Enclosure. The user is prompted to choose whether to edit the safety level and/or
   * security level of the enclosure. If the user chooses to edit, the new values are read from user input and updated
   * in the Enclosure object.
   * @param enclosure The Enclosure object to be edited.
   */
  private void editEnclosureDetails(Enclosure enclosure) {
    // change safety level
    if (Util.readEditEnclosure(sc, null, "safety level", "Edit").equals("Y")) {
      SafetyLevel safetyLevel = Util.readSafetyLevel(sc);
      enclosure.setSafetyLevel(safetyLevel);
    }
    
    // change security level
    if (Util.readEditEnclosure(sc, null, "security level", "Edit").equals("Y")) {
      int securityLevel = Util.readEnclosureSecurityLevel(sc);
      enclosure.setSecurityLevel(securityLevel);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Edits an existing Enclosure by prompting the user to choose an EnclosureType or return to the enclosure menu.
   * If an EnclosureType is chosen, the corresponding Enclosure is retrieved from the EnclosureService and the user
   * is prompted to edit its details.
   */
  public void editEnclosure() {
    for (;;) {
      System.out.printf("\nEdit by:\n");
      System.out.println("1. Enclosure Type");
      System.out.println("2. Return to enclosure menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          EnclosureType enclosureType = Util.readEnclosureType(sc);
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType);
          if (enclosure == null) {
            System.out.printf("No enclosure found for type '%s'\n", enclosureType);
          } else {
            editEnclosureDetails(enclosure);
          }
        }
        case 2 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice > 0 && choice < 3) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes an existing Enclosure by prompting the user to choose an EnclosureType or return to the enclosure menu.
   * If an EnclosureType is chosen, the corresponding Enclosure is retrieved from the EnclosureService and removed from
   * both the EnclosureService and DinosaurCareSystemService.
   */
  public void removeEnclosure() {
    for (;;) {
      System.out.printf("\nRemove by:\n");
      System.out.println("1. Enclosure Type");
      System.out.println("2. Return to enclosure menu");
      System.out.println("Enter  your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          EnclosureType  enclosureType = Util.readEnclosureType(sc);
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType);
          if (enclosure != null) {
            enclosureService.removeEnclosure(enclosure);
            dinosaurCareSystemService.removeEnclosure(enclosure);
            System.out.println("Enclosure removed");
          }
          else System.out.printf("No enclosure found for type '%s'\n", enclosureType);
        }
        case 2 -> { }
        default -> System.out.println("Invalid choice. Please try again");
      }
      if (choice > 0 && choice < 3) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sorts a set of Enclosures based on a provided Comparator. If the Comparator is null, a default Comparator is used.
   * @param enclosures Set of Enclosure objects to be sorted.
   * @param comparator Comparator to define the sorting order. If null, a default Comparator is used.
   * @return A List of sorted Enclosure objects.
   */
  public List<Enclosure> sortEnclosures(Set<Enclosure> enclosures, Comparator<Enclosure> comparator) {
    if (enclosures == null || enclosures.isEmpty()) return List.of();
    
    List<Enclosure> sortedEnclosures = new ArrayList<>(enclosures);
    Collections.sort(sortedEnclosures, comparator == null ? EnclosureComparator.getDefaultComparator() : comparator);
    return sortedEnclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
