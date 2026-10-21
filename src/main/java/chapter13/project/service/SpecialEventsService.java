package chapter13.project.service;

import chapter13.project.App;
import chapter13.project.entity.event.EventType;
import chapter13.project.entity.event.SpecialEvent;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.HashSet;
import java.util.HashMap;

/**
 * Service class for managing special events. Provides methods to add, retrieve, and manage special events.
 */
public class SpecialEventsService {
  
  private Set<SpecialEvent> specialEvents;
  private Map<LocalDate, Integer> specialEventsPerDate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new SpecialEventsService instance for managing special events, initializing the specialEvents set as 
   * an empty HashSet and the specialEventsPerDate map as an empty HashMap.
   */
  public SpecialEventsService() {
    specialEvents = new HashSet<>();
    specialEventsPerDate = new HashMap<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new SpecialEventsService instance with the provided list of special events. Initializes the 
   * specialEvents set with the given list and the specialEventsPerDate map as an empty HashMap.
   * @param specialEvents the list of special events to initialize the service with. If null, an empty list is used.
   */
  public SpecialEventsService(Set<SpecialEvent> specialEvents) {
    this.specialEvents = specialEvents == null ? new HashSet<>() : specialEvents;
    specialEventsPerDate = new HashMap<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all special events managed by this service.
   * @return a set of all special events. If there are no special events, an empty set is returned.
   */
  public Set<SpecialEvent> getAllSpecialEvents() {
    return (this.specialEvents == null) ? Set.of() : Set.copyOf(this.specialEvents);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a special event by its unique identifier (UUID).
   * @param id the UUID of the special event to retrieve. If null, the method returns null.
   * @return the special event with the specified UUID, or null if not found.
   */
  public SpecialEvent getSpecialEvent(String id) {
    if  (this.specialEvents == null || id == null) return null;
    
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.getUuid().compareTo(UUID.fromString(id)) == 0) return se;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a special event that matches the provided event object.
   * @param event the special event to search for. If null, the method returns null.
   * @return the special event that matches the provided event object, or null if not found.
   */
  public SpecialEvent getSpecialEvent(SpecialEvent event) {
    if  (this.specialEvents == null || event == null) return null;
    
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.equals(event)) return se;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of special events based on the provided search criteria.
   * @param str the search string, either a contact number or an event name depending on the value of getByContactNumber. 
   * If null, an empty list is returned.
   * @param getByContactNumber if true, the search is performed by contact number; if false, the search is performed by 
   * event name.
   * @return a set of special events that match the search criteria. If no matching events are found, an empty list is 
   * returned.
   */
  public Set<SpecialEvent> getSpecialEvents(String str, boolean getByContactNumber) {
    if (this.specialEvents == null || this.specialEvents.isEmpty() || str == null) return Set.of();
    
    Set<SpecialEvent> specialEvents = new HashSet<>();
    if (getByContactNumber) { // search by contact number
      for (SpecialEvent se : this.specialEvents) {
        if (se != null && se.getContactNumber().equals(str)) specialEvents.add(se);
      }
    } else { // search by event name
      for (SpecialEvent se : this.specialEvents) {
        if (se != null && se.getName().equals(str)) specialEvents.add(se);
      }
    }
    return specialEvents;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  /**
   * Retrieves a set of special events that match the specified event type.
   * @param eventType the event type to search for. If null, an empty set is returned. See {@link EventType)} for 
   * available event types.
   * @return a set of special events that match the specified event type. If no matching events are found, an empty 
   * set is returned.
   */
  public Set<SpecialEvent> getSpecialEvents(EventType eventType) {
    if (this.specialEvents == null || this.specialEvents.isEmpty() || eventType == null) return Set.of();
    
    Set<SpecialEvent> specialEvents = new HashSet<>();
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.getEventType() == eventType) specialEvents.add(se);
    }
    return specialEvents;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of special events that match the specified date.
   * @param date the date to search for. If null, an empty set is returned.
   * @return a set of special events that match the specified date. If no matching events are found, an empty set is 
   * returned.
   */
  public Set<SpecialEvent> getSpecialEvents(LocalDate date) {
    if (this.specialEvents == null || this.specialEvents.isEmpty() || date == null) return Set.of();
    
    Set<SpecialEvent> specialEvents = new HashSet<>();
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.getDate().isEqual(date)) specialEvents.add(se);
    }
    return specialEvents;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds one or more special events to the service.
   * @param events the special events to add. If null or empty, no events are added.
   */
  public void addSpecialEvent(SpecialEvent... events) {
    if (this.specialEvents == null || events == null || events.length == 0) {
      System.out.println("No events were added");
      return;
    }
    
    for (int i = 0; i < events.length; i++) {
      if (events[i] != null) {
        // check if daily tickets number has been reached
        Integer count = specialEventsPerDate.get(events[i].getDate());
        count = count == null ? 1 : count + 1;
        
        if (count > App.MAX_DAILY_SPECIAL_EVENTS) {
          System.out.printf(
              "Maximum number of special events on '%s' has been reached. Event '%s' was not added\n",
              events[i].getDate().toString(), events[i]
          );
        } else {
          this.specialEvents.add(events[i]);
          specialEventsPerDate.put(events[i].getDate(), count);
          System.out.printf("Special event added '%s'\n", events[i]);
        }
      } else System.out.printf("Invalid special event at index '%d'\n", i);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes a special event from the service.
   * @param event the special event to remove. If null or not found, no event is removed.
   * @return true if the event was successfully removed, false otherwise.
   */
  public boolean removeSpecialEvent(SpecialEvent event) {
    if (this.specialEvents == null || this.specialEvents.isEmpty() || event == null) return false;
    
    return this.specialEvents.remove(event);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
