package chapter10.project.entity.vehicle;

public class Helicopter extends Vehicle {
  
  public static final int DEFAULT_FLIGHT_RANGE = 200; // in Km
  public static final int DEFAULT_ALTITUDE = 3000;
  public static final int DEFAULT_LOAD = 2000;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int numberOfPassengers;  // in number of passengers
  private int flightRange;         // in Km
  private int maxAltitude;        // in meters
  private int maxLoadCapacity;    // in Kg
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Helicopter(String model, int productionYear, String color, double maxSpeed) {
    super("Boeing", model, productionYear, color, 0, maxSpeed, true);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getNumberOfPassengers() {
    return numberOfPassengers;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setNumberOfPassengers(int numberOfPassengers) {
    this.numberOfPassengers = numberOfPassengers > 0 ? numberOfPassengers : 1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getFlightRange() {
    return flightRange;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setFlightRange(int flightRange) {
    this.flightRange = flightRange > 0 ? flightRange : 200;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxAltitude() {
    return maxAltitude;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxAltitude(int maxAltitude) {
    this.maxAltitude = maxAltitude;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxLoadCapacity() {
    return maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxLoadCapacity(int maxLoadCapacity) {
    this.maxLoadCapacity = maxLoadCapacity;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void travel() {
    System.out.printf("%s '%s %s' is flying in the sky\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
