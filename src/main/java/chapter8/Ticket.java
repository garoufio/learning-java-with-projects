package chapter8;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Ticket {
  
  private UUID uuid;
  private TicketType ticketType;
  private double price;
  private Visitor visitor;
  private LocalDateTime visitDateTime;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket(TicketType ticketType, Visitor visitor) {
    this.uuid = UUID.randomUUID();
    this.ticketType = ticketType;
    this.visitor = visitor;
    this.price = ticketType.getPrice();
    this.visitDateTime = LocalDateTime.now();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket(TicketType ticketType, Visitor visitor, LocalDateTime visitDateTime) {
    this(ticketType, visitor);
    this.visitDateTime = visitDateTime;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public TicketType getTicketType() {
    return ticketType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setTicketType(TicketType ticketType) {
    this.ticketType = ticketType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public double getPrice() {
    return price;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Visitor getVisitor() {
    return visitor;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setVisitor(Visitor visitor) {
    this.visitor = visitor;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalDateTime getVisitDateTime() {
    return visitDateTime;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setVisitDateTime(LocalDateTime visitDateTime) {
    this.visitDateTime = visitDateTime;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Ticket ticket = (Ticket) o;
    return (Double.compare(price, ticket.price) == 0 &&
        Objects.equals(uuid, ticket.uuid) &&
        ticketType == ticket.ticketType &&
        Objects.equals(visitor, ticket.visitor) &&
        Objects.equals(visitDateTime, ticket.visitDateTime));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(uuid, ticketType, price, visitor, visitDateTime);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Ticket [" +
        "uuid=" + uuid +
        ", ticketType=" + ticketType.name() +
        ", price=" + price +
        ", visitor=" + visitor +
        ", visitDateTime=" + visitDateTime +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
