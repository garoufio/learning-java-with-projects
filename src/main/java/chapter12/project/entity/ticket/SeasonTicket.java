package chapter12.project.entity.ticket;

import java.time.LocalDate;
import java.util.Objects;

public non-sealed class SeasonTicket extends Ticket {
  
  private LocalDate fromDate;
  private LocalDate toDate;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SeasonTicket(TicketType ticketType, Visitor visitor, LocalDate fromDate) {
    super(ticketType, visitor);
    this.fromDate = fromDate;
    this.setVisitDate(fromDate);
    initToDate();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public LocalDate getFromDate() {
    return fromDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalDate getToDate() {
    return toDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(
        this.getUuid(), this.getTicketType(), this.getPrice(), this.getVisitor(), this.getFromDate(), this.getToDate()
    );
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
