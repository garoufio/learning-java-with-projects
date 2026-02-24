package chapter12;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestZonedDateTime {
  
  public static void main(String[] args) {
    LocalDateTime flightDepTime = LocalDateTime.of(2026, Month.JANUARY, 20, 15, 30);
    ZonedDateTime flightDepZoned = ZonedDateTime.of(flightDepTime, ZoneId.of("Europe/Athens"));
    System.out.printf("Flight departure time: %s\n", flightDepZoned);
    
    ZonedDateTime flightArrZoned = flightDepZoned.withZoneSameInstant(ZoneId.of("Europe/Paris"))
        .plusHours(2)
        .plusMinutes(45);
    System.out.println("Flight arrival time: " + flightArrZoned);
    
    ZonedDateTime flightArrZoned2 = flightDepZoned.plusHours(2).plusMinutes(45)
        .withZoneSameInstant(ZoneId.of("Europe/Paris"));
    System.out.println("Flight arrival time (2): " + flightArrZoned2);
    ZonedDateTime flightArrZoned3 = ZonedDateTime.of(
        flightDepTime.plusHours(2).plusMinutes(45),
        ZoneId.of("Europe/Paris")
    );
    System.out.println("Flight arrival time (3): " + flightArrZoned3);
    
  }
  
}
