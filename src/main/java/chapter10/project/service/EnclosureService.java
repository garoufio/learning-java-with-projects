package chapter10.project.service;

import chapter10.project.App;
import chapter10.project.entity.dinosaur.Dinosaur;
import chapter10.project.entity.dinosaur.DinosaurSpecies;
import chapter10.project.entity.dinosaur.DinosaurType;
import chapter10.project.entity.employee.Employee;
import chapter10.project.entity.employee.JobTitle;
import chapter10.project.entity.enclosure.Enclosure;
import chapter10.project.entity.enclosure.EnclosureType;
import chapter10.project.entity.enclosure.SafetyLevel;

import java.util.ArrayList;
import java.util.List;

public class EnclosureService {
  
  private List<Enclosure> enclosures;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public EnclosureService(List<Enclosure> enclosures) {
    this.enclosures = (enclosures == null ? new ArrayList<>() : enclosures);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEnclosure(Enclosure... enclosures) {
    if (this.enclosures == null || enclosures == null || enclosures.length == 0) {
      System.out.println("No enclosures were added");
      return;
    }
    
    if (this.enclosures.size() == App.MAX_ENCLOSURES) {
      System.out.println("No more enclosures can be added as maximum number of enclosures has been reached");
      return;
    }
    for (int i = 0; i < enclosures.length; i++) {
      if (enclosures[i] != null) {
        if (this.enclosures.size() < App.MAX_ENCLOSURES) {
          this.enclosures.add(enclosures[i]);
          System.out.printf("Enclosure added '%s'\n", enclosures[i]);
        } else {
          System.out.printf("Maximum number of enclosures has been reached. '%d' enclosures were added\n", i);
          break;
        }
      } else {
        System.out.printf("Invalid enclosure at index '%d'\n", i);
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getAllEnclosures() {
    return this.enclosures == null ? List.of() : List.copyOf(this.enclosures);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getEnclosure(Enclosure enclosure) {
    if (this.enclosures == null || enclosure == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e != null && e.equals(enclosure)) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getEnclosure(EnclosureType enclosureType) {
    if (this.enclosures == null || enclosureType == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e != null && e.getEnclosureType() == enclosureType) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getEnclosure(SafetyLevel safetyLevel) {
    if (this.enclosures == null || this.enclosures.isEmpty() || safetyLevel == null) return List.of();
    
    List<Enclosure> enclosures = new ArrayList<>();
    for (Enclosure e : this.enclosures) {
      if (e != null && e.getSafetyLevel() == safetyLevel) enclosures.add(e);
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getEnclosure(String name, boolean isDinosaur) {
    if (this.enclosures == null || this.enclosures.isEmpty() || name == null) return List.of();
    
    List<Enclosure> enclosures = new ArrayList<>();
    if (isDinosaur) {
      for (Enclosure e : this.enclosures) {
        if (e == null) continue;
        List<Dinosaur> dinosaurs = e.getDinosaurs();
        for (Dinosaur d : dinosaurs) {
          if (d != null && d.getName().equals(name)) enclosures.add(e);
        }
      }
    }
    else {
      for (Enclosure e : this.enclosures) {
        if (e == null) continue;
        List<Employee> employees = e.getEmployees();
        for (Employee empl : employees) {
          if (empl != null && empl.getName().equals(name)) enclosures.add(e);
        }
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getEnclosure(Dinosaur dinosaur) {
    if (this.enclosures == null || this.enclosures.isEmpty() || dinosaur == null) return List.of();
    
    List<Enclosure> enclosures = new ArrayList<>();
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      List<Dinosaur> dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.equals(dinosaur)) enclosures.add(e);
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getEnclosure(DinosaurType dinosaurType) {
    if (this.enclosures == null || this.enclosures.isEmpty() || dinosaurType == null) return List.of();
    
    List<Enclosure> enclosures = new ArrayList<>();
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      List<Dinosaur> dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.getType() == dinosaurType) enclosures.add(e);
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getEnclosure(DinosaurSpecies dinosaurSpecies) {
    if (this.enclosures == null || this.enclosures.isEmpty() || dinosaurSpecies == null) return List.of();
    
    List<Enclosure> enclosures = new ArrayList<>();
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      List<Dinosaur> dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null && d.getSpecies() == dinosaurSpecies) enclosures.add(e);
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getEnclosure(Employee employee) {
    if (this.enclosures == null || this.enclosures.isEmpty() || employee == null) return List.of();
  
    List<Enclosure> enclosures = new ArrayList<>();
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      List<Employee> employees = e.getEmployees();
      for (Employee empl : employees) {
        if (empl != null && empl.equals(employee)) enclosures.add(e);
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getEnclosure(JobTitle jobTitle) {
    if (this.enclosures == null || this.enclosures.isEmpty() || jobTitle == null) return List.of();
    
    List<Enclosure> enclosures = new ArrayList<>();
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      List<Employee> employees = e.getEmployees();
      for (Employee empl : employees) {
        if (empl != null && empl.getJobTitle() == jobTitle) enclosures.add(e);
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getEnclosure(
      EnclosureType enclosureType,
      DinosaurType dinosaurType,
      DinosaurSpecies dinosaurSpecies,
      String dinosaurName
  ) {
    if (this.enclosures == null ||
        dinosaurName == null ||
        dinosaurType == null ||
        dinosaurSpecies == null
    ) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      if (e.getEnclosureType() != enclosureType) continue;
      
      List<Dinosaur> dinosaurs = e.getDinosaurs();
      for (Dinosaur d : dinosaurs) {
        if (d != null &&
            d.getName().equals(dinosaurName) &&
            d.getType().equals(dinosaurType) &&
            d.getSpecies().equals(dinosaurSpecies)
        ) return e;
      }
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<Enclosure> getEnclosure(EnclosureType enclosureType, JobTitle jobTitle, String employeeName) {
    if (this.enclosures == null ||
        this.enclosures.isEmpty() ||
        enclosureType == null ||
        jobTitle == null ||
        employeeName == null
    ) return List.of();
    
    List<Enclosure> enclosures = new ArrayList<>();
    for (Enclosure e : this.enclosures) {
      if (e == null) continue;
      
      List<Employee> employees = e.getEmployees();
      for (Employee empl : employees) {
        if (empl != null &&
            empl.getName().equals(employeeName) &&
            empl.getJobTitle() == jobTitle
        ) enclosures.add(e);
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEnclosure(Enclosure enclosure) {
    if (this.enclosures == null || enclosure == null) return false;
    
    Enclosure e = getEnclosure(enclosure);
    if (e == null) return false;
    
    return this.enclosures.remove(e);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEnclosure(EnclosureType enclosureType) {
    return removeEnclosure(getEnclosure(enclosureType));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaurFromEnclosure(Enclosure enclosure, Dinosaur dinosaur) {
    if (this.enclosures == null || enclosure == null || dinosaur == null) return false;
    
    // check if the enclosure exists
    Enclosure e = getEnclosure(enclosure);
    if (e == null) return false;
    
    List<Dinosaur> dinosaurs = e.getDinosaurs();
    for (Dinosaur d : dinosaurs) {
      if (d != null && d.equals(dinosaur)) return dinosaurs.remove(d);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEmployeeFromEnclosure(Enclosure enclosure, Employee employee) {
    if (this.enclosures == null || enclosure == null || employee == null) return false;
    
    // check if the enclosure exists
    Enclosure e = getEnclosure(enclosure);
    if (e == null) return false;
    
    List<Employee> employees = e.getEmployees();
    for (Employee empl : employees) {
      if (empl != null && empl.equals(employee)) return employees.remove(empl);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
