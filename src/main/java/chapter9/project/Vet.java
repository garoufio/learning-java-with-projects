package chapter9.project;

import java.util.Objects;

public non-sealed class Vet extends Employee {

  private String specialty;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Vet(String name, int yearsOfExperience, String specialty) {
    super(name, JobTitle.VETERINARIAN, yearsOfExperience);
    this.specialty = specialty;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getSpecialty() {
    return specialty;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    
    Employee employee = (Employee) o;
    if (employee instanceof Vet v) return specialty.equals(v.specialty);
    
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getYearsOfExperience(), specialty);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Veterinarian: [" +
        "name=" + getName() + ", " +
        "jobTitle=" + getJobTitle().name() +
        ", yearsOfExperience=" + getYearsOfExperience() +
        ",  specialty=" + specialty +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
