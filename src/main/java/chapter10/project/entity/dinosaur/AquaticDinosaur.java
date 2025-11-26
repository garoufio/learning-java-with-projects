package chapter10.project.entity.dinosaur;

import java.util.Objects;

public non-sealed class AquaticDinosaur extends Dinosaur {
  
  private int maxDivingDepth; // in meters
  private int maxUnderwaterSpeed; // in kilometers per hour
  private boolean isAmphibious;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public AquaticDinosaur(
      String name,
      int age,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int maxDivingDepth,
      int maxUnderwaterSpeed,
      boolean isAmphibious
  ) {
    super(name, age, type, species, size);
    setMaxDivingDepth(maxDivingDepth);
    setMaxUnderwaterSpeed(maxUnderwaterSpeed);
    this.isAmphibious = isAmphibious;
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
    return this.getClass().getSimpleName() +
        " [" +
        "name=" + getName() +
        ", age=" + getAge() +
        ", type=" + getType().name() +
        ", species=" + getSpecies().name() +
        ", size=" + getSize().name() +
        ", maxDivingDepth=" + maxDivingDepth +
        ", maxUnderwaterSpeed=" + maxUnderwaterSpeed +
        ", isAmphibious=" + isAmphibious +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void move() {
    System.out.printf("%s '%s' is moving\n", this.getClass().getSimpleName(), getName());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void eat() {
    System.out.printf("%s '%s' is eating\n", this.getClass().getSimpleName(), getName());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
