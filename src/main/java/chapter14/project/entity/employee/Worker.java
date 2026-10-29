package chapter14.project.entity.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Represents a worker with methods to determine working days and days off.
 */
public interface Worker {
  
  /**
   * Returns an array of working days for the given week days and date.
   * @param weekDays the days of the week the worker is scheduled to work.
   * @param date the reference date to determine the working days.
   * @return an array of LocalDate representing the working days based on the given week days and date.
   */
  LocalDate[] workingDays(DayOfWeek[] weekDays, LocalDate date);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns an array of days off for the worker starting from the specified day of the week and for the given number of days.
   * @param from the starting day of the week for the days off.
   * @param numberOfDays the number of days off to generate.
   * @return an array of LocalDate representing the days off based on the given starting day and number of days.
   */
  LocalDate[] daysOff(DayOfWeek from, int numberOfDays);
  
  //-------------------------------------------------------------------------------------------------------------------

}
