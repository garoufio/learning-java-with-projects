package chapter9.project;

import java.util.Objects;

public class ParkManager extends Employee {
  
  private boolean hasDrivingLicense;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public ParkManager(String name, int yearsOfExperience, boolean hasDrivingLicense) {
    super(name, JobTitle.PARK_MANAGER, yearsOfExperience);
    this.hasDrivingLicense = hasDrivingLicense;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    
    Employee e = (Employee) o;
    if (e instanceof ParkManager p) return hasDrivingLicense == p.hasDrivingLicense;
    
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getYearsOfExperience(), hasDrivingLicense);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "ParkManager: [" +
        ", name=" + getName() +
        ", yearsOfExperience=" + getYearsOfExperience() +
        ", hasDrivingLicense=" + hasDrivingLicense +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
