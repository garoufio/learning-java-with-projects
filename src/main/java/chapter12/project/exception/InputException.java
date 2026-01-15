package chapter12.project.exception;

/**
 * Custom exception to handle invalid input scenarios
 */
public class InputException extends RuntimeException {
  
  public InputException() {
    super();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public InputException(String message) {
    super(message);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public InputException(Exception e) {
    super(e);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
