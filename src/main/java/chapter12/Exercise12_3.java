package chapter12;

import java.time.LocalDateTime;
import java.time.Period;

public class Exercise12_3 {
  
  public static void main(String[] args) {
    LocalDateTime lastDtmInspection = LocalDateTime.of(2025, 12, 10, 10, 30);
    LocalDateTime nextDtmInspection = lastDtmInspection.plusDays(45);
    
    Period period = Period.between(lastDtmInspection.toLocalDate(), nextDtmInspection.toLocalDate());
    System.out.printf("There are %d days left for the next safety inspection\n", period.getDays());
  }
  
}
