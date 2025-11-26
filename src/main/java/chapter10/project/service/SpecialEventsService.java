package chapter10.project.service;

import chapter10.project.App;
import chapter10.project.entity.event.EventType;
import chapter10.project.entity.event.SpecialEvent;

import java.time.LocalDate;
import java.util.*;

public class SpecialEventsService {
  
  private List<SpecialEvent> specialEvents;
  private Map<LocalDate, Integer> specialEventsPerDate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEventsService() {
    specialEvents = new ArrayList<>();
    specialEventsPerDate = new HashMap<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEventsService(List<SpecialEvent> specialEvents) {
    this.specialEvents = specialEvents == null ? new ArrayList<>() : specialEvents;
    specialEventsPerDate = new HashMap<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<SpecialEvent> getAllSpecialEvents() {
    return (this.specialEvents == null) ? List.of() : List.copyOf(this.specialEvents);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEvent getSpecialEvent(String id) {
    if  (this.specialEvents == null || id == null) return null;
    
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.getUuid().compareTo(UUID.fromString(id)) == 0) return se;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEvent getSpecialEvent(SpecialEvent event) {
    if  (this.specialEvents == null || event == null) return null;
    
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.equals(event)) return se;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<SpecialEvent> getSpecialEvents(String str, boolean getByContactNumber) {
    if (this.specialEvents == null || this.specialEvents.isEmpty() || str == null) return List.of();
    
    List<SpecialEvent> specialEvents = new ArrayList<>();
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
  
  public List<SpecialEvent> getSpecialEvents(EventType eventType) {
    if (this.specialEvents == null || this.specialEvents.isEmpty() || eventType == null) return List.of();
    
    List<SpecialEvent> specialEvents = new ArrayList<>();
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.getEventType() == eventType) specialEvents.add(se);
    }
    return specialEvents;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<SpecialEvent> getSpecialEvents(LocalDate date) {
    if (this.specialEvents == null || this.specialEvents.isEmpty() || date == null) return List.of();
    
    List<SpecialEvent> specialEvents = new ArrayList<>();
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.getDate().isEqual(date)) specialEvents.add(se);
    }
    return specialEvents;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public boolean removeSpecialEvent(SpecialEvent event) {
    if (this.specialEvents == null || this.specialEvents.isEmpty() || event == null) return false;
    
    for (SpecialEvent se : this.specialEvents) {
      if (se != null && se.equals(event)) return this.specialEvents.remove(event);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
