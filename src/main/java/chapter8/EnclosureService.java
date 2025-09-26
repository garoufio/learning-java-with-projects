package chapter8;

import java.util.Arrays;

public class EnclosureService {
  
  private Enclosure[] enclosures;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EnclosureService(Enclosure[] enclosures) {
    this.enclosures = enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEnclosure(Enclosure... enclosures) {
    if (enclosures == null) {
      System.out.println("No enclosures were added");
      return;
    }
    
    if (this.enclosures == null) {
      this.enclosures = Arrays.copyOf(enclosures, enclosures.length);
    }
    else {
      // find empty slots
      int countEmpty = 0;
      for (Enclosure e : this.enclosures) {
        if (e == null) countEmpty++;
      }
      // fill empty slots
      if (countEmpty >= enclosures.length) {
        for (int i = 0, j = 0; i < this.enclosures.length && j < enclosures.length; i++) {
          if (this.enclosures[i] == null) {
            this.enclosures[i] = enclosures[j];
            j++;
          }
        }
        return;
      }
      /* if the number of newly added enclosure is greater than the empty slots, increase the size of the array and
      add them */
      Enclosure[] arr = new Enclosure[this.enclosures.length + enclosures.length];
      System.arraycopy(this.enclosures, 0, arr, 0, this.enclosures.length);
      System.arraycopy(enclosures, 0, arr, this.enclosures.length, enclosures.length);
      this.enclosures = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getEnclosureIndex(Enclosure enclosure) {
    if (enclosure == null) return -1;
    if (this.enclosures == null) return -1;
    
    for (int i = 0; i < enclosures.length; i++) {
      if (enclosures[i] != null && enclosures[i].equals(enclosure)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure[] getAllEnclosures() {
    return this.enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getEnclosure(Enclosure enclosure) {
    if (enclosure == null) return null;
    if (this.enclosures == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e != null && e.equals(enclosure)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getEnclosure(EnclosureType enclosureType) {
    if (enclosureType == null) return null;
    if (this.enclosures == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e != null && e.getEnclosureType() == enclosureType) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getEnclosure(String dinosaurName) {
    if (dinosaurName == null) return null;
    if (this.enclosures == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      Dinosaur[] dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.getName().equals(dinosaurName)) return e;
      }
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getEnclosure(
      EnclosureType enclosureType,
      DinosaurType dinosaurType,
      DinosaurSpecies dinosaurSpecies,
      String dinosaurName
  ) {
    if (dinosaurName == null || dinosaurType == null || dinosaurSpecies == null) return null;
    if (this.enclosures == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      
      if (!e.getEnclosureType().equals(enclosureType)) continue;
      Dinosaur[] dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.getName().equals(dinosaurName) &&
          d.getType().equals(dinosaurType) && d.getSpecies().equals(dinosaurSpecies)
        ) return e;
      }
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure[] getEnclosures(DinosaurType dinosaurType) {
    if (dinosaurType == null) return null;
    if (this.enclosures == null) return null;
    
    int foundEnclosures = 0;
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      Dinosaur[] dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.getType().equals(dinosaurType)) foundEnclosures++;
      }
    }
    if (foundEnclosures == 0) return null;
    
    Enclosure[] enclosures = new Enclosure[foundEnclosures];
    int index = 0;
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      Dinosaur[] dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.getType().equals(dinosaurType)) {
          enclosures[index] = e;
        }
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure[] getEnclosures(DinosaurSpecies dinosaurSpecies) {
    if (dinosaurSpecies == null) return null;
    if (this.enclosures == null) return null;
    
    int foundEnclosures = 0;
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      Dinosaur[] dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.getSpecies().equals(dinosaurSpecies)) foundEnclosures++;
      }
    }
    if (foundEnclosures == 0) return null;
    
    Enclosure[] enclosures = new Enclosure[foundEnclosures];
    int index = 0;
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      Dinosaur[] dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.getSpecies().equals(dinosaurSpecies)) {
          enclosures[index] = e;
        }
      }
    }
    return enclosures;
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEnclosure(Enclosure enclosure) {
    if (enclosure == null) return false;
    if (this.enclosures == null) return false;
    
    int index = getEnclosureIndex(enclosure);
    if (index < 0) return false;
    
    enclosures[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEnclosure(EnclosureType enclosureType) {
    return removeEnclosure(getEnclosure(enclosureType));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
