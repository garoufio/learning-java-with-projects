package chapter11.project;

import chapter11.project.api.DinosaurCareSystem;
import chapter11.project.entity.dinosaur.*;
import chapter11.project.entity.enclosure.*;
import chapter11.project.exception.DinosaurIllException;
import chapter11.project.exception.EnclosureBreachException;

import java.util.ArrayList;
import java.util.List;

public class App {
  
  public static final int MAX_DAILY_VISITORS = 300;
  public static final int MAX_DINOSAURS = 50;
  public static final int MAX_EMPLOYEES = 50;
  public static final int MAX_ENCLOSURES = 20;
  public static final int MAX_VEHICLES = 40;
  public static final int MAX_DAILY_SPECIAL_EVENTS = 5;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args) {
    DinosaurCareSystem dinosaurCareSystem = new DinosaurCareSystem();
    
    List<Dinosaur> dinosaurs = createDinosaurs();
    try {
      dinosaurCareSystem.handleDinosaurHealth(dinosaurs);
    } catch (DinosaurIllException e) {
      System.err.println("Dinosaur health issue detected: " + e.getMessage());
    }
    
    List<Enclosure> enclosures = createEnclosures();
    try {
      dinosaurCareSystem.handleEnclosureSecurity(enclosures);
    } catch (EnclosureBreachException e) {
      System.err.println("Enclosure security issue detected: " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  //-------------------------------------------------------------------------------------------------------------------
  // Objects initialization
  //-------------------------------------------------------------------------------------------------------------------
  
  private static List<Dinosaur> createDinosaurs() {
    List<Dinosaur> dinosaurs = new ArrayList<>();
    
    dinosaurs.add(
        new TerrestrialDinosaur(
            "Claire", 5, DinosaurType.CARNIVORE, DinosaurSpecies.VELOCIRAPTOR, DinosaurSize.MEDIUM, 48,217, 65
        )
    );
    dinosaurs.add(
        new TerrestrialDinosaur(
            "Jago", 9, DinosaurType.CARNIVORE, DinosaurSpecies.TYRANNOSAURUS, DinosaurSize.BIG, 77,412, 48
        )
    );
    dinosaurs.add(
        new TerrestrialDinosaur(
            "Margot", 12, DinosaurType.HERBIVORE, DinosaurSpecies.BRACHIOSAURUS, DinosaurSize.BIG, 67,408, 35
        )
    );
    dinosaurs.add(
        new TerrestrialDinosaur(
            "Hermes", 2, DinosaurType.HERBIVORE, DinosaurSpecies.STEGOSAURUS, DinosaurSize.BIG, 51, 334, 27
        )
    );
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private static List<Enclosure> createEnclosures() {
    List<Enclosure> enclosures = new ArrayList<>();
    
    // add Raptors Park enclosure and the related dinosaurs and employees
    enclosures.add(new Enclosure(
            EnclosureType.RAPTORS_PARK,
            SafetyLevel.HIGH,
        null,
        null
        )
    );
    // add Free Park enclosure and the related dinosaurs and employees
    enclosures.add(new Enclosure(
            EnclosureType.FREE_PARK,
            SafetyLevel.MEDIUM,
        null,
        null,
        88
        )
    );
    // add Central Building enclosure and the related employees
    enclosures.add(new Enclosure(
            EnclosureType.CENTRAL_BUILDING,
            SafetyLevel.LOW,
        null,
        null,
        95
        )
    );
    // add Tickets Kiosk enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.TICKETS_KIOSK,
        SafetyLevel.LOW,
        null,
        null,
        90
    ));
    // add Vet Center enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.VET_CENTER,
        SafetyLevel.MEDIUM,
        null,
        null,
        95
    ));
    // add Food Store enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.FOOD_STORE,
        SafetyLevel.LOW,
        null,
        null,
        15
    ));
    // add Parking Lot enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.PARKING_LOT,
        SafetyLevel.LOW,
        null,
        null,
        90
    ));
    // add Surveillance Building enclosure and the related employees
    enclosures.add(new Enclosure(
        EnclosureType.SURVEILLANCE_BUILDING,
        SafetyLevel.MEDIUM,
        null,
        null,
        92
    ));
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
