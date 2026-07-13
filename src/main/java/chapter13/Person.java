package chapter13;

import java.util.Objects;

public class Person implements Comparable<Person> {
  
  private int age;
  private String name;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Person(int age, String name) {
    this.age = age;
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getAge() {
    return age;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setAge(int age) {
    this.age = age;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int compareTo(Person o) {
    return Integer.compare(this.age, o.age);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return age == person.age && name.equals(person.name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(age, name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Person {" +
        "age = " + age +
        ", name = '" + name + '\'' +
        '}';
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
