package chapter10.project.entity.vehicle;

import java.util.Objects;

public class Helicopter extends Vehicle {
  
  public static final int DEFAULT_FLIGHT_RANGE = 200; // in Km
  public static final int DEFAULT_ALTITUDE = 3000;
  public static final int DEFAULT_LOAD_CAPACITY = 2000;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int flightRange;         // in Km
  private int maxAltitude;        // in meters
  private int maxLoadCapacity;    // in Kg
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public int getFlightRange() {
    return flightRange;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setFlightRange(int flightRange) {
    this.flightRange = flightRange > 0 ? flightRange : DEFAULT_FLIGHT_RANGE;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxAltitude() {
    return maxAltitude;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxAltitude(int maxAltitude) {
    this.maxAltitude = maxAltitude > 0 ? maxAltitude : DEFAULT_ALTITUDE;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxLoadCapacity() {
    return maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxLoadCapacity(int maxLoadCapacity) {
    this.maxLoadCapacity = maxLoadCapacity > 0 ? maxLoadCapacity : DEFAULT_LOAD_CAPACITY;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void travel() {
    System.out.printf("%s '%s %s' is flying in the sky\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), flightRange, maxAltitude, maxLoadCapacity);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
