package chapter13.project.exception;

/**
 * Custom exception to handle invalid input scenarios.
 */
public class InputException extends Exception {
  
  /**
   * Constructs a new InputException with no detail message.
   */
  public InputException() {
    super();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new InputException with the specified detail message.
   * @param message the detail message.
   */
  public InputException(String message) {
    super(message);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  /**
   * Constructs a new InputException with the specified cause.
   * @param e the cause of the exception.
   */
  public InputException(Exception e) {
    super(e);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new InputException with the specified detail message and cause.
   * @param message the detail message.
   * @param cause the cause of the exception.
   */
  public InputException(String message, Throwable cause) {
    super(message, cause);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
