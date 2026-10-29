package chapter14.project.api;

import chapter14.project.collections.VehicleComparator;
import chapter14.project.entity.vehicle.*;
import chapter14.project.exception.AppException;
import chapter14.project.exception.InputException;
import chapter14.project.service.VehicleService;

import java.util.*;

/**
 * Controller class for managing vehicle-related operations.
 */
public class VehicleController {
  
  private VehicleService vehicleService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new VehicleController with the specified Scanner and VehicleService.
   * @param sc the Scanner object for reading user input.
   * @param vehicleService the VehicleService instance for managing vehicles.
   */
  public VehicleController(Scanner sc, VehicleService vehicleService) {
    this.sc = sc;
    this.vehicleService = vehicleService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Manages the vehicle-related operations by displaying a menu and handling user input. This method will continue to 
   * display the menu until the user chooses to return to the main menu.
   */
  public void manageVehicles() {
    for (;;) {
      try {
        System.out.print("""
            
            Vehicles service:
            1. Show all vehicles
            2. Add Vehicle
            3. Find Vehicle
            4. Edit Vehicle
            5. Remove Vehicle
            6. Return to main menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> printVehicles();
          case 2 -> addVehicle();
          case 3 -> findVehicle();
          case 4 -> editVehicle();
          case 5 -> removeVehicle();
          case 6 -> {
            System.out.printf("Returning to main menu...\n\n");
          }
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice == 6) {
          break;
        }
      } catch (InputMismatchException e) {
      
      } catch (InputException e) {
        System.out.printf("""
          Error : %s
          Please try again or return to Main Menu...
        """, e.getMessage()
        );
      } catch (AppException e) {
        System.out.printf("""
          Error while managing vehicles: %s
          Please try again or return to Main Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the details of the given list of vehicles.
   * @param vehicles the collection of vehicles to print. If the collection is null or empty, a message indicating no 
   * vehicles were added will be displayed.
   */
  private void printVehicles(Collection<Vehicle> vehicles) {
    if (vehicles == null || vehicles.isEmpty()) {
      System.out.println("No vehicles were added");
      return;
    }
    
    for (Vehicle v : vehicles) {
      if (v != null) System.out.println(v);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Displays a menu for sorting and printing vehicles based on various criteria. The user can choose to sort by make,
   * model, production year, color, number of wheels, number of passengers, max speed, or return to the vehicles' menu.
   * The method will continue to prompt the user until a valid choice is made.
   */
  public void printVehicles() {
    for (;;) {
      try {
        System.out.print("""
            
            Sort by:
            1. No sorting
            2. Default
            3. Make
            4. Model
            5. Production Year
            6. Color
            7. Number of Wheels
            8. Number of Passengers
            9. Max Speed
            10. Return to Vehicles Menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> printVehicles(vehicleService.getAllVehicles());
          case 2 -> printVehicles(sortVehicles(vehicleService.getAllVehicles(), null));
          case 3 -> printVehicles(sortVehicles(vehicleService.getAllVehicles(), VehicleComparator.BY_MAKE));
          case 4 -> printVehicles(sortVehicles(vehicleService.getAllVehicles(), VehicleComparator.BY_MODEL));
          case 5 -> printVehicles(sortVehicles(vehicleService.getAllVehicles(), VehicleComparator.BY_PRODUCTION_YEAR));
          case 6 -> printVehicles(sortVehicles(vehicleService.getAllVehicles(), VehicleComparator.BY_COLOR));
          case 7 -> printVehicles(sortVehicles(vehicleService.getAllVehicles(), VehicleComparator.BY_NUMBER_OF_WHEELS));
          case 8 -> printVehicles(sortVehicles(vehicleService.getAllVehicles(), VehicleComparator.BY_NUMBER_OF_PASSENGERS));
          case 9 -> printVehicles(sortVehicles(vehicleService.getAllVehicles(), VehicleComparator.BY_MAX_SPEED));
          case 10 ->  System.out.println("Returning to vehicles menu...");
          default -> System.out.println("Invalid choice. Please try again.");
        };
        if (choice > 0 && choice < 11) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 10.");
        sc.nextLine(); // Consume the invalid input
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Creates a new vehicle of the specified type by prompting the user for the necessary details, such as make, model, 
   * production year, color, and other relevant attributes depending on the vehicle type.
   * @param vehicleType the type of vehicle to create, see {@link VehicleType} for available vehicle types.
   * @return the newly created vehicle, or null if the creation failed.
   * @throws InputException if there is an error in user input while creating the vehicle.
   */
  private Vehicle createVehicle(VehicleType vehicleType) throws InputException {
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
  
  /**
   * Adds a new vehicle to the collection managed by the vehicle service by prompting the user for the necessary 
   * details. If the vehicle creation fails, an appropriate message will be displayed.
   * @throws InputException if there is an error in user input while adding the vehicle.
   */
  public void addVehicle() throws InputException {
    VehicleType vehicleType = Util.readVehicleType(sc);
    Vehicle vehicle = createVehicle(vehicleType);
    if (vehicle != null) {
      vehicleService.addVehicles(vehicle);
    } else {
      System.out.println("Vehicle creation failed");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Finds a vehicle based on user-specified criteria, such as make, vehicle type, or detailed search parameters.
   */
  public void findVehicle() {
    for(;;) {
      try {
        System.out.print("""
            
            Search by:
            1. Make
            2. Vehicle type
            3. Detailed search
            4. Return to vehicles menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> {
            String make = Util.readVehicleField(sc, "make");
            Set<Vehicle> vehicles = vehicleService.getVehicle(make);
            if (vehicles.isEmpty()) System.out.printf("No vehicles found with make '%s'\n", make);
            else printVehicles(vehicles);
          }
          case 2 -> {
            VehicleType vehicleType = Util.readVehicleType(sc);
            Set<Vehicle> vehicles = vehicleService.getVehicle(vehicleType);
            if (vehicles.isEmpty()) System.out.printf("No vehicles found of type '%s'\n", vehicleType.name());
            else printVehicles(vehicles);
          }
          case 3 -> {
            VehicleType vehicleType = Util.readVehicleType(sc);
            Vehicle vehicle = vehicleService.getVehicle(createVehicle(vehicleType));
            if (vehicle == null) System.out.println("Vehicle not found");
            else System.out.printf("Vehicle found: '%s'\n", vehicle);
          }
          case 4 -> {
          }
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice >= 1 && choice <= 4) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 4.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while searching for vehicles: %s
          Please try again or return to Vehicles Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Edits the details of the specified vehicle by prompting the user for new values for each editable field.
   * @param vehicle the vehicle to be edited. If the vehicle is null, no changes will be made.
   * @throws InputException if there is an error in user input while editing the vehicle details.
   */
  private void editVehicleDetails(Vehicle vehicle) throws InputException {
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
      default -> {
        // No additional fields to edit for other vehicle types
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Allows the user to edit vehicles based on different criteria such as make, vehicle type, or detailed search.
   * Prompts the user for the specific vehicle to edit and updates its details accordingly.
   */
  public void editVehicle() {
    for (;;) {
      try {
        System.out.print("""
            
            Edit by:
            1. Make
            2. Vehicle type
            3. Detailed search
            4. Return to vehicles menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> {
            String make = Util.readVehicleField(sc, "make");
            Set<Vehicle> vehicles = vehicleService.getVehicle(make);
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
            Set<Vehicle> vehicles = vehicleService.getVehicle(vehicleType);
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
          case 4 -> {
          }
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice >= 1 && choice <= 4) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 4.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while editing vehicles: %s
          Please try again or return to Vehicles Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified vehicle from the system if it exists.  Prints a message indicating whether the removal was 
   * successful or not.
   * @param vehicle the vehicle to be removed
   */
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
  
  /**
   * Removes vehicles based on different criteria such as make, vehicle type, or detailed search. Prompts the user for
   * the specific vehicle to remove and updates the collection accordingly.
   */
  public void removeVehicle() {
    for (;;) {
      try {
        System.out.print("""
            
            Remove by:
            1. Make
            2. Vehicle type
            3. Detailed search
            4. Return to vehicles menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> {
            String make = Util.readVehicleField(sc, "make");
            Set<Vehicle> vehicles = vehicleService.getVehicle(make);
            if (vehicles.isEmpty()) System.out.printf("No vehicles found with make '%s'\n", make);
            else {
              for (Vehicle v : vehicles) {
                removeVehicle(v);
              }
            }
          }
          case 2 -> {
            VehicleType vehicleType = Util.readVehicleType(sc);
            Set<Vehicle> vehicles = vehicleService.getVehicle(vehicleType);
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
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 4.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while removing vehicles: %s
          Please try again or return to Vehicles Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sorts the given list of vehicles using the specified comparator. If the comparator is null, the default comparator is used.
   * @param vehicles the list of vehicles to be sorted
   * @param comparator the comparator to use for sorting, or null to use the default comparator
   * @return a new list containing the sorted vehicles
   */
  public List<Vehicle> sortVehicles(Collection<Vehicle> vehicles, Comparator<Vehicle> comparator) {
    if (vehicles == null || vehicles.isEmpty()) return List.of();
    
    List<Vehicle> sortedVehicles = new ArrayList<>(vehicles);
    Collections.sort(sortedVehicles, comparator == null ? VehicleComparator.DEFAULT : comparator);
    return sortedVehicles;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
