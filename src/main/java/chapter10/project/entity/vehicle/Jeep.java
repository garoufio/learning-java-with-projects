package chapter10.project.entity.vehicle;

public class Jeep extends Vehicle {
  
  public Jeep(String model, int productionYear, String color, double maxSpeed) {
    super("Jeep", model, productionYear, color, 4, maxSpeed, false);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void travel() {
    System.out.println(getMake() + " " + getModel() + " is driving on the road");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
