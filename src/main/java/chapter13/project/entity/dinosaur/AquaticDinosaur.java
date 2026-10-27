package chapter13.project.entity.dinosaur;

import chapter13.project.api.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an aquatic dinosaur with specific attributes such as maximum diving depth, maximum underwater speed,
 * and amphibious capability. This class extends the Dinosaur class and implements the Carnivore, Herbivore, and Omnivore 
 * interfaces.
 */
public non-sealed class AquaticDinosaur extends Dinosaur implements Carnivore, Herbivore, Omnivore {
  
  private int maxDivingDepth; // in meters
  private int maxUnderwaterSpeed; // in kilometers per hour
  private boolean isAmphibious;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs an AquaticDinosaur with the specified attributes.
   * @param name the name of the aquatic dinosaur.
   * @param birthDate the birth date of the aquatic dinosaur.
   * @param type the type of the aquatic dinosaur, see {@link DinosaurType} for possible values.
   * @param species the species of the aquatic dinosaur, see {@link DinosaurSpecies} for possible values 
   * (currently only the PLIOSAURS species is supported as aquatic dinosaurs).
   * @param size the size of the aquatic dinosaur, see {@link DinosaurSize} for possible values.
   * @param healthScore the health score of the aquatic dinosaur.
   * @param maxDivingDepth the maximum diving depth of the aquatic dinosaur in meters.
   * @param maxUnderwaterSpeed the maximum underwater speed of the aquatic dinosaur in kilometers per hour.
   * @param isAmphibious whether the aquatic dinosaur is amphibious.
   */
  public AquaticDinosaur(
      String name,
      LocalDate birthDate,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int healthScore,
      int maxDivingDepth,
      int maxUnderwaterSpeed,
      boolean isAmphibious
  ) {
    super(name, birthDate, type, species, size, healthScore);
    setMaxDivingDepth(maxDivingDepth);
    setMaxUnderwaterSpeed(maxUnderwaterSpeed);
    this.isAmphibious = isAmphibious;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs an AquaticDinosaur with the specified attributes and a default health score of 100.
   * @param name the name of the aquatic dinosaur.
   * @param birthDate the birth date of the aquatic dinosaur.
   * @param type the type of the aquatic dinosaur, see {@link DinosaurType} for possible values.
   * @param species the species of the aquatic dinosaur, see {@link DinosaurSpecies} (currently only the PLIOSAURS 
   * species is supported).
   * @param size the size of the aquatic dinosaur, see {@link DinosaurSize} for possible values.
   * @param maxDivingDepth the maximum diving depth of the aquatic dinosaur in meters.
   * @param maxUnderwaterSpeed the maximum underwater speed of the aquatic dinosaur in kilometers per hour.
   * @param isAmphibious whether the aquatic dinosaur is amphibious.
   */
  public AquaticDinosaur(
      String name,
      LocalDate birthDate,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int maxDivingDepth,
      int maxUnderwaterSpeed,
      boolean isAmphibious
  ) {
    this(name, birthDate, type, species, size, 100, maxDivingDepth, maxUnderwaterSpeed, isAmphibious);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the maximum diving depth of the aquatic dinosaur in meters.
   * @return the maximum diving depth of the aquatic dinosaur in meters.
   */
  public int getMaxDivingDepth() {
    return maxDivingDepth;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the maximum diving depth of the aquatic dinosaur in meters.
   * @param maxDivingDepth the maximum diving depth of the aquatic dinosaur in meters.
   */
  public void setMaxDivingDepth(int maxDivingDepth) {
    this.maxDivingDepth = maxDivingDepth >= 0 ? maxDivingDepth : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the maximum underwater speed of the aquatic dinosaur in kilometers per hour.
   * @return the maximum underwater speed of the aquatic dinosaur in kilometers per hour.
   */
  public int getMaxUnderwaterSpeed() {
    return maxUnderwaterSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the maximum underwater speed of the aquatic dinosaur in kilometers per hour.
   * @param maxUnderwaterSpeed the maximum underwater speed of the aquatic dinosaur in kilometers per hour.
   */
  public void setMaxUnderwaterSpeed(int maxUnderwaterSpeed) {
    this.maxUnderwaterSpeed = maxUnderwaterSpeed >= 0 ? maxUnderwaterSpeed : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns whether the aquatic dinosaur is amphibious.
   * @return true if the aquatic dinosaur is amphibious, false otherwise.
   */
  public boolean isAmphibious() {
    return isAmphibious;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets whether the aquatic dinosaur is amphibious.
   * @param amphibious true if the aquatic dinosaur is amphibious, false otherwise.
   */
  public void setAmphibious(boolean amphibious) {
    isAmphibious = amphibious;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this aquatic dinosaur to the specified object. The result is true if and only if the argument is not null,
   * is an instance of AquaticDinosaur, and all significant fields are equal.
   * @param o the object to compare this AquaticDinosaur against.
   * @return true if the given object represents an AquaticDinosaur equivalent to this aquatic dinosaur, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    AquaticDinosaur that = (AquaticDinosaur) o;
    return maxDivingDepth == that.maxDivingDepth &&
        maxUnderwaterSpeed == that.maxUnderwaterSpeed &&
        isAmphibious == that.isAmphibious;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a hash code value for the aquatic dinosaur. The hash code is computed based on the fields of the aquatic 
   * dinosaur: max diving depth, maximum underwater speed, and whether it is amphibious, including those 
   * inherited from the Dinosaur class.
   * @return a hash code value for this aquatic dinosaur.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), maxDivingDepth, maxUnderwaterSpeed, isAmphibious);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the aquatic dinosaur. The string includes the scientific name, name, age, type, 
   * size, maximum diving depth, maximum underwater speed, and whether it is amphibious.
   * @return a string representation of the aquatic dinosaur.
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
        ", maxDivingDepth=" + maxDivingDepth + "m" +
        ", maxUnderwaterSpeed=" + maxUnderwaterSpeed + "Km/h" +
        ", isAmphibious=" + isAmphibious +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the aquatic dinosaur move. This method prints a message indicating the swimming speed and diving depth of the 
   * aquatic dinosaur.
   */
  @Override
  public void move() {
    System.out.printf(
        "%s '%s' can swim up to '%d' Km/h and can dive up to '%d' meters deep\n",
        getSpecies().getScientificName(), getName(), getMaxDivingDepth()
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the aquatic dinosaur eat. This method prints a message indicating the daily food requirement of the aquatic 
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
   * Feeds the aquatic dinosaur based on its type, species, and size. The method determines the appropriate food amount 
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
   * Prints information about the aquatic dinosaur by invoking its move and eat methods.
   */
  public void info() {
    move();
    eat();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
