package chapter10.project.entity.dinosaur;

import javax.swing.*;

public class Pterosaur extends FlyingDinosaur implements Omnivore {
  
  public Pterosaur(String name, int age, DinosaurSize size, int wingSpan, int maxAltitude, int maxFlightSpeed) {
    super(name, age, DinosaurType.OMNIVORE, DinosaurSpecies.PTEROSAUR, size, wingSpan, maxAltitude, maxFlightSpeed);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void eat() {
    System.out.printf(
        "%s '%s' has to eat '%.2f' Kg of food daily\n",
        this.getClass().getSimpleName(), getName(), feed(getSpecies(), getSize())
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public void move() {
    System.out.printf(
        "%s '%s' can travel up to '%d' Km/h and can flight up to '%d' meters\n",
        this.getClass().getSimpleName(), getName(), getMaxFlightSpeed(), getMaxAltitude()
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return super.toString().replace(super.getClass().getSimpleName(), this.getClass().getSimpleName());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
