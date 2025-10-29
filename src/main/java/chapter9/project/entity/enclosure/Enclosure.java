package chapter9.project.entity.enclosure;

import chapter8.project.Project;
import chapter9.project.entity.SafetyLevel;
import chapter9.project.entity.dinosaur.Dinosaur;
import chapter9.project.entity.employee.Employee;

import java.util.Arrays;
import java.util.Objects;

public class Enclosure {
  
  private EnclosureType enclosureType;
  private SafetyLevel safetyLevel;
  private Dinosaur[] dinosaurs;
  private Employee[] employees;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure(EnclosureType enclosureType, SafetyLevel safetyLevel, Dinosaur[] dinosaurs) {
    this.enclosureType = enclosureType;
    this.safetyLevel = safetyLevel;
    this.dinosaurs = dinosaurs;
    this.employees = new Employee[Project.MAX_EMPLOYEES];
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure(EnclosureType enclosureType, SafetyLevel safetyLevel, Employee[] employees) {
    this.enclosureType = enclosureType;
    this.safetyLevel = safetyLevel;
    this.employees = employees;
    this.dinosaurs = new Dinosaur[Project.MAX_DINOSAURS];
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure(EnclosureType enclosureType, SafetyLevel safetyLevel, Dinosaur[] dinosaurs, Employee[] employees) {
    this.enclosureType = enclosureType;
    this.safetyLevel = safetyLevel;
    this.dinosaurs = dinosaurs;
    this.employees = employees;
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
  
  public Dinosaur[] getDinosaurs() {
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDinosaurs(Dinosaur... dinosaurs) {
    this.dinosaurs = dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean addDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) return false;
    if (this.dinosaurs == null) this.dinosaurs = new Dinosaur[Project.MAX_DINOSAURS];
    
    int index = -1;
    for (int i = 0; i < this.dinosaurs.length; i++) {
      if (this.dinosaurs[i] == null) {
        index = i;
        break;
      }
    }
    if (index > -1) {
      this.dinosaurs[index] = dinosaur;
    } else {
      if (this.dinosaurs.length + 1 <= Project.MAX_DINOSAURS) {
        Dinosaur[] dinosaurs = new Dinosaur[this.dinosaurs.length + 1];
        for (int i = 0; i < this.dinosaurs.length; i++) {
          dinosaurs[i] = this.dinosaurs[i];
        }
        dinosaurs[dinosaurs.length - 1] = dinosaur;
        this.dinosaurs = dinosaurs;
        return true;
      }
      else {
        System.out.println("The enclosure cannot accept any more dinosaurs.");
      }
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee[] getEmployees() {
    return this.employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setEmployees(Employee... employees) {
    this.employees = employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean addEmployee(Employee employee) {
    if (employee == null) return false;
    if (this.employees == null) this.employees = new Employee[Project.MAX_EMPLOYEES];
    
    int index = -1;
    for (int i = 0; i < this.employees.length; i++) {
      if (this.employees[i] == null) {
        index = i;
        break;
      }
    }
    if (index > -1) {
      this.employees[index] = employee;
    } else {
      if (this.employees.length + 1 <= Project.MAX_EMPLOYEES) {
        Employee[] employees = new Employee[this.employees.length + 1];
        for (int i = 0; i < this.employees.length; i++) {
          employees[i] = this.employees[i];
        }
        employees[employees.length - 1] = employee;
        this.employees = employees;
        return true;
      }
      else {
        System.out.println("The enclosure cannot accept any more employees.");
      }
    }
    return false;
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
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (this == o) return true;
    
    Enclosure enclosure = (Enclosure) o;
    return (enclosureType == enclosure.getEnclosureType() &&
        safetyLevel == enclosure.safetyLevel &&
        Arrays.deepEquals(dinosaurs, enclosure.dinosaurs) &&
        Arrays.deepEquals(employees, enclosure.employees));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(enclosureType, safetyLevel, Arrays.hashCode(dinosaurs), Arrays.hashCode(employees));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enclosure [type=" + enclosureType.name() + ", [safetyLevel=" + safetyLevel.name());
    if (dinosaurs != null) {
      sb.append(", \n\tdinosaurs:\n");
      for (Dinosaur d : dinosaurs) {
        if (d != null) sb.append("\t" + d + "\n");
      }
    }
    if (employees != null) {
      sb.append(", \n\temployees:\n");
      for (Employee e : employees) {
        if (e != null) sb.append("\t" + e + "\n");
      }
    }
    sb.append("]");
    return sb.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
