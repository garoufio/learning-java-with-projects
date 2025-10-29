package chapter9.project.entity.ticket;

import java.time.LocalDate;
import java.util.Objects;

public non-sealed class RegularTicket extends Ticket {
  
  public static final double PARKING_RESERVATION_FEE = 1.50;
  private boolean reserveParking;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public RegularTicket(TicketType ticketType, Visitor visitor, LocalDate visitDate, boolean reserveParking) {
    super(ticketType, visitor, visitDate);
    setReservedParking(reserveParking);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean hasReservedParking() {
    return reserveParking;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setReservedParking(boolean reservedParking) {
    this.reserveParking = reservedParking;
    if (this.reserveParking) {
      double price = this.getTicketType().getPrice();
      this.setPrice(price + PARKING_RESERVATION_FEE);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(
        this.getUuid(), this.getTicketType(), this.getPrice(), this.getVisitor(), this.getVisitDate(), reserveParking
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Ticket [" +
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
