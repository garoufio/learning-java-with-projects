package chapter11.project.entity.dinosaur;

import chapter11.project.entity.dinosaur.AquaticDinosaur;
import chapter11.project.entity.dinosaur.FlyingDinosaur;
import chapter11.project.entity.dinosaur.TerrestrialDinosaur;

import java.util.Objects;

public sealed abstract class Dinosaur implements Actionable, Comparable<Dinosaur>
    permits FlyingDinosaur, AquaticDinosaur, TerrestrialDinosaur {
  
  private String name;
  private int age;
  private DinosaurType type;
  private DinosaurSpecies species;
  private DinosaurSize size;
  private int healthScore;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, int age, DinosaurType type, DinosaurSpecies species, DinosaurSize size) {
    this.name = name;
    setAge(age);
    this.type = type;
    this.species = species;
    this.size = size;
    setHealthScore(100);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, int age, DinosaurType type, DinosaurSpecies species, DinosaurSize size, int healthScore) {
    this.name = name;
    setAge(age);
    this.type = type;
    this.species = species;
    this.size = size;
    setHealthScore(healthScore);
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
  
  public int getHealthScore() {
    return healthScore;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setHealthScore(int healthScore) {
    this.healthScore = (healthScore >= 0 && healthScore <= 100) ? healthScore : -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    
    Dinosaur that = (Dinosaur) o;
    return (age == that.age &&
        name.equals(that.name) &&
        type == that.type &&
        species == that.species &&
        size == that.size
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
    return this.getClass().getSimpleName() +
        " [" +
        "name=" + name +
        ", age=" + age +
        ", type=" + type.name() +
        ", species=" + species +
        ", size=" + size +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void move() {
    System.out.printf("%s '%s' is moving\n", this.getClass().getSimpleName(), name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void eat() {
    System.out.printf("%s '%s' is eating\n", this.getClass().getSimpleName(), name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int compareTo(Dinosaur o) {
    return this.age > o.getAge() ? 1 : this.age < o.getAge() ? -1 : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void info() {
    move();
    eat();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
