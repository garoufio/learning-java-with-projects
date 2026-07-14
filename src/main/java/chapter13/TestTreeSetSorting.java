package chapter13;

import java.util.Comparator;
import java.util.TreeSet;

public class TestTreeSetSorting {
  
  public static void main(String[] args) {
    // create a tree set with natural ordering of Person objects (by age)
    System.out.println("Sorted by age (natural ordering):");
    TreeSet<Person> treeSet = new TreeSet<Person>();
    treeSet.add(new Person(30, "Alice"));
    treeSet.add(new Person(25, "Bob"));
    treeSet.add(new Person(35, "Charlie"));
    for (Person person : treeSet) {
      System.out.println(person);
    }
    
    // create a tree set with string ordering of Person objects (by name)
    System.out.println("\nSorted by name (string comparison):");
    Comparator<Person> comparator = (p1, p2) -> p1.getName().compareTo(p2.getName());
    TreeSet<Person> treeSetName = new  TreeSet<>(comparator);
    treeSet.add(new Person(30, "Alice"));
    treeSet.add(new Person(25, "Bob"));
    treeSet.add(new Person(35, "Charlie"));
    for (Person person : treeSet) {
      System.out.println(person);
    }
  }
  
}
