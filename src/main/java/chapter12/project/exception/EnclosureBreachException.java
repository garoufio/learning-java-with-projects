package chapter12.project.exception;

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
