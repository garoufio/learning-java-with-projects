package chapter12;

import java.time.*;

public class TestPeriodAndDuration {
  
  public static void main(String[] args) {
    System.out.println("Testing Period between two LocalDate instances:");
    LocalDate startDate = LocalDate.of(2020, 1, 1);
    LocalDate endDate = LocalDate.of(2024, 6, 15);
    Period period = Period.between(startDate, endDate);
    System.out.println(period);
    System.out.printf("Period between %s and %s is: %d years, %d months, %d days\n",
        startDate, endDate, period.getYears(), period.getMonths(), period.getDays());
    
    System.out.println("-----------------------");
    LocalTime startTime = LocalTime.of(10, 30, 0);
    LocalTime endTime = LocalTime.of(14, 45, 36);
    Duration duration1 = Duration.between(startTime, endTime);
    System.out.println(duration1);
    System.out.printf("Duration between %s and %s is: %d hours, %d minutes, %d seconds\n",
        startTime, endTime, duration1.toHoursPart(), duration1.toMinutesPart(), duration1.toSecondsPart()
    );
    
    LocalDateTime startDateTime = LocalDateTime.parse("2026-01-12T10:45:20");
    LocalDateTime endDateTime = LocalDateTime.parse("2026-01-14T08:35:15");
    Duration duration2 = Duration.between(startDateTime, endDateTime);
    System.out.println(duration2);
    System.out.printf("Duration between %s and %s is: %d hours, %d minutes, %d seconds\n",
        startDateTime, endDateTime, duration2.toHours(), duration2.toMinutesPart(), duration2.toSecondsPart()
    );
  }
  
}
