package chapter12.project.entity.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface Worker {
  
  LocalDate[] workingDays(DayOfWeek[] weekDays, LocalDate date);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  LocalDate[] daysOff(DayOfWeek from, int numberOfDays);
  
  //-------------------------------------------------------------------------------------------------------------------

}
