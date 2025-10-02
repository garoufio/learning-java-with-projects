package chapter8.project;

public record Visitor (String firstname, String lastname, String phoneNumber) {
  
  public Visitor {
    if (firstname == null) firstname = "";
    if (lastname == null) lastname = "";
    if (phoneNumber == null) phoneNumber = "";
  }
  
}
