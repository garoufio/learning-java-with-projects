package chapter9.project;

import java.util.Objects;
import java.util.UUID;

public sealed class Employee permits Curator, ParkManager, Vet {
  
  private UUID uuid;
  private String name;
  private JobTitle jobTitle;
  private int yearsOfExperience;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee(String name, JobTitle jobTitle, int yearsOfExperience) {
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.jobTitle = jobTitle;
    setYearsOfExperience(yearsOfExperience);
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
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    
    Employee employee = (Employee) o;
    return (yearsOfExperience == employee.yearsOfExperience &&
        Objects.equals(name, employee.name) &&
        jobTitle == employee.jobTitle);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, jobTitle, yearsOfExperience);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Employee [name=" + name + ", jobTitle=" + jobTitle.name() + ", yearsOfExperience=" + yearsOfExperience + "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
