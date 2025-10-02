package chapter8.project;

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
  
  public void printDinosaurs(Dinosaur... dinosaurs) {
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
    // read age
    int age = Util.readDinosaurAge(sc);
    // read dinosaur type
    DinosaurType type = Util.readDinosaurType(sc);
    sc.nextLine();
    // read dinosaur species
    DinosaurSpecies species = Util.readDinosaurSpecies(sc);
    sc.nextLine();
    Dinosaur dinosaur = new Dinosaur(name, age, type, species);
    
    // add dinosaur into an eclosure
    EnclosureType enclosureType = Util.readEnclosureType(sc);
    if (enclosureType != null) {
      Enclosure enclosure = enclosureService.getEnclosure(enclosureType);
      if (enclosure != null) {
        if (enclosure.addDinosaur(dinosaur)) {
          dinosaurService.addDinosaurs(dinosaur);
          System.out.println("Dinosaur added!");
          return;
        }
      }
      else System.out.println("The selected enclosure does not exist in the park. Please try again.");
    }
    System.out.println("No dinosaur has been added!");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void printDinosaurSubmenuOptions(String action) {
    System.out.printf("\n%s by:\n", action);
    System.out.println("1. Dinosaur name");
    System.out.println("2. Dinosaur type");
    System.out.println("3. Dinosaur species");
    System.out.println("4. Detailed search");
    System.out.println("5. Return to dinosaur menu");
    System.out.print("Enter your choice: ");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void findDinosaur() {
    for (;;) {
      printDinosaurSubmenuOptions("Find");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = Util.readDinosaurName(sc);
          Dinosaur dinosaur = dinosaurService.getDinosaur(name);
          if (dinosaur == null) System.out.printf("Dinosaur '%s' not found!\n", name);
          else System.out.printf("Dinosaur found: '%s'\n", dinosaur.toString());
        }
        case 2 -> {
          DinosaurType type = Util.readDinosaurType(sc);
          Dinosaur[] dinosaurs = dinosaurService.getDinosaurs(type);
          if (dinosaurs == null) System.out.printf("No dinosaur(s) found for type '%s'!\n", type.name());
          else printDinosaurs(dinosaurs);
        }
        case 3 -> {
          DinosaurSpecies species = Util.readDinosaurSpecies(sc);
          Dinosaur[] dinosaurs = dinosaurService.getDinosaurs(species);
          if (dinosaurs == null) System.out.printf("No dinosaur(s) found for species '%s'!\n", species.name());
          else printDinosaurs(dinosaurs);
        }
        case 4 -> {
          String name = Util.readDinosaurName(sc);
          int age = Util.readDinosaurAge(sc);
          DinosaurType type = Util.readDinosaurType(sc);
          DinosaurSpecies species = Util.readDinosaurSpecies(sc);
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
  
  public void removeDinosaurFromEnclosure(Enclosure enclosure, Dinosaur dinosaur) {
    if (enclosure == null) {
      System.out.printf("The enclosure for dinosaur '%s' does not exist in the park\n", dinosaur.getName());
    } else {
      if (enclosureService.removeDinosaurFromEnclosure(enclosure, dinosaur)) {
        if (dinosaurService.removeDinosaur(dinosaur)) System.out.printf("Dinosaur '%s' removed\n", dinosaur);
        else System.out.println("Dinosaur could not be removed from the Dinosaur's list!");
      } else {
        System.out.println("Dinosaur could not be removed from the enclosure!");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeDinosaur() {
    for (;;) {
      printDinosaurSubmenuOptions("Remove");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String name = Util.readDinosaurName(sc);
          Dinosaur dinosaur = dinosaurService.getDinosaur(name);
          if (dinosaur == null) System.out.printf("Dinosaur '%s' not found!\n", name);
          else {
            Enclosure enclosure = enclosureService.getEnclosure(name);
            removeDinosaurFromEnclosure(enclosure, dinosaur);
          }
        }
        case 2 -> {
          DinosaurType type = Util.readDinosaurType(sc);
          Dinosaur[] dinosaurs = dinosaurService.getDinosaurs(type);
          if (dinosaurs == null || dinosaurs.length == 0) {
            System.out.printf("No dinosaur(s) found for type '%s'!\n", type.name());
          } else {
            for (Dinosaur d : dinosaurs) {
              Enclosure enclosure = enclosureService.getEnclosure(d.getName());
              removeDinosaurFromEnclosure(enclosure, d);
            }
          }
        }
        case 3 -> {
          DinosaurSpecies species = Util.readDinosaurSpecies(sc);
          Dinosaur[] dinosaurs = dinosaurService.getDinosaurs(species);
          if (dinosaurs == null || dinosaurs.length == 0) {
            System.out.printf("No dinosaur(s) found for species '%s'!\n",  species.name());
          }
          else {
            for (Dinosaur d : dinosaurs) {
              Enclosure enclosure = enclosureService.getEnclosure(d.getName());
              removeDinosaurFromEnclosure(enclosure, d);
            }
          }
        }
        case 4 -> {
          String name = Util.readDinosaurName(sc);
          int age = Util.readDinosaurAge(sc);
          DinosaurType type = Util.readDinosaurType(sc);
          DinosaurSpecies species = Util.readDinosaurSpecies(sc);
          Dinosaur dinosaur = new Dinosaur(name, age, type, species);
          Enclosure enclosure = enclosureService.getEnclosure(name);
          removeDinosaurFromEnclosure(enclosure, dinosaur);
        }
        case 5 -> { break; }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 6) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
