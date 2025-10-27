package chapter9.project;

import java.util.Objects;

public non-sealed class FlyingDinosaur extends Dinosaur {

  private int maxHeight; // max flying height in meters
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public FlyingDinosaur(String name, int age, DinosaurType type, DinosaurSize size, int maxHeight) {
    super(name, age, type, DinosaurSpecies.PTEROSAUR, size);
    setMaxHeight(maxHeight);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public double getMaxHeight() {
    return maxHeight;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxHeight(int maxHeight) {
    this.maxHeight = maxHeight > 0 ? maxHeight : 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    
    Dinosaur dinosaur = (Dinosaur) o;
    if (dinosaur instanceof FlyingDinosaur f) {
      return maxHeight == f.maxHeight;
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge(), getType(), getSpecies(), getSize(), maxHeight);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "FlyingDinosaur [" +
        "name=" + getName() +
        ", age=" + getAge() +
        ", type=" + getType().name() +
        ", species=" + getSpecies().name() +
        ", size=" + getSize().name() +
        ", maxHeight=" + maxHeight +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
