package chapter9.project.entity.enclosure;

import chapter9.project.App;
import chapter9.project.entity.dinosaur.Dinosaur;
import chapter9.project.entity.employee.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Enclosure {
  
  private EnclosureType enclosureType;
  private SafetyLevel safetyLevel;
  private List<Dinosaur> dinosaurs;
  private List<Employee> employees;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure(EnclosureType enclosureType, SafetyLevel safetyLevel, List<Dinosaur> dinosaurs, List<Employee> employees) {
    this.enclosureType = enclosureType;
    this.safetyLevel = safetyLevel;
    setDinosaurs(dinosaurs);
    setEmployees(employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EnclosureType getEnclosureType() {
    return enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setEnclosureType(EnclosureType enclosureType) {
    this.enclosureType = enclosureType;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Dinosaur> getDinosaurs() {
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDinosaurs(List<Dinosaur> dinosaurs) {
    this.dinosaurs = (dinosaurs == null ? new ArrayList<>() : dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  public List<Employee> getEmployees() {
    return this.employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setEmployees(List<Employee> employees) {
    this.employees = (employees == null ? new ArrayList<>() : employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean addEmployee(Employee employee) {
    if (this.employees == null || employee == null) return false;
    if (this.employees.contains(employee)) {
      System.out.printf("Employee '%s' already exists in this enclosure\n", employee);
      return false;
    }
    if (this.employees.size() == App.MAX_EMPLOYEES) {
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
  
  public SafetyLevel getSafetyLevel() {
    return safetyLevel;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void  setSafetyLevel(SafetyLevel safetyLevel) {
    this.safetyLevel = safetyLevel;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private boolean compareDinosaurLists(List<Dinosaur> dinosaurs) {
    if (this.dinosaurs == null || dinosaurs == null) return false;
    if (this.dinosaurs.size() != dinosaurs.size()) return false;
    
    for (Dinosaur dinosaur : dinosaurs) {
      if (!this.dinosaurs.contains(dinosaur)) return false;
    }
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private boolean compareEmployeeLists(List<Employee> employees) {
    if (this.employees == null || employees == null) return false;
    if (this.employees.size() != employees.size()) return false;
    
    for (Employee employee : employees) {
      if (!this.employees.contains(employee)) return false;
    }
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
  
  @Override
  public int hashCode() {
    return Objects.hash(enclosureType, safetyLevel, dinosaurs, employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enclosure [type=").append(enclosureType.name()).append(", [safetyLevel=").append(safetyLevel.name());
    if (dinosaurs != null) {
      sb.append(", \n\tdinosaurs:\n");
      for (Dinosaur d : dinosaurs) {
        if (d != null) sb.append("\t").append(d).append("\n");
      }
    }
    if (employees != null) {
      sb.append(", \n\temployees:\n");
      for (Employee e : employees) {
        if (e != null) sb.append("\t").append(e).append("\n");
      }
    }
    sb.append("]");
    return sb.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
