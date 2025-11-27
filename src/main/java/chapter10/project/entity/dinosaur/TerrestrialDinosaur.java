package chapter10.project.entity.dinosaur;

import java.util.Objects;

public non-sealed class TerrestrialDinosaur extends Dinosaur implements Carnivore, Herbivore, Omnivore {

  private int height; // in centimeters
  private int maxRunningSpeed; // in kilometers per hour
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public TerrestrialDinosaur(
      String name,
      int age,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int height,
      int maxRunningSpeed
  ) {
    super(name, age, type, species, size);
    setHeight(height);
    setMaxRunningSpeed(maxRunningSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getHeight() {
    return height;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setHeight(int height) {
    this.height = height > 0 ? height : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxRunningSpeed() {
    return maxRunningSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxRunningSpeed(int maxRunningSpeed) {
    this.maxRunningSpeed = maxRunningSpeed > 0 ? maxRunningSpeed : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    
    TerrestrialDinosaur that = (TerrestrialDinosaur) o;
    return height == that.height && maxRunningSpeed == that.maxRunningSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), height, maxRunningSpeed);
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
        ", height=" + height + "cm" +
        ", maxRunningSpeed=" + maxRunningSpeed + "Km/h" +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void move() {
    System.out.printf(
        "%s '%s' can run up to '%d' Km/h\n",
        getSpecies().getScientificName(), getName(), getMaxRunningSpeed()
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
