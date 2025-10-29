package chapter9.project.entity.ticket;

public record Visitor(String firstname, String lastname, String phoneNumber) {
  
  public Visitor {
    if (firstname == null) firstname = "";
    if (lastname == null) lastname = "";
    if (phoneNumber == null) phoneNumber = "";
  }
  
}
