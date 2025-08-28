package chapter5;

import java.time.LocalDateTime;

public class Exercise5_3 {
  
  public static void main(String[] args) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime open = now.plusMinutes(20);
    int minutesCount = 0;

    while (now.plusMinutes(minutesCount).isBefore(open)) {
      System.out.println("The zoo will open in " + (20 - minutesCount) + " minutes");
      minutesCount++;
    }
    System.out.println("The zoo has opened its gates!");
  }
  
}
