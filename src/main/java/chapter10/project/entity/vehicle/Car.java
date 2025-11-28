package chapter10.project.entity.vehicle;

import java.util.Objects;

public class Car extends Vehicle {
  
  int numberOfPassengers;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Car(String make, String model, int productionYear, String color, double maxSpeed, int numberOfPassengers) {
    super(make, model, productionYear, color, 4, maxSpeed, false);
    setNumberOfPassengers(numberOfPassengers);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getNumberOfPassengers() {
    return numberOfPassengers;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setNumberOfPassengers(int numberOfPassengers) {
    this.numberOfPassengers = numberOfPassengers > 0 ? numberOfPassengers : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    Car that = (Car) o;
    return numberOfPassengers == that.numberOfPassengers;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), numberOfPassengers);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return  this.getClass().getSimpleName() + " [" +
        "make=" + getMake() +
        ", model=" + getModel() +
        ", productionYear=" + getProductionYear() +
        ", color=" + getColor() +
        ", numberOfWheels=" + getNumberOfWheels() +
        ", maxSpeed=" + getMaxSpeed() +
        ", numberOfPassengers=" + numberOfPassengers +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void travel() {
    System.out.printf("%s '%s %s' is driving on the road\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
