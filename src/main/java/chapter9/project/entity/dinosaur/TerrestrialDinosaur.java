package chapter9.project.entity.dinosaur;

import java.util.Objects;

public non-sealed class TerrestrialDinosaur extends Dinosaur {
  
  int maxSpeed;   // max running speed
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public TerrestrialDinosaur(
      String name,
      int age,
      DinosaurType type,
      DinosaurSpecies species,
      DinosaurSize size,
      int maxSpeed
  ) {
    super(name, age, type, species, size);
    this.maxSpeed = maxSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxSpeed() {
    return maxSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxSpeed(int maxSpeed) {
    this.maxSpeed = maxSpeed;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    
    Dinosaur dinosaur = (Dinosaur) o;
    if (dinosaur instanceof TerrestrialDinosaur t) {
      return maxSpeed == t.maxSpeed;
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge(), getType(), getSpecies(), getSize(), maxSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "TerrestrialDinosaur [" +
        "name=" + getName() +
        ", age=" + getAge() +
        ", type=" + getType().name() +
        ", species=" + getSpecies().name() +
        ", size=" + getSize().name() +
        ", maxSpeed=" + maxSpeed +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
