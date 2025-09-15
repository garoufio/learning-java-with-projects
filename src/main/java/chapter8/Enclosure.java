package chapter8;

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
  
  public void setDinosaurs(Dinosaur[] dinosaurs) {
    this.dinosaurs = dinosaurs;
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
    return "Enclosure [name=" + enclosureType.name() + ", dinosaurs=" + dinosaurs + "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
