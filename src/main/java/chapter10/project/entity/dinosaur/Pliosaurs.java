package chapter10.project.entity.dinosaur;

public class Pliosaurs extends AquaticDinosaur implements Carnivore {
  
  public Pliosaurs(String name, int age, DinosaurSize size, int maxDivingDepth, int maxUnderwaterSpeed, boolean isAmphibious) {
    super(name, age, DinosaurType.CARNIVORE, DinosaurSpecies.PLIOSAURS, size, maxDivingDepth, maxUnderwaterSpeed, isAmphibious);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void eat() {
    System.out.printf(
        "%s '%s' has to eat '%.2f' Kg of meat daily\n",
        this.getClass().getSimpleName(), getName(), feed(getSpecies(), getSize())
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void move() {
    System.out.printf(
        "%s '%s' can swim up to '%d' Km/h and can dive up to '%d' meters deep\n",
        this.getClass().getSimpleName(), getName(), getMaxDivingDepth()
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return super.toString().replace(super.getClass().getSimpleName(), this.getClass().getSimpleName());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
