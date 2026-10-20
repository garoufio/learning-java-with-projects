package chapter13.project.entity.ticket;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a season ticket that extends the base Ticket class. A season ticket has a validity period defined by a 
 * start date (fromDate) and an end date (toDate).
 * @see Ticket
 */
public non-sealed class SeasonTicket extends Ticket {
  
  private LocalDate fromDate;
  private LocalDate toDate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new SeasonTicket with the specified ticket type, visitor, and start date. The end date is 
   * automatically calculated based on the ticket type.
   * @param ticketType the type of the season ticket, see {@link TicketType} for possible values.
   * @param visitor the visitor associated with the ticket, see {@link Visitor}.
   * @param fromDate the start date of the season ticket.
   */
  public SeasonTicket(TicketType ticketType, Visitor visitor, LocalDate fromDate) {
    super(ticketType, visitor);
    this.fromDate = fromDate;
    this.setVisitDate(fromDate);
    initToDate();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Initializes the end date (toDate) of the season ticket based on its ticket type.
   * For invalid season ticket types, the ticket type is changed to a daily ticket and the end date is set to the start 
   * date.
   */
  private void initToDate() {
    switch (this.getTicketType()) {
      case SEASON_3 -> this.toDate = fromDate.plusMonths(3);
      case SEASON_6 -> this.toDate = fromDate.plusMonths(6);
      case SEASON_8 -> this.toDate = fromDate.plusMonths(8);
      case SEASON_12 -> this.toDate = fromDate.plusYears(1);
      default ->  {
        System.out.println("Invalid season ticket type. Ticket type changed to daily ticket." + this.getTicketType());
        this.toDate = fromDate;
        this.setTicketType(TicketType.ALL_DAY);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the start date (fromDate) of the season ticket.
   * @return the start date of the season ticket.
   */
  public LocalDate getFromDate() {
    return fromDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the start date (fromDate) of the season ticket.
   * @param fromDate the start date of the season ticket.
   */
  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the end date (toDate) of the season ticket.
   * @return the end date of the season ticket.
   */
  public LocalDate getToDate() {
    return toDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the end date (toDate) of the season ticket.
   * @param toDate the end date of the season ticket.
   */
  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this season ticket to the specified object. The result is true if and only if the argument is not null 
   * and is a SeasonTicket object that has the same UUID, ticket type, price, visitor, start date, and end date as this 
   * season ticket. 
   * @param o the object to compare this season ticket against.
   * @return true if the given object represents a SeasonTicket equivalent to this season ticket, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    
    Ticket ticket = (Ticket) o;
    if (ticket instanceof SeasonTicket seasonTicket) {
      return this.getTicketType() == seasonTicket.getTicketType() &&
          Double.compare(this.getPrice(), seasonTicket.getPrice()) == 0 &&
          Objects.equals(this.getUuid(), seasonTicket.getUuid()) &&
          this.getVisitor().equals(seasonTicket.getVisitor()) &&
          this.fromDate.compareTo(seasonTicket.getFromDate()) == 0 &&
          this.toDate.compareTo(seasonTicket.getToDate()) == 0;
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a hash code value for the season ticket. The hash code is computed based on the UUID, ticket type, price, 
   * visitor, start date, and end date of the season ticket.
   * @return a hash code value for this season ticket.
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        this.getUuid(), this.getTicketType(), this.getPrice(), this.getVisitor(), this.getFromDate(), this.getToDate()
    );
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the season ticket, including the UUID, ticket type, price, visitor, start date, 
   * and end date.
   */
  @Override
  public String toString() {
    return "Season Ticket [" +
        "uuid=" + this.getUuid().toString() +
        ", ticketType=" + this.getTicketType().name() +
        ", price=" + this.getPrice() +
        ", visitor=" + this.getVisitor() +
        ", fromDate=" + this.fromDate +
        ", toDate=" + this.toDate +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
