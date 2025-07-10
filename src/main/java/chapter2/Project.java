package chapter2;

public class Project {
  
  public static void main(String[] args) {
    String name = "Lucy";
    String species = "Velociraptor";
    String diet = "carnivore";
    int age = 7;
    double weight = 967;
    
    System.out.printf(
        "\"Meet %s, a %d year-old %s. As a %s, it has a robust weight of %.2f kilograms.\"",
        name, age, species, diet, weight
    );
  }
  
}
