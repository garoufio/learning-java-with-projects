package chapter10.project.entity.dinosaur;

import java.util.Objects;

public non-sealed class TerrestrialDinosaur extends Dinosaur {

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
  
  public void setHeight(int height) {
    this.height = height > 0 ? height : 0;
  }
  
  public int getMaxRunningSpeed() {
    return maxRunningSpeed;
  }
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), height, maxRunningSpeed);
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
        ", height=" + height +
        ", maxRunningSpeed=" + maxRunningSpeed +
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
