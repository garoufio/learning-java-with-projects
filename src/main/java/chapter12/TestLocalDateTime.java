package chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestLocalDateTime {
  
  public static void main(String[] args) {
    LocalDate nowDate = LocalDate.now();
    System.out.println("Today is: " + nowDate);
    LocalTime nowTime = LocalTime.now();
    System.out.println("Current time is: " + nowTime);
    
    System.out.println("-----------------------");
    LocalDateTime nowDateTime = LocalDateTime.now();
    System.out.println("Current date and time is: " + nowDateTime);
    // another way to create LocalDateTime using the factory of method
    LocalDateTime nowDateTime2 = LocalDateTime.of(nowDate, nowTime);
    System.out.println("Current date and time is (using of): " + nowDateTime2);
    
    System.out.println("-----------------------");
    LocalDate ld1 = LocalDate.of(2026, 1, 15);
    System.out.printf("LocalDate ld1: %s\n", ld1);
    System.out.printf("Year: %d, Month: %s, Day: %s\n", ld1.getYear(), ld1.getMonth(), ld1.getDayOfWeek());
    LocalDate ld2 = LocalDate.parse("2026-01-16");
    System.out.printf("LocalDate ld2: %s\n", ld2);
    LocalDate ld3 = ld2.withMonth(2);
    System.out.printf("LocalDate ld3 (ld2 with month changed to February): %s\n", ld3);
    LocalDate ld4 = ld2.withYear(2028);
    System.out.printf("LocalDate ld4 (ld2 with year changed to 2028): %s\n", ld4);
    LocalDate ld5 = ld2.minusDays(10);
    System.out.printf("LocalDate ld5 (ld2 minus 10 days): %s\n", ld5);
    LocalDateTime ldt1 = ld2.atTime(13, 40, 55);
    System.out.printf("LocalDateTime ldt1 (ld2 at time 13:40:55): %s\n", ldt1);
  }
  
}
