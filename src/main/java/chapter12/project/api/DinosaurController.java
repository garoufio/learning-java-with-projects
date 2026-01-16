package chapter12.project.api;

import chapter12.project.entity.dinosaur.*;
import chapter12.project.entity.enclosure.Enclosure;
import chapter12.project.entity.enclosure.EnclosureType;
import chapter12.project.service.DinosaurService;
import chapter12.project.service.EnclosureService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DinosaurController {
  
  private DinosaurService dinosaurService;
  private EnclosureService enclosureService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurController(Scanner sc, DinosaurService dinosaurService, EnclosureService enclosureService) {
    this.sc = sc;
    this.dinosaurService = dinosaurService;
    this.enclosureService = enclosureService;
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageDinosaurs() {
    for (;;) {
      System.out.printf("\nDinosaurs service:\n");
      System.out.println("1. Show all Dinosaurs");
      System.out.println("2. Add Dinosaur");
      System.out.println("3. Find Dinosaur");
      System.out.println("4. Edit Dinosaur");
      System.out.println("5. Remove Dinosaur");
      System.out.println("6. Return to main menu");
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
          editDinosaur();
          break;
        case 5:
          removeDinosaur();
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
  
  public void printDinosaurs(List<Dinosaur> dinosaurs) {
    if (dinosaurs == null) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    for (Dinosaur d : dinosaurs) {
      if (d != null) System.out.println(d);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaur() {
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
    
    // create new dinosaur
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
    if (dinosaur == null) {
      System.out.printf("Invalid dinosaur species '%s'. No dinosaur has been added\n", species);
      return;
    }
    
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
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void printDinosaurSubmenuOptions(String action) {
    System.out.printf("\n%s by:\n", action);
    System.out.println("1. Dinosaur name");
    System.out.println("2. Dinosaur type");
    System.out.println("3. Dinosaur species");
    System.out.println("4. Dinosaur size");
    System.out.println("5. Detailed search");
    System.out.println("6. Return to dinosaur menu");
    System.out.print("Enter your choice: ");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void findDinosaur() {
    for (;;) {
      printDinosaurSubmenuOptions("Find");
      int choice = sc.nextInt();
      List<Dinosaur> foundDinosaurs = switch (choice) {
        case 1 -> {
          String name = Util.readDinosaurName(sc);
          Dinosaur dinosaur = dinosaurService.getDinosaur(name);
          if (dinosaur == null) {
            System.out.printf("Dinosaur '%s' not found\n", name);
            yield List.of();
          } else yield List.of(dinosaur);
        }
        case 2 -> {
          DinosaurType type = Util.readDinosaurType(sc);
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(type);
          if (dinosaurs.isEmpty()) {
            System.out.printf("No dinosaur(s) found for type '%s'\n", type.name());
            yield List.of();
          } else yield dinosaurs;
        }
        case 3 -> {
          DinosaurSpecies species = Util.readDinosaurSpecies(sc);
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(species);
          if (dinosaurs.isEmpty()) {
            System.out.printf("No dinosaur(s) found for species '%s'\n", species.name());
            yield List.of();
          } else yield dinosaurs;
        }
        case 4 -> {
          DinosaurSize size = Util.readDinosaurSize(sc);
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(size);
          if (dinosaurs.isEmpty()) {
            System.out.printf("No dinosaur(s) found for size '%s'\n", size.name());
            yield List.of();
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
            yield List.of();
          } else yield List.of(foundDinosaur);
        }
        case 6 -> List.of();
        default -> {
          System.out.println("Invalid choice. Please try again");
          yield List.of();
        }
      };
      if (!foundDinosaurs.isEmpty()) {
        printDinosaurs(foundDinosaurs);
        
        for (;;) {
          System.out.print("Do you want to see more info about the found dinosaur(s)? (Y/N): ");
          String moreInfoChoice = sc.next();
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
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void editDinosaurDetails(Dinosaur dinosaur) {
    // change name
    
    // change birthdate
    
    // change species
    
    // change type
    
    // change size
    
    // change health score
    
    // change specific attributes based on speciess
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void editDinosaur() {
    for (;;) {
      System.out.printf("\nEdit by:\n");
      System.out.println("1. Name");
      System.out.println("2. Detailed search");
      System.out.println("3. Return to dinosaur menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
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
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public void removeDinosaurFromEnclosure(List<Enclosure> enclosures, Dinosaur dinosaur) {
    if (enclosures == null || enclosures.isEmpty()) {
      System.out.println("The enclosure(s) do not exist in the park\n");
    } else {
      for (Enclosure e : enclosures) {
        removeDinosaurFromEnclosure(e, dinosaur);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) {
      System.out.println("Dinosaur not found");
      return;
    }
    
    // remove dinosaur from the list
    if (dinosaurService.removeDinosaur(dinosaur)) {
      System.out.println("Dinosaur removed");
      // remove dinosaur from the related enclosure
      List<Enclosure> enclosures = enclosureService.getEnclosure(dinosaur);
      removeDinosaurFromEnclosure(enclosures, dinosaur);
    } else System.out.printf("Dinosaur '%s' could not be removed\n", dinosaur);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeDinosaur() {
    for (;;) {
      printDinosaurSubmenuOptions("Remove");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = Util.readDinosaurName(sc);
          removeDinosaur(dinosaurService.getDinosaur(name));
        }
        case 2 -> {
          DinosaurType type = Util.readDinosaurType(sc);
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(type);
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
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(species);
          if (dinosaurs.isEmpty()) {
            System.out.printf("No dinosaur(s) found for species '%s'\n",  species.name());
          }
          else {
            for (Dinosaur d : dinosaurs) {
              removeDinosaur(d);
            }
          }
        }
        case 4 -> {
          DinosaurSize size = Util.readDinosaurSize(sc);
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(size);
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
        case 6 -> { }
        default -> System.out.println("Invalid choice. Please try again");
      }
      if (choice > 0 && choice < 7) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
