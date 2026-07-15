package chapter13;

import java.time.LocalDate;
import java.util.Objects;

public class Velociraptor extends Dinosaur {
  
  private double maxRunningSpeed; // Km/h
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Velociraptor(String name, LocalDate birthDate, int healthScore, double maxRunningSpeed) {
    super(name, birthDate, healthScore);
    this.maxRunningSpeed = maxRunningSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Velociraptor(String name, LocalDate birthDate, double maxRunningSpeed) {
    super(name, birthDate);
    this.maxRunningSpeed = maxRunningSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public double getMaxRunningSpeed() {
    return maxRunningSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxRunningSpeed(double maxRunningSpeed) {
    this.maxRunningSpeed = maxRunningSpeed < 0 ? 0.0 : maxRunningSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Velociraptor that = (Velociraptor) o;
    return Double.compare(maxRunningSpeed, that.maxRunningSpeed) == 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), maxRunningSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
