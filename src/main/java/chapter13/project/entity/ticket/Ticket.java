package chapter13.project.entity.ticket;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a ticket for a visitor, which can be either a regular ticket or a season ticket. Contains information about 
 * the ticket type, price, visitor, and visit date. It is a sealed class, meaning it can only be extended by the 
 * specified subclasses, currently RegularTicket and SeasonTicket.
 * @see RegularTicket
 * @see SeasonTicket
 */
public sealed class Ticket permits RegularTicket, SeasonTicket {
  
  private UUID uuid;
  private TicketType ticketType;
  private double price;
  private Visitor visitor;
  private LocalDate visitDate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Ticket instance with the specified ticket type and visitor. The price is set based on the ticket 
   * type, and the visit date is set to the current date.
   * @param ticketType the type of the ticket, see {@link TicketType} for possible values.
   * @param visitor the visitor, see {@link Visitor}, associated with the ticket.
   */
  public Ticket(TicketType ticketType, Visitor visitor) {
    this.uuid = UUID.randomUUID();
    this.ticketType = ticketType;
    this.visitor = visitor;
    this.price = ticketType.getPrice();
    this.visitDate = LocalDate.now();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Ticket instance with the specified ticket type, visitor, and visit date. The price is set based on 
   * the ticket type.
   * @param ticketType the type of the ticket, see {@link TicketType} for possible values.
   * @param visitor the visitor, see {@link Visitor}, associated with the ticket.
   * @param visitDate the date of the visit.
   */
  public Ticket(TicketType ticketType, Visitor visitor, LocalDate visitDate) {
    this(ticketType, visitor);
    this.visitDate = visitDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the type of the ticket.
   * @return the type of the ticket, see {@link TicketType} for possible values.
   */
  public TicketType getTicketType() {
    return ticketType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the type of the ticket.
   * @param ticketType the type of the ticket, see {@link TicketType} for possible values.
   */
  public void setTicketType(TicketType ticketType) {
    this.ticketType = ticketType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the price of the ticket.
   * @return the price of the ticket.
   */
  public double getPrice() {
    return price;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the price of the ticket.
   * @param price the price of the ticket.
   */
  public void setPrice(double price) {
    this.price = price;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the visitor associated with the ticket.
   * @return the visitor associated with the ticket, see {@link Visitor}.
   */
  public Visitor getVisitor() {
    return visitor;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the visitor associated with the ticket.
   * @param visitor the visitor associated with the ticket, see {@link Visitor}.
   */
  public void setVisitor(Visitor visitor) {
    this.visitor = visitor;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the visit date of the ticket.
   * @return the visit date of the ticket.
   */
  public LocalDate getVisitDate() {
    return visitDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the visit date of the ticket.
   * @param visitDate the visit date of the ticket.
   */
  public void setVisitDate(LocalDate visitDate) {
    this.visitDate = visitDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the unique identifier of the ticket.
   * @return the unique identifier of the ticket.
   */
  public UUID getUuid() {
    return uuid;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this ticket to the specified object. The result is true if and only if the argument is not null and is a 
   * Ticket object that has the same UUID, ticket type, price, visitor, and visit date as this ticket. 
   * @param o the object to compare this ticket against.
   * @return true if the given object represents a Ticket equivalent to this ticket, false otherwise.
   */
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
  
  /**
   * Returns a hash code value for the ticket. The hash code is computed based on the UUID, ticket type, price, 
   * visitor, and visit date of the ticket.
   */
  @Override
  public int hashCode() {
    return Objects.hash(uuid, ticketType, price, visitor, visitDate);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the ticket. The string includes the UUID, ticket type, price, visitor, and visit 
   * date of the ticket.
   */
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
