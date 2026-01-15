package chapter12.project.entity.vehicle;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehicle {

  public static final String DEFAULT_PARK_VEHICLE_COLOR = "White";
  public static final int DEFAULT_PARK_VEHICLE_NUMBER_OF_WHEELS = 4;
  
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
    this.productionYear = productionYear;
    this.color = color;
    this.numberOfWheels = numberOfWheels;
    this.numberOfPassengers = numberOfPassengers;
    this.maxSpeed = maxSpeed;
    this.canFly = canFly;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public VehicleType getVehicleType() {
    return vehicleType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getMake() {
    return make;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMake(String make) {
    this.make = make;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getModel() {
    return model;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setModel(String model) {
    this.model = model;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getProductionYear() {
    return productionYear;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setProductionYear(int productionYear) {
    this.productionYear = productionYear  > 0 ? productionYear : LocalDate.now().getYear();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getColor() {
    return color;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setColor(String color) {
    this.color = (color == null || color.length() == 0) ? DEFAULT_PARK_VEHICLE_COLOR : color;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getNumberOfWheels() {
    return numberOfWheels;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setNumberOfWheels(int numberOfWheels) {
    this.numberOfWheels = numberOfWheels > 0 ? numberOfWheels : DEFAULT_PARK_VEHICLE_NUMBER_OF_WHEELS;
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
  
  public double getMaxSpeed() {
    return maxSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxSpeed(double maxSpeed) {
    this.maxSpeed = maxSpeed >= 0 ? maxSpeed : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean getCanFly() {
    return canFly;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setCanFly(boolean canFly) {
    this.canFly = canFly;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(vehicleType, make, model, productionYear, color, numberOfWheels, numberOfPassengers, maxSpeed, canFly);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public abstract void travel();
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
