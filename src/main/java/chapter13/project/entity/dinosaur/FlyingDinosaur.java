package chapter13.project.entity.dinosaur;

import chapter13.project.api.Util;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a flying dinosaur with specific attributes such as wing span, maximum altitude, and maximum flight speed.
 * This class extends the Dinosaur class and implements the Carnivore, Herbivore, and Omnivore interfaces.
 */
public non-sealed class FlyingDinosaur extends Dinosaur implements Carnivore, Herbivore, Omnivore {
  
  private int wingSpan; // in centimeters
  private int maxAltitude;  // in meters
  private int maxFlightSpeed; // in kilometers per hour
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new FlyingDinosaur with the specified attributes.
   * @param name the name of the flying dinosaur.
   * @param birthDate the birth date of the flying dinosaur.
   * @param type the type of the flying dinosaur, see {@link DinosaurType} for possible values.
   * @param species the species of the flying dinosaur, see {@link DinosaurSpecies} for possible values (currently only 
   * the PTEROSAUR species is supported).
   * @param size the size category of the flying dinosaur, see {@link DinosaurSize} for possible values.
   * @param healthScore the health score of the flying dinosaur.
   * @param wingSpan the wingspan of the flying dinosaur in centimeters.
   * @param maxAltitude the maximum altitude the flying dinosaur can reach in meters.
   * @param maxFlightSpeed the maximum flight speed of the flying dinosaur in kilometers per hour.
   */
  public FlyingDinosaur(
      String name,
      LocalDate birthDate,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int healthScore,
      int wingSpan,
      int maxAltitude,
      int maxFlightSpeed
  ) {
    super(name, birthDate, type, species, size, healthScore);
    setWingSpan(wingSpan);
    setMaxAltitude(maxAltitude);
    setMaxFlightSpeed(maxFlightSpeed);
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new FlyingDinosaur with the specified attributes, assuming a default health score of 100.
   * @param name the name of the flying dinosaur.
   * @param birthDate the birth date of the flying dinosaur.
  * @param type the type of the flying dinosaur, see {@link DinosaurType} for possible values.
   * @param species the species of the flying dinosaur, see {@link DinosaurSpecies} for possible values (currently only 
   * the PTEROSAUR species is supported).
   * @param size the size category of the flying dinosaur, see {@link DinosaurSize} for possible values.
   * @param wingSpan the wingspan of the flying dinosaur in centimeters.
   * @param maxAltitude the maximum altitude the flying dinosaur can reach in meters.
   * @param maxFlightSpeed the maximum flight speed of the flying dinosaur in kilometers per hour.
   */
  public FlyingDinosaur(
      String name,
      LocalDate birthDate,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int wingSpan,
      int maxAltitude,
      int maxFlightSpeed
  ) {
    this(name, birthDate, type, species, size, 100, wingSpan, maxAltitude, maxFlightSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the wingspan of the flying dinosaur in centimeters.
   * @return the wingspan of the flying dinosaur in centimeters.
   */
  public int getWingSpan() {
    return wingSpan;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the wingspan of the flying dinosaur in centimeters.
   * @param wingSpan the wingspan of the flying dinosaur in centimeters.
   */
  
  public void setWingSpan(int wingSpan) {
    this.wingSpan = wingSpan >= 0 ? wingSpan : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the maximum altitude the flying dinosaur can reach in meters.
   * @return the maximum altitude the flying dinosaur can reach in meters.
   */
  public int getMaxAltitude() {
    return maxAltitude;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the maximum altitude the flying dinosaur can reach in meters.
   * @param maxAltitude the maximum altitude the flying dinosaur can reach in meters.
   */
  public void setMaxAltitude(int maxAltitude) {
    this.maxAltitude = maxAltitude > 0 ? maxAltitude : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the maximum flight speed of the flying dinosaur in kilometers per hour.
   * @return the maximum flight speed of the flying dinosaur in kilometers per hour.
   */
  public int getMaxFlightSpeed() {
    return maxFlightSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the maximum flight speed of the flying dinosaur in kilometers per hour.
   * @param maxFlightSpeed the maximum flight speed of the flying dinosaur in kilometers per hour.
   */
  public void setMaxFlightSpeed(int maxFlightSpeed) {
    this.maxFlightSpeed = maxFlightSpeed > 0 ? maxFlightSpeed : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this flying dinosaur to the specified object. The result is true if and only if the argument is not null,
   * is a FlyingDinosaur object, and all attributes (including those inherited from the Dinosaur class) are equal.
   * @param o the object to compare this FlyingDinosaur against.
   * @return true if the given object represents a FlyingDinosaur equivalent to this flying dinosaur, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    FlyingDinosaur that = (FlyingDinosaur) o;
    return wingSpan == that.wingSpan && maxAltitude == that.maxAltitude && maxFlightSpeed == that.maxFlightSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a hash code value for the flying dinosaur. It is computed based on all attributes of the flying dinosaur: 
   * wingspan, max altitude, and max flight speed, including those inherited from the Dinosaur class.
   * @return a hash code value for this flying dinosaur.
  */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), wingSpan, maxAltitude, maxFlightSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the flying dinosaur. The string includes the species' scientific name, name, age,
   * type, size, wingspan, max altitude, and max flight speed of the flying dinosaur.
   * @return a string representation of the flying dinosaur.
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
        ", wingSpan=" + wingSpan + "cm" +
        ", maxAltitude=" + maxAltitude + "m" +
        ", maxFlightSpeed=" + maxFlightSpeed + "Km/h" +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the flying dinosaur move. This method prints a message indicating the flying dinosaur's maximum flight speed 
   * and altitude.
   */
  @Override
  public void move() {
    System.out.printf(
        "%s '%s' can travel up to '%d' Km/h and can flight up to '%d' meters\n",
        getSpecies().getScientificName(), getName(), getMaxFlightSpeed(), getMaxAltitude()
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Makes the flying dinosaur eat. This method prints a message indicating the amount of food the flying dinosaur 
   * needs daily.
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
   * Feeds the flying dinosaur based on its type, species, and size. The method delegates the feeding logic to the 
   * appropriate interface (Carnivore, Herbivore, or Omnivore) based on the dinosaur's type.
   * @param species the species of the flying dinosaur.
   * @param size the size of the flying dinosaur.
   * @return the amount of food the flying dinosaur needs.
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
   * Prints information about the flying dinosaur by invoking its move and eat methods.
   */
  public void info() {
    move();
    eat();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
