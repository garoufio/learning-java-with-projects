package chapter12.project.service;

import chapter12.project.App;
import chapter12.project.entity.vehicle.Vehicle;
import chapter12.project.entity.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class VehicleService {
  
  private List<Vehicle> vehicles;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public VehicleService(List<Vehicle> vehicles) {
    this.vehicles = vehicles == null ? new ArrayList<>() : vehicles;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Vehicle> getAllVehicles() {
    return this.vehicles == null ? List.of() : List.copyOf(this.vehicles);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public List<Vehicle> getVehicle(String make) {
    if (this.vehicles == null || this.vehicles.isEmpty() || make == null) return List.of();
    
    List<Vehicle> vehicles = new ArrayList<>();
    for (Vehicle v : this.vehicles) {
      if (v != null && v.getMake().equals(make)) vehicles.add(v);
    }
    return vehicles;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Vehicle getVehicle(Vehicle vehicle) {
    if (this.vehicles == null || vehicle == null) return null;
    
    for (Vehicle v : this.vehicles) {
      if (v != null && v.equals(vehicle)) return v;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Vehicle> getVehicle(VehicleType vehicleType) {
    if (this.vehicles == null || this.vehicles.isEmpty() || vehicleType == null) return List.of();
    
    List<Vehicle> vehicles = new ArrayList<>();
    for (Vehicle v : this.vehicles) {
      if (v != null && v.getVehicleType() == vehicleType) vehicles.add(v);
    }
    return vehicles;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeVehicle(Vehicle vehicle) {
    if (this.vehicles == null || vehicle == null) return false;
    
    for (Vehicle v : this.vehicles) {
      if (v != null && v.equals(vehicle)) return this.vehicles.remove(vehicle);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
