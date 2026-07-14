package chapter13;

public class TestMeasurableBag {
  
  public static void main(String[] args) {
    MeasurableBag<Measurable> bag = new MeasurableBag<>(new Laptop("Dell", "XPS 15", 35, 48, 3));
    System.out.println(bag);
    System.out.println("Measure: " + bag.getContentMeasurement());
    
    bag.setContent(new Book("The monk who sold his Ferrari", "Robin Sharma", 18, 10, 2));
    System.out.println(bag);
    System.out.println("Measure: " + bag.getContentMeasurement());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  
  
  public record Laptop(String brand, String model, int height, int width, int depth) implements Measurable {
    
    @Override
    public double measure() {
      return height * width * depth;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
      return "Laptop { Brand: " + brand + ", Model: " + model + " }";
    }
    
    //-----------------------------------------------------------------------------------------------------------------
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Book record
  //-------------------------------------------------------------------------------------------------------------------
  
  public record Book(String title, String author, int height, int width, int depth) implements Measurable {
    
    @Override
    public double measure() {
      return height * width * depth;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
      return "Book { Title: " + title + ", Author: " + author + " }";
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
