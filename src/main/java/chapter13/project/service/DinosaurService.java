package chapter13.project.service;

import chapter13.project.App;
import chapter13.project.entity.dinosaur.Dinosaur;
import chapter13.project.entity.dinosaur.DinosaurSize;
import chapter13.project.entity.dinosaur.DinosaurSpecies;
import chapter13.project.entity.dinosaur.DinosaurType;

import java.util.HashSet;
import java.util.Set;

public class DinosaurService {
  
  private Set<Dinosaur> dinosaurs;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurService(Set<Dinosaur> dinosaurs) {
    this.dinosaurs = (dinosaurs  == null ? new HashSet<>() : dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
          this.dinosaurs.add(dinosaurs[i]);
          System.out.printf("Dinosaur added '%s'\n", dinosaurs[i]);
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
  
  public Set<Dinosaur> getAllDinosaurs() {
    return this.dinosaurs == null ? Set.of() : Set.copyOf(this.dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur(String dinosaurName) {
    if (this.dinosaurs == null || dinosaurName == null) return null;
    
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getName().equals(dinosaurName)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur(Dinosaur dinosaur) {
    if  (this.dinosaurs == null || dinosaur == null) return null;
    
    return this.dinosaurs.contains(dinosaur) ? dinosaur : null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Set<Dinosaur> getDinosaurs(DinosaurType dinosaurType) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurType == null) return Set.of();
    
    Set<Dinosaur> dinosaurs = new HashSet<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getType().equals(dinosaurType)) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Set<Dinosaur> getDinosaurs(DinosaurSpecies dinosaurSpecies) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurSpecies == null) return Set.of();
    
    Set<Dinosaur> dinosaurs = new HashSet<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getSpecies().equals(dinosaurSpecies)) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Set<Dinosaur> getDinosaurs(DinosaurSize dinosaurSize) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurSize == null) return Set.of();
    
    Set<Dinosaur> dinosaurs = new HashSet<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getSize() == dinosaurSize) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (this.dinosaurs == null || dinosaur == null) return false;
    
    return this.dinosaurs.remove(dinosaur);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
