package chapter12.project.entity.dinosaur;


import java.time.LocalDate;
import java.util.Objects;

public non-sealed class AquaticDinosaur extends Dinosaur implements Carnivore, Herbivore, Omnivore {
  
  private int maxDivingDepth; // in meters
  private int maxUnderwaterSpeed; // in kilometers per hour
  private boolean isAmphibious;
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public int getMaxDivingDepth() {
    return maxDivingDepth;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxDivingDepth(int maxDivingDepth) {
    this.maxDivingDepth = maxDivingDepth >= 0 ? maxDivingDepth : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxUnderwaterSpeed() {
    return maxUnderwaterSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxUnderwaterSpeed(int maxUnderwaterSpeed) {
    this.maxUnderwaterSpeed = maxUnderwaterSpeed >= 0 ? maxUnderwaterSpeed : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean isAmphibious() {
    return isAmphibious;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setAmphibious(boolean amphibious) {
    isAmphibious = amphibious;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), maxDivingDepth, maxUnderwaterSpeed, isAmphibious);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return getSpecies().getScientificName() +
        " [" +
        "name=" + getName() +
        ", age=" + getAge() +
        ", type=" + getType().name() +
        ", size=" + getSize().name() +
        ", maxDivingDepth=" + maxDivingDepth + "m" +
        ", maxUnderwaterSpeed=" + maxUnderwaterSpeed + "Km/h" +
        ", isAmphibious=" + isAmphibious +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void move() {
    System.out.printf(
        "%s '%s' can swim up to '%d' Km/h and can dive up to '%d' meters deep\n",
        getSpecies().getScientificName(), getName(), getMaxDivingDepth()
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
