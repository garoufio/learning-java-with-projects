package chapter13.project.entity.event;

import chapter13.project.entity.ticket.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a special event with details such as name, description, contact information, date, time, and tickets.
 * This class implements the Comparable interface to allow comparison based on the event's date and time.
 */
public class SpecialEvent implements Comparable<SpecialEvent> {

  private final UUID uuid;
  private String name;
  private String description;
  private String contactNumber;
  private String contactName;
  private String contactEmail;
  private LocalDate date;
  private LocalTime time;
  private List<Ticket> tickets;
  private EventType eventType;

  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new SpecialEvent with the specified details.
   * @param name the name of the special event.
   * @param description the description of the special event.
   * @param contactNumber the contact number for the special event.
   * @param contactName the contact name for the special event.
   * @param contactEmail the contact email for the special event.
   * @param eventType the type of the special event, see {@link EventType} for possible values.
   * @param date the date of the special event.
   * @param time the time of the special event.
   */
  public SpecialEvent(
      String name,
      String description,
      String contactNumber,
      String contactName,
      String contactEmail,
      EventType eventType,
      LocalDate date,
      LocalTime time) {
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.contactNumber = contactNumber;
    this.contactName = contactName;
    this.contactEmail = contactEmail;
    this.eventType = eventType;
    this.date = date;
    this.time = time;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the unique identifier (UUID) of the special event.
   * @return the UUID of the special event.
   */
  public UUID getUuid() {
    return uuid;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the name of the special event.
   * @return the name of the special event.
   */
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the name of the special event.
   * @param name the new name of the special event.
   */
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the description of the special event.
   * @return the description of the special event.
   */
  public String getDescription() {
    return description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the description of the special event.
   * @param description the new description of the special event.
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the contact number for the special event.
   * @return the contact number for the special event.
   */
  public String getContactNumber() {
    return contactNumber;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the contact number for the special event.
   * @param contactNumber the new contact number for the special event.
   */
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the contact name for the special event.
   * @return the contact name for the special event.
   */
  public String getContactName() {
    return contactName;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the contact name for the special event.
   * @param contactName the new contact name for the special event.
   */
  public void setContactName(String contactName) {
    this.contactName = contactName;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the contact email for the special event.
   * @return the contact email for the special event.
   */
  public String getContactEmail() {
    return contactEmail;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the contact email for the special event.
   * @param contactEmail the new contact email for the special event.
   */
  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Returns the date of the special event.
   * @return the date of the special event.
   */  
  public LocalDate getDate() {
    return date;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Sets the date of the special event.
   * @param date the new date of the special event.
   */  
  public void setDate(LocalDate date) {
    this.date = date;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the time of the special event.
   * @return the time of the special event.
   */
  public LocalTime getTime() {
    return time;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the time of the special event.
   * @param time the new time of the special event.
   */
  public void setTime(LocalTime time) {
    this.time = time;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Returns the list of tickets for the special event.
   * @return the list of tickets for the special event.
   */  
  public List<Ticket> getTickets() {
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the list of tickets for the special event.
   * @param tickets the new list of tickets for the special event.
   */
  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets == null ? new ArrayList<>() : tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrurns the type of the special event.
   * @return the type of the special event, see {@link EventType} for possible values.
   */
  public EventType getEventType() {
    return eventType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the type of the special event.
   * @param eventType the new type of the special event, see {@link EventType} for possible values.
   */
  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Checks if this special event is equal to another object. The comparison if performed by the uuid, name, description,
   * contact number, contact name, contact email, date, time, tickets, and event type.
   * @param o the object to compare with.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SpecialEvent event = (SpecialEvent) o;
    return Objects.equals(uuid, event.uuid) &&
        Objects.equals(name, event.name) &&
        Objects.equals(description, event.description) &&
        Objects.equals(contactNumber, event.contactNumber) &&
        Objects.equals(contactName, event.contactName) &&
        Objects.equals(contactEmail, event.contactEmail) &&
        Objects.equals(date, event.date) &&
        Objects.equals(time, event.time) &&
        Objects.deepEquals(tickets, event.tickets) &&
        eventType == event.eventType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the hash code value for this special event. The hash code is computed based on the uuid, name, description,
   * contact number, contact name, contact email, date, time, tickets, and event type.
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        uuid, name, description, contactNumber, contactName,
        contactEmail, date, time, tickets.hashCode(), eventType
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of this special event. The string includes the uuid, name, description,
   * contact number, contact name, contact email, date, time, tickets, and event type.
   * @return a string representation of this special event.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("SpecialEvent [" +
        "id=" + uuid +
        ", name=" + name +
        ", description=" + description +
        ", contactNumber=" + contactNumber +
        ", contactName=" + contactName +
        ", contactEmail=" + contactEmail +
        ", date=" + date +
        ", time=" + time +
        ", eventType=" + eventType +
        ",\ntickets="
    );
    if (tickets == null || tickets.isEmpty()) {
      sb.append("none");
    } else {
      for (Ticket t : tickets) {
        sb.append("\n" + t.toString());
      }
    }
    sb.append("\n]");
    return sb.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this special event with the specified special event for order. The comparison is based on the name of 
   * the events.
   * @param other the special event to be compared.
   * @return a negative integer, zero, or a positive integer as this event's name is less than, equal to, or greater 
   * than the specified event's name.
   */
  @Override
  public int compareTo(SpecialEvent other) {
    if (other == null) return 1;
    
    return this.name.compareTo(other.name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}