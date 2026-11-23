package chapter14.exercises;

import java.time.LocalDate;
import java.util.function.Consumer;

public class Exercise14_3 {
  
  public static void main(String[] args) {
    Integer[] countDinosaurs = { 0 };
    
    Consumer<Dinosaur> incrementDinosaurCount = dino -> countDinosaurs[0]++;
    incrementDinosaurCount.accept(new Dinosaur("T-Rex", LocalDate.of(2010, 5, 15)));
    Consumer<Integer[]> printDinosaurCount = count -> System.out.println("Total Dinosaurs: " + count[0]);
    printDinosaurCount.accept(countDinosaurs);
  }
  
}
