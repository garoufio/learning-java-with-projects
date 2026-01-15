package chapter12.project.entity.vehicle;

import java.util.Objects;

public class Truck extends Vehicle {
  
  private int maxLoadCapacity;  // in Kg
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Truck(
      String make,
      String model,
      int productionYear,
      String color,
      int numberOfWheels,
      int numberOfPassengers,
      double maxSpeed,
      int maxLoadCapacity
  ) {
    super(VehicleType.TRUCK, make, model, productionYear, color, numberOfWheels, numberOfPassengers, maxSpeed, false);
    setMaxLoadCapacity(maxLoadCapacity);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxLoadCapacity() {
    return maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxLoadCapacity(int maxLoadCapacity) {
    this.maxLoadCapacity = maxLoadCapacity > 0 ? maxLoadCapacity : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    Truck that = (Truck) o;
    return maxLoadCapacity == that.maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), maxLoadCapacity);
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return this.getVehicleType().getDescription() + " [" +
        "make=" + getMake() +
        ", model=" + getModel() +
        ", productionYear=" + getProductionYear() +
        ", color=" + getColor() +
        ", numberOfWheels=" + getNumberOfWheels() +
        ", numberOfPassengers=" + getNumberOfPassengers() +
        ", maxSpeed=" + getMaxSpeed() +
        ", canFly=" + getCanFly() +
        ", maxLoadCapacity=" + maxLoadCapacity + "Kg" +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void travel() {
    System.out.printf("%s '%s %s' is driving on the road\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
