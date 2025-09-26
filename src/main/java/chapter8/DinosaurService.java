package chapter8;

import java.util.Arrays;

public class DinosaurService {
  
  private Dinosaur[] dinosaurs;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurService(Dinosaur[] dinosaurs) {
    this.dinosaurs = dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaurs(Dinosaur... dinosaurs) {
    if (dinosaurs == null) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    if (this.dinosaurs == null) {
      this.dinosaurs = Arrays.copyOf(dinosaurs, dinosaurs.length);
    }
    else {
      // find empty slots
      int countEmpty = 0;
      for (Dinosaur d : this.dinosaurs) {
        if (d == null) countEmpty++;
      }
      // fill empty slots
      if (countEmpty >= dinosaurs.length) {
        for (int i = 0, j = 0; i < this.dinosaurs.length && j < dinosaurs.length; i++) {
          if (this.dinosaurs[i] == null) {
            this.dinosaurs[i] = dinosaurs[j];
            j++;
          }
        }
        return;
      }
      /* if the number of newly added dinosaurs is greater than the empty slots, increase the size of the array and
      add them */
      Dinosaur[] arr = new Dinosaur[this.dinosaurs.length + dinosaurs.length];
      System.arraycopy(this.dinosaurs, 0, arr, 0, this.dinosaurs.length);
      System.arraycopy(dinosaurs, 0, arr, this.dinosaurs.length, dinosaurs.length);
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
      if (dinosaurs[i] != null && dinosaurs[i].equals(dinosaur)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur(String dinosaurName) {
    if (dinosaurs == null) return null;
    
    for (Dinosaur d : dinosaurs) {
      if (d != null && d.getName().equals(dinosaurName)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur(Dinosaur dinosaur) {
    if  (dinosaur == null) return null;
    
    for (Dinosaur d : this.dinosaurs) {
      if (d != null && d.equals(dinosaur)) return d;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur[] getDinosaurs(DinosaurType dinosaurType) {
    if (dinosaurs == null) return null;
    
    int dinosaursFound = 0;
    for (Dinosaur d : dinosaurs) {
      if (d != null && d.getType() == dinosaurType) dinosaursFound++;
    }
    if (dinosaursFound == 0) return null;
    
    Dinosaur[] arr = new Dinosaur[dinosaursFound];
    for (int i = 0, j = 0; i < dinosaurs.length && j < arr.length; i++) {
      if (dinosaurs[i] != null && dinosaurs[i].getType() == dinosaurType) {
        arr[j] =  dinosaurs[i];
        j++;
      }
    }
    return arr;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur[] getDinosaurs(DinosaurSpecies dinosaurSpecies) {
    if (dinosaurs == null) return null;
    
    int dinosaursFound = 0;
    for (Dinosaur d : dinosaurs) {
      if (d != null && d.getSpecies() == dinosaurSpecies) dinosaursFound++;
    }
    if (dinosaursFound == 0) return null;
    
    Dinosaur[] arr = new Dinosaur[dinosaursFound];
    for (int i = 0, j = 0; i < dinosaurs.length && j < arr.length; i++) {
      if (dinosaurs[i] != null && dinosaurs[i].getSpecies() == dinosaurSpecies) {
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
