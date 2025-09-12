package chapter8;

import java.util.Objects;

public class Employee {
  
  private String name;
  private String jobTitle;
  private int yearsOfExperience;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee(String name, String jobTitle, int yearsOfExperience) {
    this.name = name;
    this.jobTitle = jobTitle;
    this.yearsOfExperience = yearsOfExperience;
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
  
  public String getJobTitle() {
    return jobTitle;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getYearsOfExperience() {
    return yearsOfExperience;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setYearsOfExperience(int yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return yearsOfExperience == employee.yearsOfExperience &&
        Objects.equals(name, employee.name) &&
        Objects.equals(jobTitle, employee.jobTitle);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, jobTitle, yearsOfExperience);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Employee [name=" + name + ", jobTitle=" + jobTitle + ", yearsOfExperience=" + yearsOfExperience + "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
