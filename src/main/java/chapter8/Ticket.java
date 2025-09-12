package chapter8;

import java.time.LocalDate;

public record Ticket (double price, Visitor visitor, LocalDate visitDate) {
  
  public Ticket {
    if (price <= 0.0) {
      price = -1.0;
      visitor = new Visitor("invalid visitor", "invalid visitor");
      visitDate = LocalDate.of(9999, 12, 31);
    }
  }
  
}
