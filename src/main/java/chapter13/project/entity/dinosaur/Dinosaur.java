package chapter13.project.entity.dinosaur;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a dinosaur entity with various attributes such as name, birth date, type, species, size, and health score.
 * Implements Actionable and Comparable interfaces. Provides getters and setters for its attributes, as well as methods 
 * to calculate age and manage health score. This class is sealed and can only be extended by specific dinosaur types.
 * These specific dinosaur types are FlyingDinosaur, AquaticDinosaur, and TerrestrialDinosaur.
 * @see FlyingDinosaur
 * @see AquaticDinosaur
 * @see TerrestrialDinosaur
 */
public sealed abstract class Dinosaur implements Actionable, Comparable<Dinosaur>
    permits FlyingDinosaur, AquaticDinosaur, TerrestrialDinosaur {
  
  private String name;
  private LocalDate birthDate;
  private DinosaurType type;
  private DinosaurSpecies species;
  private DinosaurSize size;
  private int healthScore;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Dinosaur instance with the specified name, birth date, type, species, and size. The health score 
   * is initialized to 100.
   * @param name the name of the dinosaur.
   * @param birthDate the birth date of the dinosaur.
   * @param type the type of the dinosaur, see {@link DinosaurType} for possible values.
   * @param species the species of the dinosaur, see {@link DinosaurSpecies} for possible values.
   * @param size the size of the dinosaur, see {@link DinosaurSize} for possible values.
   */
  public Dinosaur(String name, LocalDate birthDate, DinosaurType type, DinosaurSpecies species, DinosaurSize size) {
    this.name = name;
    this.birthDate = birthDate;
    this.type = type;
    this.species = species;
    this.size = size;
    setHealthScore(100);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Dinosaur instance with the specified name, birth date, type, species, size, and health score.
   * @param name the name of the dinosaur.
   * @param birthDate the birth date of the dinosaur.
   * @param type the type of the dinosaur see {@link DinosaurType} for possible values.
   * @param species the species of the dinosaur, see {@link DinosaurSpecies} for possible values.
   * @param size the size of the dinosaur, see {@link DinosaurSize} for possible values.
   * @param healthScore the health score of the dinosaur.
   */
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
  
  /**
   * Returns the name of the dinosaur.
   * @return the name of the dinosaur.
   */
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the name of the dinosaur.
   * @param name the name of the dinosaur.
   */
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the birth date of the dinosaur.
   * @return the birth date of the dinosaur.
   */
  public LocalDate getBirthdate() {
    return birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the birth date of the dinosaur.
   * @param birthDate the birth date of the dinosaur.
   */
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Returns the age of the dinosaur in years.
   * @return the age of the dinosaur in years.
   */  
  public int getAge() {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the type of the dinosaur.
   * @return the type of the dinosaur, see {@link DinosaurType} for possible values.
   */
  public DinosaurType getType() {
    return type;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the type of the dinosaur.
   * @param type the type of the dinosaur see {@link DinosaurType} for possible values.
   */
  public void setType(DinosaurType type) {
    this.type = type;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the species of the dinosaur.
   * @return the species of the dinosaur, see {@link DinosaurSpecies} for possible values.
   */
  public DinosaurSpecies getSpecies() {
    return species;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the species of the dinosaur.
   * @param species the species of the dinosaur, see {@link DinosaurSpecies} for possible values.
   */
  public void setSpecies(DinosaurSpecies species) {
    this.species = species;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the size of the dinosaur.
   * @return the size of the dinosaur, see {@link DinosaurSize} for possible values.
   */
  public DinosaurSize getSize() {
    return size;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the size of the dinosaur.
   * @param size the size of the dinosaur, see {@link DinosaurSize} for possible values.
   */
  public void setSize(DinosaurSize size) {
    this.size = size;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the health score of the dinosaur.
   * @return the health score of the dinosaur.
   */
  public int getHealthScore() {
    return healthScore;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the health score of the dinosaur.
   * @param healthScore the health score of the dinosaur. If the value is not between 0 and 100, it will be set to -1.
   */
  public void setHealthScore(int healthScore) {
    this.healthScore = (healthScore >= 0 && healthScore <= 100) ? healthScore : -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this dinosaur to the specified object. The result is true if and only if the argument is not null and is 
   * a Dinosaur object that has the same name, birth date, type, species, and size as this dinosaur. 
   * @param o the object to compare this dinosaur against.
   * @return true if the given object represents a Dinosaur equivalent to this dinosaur, false otherwise.
   */
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
  
  /**
   * Returns a hash code value for the dinosaur. The hash code is computed based on the name, birth date, type, species, 
   * and size of the dinosaur.
   * @return the hash code value for the dinosaur.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, birthDate, type, species, size);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the dinosaur. The string includes the class name, name, birth date, age, type, 
   * species, and size of the dinosaur.
   * @return a string representation of the dinosaur.
   */
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
  
  /**
   * Makes the dinosaur move. This method prints a message indicating that the dinosaur is moving.
   */
  @Override
  public void move() {
    System.out.printf("%s '%s' is moving\n", this.getClass().getSimpleName(), name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the dinosaur eat. This method prints a message indicating that the dinosaur is eating.
   */
  @Override
  public void eat() {
    System.out.printf("%s '%s' is eating\n", this.getClass().getSimpleName(), name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this dinosaur with the specified dinosaur for order based on their names. Returns a negative integer, 
   * zero, or a positive integer as this dinosaur's name is less than, equal to, or greater than the specified 
   * dinosaur's name.
   * @param other the dinosaur to be compared.
   * @return a negative integer, zero, or a positive integer as this dinosaur's name is less than, equal to, or greater 
   * than the specified dinosaur's name.
   */
  @Override
  public int compareTo(Dinosaur other) {
    if (other == null) return 1;
    
    return this.name.compareTo(other.name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints information about the dinosaur by invoking its move and eat methods.
   */
  public void info() {
    move();
    eat();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
