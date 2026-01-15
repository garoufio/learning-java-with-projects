package chapter12.project.entity.vehicle;

public enum VehicleType {
  CAR("Car"),
  MOTORCYCLE("Motorcycle"),
  TRUCK("Truck"),
  HELICOPTER("Helicopter");
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private final String description;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  VehicleType(String description) {
    this.description = description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getDescription() {
    return description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
