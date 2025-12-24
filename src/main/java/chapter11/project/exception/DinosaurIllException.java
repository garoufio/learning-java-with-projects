package chapter11.project.exception;

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
