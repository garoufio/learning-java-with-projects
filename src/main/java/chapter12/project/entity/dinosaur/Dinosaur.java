package chapter12.project.entity.dinosaur;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public sealed abstract class Dinosaur implements Actionable, Comparable<Dinosaur>
    permits FlyingDinosaur, AquaticDinosaur, TerrestrialDinosaur {
  
  private String name;
  private LocalDate birthDate;
  private DinosaurType type;
  private DinosaurSpecies species;
  private DinosaurSize size;
  private int healthScore;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, LocalDate birthDate, DinosaurType type, DinosaurSpecies species, DinosaurSize size) {
    this.name = name;
    this.birthDate = birthDate;
    this.type = type;
    this.species = species;
    this.size = size;
    setHealthScore(100);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(
      String name,
      LocalDate birthDate,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int healthScore
  ) {
    this.name = name;
    this.birthDate = birthDate;
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
  
  public LocalDate getBirthdate() {
    return birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getAge() {
    return Period.between(birthDate, LocalDate.now()).getYears();
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
    return (birthDate.isEqual(that.birthDate) &&
        name.equals(that.name) &&
        type == that.type &&
        species == that.species &&
        size == that.size
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, birthDate, type, species, size);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName() +
        " [" +
        "name=" + name +
        ", birthDate=" + birthDate.format(DateTimeFormatter.ISO_DATE) +
        ", age=" + getAge() +
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
    return this.birthDate.compareTo(o.birthDate);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void info() {
    move();
    eat();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
