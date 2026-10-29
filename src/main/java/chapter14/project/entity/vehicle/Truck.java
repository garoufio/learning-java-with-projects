package chapter14.project.entity.vehicle;

import java.util.Objects;

/**
 * Represents a truck vehicle with a maximum load capacity. This class extends the Vehicle class and adds the 
 * specific attribute for the truck's load capacity.
 */
public class Truck extends Vehicle {
  
  private int maxLoadCapacity;  // in Kg
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Truck instance with the specified attributes.
   * @param make the make of the truck.
   * @param model the model of the truck.
   * @param productionYear the production year of the truck.
   * @param color the color of the truck.
   * @param numberOfWheels the number of wheels of the truck.
   * @param numberOfPassengers the number of passengers the truck can carry.
   * @param maxSpeed the maximum speed of the truck.
   * @param maxLoadCapacity the maximum load capacity of the truck in kilograms.
   */
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
  
  /**
   * Returns the maximum load capacity of the truck in kilograms.
   * @return the maximum load capacity of the truck.
   */
  public int getMaxLoadCapacity() {
    return maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the maximum load capacity of the truck in kilograms. If the specified value is less than or equal to zero, it 
   * will be set to 0.
   * @param maxLoadCapacity the maximum load capacity to set.
   */
  public void setMaxLoadCapacity(int maxLoadCapacity) {
    this.maxLoadCapacity = maxLoadCapacity > 0 ? maxLoadCapacity : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this truck to the specified object for equality. Two trucks are considered equal if they have the same 
   * attributes as defined in the {@link Vehicle#equals(Object)} method and the same maximum load capacity.
   * @param o the object to compare with this truck.
   * @return true if the specified object is equal to this truck, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    Truck that = (Truck) o;
    return maxLoadCapacity == that.maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a hash code value for the truck. The hash value is computed based on the hash code of the superclass (Vehicle) and 
   * the maximum load capacity.
   * @return a hash code value for this truck.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), maxLoadCapacity);
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the truck, including its type, make, model, production year, color, 
   * number of wheels, number of passengers, maximum speed, flying capability, and maximum load capacity.
   */
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
  
  /**
   * Makes the truck travel. The specific behavior of this method is driving on the road.
   */
  public void travel() {
    System.out.printf("%s '%s %s' is driving on the road\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
