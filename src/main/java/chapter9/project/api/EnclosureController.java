package chapter9.project.api;

import chapter9.project.entity.dinosaur.DinosaurSpecies;
import chapter9.project.entity.dinosaur.DinosaurType;
import chapter9.project.entity.enclosure.Enclosure;
import chapter9.project.entity.enclosure.EnclosureType;
import chapter9.project.entity.enclosure.SafetyLevel;
import chapter9.project.service.EnclosureService;

import java.util.Scanner;

public class EnclosureController {
  
  private EnclosureService enclosureService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EnclosureController(Scanner sc, EnclosureService enclosureService) {
    this.sc = sc;
    this.enclosureService = enclosureService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageEnclosures() {
    for (;;) {
      System.out.printf("\nEnclosures service:\n");
      System.out.println("1. Show all Enclosures");
      System.out.println("2. Add Enclosure");
      System.out.println("3. Find Enclosure");
      System.out.println("4. Remove Enclosure");
      System.out.println("5. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printEnclosures(enclosureService.getAllEnclosures());
          break;
        case 2:
          addEnclosure();
          break;
        case 3:
          findEnclosure();
          break;
        case 4:
          removeEnclosure();
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
  
  public void printEnclosures(Enclosure... enclosures) {
    if (enclosures == null) {
      System.out.println("No Enclosures were added!");
      return;
    }
    
    for (Enclosure e : enclosures) {
      if (e == null) continue;
      
      System.out.println(e);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEnclosure() {
    EnclosureType enclosureType = Util.readEnclosureType(sc);
    SafetyLevel  safetyLevel = Util.readSafetyLevel(sc);
    enclosureService.addEnclosure(new Enclosure(enclosureType, safetyLevel, null, null)); // TODO: to be implemented
    System.out.println("Enclosure added");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void findEnclosure() {
    for (;;) {
      System.out.printf("\nSearch by:\n");
      System.out.println("1. Enclosure Type");
      System.out.printf("2. SafetyLevel"); // TODO: to be implemented
      System.out.println("3. Dinosaur name");
      System.out.println("4. Dinosaur type");
      System.out.println("5. Dinosaur species");
      System.out.println("6. Employee name"); // TODO: to be implemented
      System.out.println("7. Employee job title"); // TODO: to be implemented
      System.out.println("8. Detail search");
      System.out.println("9. Return to enclosure menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          EnclosureType enclosureType = Util.readEnclosureType(sc);
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType);
          if (enclosure == null) System.out.printf("No enclosure found for type '%s'\n", enclosureType);
          else System.out.println(enclosure);
        }
        case 2 -> {
          String dinosaurName = Util.readDinosaurName(sc);
          Enclosure enclosure = enclosureService.getEnclosure(dinosaurName);
          if (enclosure == null) System.out.printf("No enclosure found for dinosaur '%s'\n", dinosaurName);
          else System.out.println(enclosure);
        }
        case 3 -> {
          DinosaurType dinosaurType = Util.readDinosaurType(sc);
          Enclosure[] enclosures = enclosureService.getEnclosures(dinosaurType);
          if (enclosures == null) System.out.printf("No enclosure found for dinosaur type '%s'\n", dinosaurType.name());
          else printEnclosures(enclosures);
        }
        case 4 -> {
          DinosaurSpecies dinosaurSpecies = Util.readDinosaurSpecies(sc);
          Enclosure[] enclosures = enclosureService.getEnclosures(dinosaurSpecies);
          if (enclosures == null) System.out.printf("No enclosure found for dinosaur species '%s'\n", dinosaurSpecies.name());
          else printEnclosures(enclosures);
        }
        case 5 -> {
          EnclosureType enclosureType = Util.readEnclosureType(sc);
          String dinosaurName = Util.readDinosaurName(sc);
          DinosaurType dinosaurType = Util.readDinosaurType(sc);
          DinosaurSpecies dinosaurSpecies = Util.readDinosaurSpecies(sc);
          Enclosure enclosure = enclosureService.getEnclosure(enclosureType, dinosaurType, dinosaurSpecies, dinosaurName);
          if (enclosure == null) System.out.println("No enclosure found");
          else System.out.println(enclosure);
        }
        case 6 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 7) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
          if (enclosureService.removeEnclosure(enclosureType)) System.out.println("Enclosure removed");
          else System.out.printf("No enclosure found for type '%s'\n", enclosureType);
        }
        case 2 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 3) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
