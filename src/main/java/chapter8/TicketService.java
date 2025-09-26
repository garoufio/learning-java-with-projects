package chapter8;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class TicketService {
  
  private  Ticket[] tickets;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public TicketService(Ticket[] tickets) {
    this.tickets = tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getAllTickets() {
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
      // find empty slots
      int countEmpty = 0;
      for (Ticket t : this.tickets) {
        if (t == null) countEmpty++;
      }
      // fill empty slots
      if (countEmpty >= tickets.length) {
        for (int i = 0, j = 0; i < this.tickets.length && j < tickets.length; i++) {
          if (this.tickets[i] == null) {
            this.tickets[i] = tickets[j];
            j++;
          }
        }
        return;
      }
      /* if the number of newly added tickets is greater than the empty slots, increase the size of the array and
      add them */
      Ticket[] arr = new Ticket[this.tickets.length + tickets.length];
      System.arraycopy(this.tickets, 0, arr, 0, this.tickets.length);
      System.arraycopy(tickets, 0, arr, this.tickets.length, tickets.length);
      this.tickets = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getTicketIndex(Ticket ticket) {
    if (ticket == null) return -1;
    
    for (int i = 0; i < tickets.length; i++) {
      if (tickets[i] != null && tickets[i].equals(ticket)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket getTicket(UUID id) {
    if (id == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t != null && t.getUuid().compareTo(id) == 0) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket getTicket(Ticket ticket) {
    if  (ticket == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t != null && t.equals(ticket)) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getTickets(Visitor visitor) {
    if  (visitor == null) return null;
    if  (tickets == null) return null;
    
    // find the number of matched Visitors
    int visitorsFound = 0;
    for (Ticket t : tickets) {
      if (t == null) continue;
      
      // search only by firstname + lastname
      if (visitor.phoneNumber().equals("")) {
        if (t.getVisitor().firstname().equals(visitor.firstname()) &&
            t.getVisitor().lastname().equals(visitor.lastname())
        ) {
          visitorsFound++;
        }
      } else { // search by firstname + lastname + phone number
        if (t.getVisitor().equals(visitor)) visitorsFound++;
      }
    }
    if (visitorsFound == 0) return null;
    
    Ticket[] arr = new Ticket[visitorsFound];
    for (int i = 0, j = 0; i < tickets.length && j < visitorsFound; i++) {
      if (tickets[i] == null) continue;
      
      if (visitor.phoneNumber().equals("")) {
        if (tickets[i].getVisitor().firstname().equals(visitor.firstname()) &&
            tickets[i].getVisitor().lastname().equals(visitor.lastname())
        ) {
          arr[j] = tickets[i];
          j++;
        }
      } else {
        if (tickets[i].getVisitor().equals(visitor)) {
          arr[j] = tickets[i];
          j++;
        }
      }
    }
    return arr;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getTickets(TicketType ticketType) {
    if  (ticketType == null) return null;
    if  (tickets == null) return null;
    
    int ticketsFound = 0;
    for (Ticket t : tickets) {
      if (t != null && t.getTicketType() == ticketType) ticketsFound++;
    }
    if (ticketsFound == 0) return null;
    
    Ticket[] arr = new Ticket[ticketsFound];
    for (int i = 0, j = 0; i < tickets.length && j < ticketsFound; i++) {
      if (tickets[i] != null && tickets[i].getTicketType() == ticketType) {
        arr[j] = tickets[i];
        j++;
      }
    }
    return arr;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getTickets(String phone) {
    if  (phone == null) return null;
    if (tickets == null) return null;
    
    int ticketsFound = 0;
    for (Ticket t : tickets) {
      if (t != null && t.getVisitor().phoneNumber().equals(phone)) ticketsFound++;
    }
    if (ticketsFound == 0) return null;
    
    Ticket[] arr = new Ticket[ticketsFound];
    for (int i = 0, j = 0; i < tickets.length && j < ticketsFound; i++) {
      if (tickets[i] != null && tickets[i].getVisitor().phoneNumber().equals(phone)) {
        arr[j] = tickets[i];
        j++;
      }
    }
    return arr;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getTickets(LocalDate date) {
    if  (date == null) return null;
    if (tickets == null) return null;
    
    int ticketsFound = 0;
    for (Ticket t : tickets) {
      if (t != null && t.getVisitDate().isEqual(date)) ticketsFound++;
    }
    if (ticketsFound == 0) return null;
    
    Ticket[] arr = new Ticket[ticketsFound];
    for (int i = 0, j = 0; i < tickets.length && j < ticketsFound; i++) {
      if (tickets[i] != null && tickets[i].getVisitDate().isEqual(date)) {
        arr[j] = tickets[i];
        j++;
      }
    }
    return arr;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(Ticket... ticket) {
    if (ticket == null || tickets.length == 0) return false;
    if (this.tickets == null || this.tickets.length == 0) return false;
    
    int countRemoved = 0;
    for  (Ticket t : ticket) {
      int index = getTicketIndex(t);
      if (index < 0) continue;
      
      this.tickets[index] = null;
      countRemoved++;
    }
    return countRemoved > 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(Visitor visitor) {
    return removeTicket(getTickets(visitor));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
