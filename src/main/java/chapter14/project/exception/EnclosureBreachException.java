package chapter14.project.exception;

/**
 * Exception thrown when an animal breaches its enclosure.
 */
public class EnclosureBreachException extends Exception {
  
  /**
   * Constructs a new EnclosureBreachException with no detail message.
   */
  public EnclosureBreachException() {
    super();
  }
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new EnclosureBreachException with the specified detail message.
   * @param message the detail message.
   */
  public EnclosureBreachException(String message) {
    super(message);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new EnclosureBreachException with the specified cause.
   * @param e the cause of the exception.
   */
  public EnclosureBreachException(Exception e) {
    super(e);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
