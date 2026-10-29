package chapter14.project.entity.ticket;

/**
 * Represents different types of tickets with their associated prices. The available ticket types include INFANT, 
 * CHILD, ADULT, ELDER, DISABLED_PERSON, SCHOOL, FORBIDDEN, VIP, ALL_DAY, SEASON_3, SEASON_6, SEASON_8, and SEASON_12.
 */
public enum TicketType {
  INFANT(0),
  CHILD(5),
  ADULT(10),
  ELDER(7),
  DISABLED_PERSON(5),
  SCHOOL(5),
  FORBIDDEN(0),
  VIP(18),
  ALL_DAY(22),
  SEASON_3(50),
  SEASON_6(100),
  SEASON_8(130),
  SEASON_12(170);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private double price;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new TicketType with the specified price.
   * @param price the price associated with this ticket type.
   */
  TicketType(double price) {
    this.price = price;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the price associated with this ticket type.
   * @return the price of this ticket type.
   */
  public double getPrice() {
    return price;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
