package chapter13.project.entity.dinosaur;

import chapter13.project.api.Util;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a terrestrial dinosaur with specific attributes such as height and maximum running speed. This class 
 * extends the Dinosaur class and implements the Carnivore, Herbivore, and Omnivore interfaces.
 */
public non-sealed class TerrestrialDinosaur extends Dinosaur implements Carnivore, Herbivore, Omnivore {

  private int height; // in centimeters
  private int maxRunningSpeed; // in kilometers per hour
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new TerrestrialDinosaur with the specified attributes.
   * @param name the name of the terrestrial dinosaur.
   * @param birthDate the birthdate of the terrestrial dinosaur.
   * @param type the type of the terrestrial dinosaur, see {@link DinosaurType} for possible values.
   * @param species the species of the terrestrial dinosaur, see {@link DinosaurSpecies} for possible values (currently 
   * only TYRANNOSAURUS, TRICERATOPS, VELOCIRAPTOR, STEGOSAURUS, BRACHIOSAURUS, SPINOSAURUS, PARASAUROLOPHUS and ANKYLOSAURUS 
   * are supported).
   * @param size the size of the terrestrial dinosaur, see {@link DinosaurSize} for possible values (SMALL, MEDIUM, LARGE).
   * @param healthScore the health score of the terrestrial dinosaur.
   * @param height the height of the terrestrial dinosaur in centimeters.
   * @param maxRunningSpeed the maximum running speed of the terrestrial dinosaur in kilometers per hour.
   */
  public TerrestrialDinosaur(
      String name,
      LocalDate birthDate,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int healthScore,
      int height,
      int maxRunningSpeed
  ) {
    super(name, birthDate, type, species, size, healthScore);
    setHeight(height);
    setMaxRunningSpeed(maxRunningSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  /**
   * Constructs a new TerrestrialDinosaur with the specified attributes, assuming a default health score of 100.
   * @param name the name of the terrestrial dinosaur.
   * @param birthDate the birthdate of the terrestrial dinosaur.
   * @param type the type of the terrestrial dinosaur, see {@link DinosaurType} for possible values.
   * @param species the species of the terrestrial dinosaur, see {@link DinosaurSpecies} for possible values (currently 
   * only TYRANNOSAURUS, TRICERATOPS, VELOCIRAPTOR, STEGOSAURUS, BRACHIOSAURUS, SPINOSAURUS, PARASAUROLOPHUS and ANKYLOSAURUS 
   * are supported).
   * @param size the size of the terrestrial dinosaur, see {@link DinosaurSize} for possible values (SMALL, MEDIUM, LARGE).
   * @param height the height of the terrestrial dinosaur in centimeters.
   * @param maxRunningSpeed the maximum running speed of the terrestrial dinosaur in kilometers per hour.
   */
  public TerrestrialDinosaur(
      String name,
      LocalDate birthDate,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int height,
      int maxRunningSpeed
  ) {
    this(name, birthDate, type, species, size, 100, height, maxRunningSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the height of the terrestrial dinosaur in centimeters.
   * @return the height of the terrestrial dinosaur in centimeters.
   */
  public int getHeight() {
    return height;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the height of the terrestrial dinosaur in centimeters.
   * @param height the height of the terrestrial dinosaur in centimeters.
   */
  public void setHeight(int height) {
    this.height = height > 0 ? height : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the maximum running speed of the terrestrial dinosaur in kilometers per hour.
   * @return the maximum running speed of the terrestrial dinosaur in kilometers per hour.
   */
  public int getMaxRunningSpeed() {
    return maxRunningSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the maximum running speed of the terrestrial dinosaur in kilometers per hour.
   * @param maxRunningSpeed the maximum running speed of the terrestrial dinosaur in kilometers per hour.
   */
  public void setMaxRunningSpeed(int maxRunningSpeed) {
    this.maxRunningSpeed = maxRunningSpeed > 0 ? maxRunningSpeed : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this terrestrial dinosaur to the specified object. The result is true if and only if the argument is 
   * not null, is an instance of TerrestrialDinosaur, and all significant fields are equal.
   * @param o the object to compare this TerrestrialDinosaur against.
   * @return true if the given object represents a TerrestrialDinosaur equivalent to this terrestrial dinosaur, 
   * false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    TerrestrialDinosaur that = (TerrestrialDinosaur) o;
    return height == that.height && maxRunningSpeed == that.maxRunningSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a hash code value for the terrestrial dinosaur. It is based on the fields of the terrestrial dinosaur: 
   * height and maxRunningSpeed, including those inherited from the Dinosaur class.
   * @return a hash code value for this terrestrial dinosaur.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), height, maxRunningSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the terrestrial dinosaur. The string includes the scientific name, name, age, 
   * type, size, height, and maximum running speed.
   * @return a string representation of the terrestrial dinosaur.
   */
  @Override
  public String toString() {
    return getSpecies().getScientificName() +
        " [" +
        "name=" + getName() +
        ", age=" + getAge() +
        ", birthDate=" + getBirthdate().format(Util.DATE_FORMAT) +
        ", type=" + getType().name() +
        ", size=" + getSize().name() +
        ", height=" + height + "cm" +
        ", maxRunningSpeed=" + maxRunningSpeed + "Km/h" +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the terrestrial dinosaur move. This method prints a message indicating the maximum running speed of the 
   * terrestrial dinosaur.
   */
  @Override
  public void move() {
    System.out.printf(
        "%s '%s' can run up to '%d' Km/h\n",
        getSpecies().getScientificName(), getName(), getMaxRunningSpeed()
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the terrestrial dinosaur eat. This method prints a message indicating the daily food requirement of the terrestrial 
   * dinosaur.
   */
  @Override
  public void eat() {
    System.out.printf(
        "%s '%s' has to eat '%.2f' Kg of %s daily\n",
        getSpecies().getScientificName(), getName(), feed(getSpecies(), getSize()),
        switch (getType()) {
          case CARNIVORE -> "meat";
          case HERBIVORE -> "plants";
          case OMNIVORE -> "meat and plants";
          default -> "food";
        }
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Feeds the terrestrial dinosaur based on its type, species, and size. The method determines the appropriate food amount 
   * for the dinosaur depending on whether it is carnivorous, herbivorous, or omnivorous.
   * @param species the species of the dinosaur.
   * @param size the size of the dinosaur.
   * @return the amount of food required for the dinosaur.
   */
  @Override
  public double feed(DinosaurSpecies species, DinosaurSize size) {
    return switch(getType()) {
      case CARNIVORE -> Carnivore.super.feed(species, size);
      case HERBIVORE -> Herbivore.super.feed(species, size);
      case OMNIVORE -> Omnivore.super.feed(species, size);
      default -> {
        System.out.printf("Unknown dinosaur type '%s'. Cannot determine food amount\n", getType());
        yield 0;
      }
    };
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints information about the terrestrial dinosaur by invoking its move and eat methods.
   */
  public void info() {
    move();
    eat();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
