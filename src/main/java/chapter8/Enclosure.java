package chapter8;

import java.util.Arrays;
import java.util.Objects;

public class Enclosure {
  
  private String name;
  private Dinosaur[] dinosaurs;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure(String name) {
    this(name, null);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure(String name, Dinosaur[] dinosaurs) {
    this.name = name;
    this.dinosaurs = dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setName(String name) {
    this.name = name;
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
    return Objects.equals(name, enclosure.name) && Objects.deepEquals(dinosaurs, enclosure.dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, Arrays.hashCode(dinosaurs));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Enclosure [name=" + name + ", dinosaurs=" + dinosaurs + "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
