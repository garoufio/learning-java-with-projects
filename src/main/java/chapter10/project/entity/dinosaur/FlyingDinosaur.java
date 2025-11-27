package chapter10.project.entity.dinosaur;

import java.util.Objects;

public non-sealed class FlyingDinosaur extends Dinosaur implements Carnivore, Herbivore, Omnivore {
  
  private int wingSpan; // in centimeters
  private int maxAltitude;  // in meters
  private int maxFlightSpeed; // in kilometers per hour
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public FlyingDinosaur(
      String name,
      int age,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int wingSpan,
      int maxAltitude,
      int maxFlightSpeed
  ) {
    super(name, age, type, species, size);
    setWingSpan(wingSpan);
    setMaxAltitude(maxAltitude);
    setMaxFlightSpeed(maxFlightSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getWingSpan() {
    return wingSpan;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setWingSpan(int wingSpan) {
    this.wingSpan = wingSpan >= 0 ? wingSpan : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxAltitude() {
    return maxAltitude;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxAltitude(int maxAltitude) {
    this.maxAltitude = maxAltitude > 0 ? maxAltitude : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxFlightSpeed() {
    return maxFlightSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxFlightSpeed(int maxFlightSpeed) {
    this.maxFlightSpeed = maxFlightSpeed > 0 ? maxFlightSpeed : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    FlyingDinosaur that = (FlyingDinosaur) o;
    return wingSpan == that.wingSpan && maxAltitude == that.maxAltitude && maxFlightSpeed == that.maxFlightSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), wingSpan, maxAltitude, maxFlightSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return getSpecies().getScientificName() +
        " [" +
        "name=" + getName() +
        ", age=" + getAge() +
        ", type=" + getType().name() +
        ", species=" + getSpecies().name() +
        ", size=" + getSize().name() +
        ", wingSpan=" + wingSpan + "cm" +
        ", maxAltitude=" + maxAltitude + "m" +
        ", maxFlightSpeed=" + maxFlightSpeed + "Km/h" +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void move() {
    System.out.printf(
        "%s '%s' can travel up to '%d' Km/h and can flight up to '%d' meters\n",
        getSpecies().getScientificName(), getName(), getMaxFlightSpeed(), getMaxAltitude()
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public void info() {
    move();
    eat();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
