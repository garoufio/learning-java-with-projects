package chapter10.project.entity.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface Worker {
  
  LocalDate[] workingDays(DayOfWeek from, DayOfWeek to, LocalDate date);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  LocalDate[] daysOff(DayOfWeek from, int numberOfDays);
  
  //-------------------------------------------------------------------------------------------------------------------

}
