package chapter14.exercises;

import java.time.LocalDate;

public class Exercise14_1 {
  
  public static void main(String[] args) {
    Dinosaur dino = new Dinosaur("T-Rex", LocalDate.of(2010, 10, 4), 100);
    DinosaurHandler putToSleep = dinosaur -> dinosaur.setAwake(false);
    
    putToSleep.handle(dino);
    System.out.println(dino);
  }
  
}
