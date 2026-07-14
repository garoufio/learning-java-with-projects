package chapter13;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TestTreeMapSorting {
  
  public static void main(String[] args) {
    // create a tree map with natural ordering of Person objects (by age)
    System.out.println("Sorted by age (natural ordering):");
    TreeMap<Person, String> treeMap = new TreeMap<>();
    treeMap.put(new Person(30, "Alice"), "Engineer");
    treeMap.put(new Person(25, "Bob"), "Designer");
    treeMap.put(new Person(35, "Charlie"), "Manager");
    for (Person person : treeMap.keySet()) {
      System.out.println(person + " - " + treeMap.get(person));
    }
    
    // create a tree map with string ordering of Person objects (by name)
    System.out.println("\nSorted by name (string comparison):");
    Comparator<Person> comparator = (p1, p2) -> p1.getName().compareTo(p2.getName());
    TreeMap<Person, String> treeMapName = new TreeMap<>(comparator);
    treeMapName.put(new Person(30, "Alice"), "Engineer");
    treeMapName.put(new Person(25, "Bob"), "Designer");
    treeMapName.put(new Person(35, "Charlie"), "Manager");
    for (Map.Entry<Person, String> entry : treeMapName.entrySet()) {
      System.out.printf("%s - %s\n", entry.getKey(), entry.getValue());
    }
  }
  
}
