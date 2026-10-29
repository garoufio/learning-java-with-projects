package chapter14.project.entity.ticket;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The RegularTicket class extends the Ticket class and adds functionality for handling reserved parking options.
 * @see Ticket
 */
public non-sealed class RegularTicket extends Ticket {
  
  public static final double PARKING_RESERVATION_FEE = 1.50;
  private boolean reserveParking;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new RegularTicket with the specified ticket type, visitor, and reserved parking option.
   * @param ticketType the type of the ticket, see {@link TicketType} for possible values.
   * @param visitor the visitor associated with the ticket, see {@link Visitor}.
   * @param reserveParking whether the ticket includes a reserved parking option.
   */
  public RegularTicket(TicketType ticketType, Visitor visitor, boolean reserveParking) {
    super(ticketType, visitor);
    setReservedParking(reserveParking);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new RegularTicket with the specified ticket type, visitor, visit date, and reserved parking option.
   * @param ticketType the type of the ticket, see {@link TicketType} for possible values.
   * @param visitor the visitor associated with the ticket, see {@link Visitor}.
   * @param visitDate the date of the visit.
   * @param reserveParking whether the ticket includes a reserved parking option.
   */
  public RegularTicket(TicketType ticketType, Visitor visitor, LocalDate visitDate, boolean reserveParking) {
    super(ticketType, visitor, visitDate);
    setReservedParking(reserveParking);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns whether the ticket includes a reserved parking option.
   * @return true if the ticket has reserved parking, false otherwise.
   */
  public boolean hasReservedParking() {
    return reserveParking;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Sets whether the ticket includes a reserved parking option.
   * @param reservedParking true to include reserved parking, false otherwise.
   */  
  public void setReservedParking(boolean reservedParking) {
    this.reserveParking = reservedParking;
    if (this.reserveParking) {
      double price = this.getTicketType().getPrice();
      this.setPrice(price + PARKING_RESERVATION_FEE);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this regular ticket to the specified object. The result is true if and only if the argument is not null 
   * and is a RegularTicket object that has the same UUID, ticket type, price, visitor, visit date, and reserved parking 
   * option as this regular ticket.
   * @param o the object to compare this regular ticket against.
   * @return true if the given object represents a RegularTicket equivalent to this regular ticket, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    
    Ticket ticket = (Ticket) o;
    if (ticket instanceof RegularTicket regularTicket) {
      return this.reserveParking == regularTicket.hasReservedParking();
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a hash code value for the regular ticket. The hash code is computed based on the UUID, ticket type, price, 
   * visitor, visit date, and reserved parking option of the regular ticket.
   * @return a hash code value for this regular ticket.
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        this.getUuid(), this.getTicketType(), this.getPrice(), this.getVisitor(), this.getVisitDate(), reserveParking
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the regular ticket, including the UUID, ticket type, price, visitor, visit date,
   * and reserved parking option.
   */
  @Override
  public String toString() {
    return "Regular Ticket [" +
        "uuid=" + this.getUuid().toString() +
        ", ticketType=" + this.getTicketType() +
        ", price=" + this.getPrice() +
        ", visitor=" + this.getVisitor() +
        ", visitDate=" + this.getVisitDate() +
        ",  reserveParking=" + this.reserveParking +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
