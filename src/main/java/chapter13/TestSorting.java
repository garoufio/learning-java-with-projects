package chapter13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestSorting {
  
  public static void main(String[] args) {
    List<Person> people = new ArrayList<>();
    people.add(new Person(30, "Alice"));
    people.add(new Person(25, "Bob"));
    people.add(new Person(35, "Charlie"));
    
    // natural ordering
    Collections.sort(people);
    System.out.println("Sorted by age (natural ordering):");
    for (Person p : people) {
      System.out.println(p.getName() + " - " + p.getAge());
    }
    
    // string comparison by name
    Comparator<Person> nameComparator = new Comparator<Person>() {
      @Override
      public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
      }
    };
    Collections.sort(people, nameComparator);
    System.out.println("\nSorted by name (string comparison):");
    for (Person p : people) {
      System.out.println(p.getName() + " - " + p.getAge());
    }
    
    // comparison by name length
    Collections.sort(people, (p1, p2) -> Integer.compare(p1.getName().length(), p2.getName().length()));
    System.out.println("\nSorted by name length (comparison by name length):");
    for (Person p : people) {
      System.out.println(p.getName() + " - " + p.getAge());
    }
  }

}
