package chapter10.project.entity.vehicle;

public class Helicopter extends Vehicle {
  
  public Helicopter(String model, int productionYear, String color, double maxSpeed) {
    super("Boeing", model, productionYear, color, 0, maxSpeed, true);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void travel() {
    System.out.println(getMake() + " " + getModel() + " is flying in the sky");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
