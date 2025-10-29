package chapter9.project.entity.dinosaur;

import java.util.Objects;

public sealed class Dinosaur permits AquaticDinosaur, FlyingDinosaur, TerrestrialDinosaur {
  
  private String name;
  private int age;
  private DinosaurType type;
  private DinosaurSpecies species;
  private DinosaurSize size;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, int age, DinosaurType type,  DinosaurSpecies species, DinosaurSize size) {
    this.name = name;
    setAge(age);
    this.type = type;
    this.species = species;
    this.size = size;
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
  
  public DinosaurSize getSize() {
    return size;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setSize(DinosaurSize size) {
    this.size = size;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    
    Dinosaur dinosaur = (Dinosaur) o;
    return (age == dinosaur.age &&
        name.equals(dinosaur.name) &&
        type == dinosaur.type &&
        species == dinosaur.species &&
        size == dinosaur.size
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, age, type, species, size);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Dinosaur [" +
        "name=" + name +
        ", age=" + age +
        ", type=" + type.name() +
        ", species=" + species +
        ", size=" + size +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
