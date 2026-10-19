package chapter13.project.entity.enclosure;

import chapter13.project.App;
import chapter13.project.entity.dinosaur.Dinosaur;
import chapter13.project.entity.employee.Employee;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents an enclosure in the park, containing dinosaurs and employees, with specific safety and security levels.
 * This class provides methods to manage the dinosaurs and employees within the enclosure, ensuring that the enclosure's 
 * capacity and type constraints are respected.
 */
public class Enclosure implements Comparable<Enclosure> {
  
  private EnclosureType enclosureType;
  private SafetyLevel safetyLevel;
  private Set<Dinosaur> dinosaurs;
  private Set<Employee> employees;
  private int securityLevel;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs an Enclosure with the specified type, safety level, dinosaurs, employees, and security level.
   * @param enclosureType the type of the enclosure.
   * @param safetyLevel the safety level of the enclosure, see {@link SafetyLevel} for possible values.
   * @param dinosaurs the set of dinosaurs contained in the enclosure.
   * @param employees the set of employees assigned to the enclosure.
   * @param securityLevel the security level of the enclosure.
   */
  public Enclosure(
      EnclosureType enclosureType,
      SafetyLevel safetyLevel,
      Set<Dinosaur> dinosaurs,
      Set<Employee> employees,
      int securityLevel
  ) {
    this.enclosureType = enclosureType;
    this.safetyLevel = safetyLevel;
    setDinosaurs(dinosaurs);
    setEmployees(employees);
    setSecurityLevel(securityLevel);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs an Enclosure with the specified type, safety level, dinosaurs, and employees, using a default security 
   * level of 100.
   * @param enclosureType the type of the enclosure.
   * @param safetyLevel the safety level of the enclosure, see {@link SafetyLevel} for possible values.
   * @param dinosaurs the set of dinosaurs contained in the enclosure.
   * @param employees the set of employees assigned to the enclosure.
   */
  public Enclosure(
      EnclosureType enclosureType,
      SafetyLevel safetyLevel,
      Set<Dinosaur> dinosaurs,
      Set<Employee> employees
  ) {
    this(enclosureType, safetyLevel, dinosaurs, employees, 100);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the type of the enclosure.
   * @return the type of the enclosure, see {@link EnclosureType} for possible values.
   */
  public EnclosureType getEnclosureType() {
    return enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the type of the enclosure.
   * @param enclosureType the type of the enclosure, see {@link EnclosureType} for possible values.
   */
  public void setEnclosureType(EnclosureType enclosureType) {
    this.enclosureType = enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the set of dinosaurs contained in the enclosure.
   * @return the set of dinosaurs contained in the enclosure.
   */
  public Set<Dinosaur> getDinosaurs() {
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the set of dinosaurs contained in the enclosure.
   * @param dinosaurs the set of dinosaurs contained in the enclosure.
   */
  public void setDinosaurs(Set<Dinosaur> dinosaurs) {
    this.dinosaurs = (dinosaurs == null ? new HashSet<>() : dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds a dinosaur to the enclosure.
   * @param dinosaur the dinosaur to be added.
   * @return true if the dinosaur was successfully added, false otherwise.
   */
  public boolean addDinosaur(Dinosaur dinosaur) {
    if (this.dinosaurs == null || dinosaur == null) return false;
    if (this.dinosaurs.contains(dinosaur)) {
      System.out.printf("Dinosaur '%s' already exists in this enclosure\n", dinosaur);
      return false;
    }
    if (this.dinosaurs.size() >= App.MAX_DINOSAURS) {
      System.out.println("The park cannot accept any more dinosaurs");
      return false;
    }
    
    if (!this.enclosureType.getDinosaurSpecies().contains(dinosaur.getSpecies())) {
      System.out.println("The enclosure cannot accept a dinosaur of species " + dinosaur.getSpecies());
      return false;
    }
    this.dinosaurs.add(dinosaur);
    
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Returns the set of employees working in the enclosure.
   * @return the set of employees working in the enclosure.
   */
  
  public Set<Employee> getEmployees() {
    return this.employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Sets the set of employees working in the enclosure.
   * @param employees the set of employees working in the enclosure.
   */  
  public void setEmployees(Set<Employee> employees) {
    this.employees = (employees == null ? new HashSet<>() : employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds an employee to the enclosure.
   * @param employee the employee to be added.
   * @return true if the employee was successfully added, false otherwise.
   */
  public boolean addEmployee(Employee employee) {
    if (this.employees == null || employee == null) return false;
    if (this.employees.contains(employee)) {
      System.out.printf("Employee '%s' already exists in this enclosure\n", employee);
      return false;
    }
    if (this.employees.size() >= App.MAX_EMPLOYEES) {
      System.out.println("The park cannot accept any more employees");
      return false;
    }
    
    if (!this.enclosureType.getEmployeeJobTitles().contains(employee.getJobTitle())) {
      System.out.println("The enclosure cannot accept an employee with job title " + employee.getJobTitle());
      return false;
    }
    this.employees.add(employee);
    
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the safety level of the enclosure.
   * @return the safety level of the enclosure.
   */
  public SafetyLevel getSafetyLevel() {
    return safetyLevel;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the safety level of the enclosure.
   * @param safetyLevel the safety level to be set.
   */
  public void  setSafetyLevel(SafetyLevel safetyLevel) {
    this.safetyLevel = safetyLevel;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares the given set of dinosaurs with the dinosaurs in the enclosure. If the sets do not contain exactly the same 
   * dinosaurs, it returns false.
   * @param dinosaurs the set of dinosaurs to be compared.
   * @return true if the sets are equal, false otherwise.
   */
  private boolean compareDinosaurLists(Set<Dinosaur> dinosaurs) {
    if (this.dinosaurs == null || dinosaurs == null) return false;
    if (this.dinosaurs.size() != dinosaurs.size()) return false;
    
    for (Dinosaur dinosaur : dinosaurs) {
      if (!this.dinosaurs.contains(dinosaur)) return false;
    }
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares the given set of employees with the employees in the enclosure. If the sets do not contain exactly the same 
   * employees, it returns false.
   * @param employees the set of employees to be compared.
   * @return true if the sets are equal, false otherwise.
   */
  private boolean compareEmployeeLists(Set<Employee> employees) {
    if (this.employees == null || employees == null) return false;
    if (this.employees.size() != employees.size()) return false;
    
    for (Employee employee : employees) {
      if (!this.employees.contains(employee)) return false;
    }
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the security level of the enclosure.
   * @return the security level.
   */
  public int getSecurityLevel() {
    return securityLevel;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the security level of the enclosure. The value must be between 0 and 100, inclusive. If the value is outside
   * this range, it will be set to -1.
   * @param securityLevel the security level to be set.
   */
  public void setSecurityLevel(int securityLevel) {
    this.securityLevel = (securityLevel >= 0 && securityLevel <= 100) ? securityLevel : -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this enclosure with the specified object for equality. Two enclosures are considered equal if they have 
   * the same enclosure type, safety level, and contain exactly the same dinosaurs and employees.
   * @param o the object to be compared for equality with this enclosure.
   * @return true if the specified object is equal to this enclosure, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Enclosure enclosure = (Enclosure) o;
    
    return enclosureType == enclosure.enclosureType &&
        safetyLevel == enclosure.safetyLevel &&
        compareDinosaurLists(enclosure.dinosaurs) &&
        compareEmployeeLists(enclosure.employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the hash code value for this enclosure. The hash code is computed based on the enclosure type, safety level,
   * dinosaurs, and employees.
   * @return the hash code value for this enclosure.
   */
  @Override
  public int hashCode() {
    return Objects.hash(enclosureType, safetyLevel, dinosaurs, employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a string representation of this enclosure, including its type, safety level, security level,
   * dinosaurs, and employees.
   * @return a string representation of this enclosure.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enclosure [type=").append(enclosureType.name())
        .append(", safetyLevel=").append(safetyLevel.name())
        .append(", securityLevel=").append(securityLevel);
    if (!(dinosaurs == null || dinosaurs.isEmpty())) {
      sb.append("\n\tDinosaurs:\n");
      for (Dinosaur d : dinosaurs) {
        if (d != null) sb.append("\t").append(d).append("\n");
      }
    }
    if (!(employees == null || employees.isEmpty())) {
      sb.append("\n\tEmployees:\n");
      for (Employee e : employees) {
        if (e != null) sb.append("\t").append(e).append("\n");
      }
    }
    sb.append("]");
    return sb.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares this enclosure with the specified enclosure for order. The comparison is based on the enclosure type.
   * @param other the enclosure to be compared.
   * @return a negative integer, zero, or a positive integer as this enclosure is less than, equal to, or greater than 
   * the specified enclosure.
   */
  @Override
  public int compareTo(Enclosure other) {
    if (other == null) return 1;
    
    return this.enclosureType.compareTo(other.getEnclosureType());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
