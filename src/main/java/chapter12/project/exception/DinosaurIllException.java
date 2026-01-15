package chapter12.project.exception;

public class DinosaurIllException extends Exception {
  
  public DinosaurIllException() {
    super();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurIllException(String message) {
    super(message);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurIllException(Exception e) {
    super(e);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
