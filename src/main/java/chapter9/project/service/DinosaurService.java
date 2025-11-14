package chapter9.project.service;

import chapter9.project.App;
import chapter9.project.entity.dinosaur.Dinosaur;
import chapter9.project.entity.dinosaur.DinosaurSize;
import chapter9.project.entity.dinosaur.DinosaurSpecies;
import chapter9.project.entity.dinosaur.DinosaurType;

import java.util.ArrayList;
import java.util.List;

public class DinosaurService {
  
  private List<Dinosaur> dinosaurs;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurService(List<Dinosaur> dinosaurs) {
    this.dinosaurs = (dinosaurs  == null ? new ArrayList<>() : dinosaurs);
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
  
  public List<Dinosaur> getAllDinosaurs() {
    return this.dinosaurs == null ? List.of() : List.copyOf(this.dinosaurs);
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
    
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.equals(dinosaur)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Dinosaur> getDinosaurs(DinosaurType dinosaurType) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurType == null) return List.of();
    
    List<Dinosaur> dinosaurs = new ArrayList<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getType().equals(dinosaurType)) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Dinosaur> getDinosaurs(DinosaurSpecies dinosaurSpecies) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurSpecies == null) return List.of();
    
    List<Dinosaur> dinosaurs = new ArrayList<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getSpecies().equals(dinosaurSpecies)) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Dinosaur> getDinosaurs(DinosaurSize dinosaurSize) {
    if (this.dinosaurs == null || this.dinosaurs.isEmpty() || dinosaurSize == null) return List.of();
    
    List<Dinosaur> dinosaurs = new ArrayList<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getSize() == dinosaurSize) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (this.dinosaurs == null || dinosaur == null) return false;
    
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.equals(dinosaur)) {
        return this.dinosaurs.remove(dinosaur);
      }
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
