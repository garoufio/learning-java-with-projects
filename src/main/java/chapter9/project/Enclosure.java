package chapter9.project;

import chapter8.project.Project;

import java.util.Arrays;
import java.util.Objects;

public class Enclosure {
  
  private EnclosureType enclosureType;
  private Dinosaur[] dinosaurs;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure(EnclosureType enclosureType) {
    this(enclosureType, null);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure(EnclosureType enclosureType, Dinosaur[] dinosaurs) {
    this.enclosureType = enclosureType;
    this.dinosaurs = dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EnclosureType getEnclosureType() {
    return enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setEnclosureType(EnclosureType enclosureType) {
    this.enclosureType = enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur[] getDinosaurs() {
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDinosaurs(Dinosaur... dinosaurs) {
    this.dinosaurs = dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean addDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) return false;
    if (this.dinosaurs == null) this.dinosaurs = new Dinosaur[Project.MAX_DINOSAURS];
    
    int index = -1;
    for (int i = 0; i < this.dinosaurs.length; i++) {
      if (this.dinosaurs[i] == null) {
        index = i;
        break;
      }
    }
    if (index > -1) {
      this.dinosaurs[index] = dinosaur;
    } else {
      if (this.dinosaurs.length + 1 <= Project.MAX_DINOSAURS) {
        Dinosaur[] dinosaurs = new Dinosaur[this.dinosaurs.length + 1];
        for (int i = 0; i < this.dinosaurs.length; i++) {
          dinosaurs[i] = this.dinosaurs[i];
        }
        dinosaurs[dinosaurs.length - 1] = dinosaur;
        this.dinosaurs = dinosaurs;
        return true;
      }
      else {
        System.out.println("The enclosure cannot accept any more dinosaurs.");
      }
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Enclosure enclosure = (Enclosure) o;
    return (enclosureType == enclosure.getEnclosureType() && Objects.deepEquals(dinosaurs, enclosure.dinosaurs));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(enclosureType, Arrays.hashCode(dinosaurs));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enclosure [type=" + enclosureType.name());
    if (dinosaurs != null) {
      sb.append(", \n\tdinosaurs:\n");
      for (Dinosaur d : dinosaurs) {
        if (d != null) sb.append("\t" + d + "\n");
      }
    }
    sb.append("]");
    return sb.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
