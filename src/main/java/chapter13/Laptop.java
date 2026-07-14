package chapter13;

public record Laptop(String brand, String model) {
  
  private static final String UNKN = "Unknown";
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Laptop {
    brand = brand == null ? UNKN : brand;
    model = model == null ? UNKN : model;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Laptop { Brand: " + brand + ", Model: " + model + " }";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
