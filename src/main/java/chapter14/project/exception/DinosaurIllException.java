package chapter14.project.exception;

/**
 * Exception thrown when a dinosaur becomes ill.
 */
public class DinosaurIllException extends Exception {
  
  /**
   * Constructs a new DinosaurIllException with no detail message.
   */
  public DinosaurIllException() {
    super();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new DinosaurIllException with the specified detail message.
   * @param message the detail message.
   */
  public DinosaurIllException(String message) {
    super(message);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new DinosaurIllException with the specified cause.
   * @param e the cause of the exception.
   */
  public DinosaurIllException(Exception e) {
    super(e);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
