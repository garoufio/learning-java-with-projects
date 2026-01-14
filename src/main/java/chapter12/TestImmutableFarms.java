package chapter12;

import java.util.ArrayList;
import java.util.List;

public class TestImmutableFarms {
  
  public static void main(String[] args) {
    List<String> animals = new ArrayList<>();
    animals.add("Cow");
    animals.add("Pig");
    animals.add("Chicken");
    
    // Create an immutable Farm
    Farm farm1 = Farm.createFarm("Happy Farm", 3, animals);
    System.out.println(farm1);
    
    // Attempt to modify the list of animals retrieved from the farm
    animals.add("Sheep");
    animals.add("Cow");
    
    // Print the farm again to show it has not changed
    System.out.println(farm1);
    
    System.out.println("Modified original animals list: " + animals);
    animals = farm1.getAnimals();
    animals.add("Goat");
    System.out.println("Modified retrieved animals list: " + animals);
    String name = farm1.getName();
    int numberOfAnimals = farm1.getNumberOfAnimals();
    name = "Big Farm";
    numberOfAnimals = 10;
    System.out.println("Actual farm " + farm1.getAnimals());
  }
  
}
