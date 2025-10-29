package chapter8.project.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class SpecialEvent {

  private UUID uuid;
  private String name;
  private String description;
  private String contactNumber;
  private String contactName;
  private String contactEmail;
  private LocalDate date;
  private LocalTime time;
  private Ticket[] tickets;
  private EventType eventType;

  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public UUID getUuid() {
    return uuid;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getDescription() {
    return description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getContactNumber() {
    return contactNumber;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getContactName() {
    return contactName;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setContactName(String contactName) {
    this.contactName = contactName;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getContactEmail() {
    return contactEmail;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalDate getDate() {
    return date;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDate(LocalDate date) {
    this.date = date;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalTime getTime() {
    return time;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setTime(LocalTime time) {
    this.time = time;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getTickets() {
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setTickets(Ticket[] tickets) {
    this.tickets = tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EventType getEventType() {
    return eventType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(
        uuid, name, description, contactNumber, contactName,
        contactEmail, date, time, Arrays.hashCode(tickets), eventType
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "SpecialEvent [" +
        "id=" + uuid +
        ", name=" + name +
        ", description=" + description +
        ", contactNumber=" + contactNumber +
        ", contactName=" + contactName +
        ", contactEmail=" + contactEmail +
        ", date=" + date +
        ", time=" + time +
        ", eventType=" + eventType +
        ",\ntickets=" + Arrays.toString(tickets) +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}