package chapter14.project.entity.ticket;

/**
 * Represents a visitor with a first name, last name, and phone number.
 * @param firstname the first name of the visitor
 * @param lastname the last name of the visitor
 * @param phoneNumber the phone number of the visitor
 */
public record Visitor(String firstname, String lastname, String phoneNumber) {
  
  /**
   * Constructs a new Visitor record, ensuring that none of the fields are null.
   * If any of the parameters are null, they are replaced with an empty string.
   * @param firstname the first name of the visitor
   * @param lastname the last name of the visitor
   * @param phoneNumber the phone number of the visitor
   */
  public Visitor {
    if (firstname == null) firstname = "";
    if (lastname == null) lastname = "";
    if (phoneNumber == null) phoneNumber = "";
  }
  
}
