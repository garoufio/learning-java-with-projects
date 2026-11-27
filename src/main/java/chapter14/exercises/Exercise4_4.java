package chapter14.exercises;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Exercise4_4 {
  
  public static void main(String[] args) {
    Consumer<Dinosaur> printDinosaurInfo = System.out::println;
    Function<Dinosaur, String> getDinosaurName = Dinosaur::getName;
    Consumer<List<Dinosaur>> sortDinosaursList = Collections::sort;
    Supplier<Dinosaur> createDinosaur = Dinosaur::new;
    
    Dinosaur dino1 = createDinosaur.get();
    printDinosaurInfo.accept(dino1);
    Dinosaur dino2 = new Dinosaur("Tommy", LocalDate.of(2017, 11, 27), 99);
    printDinosaurInfo.accept(dino2);
    Dinosaur dino3 = new Dinosaur("Rex", LocalDate.of(2015, 5, 15), 85);
    printDinosaurInfo.accept(dino3);
    
    List<Dinosaur> dinosaurs = Arrays.asList(dino1, dino2, dino3);
    System.out.println("Before sorting:");
    dinosaurs.forEach(printDinosaurInfo);
    sortDinosaursList.accept(dinosaurs);
    System.out.println("After sorting:");
    dinosaurs.forEach(printDinosaurInfo);
  }
  
}
