package chapter8;

import java.util.Arrays;

public class DinosaurService {
  
  private Dinosaur[] dinosaurs;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurService(Dinosaur[] dinosaurs) {
    this.dinosaurs = dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaurs(Dinosaur... dinosaur) {
    if (dinosaur == null) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    if (this.dinosaurs == null) {
      this.dinosaurs = Arrays.copyOf(dinosaur, dinosaur.length);
    }
    else {
      Dinosaur[] arr = new Dinosaur[this.dinosaurs.length + dinosaur.length];
      System.arraycopy(this.dinosaurs, 0, arr, 0, this.dinosaurs.length);
      System.arraycopy(dinosaur, 0, arr, this.dinosaurs.length, dinosaur.length);
      this.dinosaurs = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur[] getAllDinosaurs() {
    return dinosaurs == null ? null : Arrays.copyOf(dinosaurs, dinosaurs.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getDinosaurIndex(Dinosaur dinosaur) {
    if  (dinosaurs == null) return -1;
    
    for (int i = 0; i < dinosaurs.length; i++) {
      if (dinosaurs[i].equals(dinosaur)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur(String dinosaurName) {
    if (dinosaurs == null) return null;
    
    for (Dinosaur d : dinosaurs) {
      if (d.getName().equals(dinosaurName)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur(Dinosaur dinosaur) {
    if  (dinosaur == null) return null;
    
    for (Dinosaur d : this.dinosaurs) {
      if (d.equals(dinosaur)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur[] getDinosaursByType(DinosaurType dinosaurType) {
    if (dinosaurs == null) return null;
    
    int capacity = 0;
    for (Dinosaur d : dinosaurs) {
      if (d.getType() == dinosaurType) capacity++;
    }
    if (capacity == 0) return null;
    
    Dinosaur[] arr = new Dinosaur[capacity];
    for (int i = 0, j = 0; i < dinosaurs.length && j < arr.length; i++) {
      if (dinosaurs[i].getType() == dinosaurType) {
        arr[j] =  dinosaurs[i];
        j++;
      }
    }
    return arr;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur[] getDinosaursBySpecies(DinosaurSpecies dinosaurSpecies) {
    if (dinosaurs == null) return null;
    
    int capacity = 0;
    for (Dinosaur d : dinosaurs) {
      if (d.getSpecies() == dinosaurSpecies) capacity++;
    }
    if (capacity == 0) return null;
    
    Dinosaur[] arr = new Dinosaur[capacity];
    for (int i = 0, j = 0; i < dinosaurs.length && j < arr.length; i++) {
      if (dinosaurs[i].getSpecies() == dinosaurSpecies) {
        arr[j] =  dinosaurs[i];
        j++;
      }
    }
    return arr;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) return false;
    
    int index = getDinosaurIndex(dinosaur);
    if (index < 0) return false;
    
    dinosaurs[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(String dinosaurName) {
    return removeDinosaur(getDinosaur(dinosaurName));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
