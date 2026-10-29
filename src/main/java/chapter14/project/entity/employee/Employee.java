package chapter14.project.entity.employee;

import chapter14.project.api.Util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents an employee with details such as name, job title, years of experience, birth date, and information to 
 * his/her working schedule including weekly work days, and weekly days off. Provides methods to access and modify these 
 * employee's information.
 */
public class Employee implements Worker, Comparable<Employee> {
  
  private UUID uuid;
  private String name;
  private JobTitle jobTitle;
  private int yearsOfExperience;
  private LocalDate birthDate;
  private LocalDate[] weeklyWorkDays;
  private LocalDate[] weeklyDaysOff;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Employee instance with personal and professional details: name, job title, years of experience, 
   * and birthdate.
   * @param name the name of the employee.
   * @param jobTitle the job title of the employee, (see {@link JobTitle} for possible values).
   * @param yearsOfExperience the number of years of experience the employee has.
   * @param birthDate the birthdate of the employee.
   */
  public Employee(String name, JobTitle jobTitle, int yearsOfExperience, LocalDate birthDate) {
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.jobTitle = jobTitle;
    setYearsOfExperience(yearsOfExperience);
    this.birthDate = birthDate;
    setWeeklyWorkDays(null);    // default working days based on job titles
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the unique identifier (UUID) of the employee as a string.
   * @return the UUID of the employee.
   */
  public String getUuid() {
    return uuid.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the name of the employee.
   * @return the name of the employee.
   */
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the name of the employee.
   * @param name the new name of the employee.
   */
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the job title of the employee.
   * @return the job title of the employee, see {@link JobTitle} for possible values.
   */
  public JobTitle getJobTitle() {
    return jobTitle;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the job title of the employee.
   * @param jobTitle the new job title of the employee, see {@link JobTitle} for possible values.
   */
  public void setJobTitle(JobTitle jobTitle) {
    this.jobTitle = jobTitle;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the number of years of experience the employee has.
   * @return the number of years of experience.
   */
  public int getYearsOfExperience() {
    return yearsOfExperience;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the number of years of experience the employee has.
   * @param yearsOfExperience the new number of years of experience.
   */
  public void setYearsOfExperience(int yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience >= 0 ? yearsOfExperience : -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the birthdate of the employee.
   * @return the birthdate of the employee.
   */
  public LocalDate getBirthDate() {
    return birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the birthdate of the employee.
   * @param birthDate the new birthdate of the employee.
   */
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Calculates and returns the age of the employee based on the birthdate and the current date.
   * @return the age of the employee in years.
   */
  public int getAge() {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the weekly work days of the employee.
   * @return the weekly work days of the employee.
   */
  public LocalDate[] getWeeklyWorkDays() {
    return weeklyWorkDays;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the weekly work days of the employee.
   * @param weeklyWorkDays the new weekly work days of the employee.
   */
  public void setWeeklyWorkDays(LocalDate[] weeklyWorkDays) {
    this.weeklyWorkDays = (weeklyWorkDays == null || weeklyWorkDays.length == 0) ?
      workingDays(getJobTitle().getWorkingDays(), LocalDate.now()) : weeklyWorkDays;
      
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the weekly days off of the employee.
   * @return the weekly days off of the employee.
   */
  public LocalDate[] getWeeklyDaysOff() {
    return weeklyDaysOff;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the weekly days off of the employee.
   * @param weeklyDaysOff the new weekly days off of the employee.
   */
  public void setWeeklyDaysOff(LocalDate[] weeklyDaysOff) {
    this.weeklyDaysOff = weeklyDaysOff;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this employee to the specified object. The result is true if and only if the argument is not null and is 
   * an Employee object that has the same name, job title, years of experience, and birth date as this employee.
   * @param o the object to compare this Employee against.
   * @return true if the given object represents an Employee equivalent to this employee, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    
    Employee that = (Employee) o;
    return (yearsOfExperience == that.yearsOfExperience &&
        Objects.equals(name, that.name) &&
        jobTitle == that.jobTitle &&
        birthDate.isEqual(that.birthDate)
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a hash code value for the employee. The hash code is computed based on the employee's name, job title, 
   * years of experience, and birth date.
   * @return a hash code value for this employee.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, jobTitle, yearsOfExperience, birthDate);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of the employee. The string includes the employee's job title, name, responsibility,
   * years of experience, and birth date.
   * @return a string representation of this employee.
   */
  @Override
  public String toString() {
    return jobTitle.getTitle() + " [" +
        "name=" + name +
        ", responsibility=" + jobTitle.getDescription() +
        ", yearsOfExperience=" + yearsOfExperience +
        ", birthDate=" + birthDate.format(Util.DATE_FORMAT) +
        ", age=" + getAge() +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns an array of LocalDate objects representing the working days for the employee based on the specified week days and starting date.
   * @param weekDays an array of DayOfWeek representing the working days of the week.
   * @param date the starting date to calculate the working days.
   * @return an array of LocalDate objects corresponding to the working days.
   */
  @Override
  public LocalDate[] workingDays(DayOfWeek[] weekDays, LocalDate date) {
    LocalDate[] dates = new LocalDate[weekDays.length];
    
    // convert DayOfWeek to LocalDate for the current weeks
    for (int i = 0; i < weekDays.length; i++) {
      dates[i] = date.with(weekDays[i]);
    }
    return dates;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns an array of LocalDate objects representing the days off for the employee based on the specified starting
   * day and number of days.
   * @param from the starting day of the days off.
   * @param numberOfDays the number of days off.
   * @return an array of LocalDate objects corresponding to the days off, or null if the number of days off exceeds the 
   * weekly working days or the working week.
   */
  @Override
  public LocalDate[] daysOff(DayOfWeek from, int numberOfDays) {
    if (numberOfDays > getJobTitle().getWorkingDays().length) {
      System.out.println("Number of days off exceeds weekly working days");
      return null;
    }
    
    LocalDate[] daysOffPeriod = new LocalDate[2];
    
    daysOffPeriod[0] = LocalDate.now().with(from); // start of days off
    daysOffPeriod[1] = daysOffPeriod[0].plusDays(numberOfDays - 1); // end of days off
    LocalDate endDate = LocalDate.now().with(getJobTitle().getWorkingDays()[getJobTitle().getWorkingDays().length - 1]);
    if (daysOffPeriod[1].isAfter(endDate)) {
      System.out.println("Days off exceed the working week");
      return null;
    }
    return daysOffPeriod;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this employee with the specified employee for order based on their names.
   * @param other the employee to be compared.
   * @return a negative integer, zero, or a positive integer as this employee's name is less than, equal to, or greater 
   * than the specified employee's name.
   */
  @Override
  public int compareTo(Employee other) {
    if (other == null) return 1;
    
    return this.name.compareTo(other.name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
