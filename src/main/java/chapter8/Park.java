package chapter8;

import java.time.LocalTime;
import java.util.Arrays;

public class Park {
  
  public static final LocalTime OPENING_HOUR = LocalTime.of(10, 0, 0);
  public static final LocalTime CLOSING_HOUR = LocalTime.of(19, 30, 0);
  
  private boolean isOpen;
 
  private Employee[] employees;
  private Ticket[] tickets;
  private Enclosure[] enclosures;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park() {
    super();
    setParkStatus();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park(Dinosaur[] dinosaurs, Employee[] employees, Ticket[] tickets, Enclosure[] enclosures) {
    this();
    this.dinosaurs = Arrays.copyOf(dinosaurs, dinosaurs.length);
    this.employees = Arrays.copyOf(employees, employees.length);
    this.tickets = Arrays.copyOf(tickets, tickets.length);
    this.enclosures = Arrays.copyOf(enclosures, enclosures.length);
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
  // Dinosaurs handling
  //-------------------------------------------------------------------------------------------------------------------
  
  
  
  //-------------------------------------------------------------------------------------------------------------------
  // Employees handling
  //-------------------------------------------------------------------------------------------------------------------
  
  //-------------------------------------------------------------------------------------------------------------------
  // Tickets handling
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getTickets() {
    return tickets == null ? null : Arrays.copyOf(tickets, tickets.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addTickets(Ticket... tickets) {
    if (tickets == null) {
      System.out.println("No tickets were added");
      return;
    }
    
    if (this.tickets == null) {
      this.tickets = Arrays.copyOf(tickets, tickets.length);
    }
    else {
      Ticket[] arr = new Ticket[this.tickets.length + tickets.length];
      System.arraycopy(this.tickets, 0, arr, 0, this.tickets.length);
      System.arraycopy(tickets, 0, arr, this.tickets.length, tickets.length);
      this.tickets = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getSingleTicketIndex(Ticket ticket) {
    if (ticket == null) return -1;
    
    for (int i = 0; i < tickets.length; i++) {
      if (tickets[i].equals(ticket)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket getSingleTicket(Ticket ticket) {
    if  (ticket == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t.equals(ticket)) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(Ticket ticket) {
    if (ticket == null) return false;
    
    int index = getSingleTicketIndex(ticket);
    if (index < 0) return false;
    
    tickets[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Enclosures handling
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure[] getEnclosures() {
    return enclosures == null ? null : Arrays.copyOf(enclosures, enclosures.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEnclosure(Enclosure... enclosure) {
    if (enclosure == null) {
      System.out.println("No tickets were added");
      return;
    }
    
    if (this.enclosures == null) {
      this.enclosures = Arrays.copyOf(enclosure, enclosure.length);
    }
    else {
      Enclosure[] arr = new Enclosure[this.enclosures.length + enclosure.length];
      System.arraycopy(this.enclosures, 0, arr, 0, this.enclosures.length);
      System.arraycopy(enclosure, 0, arr, this.enclosures.length, enclosure.length);
      this.enclosures = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getSingleEnclosureIndex(Enclosure enclosure) {
    if (enclosure == null) return -1;
    
    for (int i = 0; i < enclosures.length; i++) {
      if (enclosures[i].equals(enclosure)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getSingleEnclosure(Enclosure enclosure) {
    if  (enclosure == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e.getEnclosureType().equals(enclosure.getEnclosureType())) {
        return e;
      }
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEnclosure(Enclosure enclosure) {
    if (enclosure == null) return false;
    
    int index = getSingleEnclosureIndex(enclosure);
    if (index < 0) return false;
    
    enclosures[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
