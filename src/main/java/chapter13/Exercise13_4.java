package chapter13;

import java.time.LocalDate;

public class Exercise13_4 {
  
  public static void main(String[] args) {
    Crate<Person> personCrate = new Crate(new Person(34, "Mike" ));
    Crate<Bag<Book>> bagCrate = new Crate(new Bag<>(new Book("1948", "George Orwell")));
    Crate<Dinosaur> dinosaurCrate = new Crate(new Dinosaur("Velociraptor", LocalDate.of(2020, 5, 15)));
    Crate<TRex> tRexcrate = new Crate(new TRex("Rexy", LocalDate.of(2015, 3, 10), 12.5, 6000));
  }
  
}
