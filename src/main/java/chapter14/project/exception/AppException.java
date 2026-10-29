package chapter14.project.exception;

/**
 * Custom exception to handle application-specific error scenarios.
 */
public class AppException extends RuntimeException {
  
  /**
   * Constructs a new AppException with no detail message.
   */
  public AppException() {
    super();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new AppException with the specified detail message.
   * @param message the detail message.
   */
  public AppException(String message) {
    super(message);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new AppException with the specified cause.
   * @param message the detail message.
   * @param cause the cause of the exception.
   */
  public AppException(String message, Throwable cause) {
    super(message, cause);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
