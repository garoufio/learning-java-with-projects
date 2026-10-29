package chapter14.project.entity.event;

/**
 * Enumeration representing different types of events and their associated number of visitors. There are different 
 * event types such as SCHOOL, GROUP, SUMMER_CAMP, VIP, and EMERGENCY.
*/
public enum EventType {
  SCHOOL(20),
  GROUP(10),
  SUMMER_CAMP(30),
  VIP(5),
  EMERGENCY(0);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int numberOfVisitors;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs an EventType with the specified number of visitors.
   * @param numberOfVisitors the number of visitors associated with this event type.
   */
  EventType(int numberOfVisitors) {
    this.numberOfVisitors = numberOfVisitors;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the number of visitors associated with this event type.
   * @return the number of visitors.
   */
  public int getNumberOfVisitors() {
    return numberOfVisitors;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
