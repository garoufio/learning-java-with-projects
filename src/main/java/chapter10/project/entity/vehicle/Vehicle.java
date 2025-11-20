package chapter10.project.entity.vehicle;

import java.util.Objects;

public abstract class Vehicle {

  private String make;
  private String model;
  private int productionYear;
  private String color;
  private int numberOfWheels;
  private double maxSpeed;
  private boolean canFly;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Vehicle(String make, String model, int productionYear, String color, int numberOfWheels, double maxSpeed, boolean canFly) {
    this.make = make;
    this.model = model;
    this.productionYear = productionYear;
    this.color = color;
    this.numberOfWheels = numberOfWheels;
    this.maxSpeed = maxSpeed;
    this.canFly = canFly;
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
    this.productionYear = productionYear;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getColor() {
    return color;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setColor(String color) {
    this.color = color;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getNumberOfWheels() {
    return numberOfWheels;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setNumberOfWheels(int numberOfWheels) {
    this.numberOfWheels = numberOfWheels;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public double getMaxSpeed() {
    return maxSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxSpeed(double maxSpeed) {
    this.maxSpeed = maxSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean isCanFly() {
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
    
    Vehicle vehicle = (Vehicle) o;
    return productionYear == vehicle.productionYear &&
        numberOfWheels == vehicle.numberOfWheels &&
        Double.compare(maxSpeed, vehicle.maxSpeed) == 0 &&
        canFly == vehicle.canFly &&
        make.equalsIgnoreCase(vehicle.make) &&
        model.equalsIgnoreCase(vehicle.model) &&
        model.equalsIgnoreCase(vehicle.color);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(make, model, productionYear, color, numberOfWheels, maxSpeed, canFly);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Vehicle [" +
        "make=" + make +
        ", model=" + model +
        ", productionYear=" + productionYear +
        ", color=" + color +
        ", numberOfWheels=" + numberOfWheels +
        ", maxSpeed=" + maxSpeed +
        ", canFly=" + canFly +
        "]";
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public abstract void travel();
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
