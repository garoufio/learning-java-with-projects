package chapter13.project.entity.dinosaur;

/**
 * Represents an actionable dinosaur that can perform basic actions such as eating and moving. Provides abstract 
 * methods for eating and moving.
 */
public interface Actionable {
  
  /**
   * Makes the dinosaur eat.
   */
  public void eat();
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Makes the dinosaur move.
   */
  public void move();
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
