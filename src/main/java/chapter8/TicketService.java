package chapter8;

import java.util.Arrays;

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
  
  public Ticket getTicket(Ticket ticket) {
    if  (ticket == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t != null && t.equals(ticket)) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket getTicket(Visitor visitor) {
    if (visitor == null) return null;
    if  (tickets == null) return null;
    
    for (Ticket t : tickets) {
      if (t != null && t.getVisitor().equals(visitor)) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getTickets(TicketType ticketType) {
    if  (ticketType == null) return null;
    if  (tickets == null) return null;
    
    int capacity = 0;
    for (Ticket t : tickets) {
      if (t != null && t.getTicketType() == ticketType) capacity++;
    }
    if (capacity == 0) return null;
    
    Ticket[] arr = new Ticket[capacity];
    for (int i = 0, j = 0; i < tickets.length && j < capacity; i++) {
      if (tickets[i] != null && tickets[i].getTicketType() == ticketType) {
        arr[j] = tickets[i];
        j++;
      }
    }
    return arr;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(Ticket ticket) {
    if (ticket == null) return false;
    
    int index = getTicketIndex(ticket);
    if (index < 0) return false;
    
    tickets[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(Visitor visitor) {
    return removeTicket(getTicket(visitor));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
