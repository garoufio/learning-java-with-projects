package chapter8;

public class PersonRecordExample {
  
  public static void main(String[] args) {
    Person p1 = new Person("Mike", "Ross", 39);
    Person p2 = new Person("Done", "Adams", 17);
    
    System.out.println(p1);
    System.out.println(p2);
    
    if (p1 instanceof Person(String firstname, String lastname, int age)) {
      System.out.println(firstname + " " + lastname + " " + age);
    }
    if (p2 instanceof Person p) {
      System.out.println(p);
    }
  }
  
}
