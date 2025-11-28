package chapter10.project.entity.vehicle;

import java.util.Objects;

public class Motorcycle extends Vehicle {
  
  public Motorcycle(
      String make,
      String model,
      int productionYear,
      String color,
      int numberOfWheels,
      double maxSpeed
  ) {
    super(make, model, productionYear, color, numberOfWheels, maxSpeed, false);
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " [" +
        "make=" + getMake() +
        ", model=" + getModel() +
        ", productionYear=" + getProductionYear() +
        ", color=" + getColor() +
        ", numberOfWheels=" + getNumberOfWheels() +
        ", maxSpeed=" + getMaxSpeed() +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void travel() {
    System.out.printf("%s '%s %s' is driving on the road\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  
}
