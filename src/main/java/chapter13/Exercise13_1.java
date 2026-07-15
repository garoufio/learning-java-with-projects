package chapter13;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Exercise13_1 {
  
  public static void main(String[] args) {
    List<Dinosaur> dinosaurs = new ArrayList<>();
    
    dinosaurs.add(new TRex("Claire", LocalDate.of(2010, 2, 25), 13.5, 5670));
    dinosaurs.add(new Velociraptor("Blue", LocalDate.of(2012, 5, 15), 45.0));
    
    for (Dinosaur d : dinosaurs) {
      System.out.println(d);
    }
    
    Velociraptor raptor = (Velociraptor) dinosaurs.get(1);
    System.out.printf("Velociraptor's '%s' max running speed is '%.2f' Km/h\n", raptor.getName(), raptor.getMaxRunningSpeed());
  }
  
}
