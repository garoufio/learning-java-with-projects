package chapter13.project.collections;

import chapter13.project.entity.vehicle.Vehicle;

import java.util.Comparator;

public class VehicleComparator implements Comparator<Vehicle> {
  
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

    public static Comparator<Vehicle> getDefaultComparator() {
        return new VehicleComparator();
    }

    //-------------------------------------------------------------------------------------------------------------------
  
}
