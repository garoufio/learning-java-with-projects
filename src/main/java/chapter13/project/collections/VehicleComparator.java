package chapter13.project.collections;

import chapter13.project.entity.vehicle.Vehicle;

import java.util.Comparator;

/**
 * Comparator for Vehicle objects based on make, model, production year, color, and vehicle type. VehicleComparator
 * implements the Comparator interface for Vehicle objects.
 */
public class VehicleComparator implements Comparator<Vehicle> {
  
  public static final Comparator<Vehicle> BY_MAKE = (v1, v2) -> v1.getMake().compareTo(v2.getMake());
  public static final Comparator<Vehicle> BY_MODEL = (v1, v2) -> v1.getModel().compareTo(v2.getModel());
  public static final Comparator<Vehicle> BY_PRODUCTION_YEAR =
      (v1, v2) -> Integer.compare(v1.getProductionYear(), v2.getProductionYear());
  public static final Comparator<Vehicle> BY_COLOR = (v1, v2) -> v1.getColor().compareTo(v2.getColor());
  public static final Comparator<Vehicle> BY_NUMBER_OF_WHEELS =
      (v1, v2) -> Integer.compare(v1.getNumberOfWheels(), v2.getNumberOfWheels());
  public static final Comparator<Vehicle> BY_NUMBER_OF_PASSENGERS =
      (v1, v2) -> Integer.compare(v1.getNumberOfPassengers(), v2.getNumberOfPassengers());
  public static final Comparator<Vehicle> BY_MAX_SPEED =
      (v1, v2) -> Double.compare(v1.getMaxSpeed(), v2.getMaxSpeed());
  public static final Comparator<Vehicle> BY_CAN_FLY =
      (v1, v2) -> Boolean.compare(v1.getCanFly(), v2.getCanFly());
  public static final Comparator<Vehicle> BY_VEHICLE_TYPE =
      (v1, v2) -> v1.getVehicleType().compareTo(v2.getVehicleType());
  public static final Comparator<Vehicle> DEFAULT =
      (v1, v2) -> (new VehicleComparator()).compare(v1, v2);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
    public int compare(Vehicle v1, Vehicle v2) {
      if (v1 == null && v2 == null) return 0;
      if (v1 == null) return -1;
      if (v2 == null) return 1;
      
      // compare by make
      int cmp = v1.getMake().compareTo(v2.getMake());
      if (cmp != 0) {
        return cmp;
      }

      // compare by model
      cmp = v1.getModel().compareTo(v2.getModel());
      if (cmp != 0) {
        return cmp;
      }
      
      // compare by production year
      cmp = Integer.compare(v1.getProductionYear(), v2.getProductionYear());
      if (cmp != 0) {
        return cmp;
      }
      
      // compare by color
      cmp = v1.getColor().compareTo(v2.getColor());
      if (cmp != 0) {
        return cmp;
      }
      
      // compare by vehicle type
      return v1.getVehicleType().compareTo(v2.getVehicleType());
    }

    //-------------------------------------------------------------------------------------------------------------------
  
}
