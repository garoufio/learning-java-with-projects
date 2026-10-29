package chapter14.project.entity.vehicle;

/**
 * Enumeration representing different types of vehicles. These types are CAR, MOTORCYCLE, TRUCK, and HELICOPTER.
 */
public enum VehicleType {
  CAR("Car"),
  MOTORCYCLE("Motorcycle"),
  TRUCK("Truck"),
  HELICOPTER("Helicopter");
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Description of the vehicle type.
   */
  private final String description;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a VehicleType with the specified description.
   * @param description the description of the vehicle type.
   */
  VehicleType(String description) {
    this.description = description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the description of the vehicle type.
   * @return the description of the vehicle type.
   */
  public String getDescription() {
    return description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
