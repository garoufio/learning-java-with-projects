package chapter10.project.api;

import chapter10.project.entity.employee.Employee;
import chapter10.project.entity.employee.JobTitle;
import chapter10.project.entity.vehicle.*;
import chapter10.project.service.VehicleService;

import java.util.List;
import java.util.Scanner;

public class VehicleController {
  
  private VehicleService vehicleService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public VehicleController(Scanner sc, VehicleService vehicleService) {
    this.sc = sc;
    this.vehicleService = vehicleService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageVehicles() {
    for (;;) {
      System.out.printf("\nVehicles service:\n");
      System.out.println("1. Show all vehicles");
      System.out.println("2. Add Vehicle");
      System.out.println("3. Find Vehicle");
      System.out.println("4. Edit Vehicle");
      System.out.println("5. Remove Vehicle");
      System.out.println("6. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> printVehicles(vehicleService.getAllVehicles());
        case 2 -> addVehicle();
        case 3 -> findVehicle();
        case 4 -> editVehicle();
        case 5 -> removeVehicle();
        case 6 -> { System.out.printf("Returning to main menu...\n\n"); }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 6) {
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void printVehicles(List<Vehicle> vehicles) {
    if (vehicles == null || vehicles.isEmpty()) {
      System.out.println("No vehicles were added");
      return;
    }
    
    for (Vehicle v : vehicles) {
      if (v != null) System.out.println(v);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Vehicle createVehicle(VehicleType vehicleType) {
    String make = Util.readVehicleField(sc, "make");
    String model = Util.readVehicleField(sc, "model");
    int productionYear = Util.readVehicleIntField(sc, "production year");
    String color = Util.readVehicleField(sc, "color");
    int numberOfWheels = Util.readVehicleIntField(sc, "number of wheels");
    int numberOfPassengers = Util.readVehicleIntField(sc, "number of passengers");
    double maxSpeed = Util.readVehicleDoubleField(sc, "max speed");
    
    return switch (vehicleType) {
      case CAR -> new Car(make, model, productionYear, color, numberOfPassengers, maxSpeed);
      case MOTORCYCLE -> new Motorcycle(make, model, productionYear, color, numberOfPassengers, maxSpeed);
      case TRUCK -> {
        int loadCapacity = Util.readVehicleIntField(sc, "load capacity");
        yield new Truck(make, model, productionYear, color, numberOfWheels, numberOfPassengers, maxSpeed, loadCapacity);
      }
      case HELICOPTER -> {
        int flightRange = Util.readVehicleIntField(sc, "flight range");
        int maxAltitude = Util.readVehicleIntField(sc, "max altitude");
        int loadCapacity = Util.readVehicleIntField(sc, "load capacity");
        yield new Helicopter(
            make, model, productionYear, color, numberOfPassengers,
            maxSpeed, flightRange, maxAltitude, loadCapacity
        );
      }
      default -> {
        System.out.println("Invalid vehicle type. Please try again");
        yield null;
      }
    };
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addVehicle() {
    VehicleType vehicleType = Util.readVehicleType(sc);
    Vehicle vehicle = createVehicle(vehicleType);
    if (vehicle != null) {
      vehicleService.addVehicles(vehicle);
    } else {
      System.out.println("Vehicle creation failed");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void findVehicle() {
    for(;;) {
      System.out.printf("\nSearch by:\n");
      System.out.println("1. Make");
      System.out.println("2. Vehicle type");
      System.out.println("3. Detailed search");
      System.out.println("4. Return to vehicles menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String make = Util.readVehicleField(sc, "make");
          List<Vehicle> vehicles = vehicleService.getVehicle(make);
          if (vehicles.isEmpty()) System.out.printf("No vehicles found with make '%s'\n", make);
          else printVehicles(vehicles);
        }
        case 2 -> {
          VehicleType vehicleType = Util.readVehicleType(sc);
          List<Vehicle> vehicles = vehicleService.getVehicle(vehicleType);
          if (vehicles.isEmpty()) System.out.printf("No vehicles found of type '%s'\n", vehicleType.name());
          else printVehicles(vehicles);
        }
        case 3 -> {
          VehicleType vehicleType = Util.readVehicleType(sc);
          Vehicle vehicle = vehicleService.getVehicle(createVehicle(vehicleType));
          if (vehicle == null) System.out.println("Vehicle not found");
          else System.out.printf("Vehicle found: '%s'\n", vehicle);
        }
        case 4 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice >= 1 && choice <= 4) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void editVehicleDetails(Vehicle vehicle) {
    // change make
    if (Util.readEditVehicle(sc, null, "make").equals("Y")) {
      String newMake = Util.readVehicleField(sc, "make");
      vehicle.setMake(newMake);
    }
    // change model
    if (Util.readEditVehicle(sc, null, "model").equals("Y")) {
      String newModel = Util.readVehicleField(sc, "model");
      vehicle.setModel(newModel);
    }
    // change production year
    if (Util.readEditVehicle(sc, null, "production year").equals("Y")) {
      int newProductionYear = Util.readVehicleIntField(sc, "production year");
      vehicle.setProductionYear(newProductionYear);
    }
    // change color
    if (Util.readEditVehicle(sc, null, "color").equals("Y")) {
      String newColor = Util.readVehicleField(sc, "color");
      vehicle.setColor(newColor);
    }
    // change number of wheels
    if (Util.readEditVehicle(sc, null, "number of wheels").equals("Y)")) {
      int newNumberOfWheels = Util.readVehicleIntField(sc, "number of wheels");
      vehicle.setNumberOfWheels(newNumberOfWheels);
    }
    // change number of passengers
    if (Util.readEditVehicle(sc, null, "number of passengers").equals("Y")) {
      int newNumberOfPassengers = Util.readVehicleIntField(sc, "number of passengers");
      vehicle.setNumberOfPassengers(newNumberOfPassengers);
    }
    // change max speed
    if (Util.readEditVehicle(sc, null, "max speed").equals("Y")) {
      double newMaxSpeed = Util.readVehicleDoubleField(sc, "max speed");
      vehicle.setMaxSpeed(newMaxSpeed);
    }
    switch (vehicle.getVehicleType()) {
      case TRUCK -> {
        // change load capacity
        if (Util.readEditVehicle(sc, null, "load capacity").equals("Y")) {
          int newLoadCapacity = Util.readVehicleIntField(sc, "load capacity");
          ((Truck) vehicle).setMaxLoadCapacity(newLoadCapacity);
        }
      }
      case HELICOPTER -> {
        // change flight range
        if (Util.readEditVehicle(sc, null, "flight range").equals("Y")) {
          int newFlightRange = Util.readVehicleIntField(sc, "flight range");
          ((Helicopter) vehicle).setFlightRange(newFlightRange);
        }
        // change max altitude
        if (Util.readEditVehicle(sc, null, "max altitude").equals("Y")) {
          int newMaxAltitude = Util.readVehicleIntField(sc, "max altitude");
          ((Helicopter) vehicle).setMaxAltitude(newMaxAltitude);
        }
        // change load capacity
        if (Util.readEditVehicle(sc, null, "load capacity").equals("Y")) {
          int newLoadCapacity = Util.readVehicleIntField(sc, "load capacity");
          ((Helicopter) vehicle).setMaxLoadCapacity(newLoadCapacity);
        }
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  
  public void editVehicle() {
    for (;;) {
      System.out.printf("\nEdit by:\n");
      System.out.println("1. Make");
      System.out.println("2. Vehicle type");
      System.out.println("3. Detailed search");
      System.out.println("4. Return to vehicles menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String make = Util.readVehicleField(sc, "make");
          List<Vehicle> vehicles = vehicleService.getVehicle(make);
          if (vehicles.isEmpty()) System.out.printf("No vehicles found with make '%s'\n", make);
          else {
            System.out.printf("'%d' vehicles found with make '%s'\n", vehicles.size(), make);
            for (Vehicle v : vehicles) {
              if (Util.readEditVehicle(sc, v, null).equals("Y")) {
                editVehicleDetails(v);
                System.out.printf("Vehicle updated: '%s'\n", v);
              }
            }
          }
        }
        case 2 -> {
          VehicleType vehicleType = Util.readVehicleType(sc);
          List<Vehicle> vehicles = vehicleService.getVehicle(vehicleType);
          if (vehicles.isEmpty()) System.out.printf("No vehicles found of type '%s'\n", vehicleType.name());
          else {
            System.out.printf("'%d' vehicles found with type '%s'\n", vehicles.size(), vehicleType.name());
            for (Vehicle v : vehicles) {
              if (Util.readEditVehicle(sc, v, null).equals("Y")) {
                editVehicleDetails(v);
                System.out.printf("Vehicle updated: '%s'\n", v);
              }
            }
          }
        }
        case 3 -> {
          VehicleType vehicleType = Util.readVehicleType(sc);
          Vehicle vehicle = vehicleService.getVehicle(createVehicle(vehicleType));
          if (vehicle == null) System.out.println("Vehicle not found");
          else {
            if (Util.readEditVehicle(sc, vehicle, null).equals("Y")) {
              editVehicleDetails(vehicle);
              System.out.printf("Vehicle updated: '%s'\n", vehicle);
            }
          }
        }
        case 4 -> {  }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice >= 1 && choice <= 4) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeVehicle(Vehicle vehicle) {
    if (vehicle == null) {
      System.out.println("Vehicle not found");
      return;
    }
    
    if (vehicleService.removeVehicle(vehicle)) {
      System.out.printf("Vehicle removed: '%s'\n", vehicle);
    } else {
      System.out.printf("Vehicle '%s' could not be removed", vehicle);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeVehicle() {
    for (;;) {
      System.out.println("\nRemove by:");
      System.out.println("1. Make");
      System.out.println("2. Vehicle type");
      System.out.println("3. Detailed search");
      System.out.println("4. Return to vehicles menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          String make = Util.readVehicleField(sc, "make");
          List<Vehicle> vehicles = vehicleService.getVehicle(make);
          if (vehicles.isEmpty()) System.out.printf("No vehicles found with make '%s'\n", make);
          else {
            for (Vehicle v : vehicles) {
              removeVehicle(v);
            }
          }
        }
        case 2 -> {
          VehicleType vehicleType = Util.readVehicleType(sc);
          List<Vehicle> vehicles = vehicleService.getVehicle(vehicleType);
          if (vehicles.isEmpty()) System.out.printf("No vehicles found of type '%s'\n", vehicleType.name());
          else {
            for (Vehicle v : vehicles) {
              removeVehicle(v);
            }
          }
        }
        case 3 -> {
          VehicleType vehicleType = Util.readVehicleType(sc);
          Vehicle vehicle = vehicleService.getVehicle(createVehicle(vehicleType));
          if (vehicle == null) System.out.println("Vehicle not found");
          else removeVehicle(vehicle);
        }
        case 4 -> {
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice >= 1 && choice <= 4) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
