package chapter11.project.exception;

public class EnclosureBreachException extends Exception {
  
  public EnclosureBreachException() {
    super();
  }
  //-------------------------------------------------------------------------------------------------------------------
  
  public EnclosureBreachException(String message) {
    super(message);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EnclosureBreachException(Exception e) {
    super(e);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
