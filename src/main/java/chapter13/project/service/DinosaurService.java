package chapter13.project.service;

import chapter13.project.App;
import chapter13.project.entity.dinosaur.Dinosaur;
import chapter13.project.entity.dinosaur.DinosaurSize;
import chapter13.project.entity.dinosaur.DinosaurSpecies;
import chapter13.project.entity.dinosaur.DinosaurType;

import java.util.HashSet;
import java.util.Set;

/**
 * Service class for managing dinosaurs, including adding, retrieving, and filtering dinosaurs by type, species, and size.
 */
public class DinosaurService {
  
  private Set<Dinosaur> dinosaurs;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new DinosaurService with the given set of dinosaurs. If the provided set is null, an empty set is 
   * initialized.
   * @param dinosaurs the initial set of dinosaurs to manage, or null to initialize an empty set.
   */
  public DinosaurService(Set<Dinosaur> dinosaurs) {
    this.dinosaurs = (dinosaurs  == null ? new HashSet<>() : dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds the given dinosaurs to the service. If the provided array is null or empty, no dinosaurs are added.
   * @param dinosaurs the dinosaurs to add, or null/empty array to add none.
   */
  public void addDinosaurs(Dinosaur... dinosaurs) {
    if (this.dinosaurs == null || dinosaurs == null || dinosaurs.length == 0) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    if (this.dinosaurs.size() == App.MAX_DINOSAURS) {
      System.out.println("No more dinosaurs can be added as maximum number of dinosaurs has been reached");
      return;
    }
    for (int i = 0; i < dinosaurs.length; i++) {
      if (dinosaurs[i] != null) {
        if (this.dinosaurs.size() < App.MAX_DINOSAURS) {
          if (this.dinosaurs.add(dinosaurs[i])) {
            System.out.printf("Dinosaur added '%s'\n", dinosaurs[i]);
          } else {
            System.out.printf("Dinosaur already exists '%s'\n", dinosaurs[i]);
          }
        } else {
          System.out.printf("Maximum number of dinosaurs has been reached. '%d' dinosaurs were added\n", i);
          break;
        }
      } else {
        System.out.printf("Invalid dinosaur at index '%d'\n", i);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all dinosaurs managed by the service. If no dinosaurs are present, an empty set is returned.
   * @return a set of all dinosaurs, or an empty set if none are present.
   */
  public Set<Dinosaur> getAllDinosaurs() {
    return this.dinosaurs == null ? Set.of() : Set.copyOf(this.dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a dinosaur by its name. If no dinosaur with the given name exists, null is returned.
   * @param dinosaurName the name of the dinosaur to retrieve.
   * @return the dinosaur with the specified name, or null if not found.
   */
  public Dinosaur getDinosaur(String dinosaurName) {
    if (this.dinosaurs == null || dinosaurName == null) return null;
    
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getName().equals(dinosaurName)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a dinosaur by its instance. If the dinosaur is not managed by the service, null is returned.
   * @param dinosaur the dinosaur instance to retrieve.
   * @return the dinosaur if it exists in the service, or null if not found.
   */
  public Dinosaur getDinosaur(Dinosaur dinosaur) {
    if  (this.dinosaurs == null || dinosaur == null) return null;
    
    return this.dinosaurs.contains(dinosaur) ? dinosaur : null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all dinosaurs of the specified type. If no dinosaurs of the given type exist, an empty set is returned.
   * @param dinosaurType the type of dinosaurs to retrieve.
   * @return a set of dinosaurs matching the specified type, or an empty set if none are found.
   */
  public Set<Dinosaur> getDinosaurs(DinosaurType dinosaurType) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurType == null) return Set.of();
    
    Set<Dinosaur> dinosaurs = new HashSet<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getType().equals(dinosaurType)) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all dinosaurs of the specified species. If no dinosaurs of the given species exist, an empty set is 
   * returned.
   * @param dinosaurSpecies the species of dinosaurs to retrieve.
   * @return a set of dinosaurs matching the specified species, or an empty set if none are found.
   */
  public Set<Dinosaur> getDinosaurs(DinosaurSpecies dinosaurSpecies) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurSpecies == null) return Set.of();
    
    Set<Dinosaur> dinosaurs = new HashSet<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getSpecies().equals(dinosaurSpecies)) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all dinosaurs of the specified size. If no dinosaurs of the given size exist, an empty set is returned.
   * @param dinosaurSize the size of dinosaurs to retrieve.
   * @return a set of dinosaurs matching the specified size, or an empty set if none are found.
   */
  public Set<Dinosaur> getDinosaurs(DinosaurSize dinosaurSize) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurSize == null) return Set.of();
    
    Set<Dinosaur> dinosaurs = new HashSet<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getSize() == dinosaurSize) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified dinosaur from the collection. Returns true if the dinosaur was successfully removed, false 
   * otherwise.
   * @param dinosaur the dinosaur to be removed.
   * @return true if the dinosaur was successfully removed, false otherwise.
   */
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (this.dinosaurs == null || dinosaur == null) return false;
    
    return this.dinosaurs.remove(dinosaur);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
