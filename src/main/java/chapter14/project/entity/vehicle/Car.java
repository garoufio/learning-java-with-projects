package chapter14.project.entity.vehicle;

/**
 * Represents a car, which is a type of vehicle that travels on the road. This class extends the Vehicle class and 
 * provides specific implementations for cars.
 */
public class Car extends Vehicle {
  
  /**
   * Constructs a new Car instance with the specified attributes.
   * @param make the make of the car.
   * @param model the model of the car.
   * @param productionYear the production year of the car.
   * @param color the color of the car.
   * @param numberOfPassengers the number of passengers the car can carry.
   * @param maxSpeed the maximum speed of the car in kilometers per hour.
   */
  public Car(String make, String model, int productionYear, String color, int numberOfPassengers, double maxSpeed) {
    super(VehicleType.CAR, make, model, productionYear, color, 4, numberOfPassengers, maxSpeed, false);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this car to the specified object for equality. Two cars are considered equal if they have the same 
   * attributes as defined in the {@link Vehicle#equals(Object)} method.
   * @param o the object to compare with this car.
   * @return true if the specified object is equal to this car, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    
    return super.equals(o);
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Returns a string representation of the car, including its type, make, model, production year, color, number of 
   * wheels, number of passengers, maximum speed, and flying capability.
   */  
  @Override
  public String toString() {
    return  this.getVehicleType().getDescription() + " [" +
        "make=" + getMake() +
        ", model=" + getModel() +
        ", productionYear=" + getProductionYear() +
        ", color=" + getColor() +
        ", numberOfWheels=" + getNumberOfWheels() +
        ", numberOfPassengers=" + getNumberOfPassengers() +
        ", maxSpeed=" + getMaxSpeed() +
        ", canFly=" + getCanFly() +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the car travel. The specific behavior of this method is driving on the road.
   */
  @Override
  public void travel() {
    System.out.printf("%s '%s %s' is driving on the road\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
