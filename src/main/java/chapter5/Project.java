package chapter5;

import java.time.LocalDateTime;

public class Project {
  
  public static void main(String[] args) {
    double tRexMeal = 100;
    double brachioMeal = 250;
    LocalDateTime now = LocalDateTime.now();
    
    for (int time = now.getHour(); time < 23; time++) {
      if (time == 8 || time == 14 || time == 20) {
        System.out.printf("It's %d:00 - Feeding time for T-Rex with %.2f Kg of food\n", time, tRexMeal);
      }
      if (time == 7 || time == 11 || time == 15 | time == 19) {
        System.out.printf("It's %d:00 - Feeding time for Brachiosaurus with %.2f Kg of food\n", time, brachioMeal);
      }
    }
    
  }
  
}
