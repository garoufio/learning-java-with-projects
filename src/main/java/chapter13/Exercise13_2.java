package chapter13;

import java.time.LocalDate;
import java.util.*;

public class Exercise13_2 {
  
  public static void main(String[] args) {
    List<Dinosaur> dinosaurs = new ArrayList<>();
    
    dinosaurs.add(new TRex("Claire", LocalDate.of(2010, 2, 25), 13.5, 5670));
    dinosaurs.add(new TRex("Ramon", LocalDate.of(2018, 1, 4), 25, 15.2, 6350));
    dinosaurs.add(new Velociraptor("Blue", LocalDate.of(2012, 5, 15), 92,45.0));
    dinosaurs.add(new Velociraptor("Billy", LocalDate.of(2022, 8, 5), 40, 48.0));
    
    // comparator based on the health score
    Comparator<Dinosaur> healthScoreComparator = (d1, d2) -> Integer.compare(d1.getHealthScore(), d2.getHealthScore());
    
    // add dinosaurs into the priority queue based on the health score
    PriorityQueue<Dinosaur> queueInDanger = new PriorityQueue<>(healthScoreComparator);
    for (Dinosaur dinosaur : dinosaurs) {
      queueInDanger.add(dinosaur);
    }
    // the most in danger dinosaur, is extracted first
    while (!queueInDanger.isEmpty()) {
      Dinosaur dinosaur = queueInDanger.poll();
      if (dinosaur != null) System.out.println(dinosaur);
    }
//    for (Dinosaur d : queueInDanger) System.out.println(d);
  }
  
}
