package chapter12;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable Farm class
 */
public final class Farm {

  private final String name;
  private final int numberOfAnimals;
  private final List<String> animals;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Farm(final String name, final int numberOfAnimals, final List<String> animals) {
    this.name = name;
    this.numberOfAnimals = numberOfAnimals;
//    this.animals = animals; // breaks encapsulation
    this.animals = new ArrayList<String>(animals);
//    this.animals = List.copyOf(animals);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getNumberOfAnimals() {
    return numberOfAnimals;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<String> getAnimals() {
    return new ArrayList<String>(animals);
//    return List.copyOf(animals);
//    return animals; // breaks encapsulation
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static Farm createFarm(String name, int numberOfAnimals, List<String> animals) {
    return new Farm(name, numberOfAnimals, animals);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Farm {" +
        "name='" + name + '\'' +
        ", numberOfAnimals=" + numberOfAnimals +
        ", animals=" + animals +
        '}';
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
