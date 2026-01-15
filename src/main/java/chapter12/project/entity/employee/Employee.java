package chapter12.project.entity.employee;

import chapter12.project.api.Util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Employee implements Worker {
  
  private UUID uuid;
  private String name;
  private JobTitle jobTitle;
  private int yearsOfExperience;
  private LocalDate birthDate;
  private LocalDate[] weeklyWorkDays;
  private LocalDate[] weeklyDaysOff;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee(String name, JobTitle jobTitle, int yearsOfExperience, LocalDate birthDate) {
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.jobTitle = jobTitle;
    setYearsOfExperience(yearsOfExperience);
    this.birthDate = birthDate;
    setWeeklyWorkDays(null);    // default working days based on job titles
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getUuid() {
    return uuid.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public JobTitle getJobTitle() {
    return jobTitle;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setJobTitle(JobTitle jobTitle) {
    this.jobTitle = jobTitle;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getYearsOfExperience() {
    return yearsOfExperience;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setYearsOfExperience(int yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience >= 0 ? yearsOfExperience : -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalDate getBirthDate() {
    return birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalDate[] getWeeklyWorkDays() {
    return weeklyWorkDays;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setWeeklyWorkDays(LocalDate[] weeklyWorkDays) {
    this.weeklyWorkDays = (weeklyWorkDays == null || weeklyWorkDays.length == 0) ?
      workingDays(getJobTitle().getWorkingDays(), LocalDate.now()) : weeklyWorkDays;
      
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalDate[] getWeeklyDaysOff() {
    return weeklyDaysOff;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setWeeklyDaysOff(LocalDate[] weeklyDaysOff) {
    this.weeklyDaysOff = weeklyDaysOff;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(name, jobTitle, yearsOfExperience, birthDate);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return jobTitle.getTitle() + " [" +
        "name=" + name +
        ", responsibility=" + jobTitle.getDescription() +
        ", yearsOfExperience=" + yearsOfExperience +
        ", birthDate=" + birthDate.format(Util.DATE_FORMAT) +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
}
