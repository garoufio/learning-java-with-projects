package chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TestDateTimeFormatters {
  
  public static void main(String[] args) {
    System.out.println("Popular DateTimeFormatters:");
    LocalDate nowDate = LocalDate.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
    System.out.println("ISO_DATE: " + nowDate.format(dateFormatter));
    DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    System.out.println("FULL Date: " + nowDate.format(fullDateFormatter));
    
    LocalDateTime nowDateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
    System.out.println("ISO_DATE_TIME: " + nowDateTime.format(dateTimeFormatter));
    DateTimeFormatter fullDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    System.out.println("MEDIUM DateTime: " + nowDateTime.format(fullDateTimeFormatter));
    
    System.out.println("Custom DateTimeFormatter:");
    DateTimeFormatter customDate12TimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
    System.out.println("12-hour format: " + nowDateTime.format(customDate12TimeFormatter));
    DateTimeFormatter customDate24TimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss ");
    System.out.println("24-hour format: " + nowDateTime.format(customDate24TimeFormatter));
    
    ZonedDateTime zonedDateTime = ZonedDateTime.now();
    DateTimeFormatter customDateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy MMM dd, HH:mm:ss");
    System.out.println("Custom format 1: " + zonedDateTime.format(customDateTimeFormatter1));
    DateTimeFormatter customDateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy MMMM dd, HH:mm:ss");
    System.out.println("Custom format 2: " + zonedDateTime.format(customDateTimeFormatter2));
    DateTimeFormatter customDateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyy MMMM EEEE, HH:mm:ss z");
    System.out.println("Custom format 3: " + zonedDateTime.format(customDateTimeFormatter3));
    // insert text
    DateTimeFormatter customDateTimeFormatter4 = DateTimeFormatter.ofPattern(
        "'Year': yyyy, 'Month': MMMM, 'Day': dd, 'Hour': HH, 'Minutes': mm, 'Zone': z"
    );
    System.out.println("Custom format 4: " + zonedDateTime.format(customDateTimeFormatter4));
  
  }
  
}
