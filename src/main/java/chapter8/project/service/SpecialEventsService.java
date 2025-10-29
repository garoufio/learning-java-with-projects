package chapter8.project.service;

import chapter8.project.Project;
import chapter8.project.entity.EventType;
import chapter8.project.entity.SpecialEvent;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class SpecialEventsService {
  
  private SpecialEvent[]  specialEvents;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEventsService() {
    specialEvents = new SpecialEvent[Project.MAX_SPECIAL_EVENTS];
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEventsService(SpecialEvent[] specialEvents) {
    this.specialEvents = specialEvents;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEvent[] getAllSpecialEvents() {
    return (specialEvents == null) ? null : Arrays.copyOf(specialEvents, specialEvents.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEvent getSpecialEvent(String id) {
    if  (specialEvents == null || id == null) return null;
    
    for (SpecialEvent se : specialEvents) {
      if (se != null && se.getUuid().compareTo(UUID.fromString(id)) == 0) return se;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEvent getSpecialEvent(SpecialEvent event) {
    if  (specialEvents == null || event == null) return null;
    
    for (SpecialEvent se : specialEvents) {
      if (se != null && se.equals(event)) return se;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEvent[] getSpecialEvents(String str, boolean getByContactNumber) {
    if (specialEvents == null || str == null) return null;
    
    int eventsFound = 0;
    if (getByContactNumber) { // search by contact number
      for (SpecialEvent se : specialEvents) {
        if (se != null && se.getContactNumber().equals(str)) eventsFound++;
      }
    } else { // search by event name
      for (SpecialEvent se : specialEvents) {
        if (se != null && se.getName().equals(str)) eventsFound++;
      }
    }
    if (eventsFound == 0) return null;
    
    SpecialEvent[] events = new SpecialEvent[eventsFound];
    if (getByContactNumber) { // search by contact number
      for (int i = 0, j = 0; i < specialEvents.length && j < events.length; i++) {
        if (specialEvents[i] != null && specialEvents[i].getContactNumber().equals(str)) {
          events[j] = specialEvents[i];
          j++;
        }
      }
    } else { // search by event name
      for (int i = 0, j = 0; i < specialEvents.length && j < events.length; i++) {
        if (specialEvents[i] != null && specialEvents[i].getName().equals(str)) {
          events[j] = specialEvents[i];
          j++;
        }
      }
    }
    return events;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEvent[] getSpecialEvents(EventType eventType) {
    if (specialEvents == null || eventType == null) return null;
    
    int eventsFound = 0;
    for (SpecialEvent se : specialEvents) {
      if (se != null && se.getEventType().equals(eventType)) eventsFound++;
    }
    if (eventsFound == 0) return null;
    
    SpecialEvent[] events = new SpecialEvent[eventsFound];
    for (int i = 0, j = 0; i < specialEvents.length && j < events.length; i++) {
      if (specialEvents[i] != null && specialEvents[i].getEventType().equals(eventType)) {
        events[j] = specialEvents[i];
        j++;
      }
    }
    return events;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEvent[] getSpecialEvents(LocalDate date) {
    if (specialEvents == null || date == null) return null;
    
    int eventsFound = 0;
    for (SpecialEvent se : specialEvents) {
      if (se != null && se.getDate().isEqual(date)) eventsFound++;
    }
    if (eventsFound == 0) return null;
    
    SpecialEvent[] events = new SpecialEvent[eventsFound];
    for (int i = 0, j = 0; i < specialEvents.length && j < events.length; i++) {
      if (specialEvents[i] != null && specialEvents[i].getDate().isEqual(date)) {
        events[j] = specialEvents[i];
        j++;
      }
    }
    return events;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getSpecialEventIndex(SpecialEvent event) {
    if  (specialEvents == null || event == null) return -1;
    
    for (int i = 0; i < specialEvents.length; i++) {
      if (specialEvents[i] != null && specialEvents[i].equals(event)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addSpecialEvent(SpecialEvent... events) {
    if (events == null || events.length == 0) {
      System.out.println("No events were added");
      return;
    }
    
    if (this.specialEvents == null) {
      this.specialEvents = Arrays.copyOf(events, events.length);
    }
    else {
      // find empty slots
      int countEmpty = 0;
      for (SpecialEvent se : this.specialEvents) {
        if (se == null) countEmpty++;
      }
      // fill empty slots
      if (countEmpty >= events.length) {
        for (int i = 0, j = 0; i < this.specialEvents.length && j < events.length; i++) {
          if (this.specialEvents[i] == null) {
            this.specialEvents[i] = events[j];
            j++;
          }
        }
        return;
      }
      /* if the number of newly added dinosaurs is greater than the empty slots, increase the size of the array and
      add them */
      SpecialEvent[] arr = new SpecialEvent[this.specialEvents.length + events.length];
      System.arraycopy(this.specialEvents, 0, arr, 0, this.specialEvents.length);
      System.arraycopy(events, 0, arr, this.specialEvents.length, events.length);
      this.specialEvents = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeSpecialEvent(SpecialEvent event) {
    if (specialEvents == null || event == null) return false;
    
    int index = getSpecialEventIndex(event);
    if (index < 0) return false;
    
    specialEvents[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
