package chapter14.project.service;

import chapter14.project.App;
import chapter14.project.entity.vehicle.Vehicle;
import chapter14.project.entity.vehicle.VehicleType;

import java.util.HashSet;
import java.util.Set;

/**
 * Service class for managing vehicles. Provides methods to add, retrieve, and remove vehicles from the collection.
 */
public class VehicleService {
  
  private Set<Vehicle> vehicles;
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Constructs a new VehicleService with the specified set of vehicles. If the provided set is null, an empty set is initialized.
   * @param vehicles the initial set of vehicles to manage, or null to initialize an empty set.
   */
  public VehicleService(Set<Vehicle> vehicles) {
    this.vehicles = vehicles == null ? new HashSet<>() : vehicles;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all vehicles managed by this service.
   * @return a set of all vehicles, or an empty set if no vehicles are available.
   */
  public Set<Vehicle> getAllVehicles() {
    return this.vehicles == null ? Set.of() : Set.copyOf(this.vehicles);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds one or more vehicles to the collection managed by this service.
   * @param vehicles the vehicles to add, or null/empty array if no vehicles are to be added.
   */
  public void addVehicles(Vehicle... vehicles) {
    if (this.vehicles == null || vehicles == null || vehicles.length == 0) {
      System.out.println("No vehicles were added");
      return;
    }
    
    if (this.vehicles.size() == App.MAX_VEHICLES) {
      System.out.println("No more vehicles can be added as maximum number of vehicles has been reached");
      return;
    }
    for (int i = 0; i < vehicles.length; i++) {
      if (vehicles[i] != null) {
        if (this.vehicles.size() < App.MAX_VEHICLES) {
          this.vehicles.add(vehicles[i]);
          System.out.printf("Vehicle added '%s'\n", vehicles[i]);
        } else {
          System.out.printf("Maximum number of vehicles has been reached. '%d' vehicles were added\n", i);
          break;
        }
      } else {
        System.out.printf("Invalid vehicle at index '%d'\n", i);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all vehicles with the specified make.
   * @param make the make of the vehicles to retrieve.
   * @return a set of vehicles with the specified make, or an empty set if no such vehicles are found.
   */
  public Set<Vehicle> getVehicle(String make) {
    if (this.vehicles == null || this.vehicles.isEmpty() || make == null) return Set.of();
    
    Set<Vehicle> vehicles = new HashSet<>();
    for (Vehicle v : this.vehicles) {
      if (v != null && v.getMake().equals(make)) vehicles.add(v);
    }
    return vehicles;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a specific vehicle from the collection managed by this service.
   * @param vehicle the vehicle to retrieve.
   * @return the vehicle if found, or null if the vehicle is not in the collection.
   */
  public Vehicle getVehicle(Vehicle vehicle) {
    if (this.vehicles == null || vehicle == null) return null;
    
    for (Vehicle v : this.vehicles) {
      if (v != null && v.equals(vehicle)) return v;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all vehicles with the specified vehicle type.
   * @param vehicleType the type of the vehicles to retrieve.
   * @return a set of vehicles with the specified vehicle type, or an empty set if no such vehicles are found.
   */
  public Set<Vehicle> getVehicle(VehicleType vehicleType) {
    if (this.vehicles == null || this.vehicles.isEmpty() || vehicleType == null) return Set.of();
    
    Set<Vehicle> vehicles = new HashSet<>();
    for (Vehicle v : this.vehicles) {
      if (v != null && v.getVehicleType() == vehicleType) vehicles.add(v);
    }
    return vehicles;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes a specific vehicle from the collection managed by this service.
   * @param vehicle the vehicle to remove.
   * @return true if the vehicle was successfully removed, false otherwise.
   */
  public boolean removeVehicle(Vehicle vehicle) {
    if (this.vehicles == null || vehicle == null) return false;
    
    return this.vehicles.remove(vehicle);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
