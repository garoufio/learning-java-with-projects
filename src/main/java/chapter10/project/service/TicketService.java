package chapter10.project.service;

import chapter10.project.App;
import chapter10.project.entity.ticket.Ticket;
import chapter10.project.entity.ticket.TicketType;
import chapter10.project.entity.ticket.Visitor;

import java.time.LocalDate;
import java.util.*;

public class TicketService {
  
  private List<Ticket> tickets;
  private Map<LocalDate, Integer> ticketsPerDate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public TicketService(List<Ticket> tickets) {
    this.tickets = (tickets == null ? new ArrayList<>() : tickets);
    this.ticketsPerDate = new HashMap<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Ticket> getAllTickets() {
    return tickets == null ? List.of() : List.copyOf(tickets);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public void addTickets(List<Ticket> tickets) {
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
  
  public Ticket getTicket(UUID id) {
    if (this.tickets == null || id == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t != null && t.getUuid().compareTo(id) == 0) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket getTicket(Ticket ticket) {
    if  (this.tickets == null || ticket == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t != null && t.equals(ticket)) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Ticket> getTickets(Visitor visitor) {
    if  (this.tickets == null || this.tickets.isEmpty() || visitor == null) return List.of();
    
    List<Ticket> tickets = new ArrayList<>();
    for (Ticket t : this.tickets) {
      if (t != null && t.getVisitor().equals(visitor)) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Ticket> getTickets(TicketType ticketType) {
    if  (this.tickets == null || this.tickets.isEmpty() || ticketType == null) return List.of();
    
    List<Ticket> tickets = new ArrayList<>();
    for (Ticket t : this.tickets) {
      if (t != null && t.getTicketType() == ticketType) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Ticket> getTickets(String firstName, String lastName) {
    if  (this.tickets == null || this.tickets.isEmpty() || firstName == null || lastName == null) return List.of();
    
    List<Ticket> tickets = new ArrayList<>();
    for (Ticket t : this.tickets) {
      if (t != null &&
          t.getVisitor().firstname().equals(firstName) &&
          t.getVisitor().lastname().equals(lastName)
      ) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Ticket> getTickets(String phone) {
    if (this.tickets == null || this.tickets.isEmpty() || phone == null) return List.of();
    
    List<Ticket> tickets = new ArrayList<>();
    for (Ticket t : this.tickets) {
      if (t != null && t.getVisitor().phoneNumber().equals(phone)) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Ticket> getTickets(LocalDate date) {
    if  (this.tickets == null || this.tickets.isEmpty() || date == null) return List.of();
    
    List<Ticket> tickets = new ArrayList<>();
    for (Ticket t : this.tickets) {
      if (t != null && t.getVisitDate().isEqual(date)) tickets.add(t);
    }
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(Ticket ticket) {
    if (this.tickets == null || ticket == null) return false;
    
    for (Ticket t : this.tickets) {
      if (t != null && t.equals(ticket)) return this.tickets.remove(t);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(List<Ticket> tickets) {
    if (this.tickets == null || this.tickets.isEmpty() || tickets == null || tickets.isEmpty()) return false;
    
    for (Ticket t : tickets) {
      if (t != null && this.tickets.contains(t)) this.tickets.remove(t);
    }
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(Visitor visitor) {
    return removeTicket(getTickets(visitor));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
