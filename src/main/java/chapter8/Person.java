package chapter8;

public record Person(String firstName, String lastName, int age) {
  
  public Person {
    if (age < 18) {
      firstName = "Error";
      lastName = "Error";
      age = -1;
    }
  }
  
}
