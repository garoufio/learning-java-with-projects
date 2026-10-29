package chapter14.project.entity.vehicle;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Abstract class representing a vehicle with common properties such as make, model, production year, color, number of 
 * wheels, number of passengers, maximum speed, and the ability to fly. This class serves as a base for specific types 
 * of vehicles and implements common functionality for all vehicle types. It also implements the Comparable interface 
 * to allow vehicles to be compared based on their production year.
 */
public abstract class Vehicle implements Comparable<Vehicle> {

  public static final String DEFAULT_PARK_VEHICLE_COLOR = "White";
  public static final int DEFAULT_PARK_VEHICLE_NUMBER_OF_WHEELS = 4;
  public static final int DEFAULT_PARK_VEHICLE_NUMBER_OF_PASSENGERS = 1;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private String make;
  private String model;
  private int productionYear;
  private String color;
  private int numberOfWheels;
  private int numberOfPassengers;
  private double maxSpeed;
  private boolean canFly;
  private VehicleType vehicleType;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a Vehicle with the specified properties.
   * @param type the type of the vehicle, see {@link VehicleType} for possible values.
   * @param make the make of the vehicle.
   * @param model the model of the vehicle.
   * @param productionYear the production year of the vehicle.
   * @param color the color of the vehicle.
   * @param numberOfWheels the number of wheels of the vehicle.
   * @param numberOfPassengers the number of passengers the vehicle can carry.
   * @param maxSpeed the maximum speed of the vehicle.
   * @param canFly whether the vehicle can fly.
   */
  public Vehicle(
      VehicleType type,
      String make,
      String model,
      int productionYear,
      String color,
      int numberOfWheels,
      int numberOfPassengers,
      double maxSpeed,
      boolean canFly
  ) {
    this.vehicleType = type;
    this.make = make;
    this.model = model;
    setProductionYear(productionYear);
    setColor(color);
    setNumberOfWheels(numberOfWheels);
    setNumberOfPassengers(numberOfPassengers);
    setMaxSpeed(maxSpeed);
    this.canFly = canFly;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the type of the vehicle.
   * @return the type of the vehicle, see {@link VehicleType} for possible values.
   */
  public VehicleType getVehicleType() {
    return vehicleType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the type of the vehicle.
   * @param vehicleType the type of the vehicle, see {@link VehicleType} for possible values.
   */
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the make of the vehicle.
   * @return the make of the vehicle.
   */
  public String getMake() {
    return make;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the make of the vehicle.
   * @param make the make of the vehicle.
   */
  public void setMake(String make) {
    this.make = make;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the model of the vehicle.
   * @return the model of the vehicle.
   */
  public String getModel() {
    return model;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the model of the vehicle.
   * @param model the model of the vehicle.
   */
  public void setModel(String model) {
    this.model = model;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the production year of the vehicle.
   * @return the production year of the vehicle.
   */
  public int getProductionYear() {
    return productionYear;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the production year of the vehicle. If the provided year is not positive, it defaults to the current year.
   * @param productionYear the production year of the vehicle.
   */
  public void setProductionYear(int productionYear) {
    this.productionYear = productionYear  > 0 ? productionYear : LocalDate.now().getYear();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the color of the vehicle.
   * @return the color of the vehicle.
   */
  public String getColor() {
    return color;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the color of the vehicle. If the provided color is null or empty, it defaults to the standard color, 
   * see {@link #DEFAULT_PARK_VEHICLE_COLOR}.
   * @param color the color of the vehicle.
   */
  public void setColor(String color) {
    this.color = (color == null || color.length() == 0) ? DEFAULT_PARK_VEHICLE_COLOR : color;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the number of wheels of the vehicle.
   * @return the number of wheels of the vehicle.
   */
  public int getNumberOfWheels() {
    return numberOfWheels;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the number of wheels of the vehicle. If the provided number is not positive, it defaults to the standard number 
   * of wheels, see {@link #DEFAULT_PARK_VEHICLE_NUMBER_OF_WHEELS}.
   * @param numberOfWheels the number of wheels of the vehicle.
   */
  public void setNumberOfWheels(int numberOfWheels) {
    this.numberOfWheels = numberOfWheels > 0 ? numberOfWheels : DEFAULT_PARK_VEHICLE_NUMBER_OF_WHEELS;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the number of passengers the vehicle can carry.
   * @return the number of passengers the vehicle can carry.
   */
  public int getNumberOfPassengers() {
    return numberOfPassengers;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the number of passengers the vehicle can carry. If the provided number is not positive, it defaults to the 
   * standard number, see {@link #DEFAULT_PARK_VEHICLE_NUMBER_OF_PASSENGERS}.
   * @param numberOfPassengers the number of passengers the vehicle can carry.
   */
  public void setNumberOfPassengers(int numberOfPassengers) {
    this.numberOfPassengers = numberOfPassengers > 0 ? numberOfPassengers : DEFAULT_PARK_VEHICLE_NUMBER_OF_PASSENGERS;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the maximum speed of the vehicle.
   * @return the maximum speed of the vehicle.
   */
  public double getMaxSpeed() {
    return maxSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  /**
   * Sets the maximum speed of the vehicle. If the provided speed is negative, it defaults to 0.
   * @param maxSpeed the maximum speed of the vehicle.
   */
  public void setMaxSpeed(double maxSpeed) {
    this.maxSpeed = maxSpeed >= 0 ? maxSpeed : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns whether the vehicle can fly.
   * @return true if the vehicle can fly, false otherwise.
   */
  public boolean getCanFly() {
    return canFly;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets whether the vehicle can fly.
   * @param canFly true if the vehicle can fly, false otherwise.
   */
  public void setCanFly(boolean canFly) {
    this.canFly = canFly;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this vehicle to the specified object for equality. Two vehicles are considered equal if they have the
   * same type, make, model, production year, color, number of wheels, number of passengers, maximum speed, and flying 
   * capability.
   * @param o the object to compare with this vehicle.
   * @return true if the specified object is equal to this vehicle, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    
    Vehicle that = (Vehicle) o;
    return vehicleType == that.vehicleType &&
        make.equalsIgnoreCase(that.make) &&
        model.equalsIgnoreCase(that.model) &&
        productionYear == that.productionYear &&
        color.equalsIgnoreCase(that.color) &&
        numberOfWheels == that.numberOfWheels &&
        numberOfPassengers == that.numberOfPassengers &&
        Double.compare(maxSpeed, that.maxSpeed) == 0 &&
        canFly == that.canFly;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a hash code value for the vehicle. The hash value is computed based on the vehicle's type (see 
   * {@link #getVehicleType()} for possible values), make, model, production year, color, number of wheels, number of 
   * passengers, maximum speed, and flying capability.
   * @return a hash code value for this vehicle.
   */
  @Override
  public int hashCode() {
    return Objects.hash(vehicleType, make, model, productionYear, color, numberOfWheels, numberOfPassengers, maxSpeed, canFly);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the vehicle, including its type, make, model, production year, color, 
   * number of wheels, number of passengers, maximum speed, and flying capability.
   */
  @Override
  public String toString() {
    return "Vehicle [" +
        "type=" + vehicleType.name() +
        ", make=" + make +
        ", model=" + model +
        ", productionYear=" + productionYear +
        ", color=" + color +
        ", numberOfWheels=" + numberOfWheels +
        ", numberOfPassengers=" + numberOfPassengers +
        ", maxSpeed=" + maxSpeed +
        ", canFly=" + canFly +
        "]";
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this vehicle with the specified vehicle for order based on the make of the vehicle. Returns a negative 
   * integer, zero, or a positive integer as this vehicle's make is less than, equal to, or greater than the specified 
   * vehicle's make. If the other vehicle is null, this vehicle is considered greater.
   * @param other the vehicle to be compared.
   * @return a negative integer, zero, or a positive integer as this vehicle's make is less than, equal to, or greater 
   * than the specified vehicle's make.
   */
  @Override
  public int compareTo(Vehicle other) {
    if  (other == null) return 1;
    
    return this.make.compareToIgnoreCase(other.make);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the vehicle travel. The specific behavior of this method depends on the type of vehicle.
   */
  public abstract void travel();
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
