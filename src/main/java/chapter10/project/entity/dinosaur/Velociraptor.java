package chapter10.project.entity.dinosaur;

public class Velociraptor extends TerrestrialDinosaur implements Carnivore {
  
  public Velociraptor(String name, int age, DinosaurSize size, int height, int maxRunningSpeed) {
    super(name, age, DinosaurType.CARNIVORE, DinosaurSpecies.VELOCIRAPTOR, size, height, maxRunningSpeed);
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
        "%s '%s' can run up to '%d' Km/h\n",
        this.getClass().getSimpleName(), getName(), getMaxRunningSpeed()
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return super.toString().replace(super.getClass().getSimpleName(), this.getClass().getSimpleName());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
