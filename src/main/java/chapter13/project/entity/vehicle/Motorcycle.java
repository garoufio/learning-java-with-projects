package chapter13.project.entity.vehicle;

/**
 * Represents a motorcycle, which is a type of vehicle with two wheels. It extends the Vehicle class and provides specific 
 * behavior for motorcycles, such as traveling on the road.
 */
public class Motorcycle extends Vehicle {
  
  /**
   * Constructs a new Motorcycle instance with the specified attributes.
   * @param make the make of the motorcycle.
   * @param model the model of the motorcycle.
   * @param productionYear the production year of the motorcycle.
   * @param color the color of the motorcycle.
   * @param numberOfPassengers the number of passengers the motorcycle can carry.
   * @param maxSpeed the maximum speed of the motorcycle in kilometers per hour.
   */
  public Motorcycle(
      String make,
      String model,
      int productionYear,
      String color,
      int numberOfPassengers,
      double maxSpeed
  ) {
    super(VehicleType.MOTORCYCLE ,make, model, productionYear, color, 2, numberOfPassengers, maxSpeed, false);
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this motorcycle to the specified object for equality. Two motorcycles are considered equal if they have the same 
   * attributes as defined in the {@link Vehicle#equals(Object)} method.
   * @param o the object to compare with this motorcycle.
   * @return true if the specified object is equal to this motorcycle, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
   
    return super.equals(o);
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Returns a string representation of the motorcycle, including its type, make, model, production year, color, 
   * number of wheels, number of passengers, maximum speed, and flying capability.
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
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the motorcycle travel. The specific behavior of this method is driving on the road.
   */
  public void travel() {
    System.out.printf("%s '%s %s' is driving on the road\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
