package chapter13.project.collections;

import chapter13.project.entity.event.EventType;
import chapter13.project.entity.event.SpecialEvent;
import chapter13.project.entity.ticket.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Set;
import java.util.UUID;

/**
 * Comparator for SpecialEvent objects based on date, time, name, and event type. EventComparator implements the
 * Comparator interface for SpecialEvent objects.
 */
public class EventComparator implements Comparator<SpecialEvent> {
  
  public static final Comparator<SpecialEvent> BY_ID =
      (e1, e2) -> e1.getUuid().compareTo(e2.getUuid());
  public static final Comparator<SpecialEvent> BY_NAME =
      (e1, e2) -> e1.getName().compareTo(e2.getName());
  public static final Comparator<SpecialEvent> BY_DESCRIPTION =
      (e1, e2) -> e1.getDescription().compareTo(e2.getDescription());
  public static final Comparator<SpecialEvent> BY_CONTACT_NUMBER =
      (e1, e2) -> e1.getContactNumber().compareTo(e2.getContactNumber());
  public static final Comparator<SpecialEvent> BY_CONTACT_NAME =
      (e1, e2) -> e1.getContactName().compareTo(e2.getContactName());
  public static final Comparator<SpecialEvent> BY_CONTACT_EMAIL =
      (e1, e2) -> e1.getContactEmail().compareTo(e2.getContactEmail());
  public static final Comparator<SpecialEvent> BY_DATE =
      (e1, e2) -> e1.getDate().compareTo(e2.getDate());
  public static final Comparator<SpecialEvent> BY_TIME =
      (e1, e2) -> e1.getTime().compareTo(e2.getTime());
  public static final Comparator<SpecialEvent> BY_EVENT_TYPE =
      (e1, e2) -> e1.getEventType().compareTo(e2.getEventType());
  public static final Comparator<SpecialEvent> DEFAULT =
      (e1, e2) -> (new EventComparator()).compare(e1, e2);
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
}
