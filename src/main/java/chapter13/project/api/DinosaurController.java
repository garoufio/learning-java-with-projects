package chapter13.project.api;

import chapter13.project.collections.DinosaurComparator;
import chapter13.project.entity.dinosaur.*;
import chapter13.project.entity.enclosure.Enclosure;
import chapter13.project.entity.enclosure.EnclosureType;
import chapter13.project.exception.AppException;
import chapter13.project.exception.InputException;
import chapter13.project.service.DinosaurCareSystemService;
import chapter13.project.service.DinosaurService;
import chapter13.project.service.EnclosureService;

import java.time.LocalDate;
import java.util.*;

/**
 * DinosaurController class is responsible for managing the dinosaurs in the park. It provides methods to add, find,
 * edit, and remove dinosaurs, as well as to display the list of dinosaurs. It interacts with the DinosaurService,
 * EnclosureService, and DinosaurCareSystemService to perform these operations.
 */
public class DinosaurController {
  
  private DinosaurService dinosaurService;
  private EnclosureService enclosureService;
  private DinosaurCareSystemService dinosaurCareSystemService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a DinosaurController object. It initializes the controller with the DinosaurService, EnclosureService, 
   * DinosaurCareSystemService services and a Scanner object for user input.
   * @param sc Scanner for user input.
   * @param dinosaurService DinosaurService for managing dinosaurs.
   * @param enclosureService EnclosureService for managing enclosures.
   * @param dinosaurCareSystemService DinosaurCareSystemService for managing the dinosaur care system.
   */
  public DinosaurController(
      Scanner sc,
      DinosaurService dinosaurService,
      EnclosureService enclosureService,
      DinosaurCareSystemService dinosaurCareSystemService
  ) {
    this.sc = sc;
    this.dinosaurService = dinosaurService;
    this.enclosureService = enclosureService;
    this.dinosaurCareSystemService = dinosaurCareSystemService;
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Manages the dinosaur service menu. It provides options to show all dinosaurs, add a new dinosaur, find a dinosaur,
   * edit a dinosaur, remove a dinosaur, or return to the main menu. It continuously prompts the user for input until
   * they choose to return to the main menu. It handles the user's choice and calls the appropriate methods to perform
   * the selected operation.
   */
  public void manageDinosaurs() {
    for (;;) {
      try {
        System.out.printf("""
            
            Dinosaurs service:
            1. Show all Dinosaurs
            2. Add Dinosaur
            3. Find Dinosaur
            4. Edit Dinosaur
            5. Remove Dinosaur
            6. Return to main menu
            Enter your choice:\t"""
        );
        
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> printDinosaurs();
          case 2 -> addDinosaur();
          case 3 -> findDinosaur();
          case 4 -> editDinosaur();
          case 5 -> removeDinosaur();
          case 6 -> System.out.println("Returning to main menu...");
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice == 6) {
          System.out.println();
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 6.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error: %s
          Please try again or return to Main Menu...
        """, e.getMessage()
        );
      }
      catch (AppException e) {
        System.out.printf("""
          Error while managing Dinosaurs: %s
          Please try again or return to Main Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the Dinosaur(s) to the console.
   * @param dinosaurs Collection of Dinosaur objects to be printed.
   */
  private void printDinosaurs(Collection<Dinosaur> dinosaurs) {
    if (dinosaurs == null || dinosaurs.isEmpty()) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    for (Dinosaur d : dinosaurs) {
      if (d != null) System.out.println(d);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the list of dinosaurs sorted by various criteria. It prompts the user to choose a sorting option and then
   * displays the sorted list.
   */
  public void printDinosaurs() {
    for (;;) {
      try {
        System.out.print("""
            
            Print dinosaurs sorted by:
            1. No sorting
            2. Default
            3. Name
            4. Birthdate
            5. Age
            6. Type
            7. Species
            8. Size
            9. Health Score
            10. Return to Dinosaur Menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        
        switch (choice) {
          case 1 -> printDinosaurs(dinosaurService.getAllDinosaurs());
          case 2 -> printDinosaurs(sortDinosaurs(dinosaurService.getAllDinosaurs(), null));
          case 3 -> printDinosaurs(sortDinosaurs(dinosaurService.getAllDinosaurs(), DinosaurComparator.BY_NAME));
          case 4 -> printDinosaurs(sortDinosaurs(dinosaurService.getAllDinosaurs(), DinosaurComparator.BY_BIRTHDATE));
          case 5 -> printDinosaurs(sortDinosaurs(dinosaurService.getAllDinosaurs(), DinosaurComparator.BY_AGE));
          case 6 -> printDinosaurs(sortDinosaurs(dinosaurService.getAllDinosaurs(), DinosaurComparator.BY_TYPE));
          case 7 -> printDinosaurs(sortDinosaurs(dinosaurService.getAllDinosaurs(), DinosaurComparator.BY_SPECIES));
          case 8 -> printDinosaurs(sortDinosaurs(dinosaurService.getAllDinosaurs(), DinosaurComparator.BY_SIZE));
          case 9 -> printDinosaurs(sortDinosaurs(dinosaurService.getAllDinosaurs(), DinosaurComparator.BY_HEALTH_SCORE));
          case 10 -> System.out.println("Returning to Dinosaur Menu...");
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice > 0 && choice < 11) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 10.");
        sc.nextLine(); // Consume the invalid input
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds a new dinosaur to the park. It prompts the user for the dinosaur's name, birthdate, type, species, size, and
   * health score. Based on the species, it collects additional attributes specific to that species. It then creates a
   * new Dinosaur object and adds it to the appropriate enclosure and the dinosaur care system. If the dinosaur cannot
   * be added to the enclosure, it informs the user and does not add the dinosaur to the park. It also handles invalid
   * inputs and informs the user accordingly.
   * @throws InputException if there is an error in user input.
   */
  public void addDinosaur() throws InputException {
    // read name
    String name = Util.readDinosaurName(sc);
    // read birthdate
    LocalDate birthDate = Util.readDinosaurBirthDate(sc);
    // read dinosaur type
    DinosaurType type = Util.readDinosaurType(sc);
    // read dinosaur species
    DinosaurSpecies species = Util.readDinosaurSpecies(sc);
    // read dinosaur size
    DinosaurSize size = Util.readDinosaurSize(sc);
    // read health score
    int healthScore = 100;
    if (Util.readEditDinosaur(sc, null, "health score", "Add").equals("Y")) {
      healthScore = Util.readDinosaurIntField(sc, "health score (0-100)");
    }
    
    // create new dinosaur
    Dinosaur dinosaur = switch (species) {
      case TYRANNOSAURUS, TRICERATOPS, VELOCIRAPTOR, STEGOSAURUS, BRACHIOSAURUS,
           SPINOSAURUS, PARASAUROLOPHUS, ANKYLOSAURUS -> {
        int height = Util.readDinosaurIntField(sc, "height (in centimeters)");
        int speed = Util.readDinosaurIntField(sc, "max running speed (in Km/h)");
        yield new TerrestrialDinosaur(name, birthDate, type, species, size, healthScore, height, speed);
      }
      case PLIOSAURS -> {
        int divingDepth = Util.readDinosaurIntField(sc, "max diving depth (in meters)");
        int underwaterSpeed = Util.readDinosaurIntField(sc, "max underwater speed (in Km/h)");
        boolean isAmphibious = Util.readDinosaurAmphibiousCapability(sc);
        yield new AquaticDinosaur(name, birthDate, type, species, size, healthScore, divingDepth, underwaterSpeed, isAmphibious);
      }
      case PTEROSAUR -> {
        int wingspan = Util.readDinosaurIntField(sc, "wingspan (in centimeters)");
        int maxAltitude = Util.readDinosaurIntField(sc, "max altitude (in meters)");
        int flyingSpeed = Util.readDinosaurIntField(sc, "max flying speed (in Km/h)");
        yield new FlyingDinosaur(name, birthDate, type, species, size, healthScore, wingspan, maxAltitude, flyingSpeed);
      }
      default -> {
        System.out.printf("Invalid dinosaur species '%s'. No dinosaur has been added\n", species);
        yield null;
      }
    };
    if (dinosaur == null) return;
    
    // add dinosaur into an eclosure
    EnclosureType enclosureType = Util.readEnclosureType(sc);
    if (enclosureType != null) {
      Enclosure enclosure = enclosureService.getEnclosure(enclosureType);
      if (enclosure != null) {
        if (enclosure.addDinosaur(dinosaur)) {
          dinosaurService.addDinosaurs(dinosaur);
        } else System.out.println("The dinosaur could not be added to this enclosure. Please try again");
      } else System.out.println("The selected enclosure does not exist in the park. Please try again");
    } else System.out.println("Invalid enclosure type. No dinosaur has been added\n");
    
    // add dinosaur into the dinosaur care system
    dinosaurCareSystemService.addDinosaurs(dinosaur);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the submenu options for finding, editing, or removing dinosaurs based on the specified action.
   * @param action The action to be performed (e.g. "Find", "Edit", "Remove").
   */
  private void printDinosaurSubmenuOptions(String action) {
    System.out.printf("""
    
    %s by:
    1. Dinosaur name
    2. Dinosaur type
    3. Dinosaur species
    4. Dinosaur size
    5. Detailed search
    6. Return to dinosaur menu
    Enter your choice:\t""",
        action
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Finds dinosaurs based on various criteria specified by the user.
   */
  public void findDinosaur() {
    for (;;) {
      try {
        printDinosaurSubmenuOptions("Find");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        Set<Dinosaur> foundDinosaurs = switch (choice) {
          case 1 -> {
            String name = Util.readDinosaurName(sc);
            Dinosaur dinosaur = dinosaurService.getDinosaur(name);
            if (dinosaur == null) {
              System.out.printf("Dinosaur '%s' not found\n", name);
              yield Set.of();
            } else yield Set.of(dinosaur);
          }
          case 2 -> {
            DinosaurType type = Util.readDinosaurType(sc);
            Set<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(type);
            if (dinosaurs.isEmpty()) {
              System.out.printf("No dinosaur(s) found for type '%s'\n", type.name());
              yield Set.of();
            } else yield dinosaurs;
          }
          case 3 -> {
            DinosaurSpecies species = Util.readDinosaurSpecies(sc);
            Set<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(species);
            if (dinosaurs.isEmpty()) {
              System.out.printf("No dinosaur(s) found for species '%s'\n", species.name());
              yield Set.of();
            } else yield dinosaurs;
          }
          case 4 -> {
            DinosaurSize size = Util.readDinosaurSize(sc);
            Set<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(size);
            if (dinosaurs.isEmpty()) {
              System.out.printf("No dinosaur(s) found for size '%s'\n", size.name());
              yield Set.of();
            } else yield dinosaurs;
          }
          case 5 -> {
            String name = Util.readDinosaurName(sc);
            LocalDate birthDate = Util.readDinosaurBirthDate(sc);
            DinosaurType type = Util.readDinosaurType(sc);
            DinosaurSpecies species = Util.readDinosaurSpecies(sc);
            DinosaurSize size = Util.readDinosaurSize(sc);
            Dinosaur dinosaur = switch (species) {
              case TYRANNOSAURUS, TRICERATOPS, VELOCIRAPTOR, STEGOSAURUS, BRACHIOSAURUS,
                   SPINOSAURUS, PARASAUROLOPHUS, ANKYLOSAURUS -> {
                int height = Util.readDinosaurIntField(sc, "height (in centimeters)");
                int speed = Util.readDinosaurIntField(sc, "max running speed (in Km/h)");
                yield new TerrestrialDinosaur(name, birthDate, type, species, size, height, speed);
              }
              case PLIOSAURS -> {
                int divingDepth = Util.readDinosaurIntField(sc, "max diving depth (in meters)");
                int underwaterSpeed = Util.readDinosaurIntField(sc, "max underwater speed (in Km/h)");
                boolean isAmphibious = Util.readDinosaurAmphibiousCapability(sc);
                yield new AquaticDinosaur(name, birthDate, type, species, size, divingDepth, underwaterSpeed, isAmphibious);
              }
              case PTEROSAUR -> {
                int wingspan = Util.readDinosaurIntField(sc, "wingspan (in centimeters)");
                int maxAltitude = Util.readDinosaurIntField(sc, "max altitude (in meters)");
                int flyingSpeed = Util.readDinosaurIntField(sc, "max flying speed (in Km/h)");
                yield new FlyingDinosaur(name, birthDate, type, species, size, wingspan, maxAltitude, flyingSpeed);
              }
              default -> null;
            };
            Dinosaur foundDinosaur = dinosaurService.getDinosaur(dinosaur);
            if (foundDinosaur == null) {
              if (dinosaur == null) System.out.println("No dinosaur found with the provided details");
              else System.out.printf("Dinosaur not found with the provided details: %s\n", dinosaur);
              yield Set.of();
            } else yield Set.of(foundDinosaur);
          }
          case 6 -> Set.of();
          default -> {
            System.out.println("Invalid choice. Please try again");
            yield Set.of();
          }
        };
        if (!foundDinosaurs.isEmpty()) {
          printDinosaurs(foundDinosaurs);
          
          for (; ; ) {
            System.out.print("Do you want to see more info about the found dinosaur(s)? (Y/N): ");
            String moreInfoChoice = sc.next();
            sc.nextLine(); // Consume the newline character after next()
            if (moreInfoChoice.equalsIgnoreCase("Y")) {
              for (Dinosaur d : foundDinosaurs) {
                d.info();
                System.out.println("-------------------------");
              }
              break;
            } else if (moreInfoChoice.equalsIgnoreCase("N")) {
              break;
            } else {
              System.out.println("Invalid choice. Please enter Y or N");
            }
          }
        }
        if (choice > 0 && choice < 7) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 6.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while finding dinosaur: %s
          Please try again or return to Dinosaur Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Edits the details of the given dinosaur by prompting the user for changes to its name, birthdate, species, type,
   * size, health score, and specific attributes based on its type.
   * @param dinosaur the dinosaur whose details are to be edited.
   * @throws InputException if there is an error in user input.
   */
  private void editDinosaurDetails(Dinosaur dinosaur) throws InputException {
    // change name
    if (Util.readEditDinosaur(sc, null, "name", "Edit").equals("Y")) {
      String newName = Util.readDinosaurName(sc);
      dinosaur.setName(newName);
    }
    // change birthdate
    if (Util.readEditDinosaur(sc, null, "birth date", "Edit").equals("Y")) {
      LocalDate newBirthDate = Util.readDinosaurBirthDate(sc);
      dinosaur.setBirthDate(newBirthDate);
    }
    // change species
    if (Util.readEditDinosaur(sc, null, "species", "Edit").equals("Y")) {
      DinosaurSpecies newSpecies = Util.readDinosaurSpecies(sc);
      dinosaur.setSpecies(newSpecies);
    }
    // change type
    if (Util.readEditDinosaur(sc, null, "type", "Edit").equals("Y")) {
      DinosaurType newType = Util.readDinosaurType(sc);
      dinosaur.setType(newType);
    }
    // change size
    if (Util.readEditDinosaur(sc, null, "size", "Edit").equals("Y")) {
      DinosaurSize newSize = Util.readDinosaurSize(sc);
      dinosaur.setSize(newSize);
    }
    // change health score
    if (Util.readEditDinosaur(sc, null, "health score", "Edit").equals("Y")) {
      int newHealthScore = Util.readDinosaurIntField(sc, "health score (0-100)");
      dinosaur.setHealthScore(newHealthScore);
    }
    // change specific attributes based on dinosaur type
    switch (dinosaur) {
      case TerrestrialDinosaur terrestrialDinosaur -> {
        // change height
        if (Util.readEditDinosaur(sc, null, "height", "Edit").equals("Y")) {
          int newHeight = Util.readDinosaurIntField(sc, "height (in centimeters)");
          terrestrialDinosaur.setHeight(newHeight);
        }
        // change speed
        if (Util.readEditDinosaur(sc, null, "max running speed", "Edit").equals("Y")) {
          int newSpeed = Util.readDinosaurIntField(sc, "max running speed (in Km/h)");
          terrestrialDinosaur.setMaxRunningSpeed(newSpeed);
        }
      }
      case AquaticDinosaur aquaticDinosaur -> {
        // change diving depth
        if (Util.readEditDinosaur(sc, null, "max diving depth", "Edit").equals("Y")) {
          int newDivingDepth = Util.readDinosaurIntField(sc, "max diving depth (in meters)");
          aquaticDinosaur.setMaxDivingDepth(newDivingDepth);
        }
        // change underwater speed
        if (Util.readEditDinosaur(sc, null, "max underwater speed", "Edit").equals("Y")) {
          int newUnderwaterSpeed = Util.readDinosaurIntField(sc, "max underwater speed (in Km/h)");
          aquaticDinosaur.setMaxUnderwaterSpeed(newUnderwaterSpeed);
        }
        // amphibious capability
        if (Util.readEditDinosaur(sc, null, "amphibious capability", "Edit").equals("Y")) {
          boolean isAmphibious = Util.readDinosaurAmphibiousCapability(sc);
          aquaticDinosaur.setAmphibious(isAmphibious);
        }
      }
      case FlyingDinosaur flyingDinosaur -> {
        // change wingspan
        if (Util.readEditDinosaur(sc, null, "wingspan", "Edit").equals("Y")) {
          int newWingspan = Util.readDinosaurIntField(sc, "wingspan (in centimeters)");
          flyingDinosaur.setWingSpan(newWingspan);
        }
        
        // change max altitude
        if (Util.readEditDinosaur(sc, null, "max flying altitude", "Edit").equals("Y")) {
          int newMaxAltitude = Util.readDinosaurIntField(sc, "max flying altitude (in meters)");
          flyingDinosaur.setMaxAltitude(newMaxAltitude);
        }
        
        // change flying speed
        if (Util.readEditDinosaur(sc, null, "max flying speed", "Edit").equals("Y")) {
          int newFlyingSpeed = Util.readDinosaurIntField(sc, "max flying speed (in Km/h)");
          flyingDinosaur.setMaxFlightSpeed(newFlyingSpeed);
        }
      }
      default -> System.out.println("Unknown dinosaur type. No specific attributes to edit");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Edits a dinosaur based on user input. The user can choose to edit a dinosaur by name or perform a detailed search
   * to find the dinosaur. Once the dinosaur is found, the user can edit its details using the editDinosaurDetails
   * method. If the dinosaur is not found, an appropriate message is displayed. The method continues to prompt the user
   * until they choose to return to the dinosaur menu.
   */
  private void editDinosaur() {
    for (;;) {
      try {
        System.out.print("""
            
            Edit by:
            1. Name
            2. Detailed search
            3. Return to dinosaur menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> {
            String name = Util.readDinosaurName(sc);
            Dinosaur foundDinosaur = dinosaurService.getDinosaur(name);
            if (foundDinosaur == null) {
              System.out.printf("Dinosaur '%s' not found\n", name);
            } else {
              editDinosaurDetails(foundDinosaur);
            }
          }
          case 2 -> {
            String name = Util.readDinosaurName(sc);
            LocalDate birthDate = Util.readDinosaurBirthDate(sc);
            DinosaurType type = Util.readDinosaurType(sc);
            DinosaurSpecies species = Util.readDinosaurSpecies(sc);
            DinosaurSize size = Util.readDinosaurSize(sc);
            Dinosaur dinosaur = switch (species) {
              case TYRANNOSAURUS, TRICERATOPS, VELOCIRAPTOR, STEGOSAURUS, BRACHIOSAURUS,
                   SPINOSAURUS, PARASAUROLOPHUS, ANKYLOSAURUS -> {
                int height = Util.readDinosaurIntField(sc, "height (in centimeters)");
                int speed = Util.readDinosaurIntField(sc, "max running speed (in Km/h)");
                yield new TerrestrialDinosaur(name, birthDate, type, species, size, height, speed);
              }
              case PLIOSAURS -> {
                int divingDepth = Util.readDinosaurIntField(sc, "max diving depth (in meters)");
                int underwaterSpeed = Util.readDinosaurIntField(sc, "max underwater speed (in Km/h)");
                boolean isAmphibious = Util.readDinosaurAmphibiousCapability(sc);
                yield new AquaticDinosaur(name, birthDate, type, species, size, divingDepth, underwaterSpeed, isAmphibious);
              }
              case PTEROSAUR -> {
                int wingspan = Util.readDinosaurIntField(sc, "wingspan (in centimeters)");
                int maxAltitude = Util.readDinosaurIntField(sc, "max altitude (in meters)");
                int flyingSpeed = Util.readDinosaurIntField(sc, "max flying speed (in Km/h)");
                yield new FlyingDinosaur(name, birthDate, type, species, size, wingspan, maxAltitude, flyingSpeed);
              }
              default -> null;
            };
            Dinosaur foundDinosaur = dinosaurService.getDinosaur(dinosaur);
            if (foundDinosaur == null) {
              System.out.println("Dinosaur not found");
            } else {
              editDinosaurDetails(foundDinosaur);
            }
          }
          case 3 -> { }
          default -> System.out.println("Invalid choice. Please try again");
        }
        if (choice > 0 && choice < 4) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 3.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while editing dinosaur: %s
          Please try again or return to Dinosaur Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes a dinosaur from a specific enclosure.
   * @param enclosure the enclosure from which the dinosaur will be removed.
   * @param dinosaur the dinosaur to be removed.
   */
  private void removeDinosaurFromEnclosure(Enclosure enclosure, Dinosaur dinosaur) {
    if (enclosure == null) {
      System.out.printf("The enclosure for dinosaur '%s' does not exist in the park\n", dinosaur);
    } else {
      if (enclosureService.removeDinosaurFromEnclosure(enclosure, dinosaur)) {
        System.out.printf("Dinosaur '%s' removed from the enclosure '%s'\n", dinosaur, enclosure.getEnclosureType());
      } else {
        System.out.printf("Dinosaur '%s' could not be removed from the enclosure\n", dinosaur);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes a dinosaur from a set of enclosures. It iterates through the set of enclosures and calls the
   * removeDinosaurFromEnclosure method for each enclosure to remove the specified dinosaur.
   * @param enclosures the set of enclosures from which the dinosaur will be removed.
   * @param dinosaur the dinosaur to be removed.
   */
  public void removeDinosaurFromEnclosure(Set<Enclosure> enclosures, Dinosaur dinosaur) {
    if (enclosures == null || enclosures.isEmpty()) {
      System.out.println("The enclosure(s) do not exist in the park\n");
    } else {
      for (Enclosure e : enclosures) {
        removeDinosaurFromEnclosure(e, dinosaur);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes a dinosaur from the park. It first removes the dinosaur from the set of dinosaurs and then removes it from
   * any related enclosures. If the dinosaur does not exist, it informs the user that the dinosaur was not found.
   * @param dinosaur the dinosaur to be removed.
   */
  private void removeDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) {
      System.out.println("Dinosaur not found");
      return;
    }
    // remove dinosaur from the set of dinosaurs
    if (dinosaurService.removeDinosaur(dinosaur)) {
      System.out.println("Dinosaur removed");
      // remove dinosaur from the related enclosure
      Set<Enclosure> enclosures = enclosureService.getEnclosure(dinosaur);
      removeDinosaurFromEnclosure(enclosures, dinosaur);
    } else System.out.printf("Dinosaur '%s' could not be removed\n", dinosaur);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes dinosaurs based on various criteria specified by the user. The user can choose to remove dinosaurs by name,
   * type, species, size, or perform a detailed search. Once the dinosaurs are found, they are removed from the park and
   * any related enclosures. If no dinosaurs are found for the specified criteria, an appropriate message is displayed.
   * The method continues to prompt the user until they choose to return to the dinosaur menu.
   */
  public void removeDinosaur() {
    for (;;) {
      try {
        printDinosaurSubmenuOptions("Remove");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> {
            String name = Util.readDinosaurName(sc);
            removeDinosaur(dinosaurService.getDinosaur(name));
          }
          case 2 -> {
            DinosaurType type = Util.readDinosaurType(sc);
            Set<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(type);
            if (dinosaurs.isEmpty()) {
              System.out.printf("No dinosaur(s) found for type '%s'\n", type);
            } else {
              for (Dinosaur d : dinosaurs) {
                removeDinosaur(d);
              }
            }
          }
          case 3 -> {
            DinosaurSpecies species = Util.readDinosaurSpecies(sc);
            Set<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(species);
            if (dinosaurs.isEmpty()) {
              System.out.printf("No dinosaur(s) found for species '%s'\n", species.name());
            } else {
              for (Dinosaur d : dinosaurs) {
                removeDinosaur(d);
              }
            }
          }
          case 4 -> {
            DinosaurSize size = Util.readDinosaurSize(sc);
            Set<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(size);
            if (dinosaurs.isEmpty()) {
              System.out.printf("No dinosaur(s) found for size '%s'\n", size.name());
            } else {
              for (Dinosaur d : dinosaurs) {
                removeDinosaur(d);
              }
            }
          }
          case 5 -> {
            String name = Util.readDinosaurName(sc);
            LocalDate birthDate = Util.readDinosaurBirthDate(sc);
            DinosaurType type = Util.readDinosaurType(sc);
            DinosaurSpecies species = Util.readDinosaurSpecies(sc);
            DinosaurSize size = Util.readDinosaurSize(sc);
            Dinosaur dinosaur = switch (species) {
              case TYRANNOSAURUS, TRICERATOPS, VELOCIRAPTOR, STEGOSAURUS, BRACHIOSAURUS,
                   SPINOSAURUS, PARASAUROLOPHUS, ANKYLOSAURUS -> {
                int height = Util.readDinosaurIntField(sc, "height (in centimeters)");
                int speed = Util.readDinosaurIntField(sc, "max running speed (in Km/h)");
                yield new TerrestrialDinosaur(name, birthDate, type, species, size, height, speed);
              }
              case PLIOSAURS -> {
                int divingDepth = Util.readDinosaurIntField(sc, "max diving depth (in meters)");
                int underwaterSpeed = Util.readDinosaurIntField(sc, "max underwater speed (in Km/h)");
                boolean isAmphibious = Util.readDinosaurAmphibiousCapability(sc);
                yield new AquaticDinosaur(name, birthDate, type, species, size, divingDepth, underwaterSpeed, isAmphibious);
              }
              case PTEROSAUR -> {
                int wingspan = Util.readDinosaurIntField(sc, "wingspan (in centimeters)");
                int maxAltitude = Util.readDinosaurIntField(sc, "max altitude (in meters)");
                int flyingSpeed = Util.readDinosaurIntField(sc, "max flying speed (in Km/h)");
                yield new FlyingDinosaur(name, birthDate, type, species, size, wingspan, maxAltitude, flyingSpeed);
              }
              default -> null;
            };
            removeDinosaur(dinosaur);
          }
          case 6 -> {
          }
          default -> System.out.println("Invalid choice. Please try again");
        }
        if (choice > 0 && choice < 7) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 6.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while removing dinosaur: %s
          Please try again or return to Dinosaur Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sorts a collection of dinosaurs based on the provided comparator. If the comparator is null, it uses the default
   *  comparator defined in the DinosaurComparator class. The method returns a sorted list of dinosaurs.
   * @param dinosaurs the collection of dinosaurs to be sorted.
   * @param comparator the comparator to determine the order of the dinosaurs. If null, the default comparator is used.
   * @return a sorted list of dinosaurs. If the input collection is null or empty, an empty list is returned.
   */
  public List<Dinosaur> sortDinosaurs(Collection<Dinosaur> dinosaurs, Comparator<Dinosaur> comparator) {
    if (dinosaurs == null || dinosaurs.isEmpty()) return List.of();
    
    List<Dinosaur> sortedDinosaurs = new ArrayList<>(dinosaurs);
    Collections.sort(sortedDinosaurs, comparator == null ? DinosaurComparator.DEFAULT : comparator);
    return sortedDinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}