package chapter13.project.collections;

import chapter13.project.entity.event.SpecialEvent;

import java.util.Comparator;

/**
 * Comparator for SpecialEvent objects based on date, time, name, and event type. EventComparator implements the
 * Comparator interface for SpecialEvent objects.
 */
public class EventComparator implements Comparator<SpecialEvent> {
  
  /**
   * Compares two SpecialEvent objects based on date, time, name, and event type.
   * @param e1 the first object to be compared.
   * @param e2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
   * than the second.
   */
  @Override
    public int compare(SpecialEvent e1, SpecialEvent e2) {
      if (e1 == null && e2 == null) return 0;
      if (e1 == null) return -1;
      if (e2 == null) return 1;
    
      // compare by date
      int cmp = e1.getDate().compareTo(e2.getDate());
      if (cmp != 0) {
        return cmp;
      }
      
      // compare by time
      cmp = e1.getTime().compareTo(e2.getTime());
      if (cmp != 0) {
        return cmp;
      }
    
    // compare by name
    cmp = e1.getName().compareTo(e2.getName());
    if (cmp != 0) {
      return cmp;
    }
    
    // compare by event type
    return e1.getEventType().compareTo(e2.getEventType());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the default EventComparator instance.
   * @return a Comparator that compares SpecialEvent objects by date, time, name, and event type.
   */
  public static Comparator<SpecialEvent> getDefaultComparator() {
    return new EventComparator();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
