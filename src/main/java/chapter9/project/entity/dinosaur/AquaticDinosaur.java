package chapter9.project.entity.dinosaur;

import java.util.Objects;

public non-sealed class AquaticDinosaur extends Dinosaur {

  private int maxDepth;   // max diving depth in meters
  private boolean isAmphibious;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public AquaticDinosaur(
      String name,
      int age,
      DinosaurType type,
      DinosaurSize size,
      int maxDepth,
      boolean isAmphibious
  ) {
    super(name, age, type, DinosaurSpecies.PLIOSAURS, size);
    setMaxDepth(maxDepth);
    this.isAmphibious = isAmphibious;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getMaxDepth() {
    return maxDepth;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setMaxDepth(int maxDepth) {
    this.maxDepth = maxDepth > 0 ? maxDepth : 0;
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
    if (!super.equals(o)) return false;
    
    Dinosaur dinosaur = (Dinosaur) o;
    if (dinosaur instanceof AquaticDinosaur a) {
      return maxDepth == a.maxDepth &&
          isAmphibious == a.isAmphibious;
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getAge(), getType(), getSpecies(), getSize(), maxDepth, isAmphibious);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "AquaticDinosaur [" +
        "name=" + getName() +
        ", age=" + getAge() + ", " +
        ", type=" + getType().name() +
        ", species=" + getSpecies().name() +
        ", size=" + getSize().name() +
        ", maxDepth=" + maxDepth +
        ", isAmphibious=" + isAmphibious +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
