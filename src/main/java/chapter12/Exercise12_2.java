package chapter12;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Exercise12_2 {
  
  public static void main(String[] args) {
    EventLog eventLog = new EventLog();
    
    eventLog.log("Feeding", LocalDateTime.now().minusHours(2));
    eventLog.log("Cleaning", LocalDateTime.now().minusHours(1).minusMinutes(30));
    eventLog.printLog();
    System.out.println(
        "Zoned feeding time: " + eventLog.getEventTime("Feeding").atZone(ZoneId.of("Europe/London"))
    );
    eventLog.removeEvent("Cleaning");
    eventLog.printLog();
    System.out.println("After clearing the log:");
    eventLog.clearLog();
    eventLog.printLog();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * EventLog class to log events with their timestamps
   */
  static class EventLog {
    
    private Map<String, LocalDateTime> eventLog;
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public EventLog() {
      this.eventLog = new HashMap<>();
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public void log(String eventName, LocalDateTime eventTime) {
      eventLog.put(eventName, eventTime);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public LocalDateTime getEventTime(String eventName) {
      return eventLog.get(eventName);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public void removeEvent(String eventName) {
      eventLog.remove(eventName);
    }
    
    //----------------------------------------------------------------------------------------------------------------
    
    public void printLog() {
      //DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
      
      for (Map.Entry<String, LocalDateTime> entry : eventLog.entrySet()) {
        System.out.println("Event: " + entry.getKey() + ", Time: " + entry.getValue().format(dtf));
      }
    }
    
    //----------------------------------------------------------------------------------------------------------------
    
    public void clearLog() {
      eventLog.clear();
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
