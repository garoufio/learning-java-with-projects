package chapter8;

import java.time.LocalDateTime;

public record Ticket (TicketType ticketType, double price, Visitor visitor, LocalDateTime visitDateTime) {
  
  public Ticket {
    if (price <= 0.0) {
      ticketType = TicketType.FORBIDDEN;
      price = -1.0;
      visitor = new Visitor("invalid visitor", "invalid visitor");
      visitDateTime = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    }
  }
  
}
