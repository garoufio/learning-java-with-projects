package chapter12.project.entity.vehicle;

public class Motorcycle extends Vehicle {
  
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
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
   
    return super.equals(o);
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
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void travel() {
    System.out.printf("%s '%s %s' is driving on the road\n", this.getClass().getSimpleName(), getMake(), getModel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
