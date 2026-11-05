package chapter14.exercises;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.IO.println;

public class MethodRefsAsContext {
  
  static void main() {
    // no person objects are created, so the length is 0
    Supplier<Integer> lambda1 = () -> Person.howMany();
    println("Lambda -> howMany: " + lambda1.get());
    Supplier<Integer> methodRef1 = Person::howMany;
    println("Method Reference -> howMany: " + methodRef1.get());
    
    // one person object is created, so the length is 1
    Function<Person, Integer> lambda2 = (person) -> Person.howMany(person);
    println("Lambda -> howMany: " + lambda2.apply(new Person()));
    Function<Person, Integer> methodRef2 = Person::howMany;
    println("Method Reference -> howMany: " + methodRef2.apply(new Person()));
    
    // two person objects are created, so the length is 2
    BiFunction<Person, Person, Integer> lambda3 = (person1, person2) -> Person.howMany(person1, person2);
    println("Lambda -> howMany: " + lambda3.apply(new Person(), new Person()));
    BiFunction<Person, Person, Integer> methodRef3 = Person::howMany;
    println("Method Reference -> howMany: " + methodRef3.apply(new Person(), new Person()));
    
    // an array of person objects is created, so the length is 3
    Function<Person[], Integer> lambda4 = (people) -> Person.howMany(people);
    println("Lambda -> howMany: " + lambda4.apply(new Person[] {new Person(), new Person(), new Person()}));
    Function<Person[], Integer> methodRef4 = Person::howMany;
    println("Method Reference -> howMany: " + methodRef4.apply(new Person[] {new Person(), new Person(), new Person()}));
  }
  
}
