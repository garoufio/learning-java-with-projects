package chapter8.project;

import java.util.Objects;

public class Dinosaur {
  
  private String name;
  private int age;
  private DinosaurType type;
  private DinosaurSpecies species;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, int age, DinosaurType type,  DinosaurSpecies species) {
    this.name = name;
    setAge(age);
    this.type = type;
    this.species = species;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getAge() {
    return age;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setAge(int age) {
    this.age = age >= 0 ? age : -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurType getType() {
    return type;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setType(DinosaurType type) {
    this.type = type;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurSpecies getSpecies() {
    return species;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setSpecies(DinosaurSpecies species) {
    this.species = species;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Dinosaur dinosaur = (Dinosaur) o;
    return (age == dinosaur.age &&
        Objects.equals(name, dinosaur.name) &&
        type == dinosaur.type &&
        species == dinosaur.species
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, age, type, species);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Dinosaur [name=" + name + ", age=" + age + ", type=" + type.name() + ", species=" + species + "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
