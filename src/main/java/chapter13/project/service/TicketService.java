package chapter13.project.service;

import chapter13.project.App;
import chapter13.project.entity.ticket.Ticket;
import chapter13.project.entity.ticket.TicketType;
import chapter13.project.entity.ticket.Visitor;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.UUID;

/**
 * Service class for managing tickets, including adding, retrieving, and tracking tickets per date.
 */
public class TicketService {
  
  private Set<Ticket> tickets;
  private Map<LocalDate, Integer> ticketsPerDate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new TicketService with the specified set of tickets. If the provided set is null, an empty set is used.
   * @param tickets the initial set of tickets to be managed by the service. If null, an empty set is used.
   */
  public TicketService(Set<Ticket> tickets) {
    this.tickets = (tickets == null ? new HashSet<>() : tickets);
    this.ticketsPerDate = new HashMap<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all tickets managed by the service. If no tickets exist, an empty set is returned.
   * @return a set of all tickets, or an empty set if none are found.
   */
  public Set<Ticket> getAllTickets() {
    return tickets == null ? Set.of() : Set.copyOf(tickets);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds the specified tickets to the service. Tickets that exceed the daily limit or are invalid are not added.
   * @param tickets the tickets to be added to the service. Tickets exceeding the daily limit or are invalid are 
   * not added.
   */
  public void addTickets(Ticket... tickets) {
    if (this.tickets == null || tickets == null || tickets.length == 0) {
      System.out.println("No tickets were added");
      return;
    }
    
    for (int i = 0; i < tickets.length; i++) {
      if (tickets[i] != null) {
        // check if daily tickets number has been reached
        Integer count = ticketsPerDate.get(tickets[i].getVisitDate());
        count = count == null ? 1 : count + 1;
        
        if (count > App.MAX_DAILY_VISITORS) {
          System.out.printf(
              "Maximum number of daily tickets on '%s' has been reached. Ticket '%s' was not added\n",
              tickets[i].getVisitDate().toString(), tickets[i]
          );
        } else {
          this.tickets.add(tickets[i]);
          ticketsPerDate.put(tickets[i].getVisitDate(), count);
          System.out.printf("Ticket added '%s'\n", tickets[i]);
        }
      } else System.out.printf("Invalid ticket at index '%d'\n", i);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds the specified tickets to the service. Tickets that exceed the daily limit or are incvalid  are not added.
   * @param tickets a collection of tickets to be added to the service. Tickets exceeding the daily limit or are 
   * invalid are not added.
   */
  public void addTickets(Collection<Ticket> tickets) { // 
    if (this.tickets == null || tickets == null || tickets.isEmpty()) {
      System.out.println("No tickets were added");
      return;
    }
    
    int index = 0;
    for (Ticket t : tickets) {
      if (t != null) {
        // check if daily tickets number has been reached
        Integer count = ticketsPerDate.get(t.getVisitDate());
        count = count == null ? 1 : count + 1;
        
        if (count > App.MAX_DAILY_VISITORS) {
          System.out.printf(
              "Maximum number of daily tickets on '%s' has been reached. Ticket '%s' was not added\n",
              t.getVisitDate().toString(), t
          );
        } else {
          this.tickets.add(t);
          ticketsPerDate.put(t.getVisitDate(), count);
          System.out.printf("Ticket added '%s'\n", t);
        }
      } else System.out.printf("Invalid ticket at index '%d'\n", index);
      index++;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a ticket by its unique identifier. If no ticket with the given ID exists, null is returned.
   * @param id the unique identifier of the ticket to retrieve.
   * @return the ticket with the specified ID, or null if not found.
   */
  public Ticket getTicket(UUID id) {
    if (this.tickets == null || id == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t != null && t.getUuid().compareTo(id) == 0) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a ticket by its instance. If the ticket is not managed by the service, null is returned.
   * @param ticket the ticket instance to retrieve.
   * @return the ticket if it exists in the service, or null if not found.
   */
  public Ticket getTicket(Ticket ticket) {
    if  (this.tickets == null || ticket == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t != null && t.equals(ticket)) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all tickets associated with the specified visitor. If no tickets for the visitor exist, an empty set is 
   * returned.
   * @param visitor the visitor whose tickets are to be retrieved.
   * @return a set of tickets for the specified visitor, or an empty set if none are found.
   */
  public Set<Ticket> getTickets(Visitor visitor) {
    if  (this.tickets == null || this.tickets.isEmpty() || visitor == null) return Set.of();
    
    Set<Ticket> tickets = new HashSet<>();
    for (Ticket t : this.tickets) {
      if (t != null && t.getVisitor().equals(visitor)) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all tickets of the specified ticket type. If no tickets of the given type exist, an empty set is 
   * returned.
   * @param ticketType the type of tickets to retrieve.
   * @return a set of tickets matching the specified type, or an empty set if none are found.
   */
  public Set<Ticket> getTickets(TicketType ticketType) {
    if  (this.tickets == null || this.tickets.isEmpty() || ticketType == null) return Set.of();
    
    Set<Ticket> tickets = new HashSet<>();
    for (Ticket t : this.tickets) {
      if (t != null && t.getTicketType() == ticketType) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all tickets for a visitor with the specified first and last name. If no tickets for the visitor exist, 
   * an empty set is returned.
   * @param firstName the first name of the visitor.
   * @param lastName the last name of the visitor.
   * @return a set of tickets for the specified visitor, or an empty set if none are found.
   */
  
  public Set<Ticket> getTickets(String firstName, String lastName) {
    if  (this.tickets == null || this.tickets.isEmpty() || firstName == null || lastName == null) return Set.of();
    
    Set<Ticket> tickets = new HashSet<>();
    for (Ticket t : this.tickets) {
      if (t != null &&
          t.getVisitor().firstname().equals(firstName) &&
          t.getVisitor().lastname().equals(lastName)
      ) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all tickets for a visitor with the specified phone number. If no tickets for the visitor exist, an empty 
   * set is returned.
   * @param phone the phone number of the visitor.
   * @return a set of tickets for the specified visitor, or an empty set if none are found.
   */
  
  public Set<Ticket> getTickets(String phone) {
    if (this.tickets == null || this.tickets.isEmpty() || phone == null) return Set.of();
    
    Set<Ticket> tickets = new HashSet<>();
    for (Ticket t : this.tickets) {
      if (t != null && t.getVisitor().phoneNumber().equals(phone)) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all tickets for a specific visit date. If no tickets for the given date exist, an empty set is returned.
   * @param date the visit date of the tickets to retrieve.
   * @return a set of tickets for the specified visit date, or an empty set if none are found.
   */
  public Set<Ticket> getTickets(LocalDate date) {
    if  (this.tickets == null || this.tickets.isEmpty() || date == null) return Set.of();
    
    Set<Ticket> tickets = new HashSet<>();
    for (Ticket t : this.tickets) {
      if (t != null && t.getVisitDate().isEqual(date)) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
   /**
    * Removes the specified ticket from the set of tickets. If the ticket does not exist or the set is null, it returns false.
    * @param ticket the ticket to be removed.
    * @return true if the ticket was successfully removed, false otherwise.
    */
  public boolean removeTicket(Ticket ticket) {
    if (this.tickets == null || ticket == null) return false;
    
    return this.tickets.remove(ticket);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified tickets from the set of tickets. If any of the tickets do not exist or the collection is null, 
   * they are ignored.
   * @param tickets the collection of tickets to be removed.
   * @return true if the tickets were successfully removed, false otherwise.
   */
  public boolean removeTicket(Collection<Ticket> tickets) {
    if (this.tickets == null || this.tickets.isEmpty() || tickets == null || tickets.isEmpty()) return false;
    
    for (Ticket t : tickets) {
      if (t != null && this.tickets.contains(t)) this.tickets.remove(t);
    }
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes all tickets associated with the specified visitor. If no tickets for the visitor exist, it returns false.
   * @param visitor the visitor whose tickets are to be removed.
   * @return true if any tickets were successfully removed, false otherwise.
   */
  public boolean removeTicket(Visitor visitor) {
    return removeTicket(getTickets(visitor));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
