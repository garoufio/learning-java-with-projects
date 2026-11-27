package chapter14.exercises;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exercise14_2 {

  public static void main(String[] args) {
    Predicate<Dinosaur> isCarnivore = Dinosaur::isCarnivore;
    Supplier<Dinosaur> createDinosaur = () -> new Dinosaur("Tommy", LocalDate.of(2026, 11, 5));
    Consumer<Dinosaur> printName = dino -> System.out.println(dino.getName());
    Function<Dinosaur, String> getDiet = Dinosaur::getDiet;
    
    Dinosaur dino = createDinosaur.get();
    System.out.println("Is the dinosaur a carnivore? " + isCarnivore.test(dino));
    printName.accept(dino);
    System.out.println("Diet: " + getDiet.apply(dino));
  }
  
}
