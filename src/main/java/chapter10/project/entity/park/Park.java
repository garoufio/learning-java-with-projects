package chapter10.project.entity.park;

import java.time.LocalTime;

public class Park {
  
  public static final LocalTime OPENING_HOUR = LocalTime.of(10, 0, 0);
  public static final LocalTime CLOSING_HOUR = LocalTime.of(18, 30, 0);
  
  private boolean isOpen;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park() {
    super();
    setParkStatus();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean getParkStatus() {
    return isOpen;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setParkStatus() {
    LocalTime now = LocalTime.now();
    
    isOpen = now.isAfter(OPENING_HOUR) && now.isBefore(CLOSING_HOUR);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setParkStatus(boolean isOpen) {
    this.isOpen = isOpen;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void openPark() {
    isOpen = true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void closePark() {
    isOpen = false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
