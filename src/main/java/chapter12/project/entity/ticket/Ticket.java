package chapter12.project.entity.ticket;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public sealed class Ticket permits RegularTicket, SeasonTicket {
  
  private UUID uuid;
  private TicketType ticketType;
  private double price;
  private Visitor visitor;
  private LocalDate visitDate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket(TicketType ticketType, Visitor visitor) {
    this.uuid = UUID.randomUUID();
    this.ticketType = ticketType;
    this.visitor = visitor;
    this.price = ticketType.getPrice();
    this.visitDate = LocalDate.now();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket(TicketType ticketType, Visitor visitor, LocalDate visitDate) {
    this(ticketType, visitor);
    this.visitDate = visitDate;
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
  
  public LocalDate getVisitDate() {
    return visitDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setVisitDate(LocalDate visitDate) {
    this.visitDate = visitDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public UUID getUuid() {
    return uuid;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    
    Ticket ticket = (Ticket) o;
    return (Double.compare(price, ticket.price) == 0 &&
        Objects.equals(uuid, ticket.uuid) &&
        ticketType == ticket.ticketType &&
        visitor.equals(ticket.visitor) &&
        Objects.equals(visitDate, ticket.visitDate));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(uuid, ticketType, price, visitor, visitDate);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Ticket [" +
        "uuid=" + uuid.toString() +
        ", ticketType=" + ticketType.name() +
        ", price=" + price +
        ", visitor=" + visitor +
        ", visitDate=" + visitDate +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
