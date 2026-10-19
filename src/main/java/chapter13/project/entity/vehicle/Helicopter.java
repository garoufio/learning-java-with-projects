package chapter13.project.entity.vehicle;

import java.util.Objects;

/**
 * Represents a helicopter, which is a type of vehicle capable of flying. It extends the Vehicle class and adds 
 * specific attributes for flight range, maximum altitude, and load capacity.
 */
public class Helicopter extends Vehicle {
  
  public static final int DEFAULT_FLIGHT_RANGE = 200; // in Km
  public static final int DEFAULT_ALTITUDE = 3000;
  public static final int DEFAULT_LOAD_CAPACITY = 2000;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int flightRange;         // in Km
  private int maxAltitude;        // in meters
  private int maxLoadCapacity;    // in Kg
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Helicopter instance with the specified attributes.
   * @param make the make of the helicopter.
   * @param model the model of the helicopter.
   * @param productionYear the production year of the helicopter.
   * @param color the color of the helicopter.
   * @param numberOfPassengers the number of passengers the helicopter can carry.
   * @param maxSpeed the maximum speed of the helicopter in kilometers per hour.
   * @param flightRange the flight range of the helicopter in kilometers.
   * @param maxAltitude the maximum altitude the helicopter can reach in meters.
   * @param maxLoadCapacity the maximum load capacity of the helicopter in kilograms.
   */
  public Helicopter(
      String make,
      String model,
      int productionYear,
      String color,
      int numberOfPassengers,
      double maxSpeed,
      int flightRange,
      int maxAltitude,
      int maxLoadCapacity
  ) {
    super(
        VehicleType.HELICOPTER, make, model, productionYear, color,
        0, numberOfPassengers, maxSpeed, true
    );
    setFlightRange(flightRange);
    setMaxAltitude(maxAltitude);
    setMaxLoadCapacity(maxLoadCapacity);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the flight range of the helicopter in kilometers.
   * @return the flight range of the helicopter in kilometers.
   */
  public int getFlightRange() {
    return flightRange;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the flight range of the helicopter in kilometers. If the specified value is not positive, the default flight 
   * range will be used, see {@link #DEFAULT_FLIGHT_RANGE}.
   * @param flightRange the flight range of the helicopter in kilometers.
   */
  public void setFlightRange(int flightRange) {
    this.flightRange = flightRange > 0 ? flightRange : DEFAULT_FLIGHT_RANGE;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the maximum altitude the helicopter can reach in meters.
   * @return the maximum altitude the helicopter can reach in meters.
   */
  public int getMaxAltitude() {
    return maxAltitude;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the maximum altitude the helicopter can reach in meters. If the specified value is not positive, the 
   * default altitude will be used, see {@link #DEFAULT_ALTITUDE}.
   * @param maxAltitude the maximum altitude the helicopter can reach in meters.
   */
  public void setMaxAltitude(int maxAltitude) {
    this.maxAltitude = maxAltitude > 0 ? maxAltitude : DEFAULT_ALTITUDE;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the maximum load capacity of the helicopter in kilograms.
   * @return the maximum load capacity of the helicopter in kilograms.
   */
  public int getMaxLoadCapacity() {
    return maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the maximum load capacity of the helicopter in kilograms. If the specified value is not positive, the default
   *  load capacity will be used, see {@link #DEFAULT_LOAD_CAPACITY}.
   * @param maxLoadCapacity the maximum load capacity of the helicopter in kilograms.
   */
  public void setMaxLoadCapacity(int maxLoadCapacity) {
    this.maxLoadCapacity = maxLoadCapacity > 0 ? maxLoadCapacity : DEFAULT_LOAD_CAPACITY;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the helicopter travel by flying in the sky.
   */
  @Override
  public void travel() {
    System.out.printf("%s '%s %s' is flying in the sky\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this helicopter to the specified object. The result is true if and only if the argument is not null, is a
   * Helicopter object, and all significant fields (including those inherited from the superclass) are equal.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    Helicopter that = (Helicopter) o;
    return flightRange == that.flightRange &&
        maxAltitude == that.maxAltitude &&
        maxLoadCapacity == that.maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  /**
   * Returns a hash code value for the helicopter. The hash code is computed based on flight range, maximum altitude, 
   * maximum load capacity, and the hash code of the superclass.
   * @return a hash code value for this helicopter.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), flightRange, maxAltitude, maxLoadCapacity);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the helicopter. The string includes the vehicle type, make, model, production year,
   * color, number of wheels, maximum speed, flight capability, flight range, maximum altitude, and maximum load capacity.
   * @return a string representation of this helicopter.
   */
  @Override
  public String toString() {
    return getVehicleType().getDescription() + " [" +
        ", make=" + getMake() +
        ", model=" + getModel() +
        ", productionYear=" + getProductionYear() +
        ", color=" + getColor() +
        ", numberOfWheels=" + getNumberOfWheels() +
        ", maxSpeed=" + getMaxSpeed() +
        ", canFly=" + getCanFly() +
        ", flightRange=" + flightRange +
        ", maxAltitude=" + maxAltitude +
        ", maxLoadCapacity=" + maxLoadCapacity +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
