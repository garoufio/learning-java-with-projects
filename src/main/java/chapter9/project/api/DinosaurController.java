package chapter9.project.api;

import chapter9.project.entity.dinosaur.Dinosaur;
import chapter9.project.entity.dinosaur.DinosaurSize;
import chapter9.project.entity.enclosure.Enclosure;
import chapter9.project.entity.dinosaur.DinosaurSpecies;
import chapter9.project.entity.dinosaur.DinosaurType;
import chapter9.project.entity.enclosure.EnclosureType;
import chapter9.project.service.DinosaurService;
import chapter9.project.service.EnclosureService;

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
    // read age
    int age = Util.readDinosaurAge(sc);
    // read dinosaur type
    DinosaurType type = Util.readDinosaurType(sc);
    sc.nextLine();
    // read dinosaur species
    DinosaurSpecies species = Util.readDinosaurSpecies(sc);
    sc.nextLine();
    // read dinosaur size
    DinosaurSize size = Util.readDinosaurSize(sc);
    // create new Dinosaur
    Dinosaur dinosaur = new Dinosaur(name, age, type, species, size);
    
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
      switch (choice) {
        case 1 -> {
          String name = Util.readDinosaurName(sc);
          Dinosaur dinosaur = dinosaurService.getDinosaur(name);
          if (dinosaur == null) System.out.printf("Dinosaur '%s' not found\n", name);
          else System.out.printf("Dinosaur found: '%s'\n", dinosaur);
        }
        case 2 -> {
          DinosaurType type = Util.readDinosaurType(sc);
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(type);
          if (dinosaurs.isEmpty()) System.out.printf("No dinosaur(s) found for type '%s'\n", type.name());
          else printDinosaurs(dinosaurs);
        }
        case 3 -> {
          DinosaurSpecies species = Util.readDinosaurSpecies(sc);
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(species);
          if (dinosaurs.isEmpty()) System.out.printf("No dinosaur(s) found for species '%s'\n", species.name());
          else printDinosaurs(dinosaurs);
        }
        case 4 -> {
          DinosaurSize size = Util.readDinosaurSize(sc);
          List<Dinosaur> dinosaurs = dinosaurService.getDinosaurs(size);
          if (dinosaurs.isEmpty()) System.out.printf("No dinosaur(s) found for size '%s'\n", size.name());
          else printDinosaurs(dinosaurs);
        }
        case 5 -> {
          String name = Util.readDinosaurName(sc);
          int age = Util.readDinosaurAge(sc);
          DinosaurType type = Util.readDinosaurType(sc);
          DinosaurSpecies species = Util.readDinosaurSpecies(sc);
          DinosaurSize size = Util.readDinosaurSize(sc);
          Dinosaur dinosaur = dinosaurService.getDinosaur(new Dinosaur(name, age, type, species, size));
          if (dinosaur == null) System.out.println("Dinosaur not found");
          else System.out.printf("Dinosaur found: '%s'\n", dinosaur);
        }
        case 6 -> { }
        default -> System.out.println("Invalid choice. Please try again");
      }
      if (choice > 0 && choice < 7) break;
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
    }
    else System.out.printf("Dinosaur '%s' could not be removed\n", dinosaur);
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
          int age = Util.readDinosaurAge(sc);
          DinosaurType type = Util.readDinosaurType(sc);
          DinosaurSpecies species = Util.readDinosaurSpecies(sc);
          DinosaurSize size = Util.readDinosaurSize(sc);
          removeDinosaur(new Dinosaur(name, age, type, species, size));
        }
        case 6 -> { }
        default -> System.out.println("Invalid choice. Please try again");
      }
      if (choice > 0 && choice < 7) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
