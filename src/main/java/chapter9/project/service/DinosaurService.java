package chapter9.project.service;

import chapter9.project.entity.dinosaur.Dinosaur;
import chapter9.project.entity.dinosaur.DinosaurSpecies;
import chapter9.project.entity.dinosaur.DinosaurType;

import java.util.ArrayList;
import java.util.List;

public class DinosaurService {
  
  private List<Dinosaur> dinosaurs;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurService(List<Dinosaur> dinosaurs) {
    this.dinosaurs = dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaurs(Dinosaur... dinosaurs) {
    if (dinosaurs == null || dinosaurs.length == 0) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    for (Dinosaur d : dinosaurs) {
      if (d == null) continue;
      this.dinosaurs.add(d);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Dinosaur> getAllDinosaurs() {
    return this.dinosaurs == null ? null : List.copyOf(this.dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur(String dinosaurName) {
    if (dinosaurs == null || dinosaurName == null) return null;
    
    for (Dinosaur d : dinosaurs) {
      if (d != null && d.getName().equals(dinosaurName)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur(Dinosaur dinosaur) {
    if  (dinosaurs == null || dinosaur == null) return null;
    
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.equals(dinosaur)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Dinosaur> getDinosaurs(DinosaurType dinosaurType) {
    if (dinosaurs == null || dinosaurType == null) return null;
    
    List<Dinosaur> dinosaurs = new ArrayList<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getType().equals(dinosaurType)) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Dinosaur> getDinosaurs(DinosaurSpecies dinosaurSpecies) {
    if (dinosaurs == null || dinosaurSpecies == null) return null;
    
    List<Dinosaur> dinosaurs = new ArrayList<>();
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.getSpecies().equals(dinosaurSpecies)) dinosaurs.add(d);
    }
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (dinosaurs == null || dinosaur == null) return false;
    
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.equals(dinosaur)) return true;
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(String dinosaurName) {
    return removeDinosaur(getDinosaur(dinosaurName));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
