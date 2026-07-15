package chapter13;

import java.time.LocalDate;
import java.util.Objects;

public class TRex extends Dinosaur {
  
  private double height;  // meters
  private double weight;  // Kg
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public TRex(String name, LocalDate birthdate, double height, double weight) {
    super(name, birthdate);
    setHeight(height);
    setWeight(weight);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public TRex(String name, LocalDate birthdate, int healthcare, double height, double weight) {
    super(name, birthdate, healthcare);
    setHeight(height);
    setWeight(weight);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public double getHeight() {
    return height;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setHeight(double height) {
    this.height = height > 0 ? height : 0.0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public double getWeight() {
    return weight;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setWeight(double weight) {
    this.weight = weight > 0 ? weight : 0.0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    TRex tRex = (TRex) o;
    return Double.compare(height, tRex.height) == 0 && Double.compare(weight, tRex.weight) == 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), height, weight);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
