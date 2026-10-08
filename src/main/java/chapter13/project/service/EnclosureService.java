package chapter13.project.service;

import chapter13.project.App;
import chapter13.project.entity.dinosaur.Dinosaur;
import chapter13.project.entity.dinosaur.DinosaurSpecies;
import chapter13.project.entity.dinosaur.DinosaurType;
import chapter13.project.entity.employee.Employee;
import chapter13.project.entity.employee.JobTitle;
import chapter13.project.entity.enclosure.Enclosure;
import chapter13.project.entity.enclosure.EnclosureType;
import chapter13.project.entity.enclosure.SafetyLevel;

import java.util.HashSet;
import java.util.Set;

/**
 * The EnclosureService class provides methods to manage a collection of Enclosure objects. It allows adding,
 * retrieving, editing, and removing enclosures based on various criteria such as enclosure type, safety level,
 * dinosaur attributes, and employee attributes. The service ensures that the maximum number of enclosures is not
 * exceeded and provides feedback on operations performed.
 */
public class EnclosureService {
  
  private Set<Enclosure> enclosures;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs an EnclosureService with the specified set of enclosures. If the provided set is null, an empty
   * HashSet is initialized.
   * @param enclosures the set of enclosures to manage; if null, an empty set is created.
   */
  public EnclosureService(Set<Enclosure> enclosures) {
    this.enclosures = (enclosures == null ? new HashSet<>() : enclosures);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds one or more enclosures to the service. If the maximum number of enclosures has been reached, no further
   * enclosures will be added. The method provides feedback on the success or failure of each addition, including
   * handling of null values and duplicates.
   * @param enclosures the enclosures to be added; if null or empty, no action is taken.
   */
  public void addEnclosure(Enclosure... enclosures) {
    if (this.enclosures == null || enclosures == null || enclosures.length == 0) {
      System.out.println("No enclosures were added");
      return;
    }
    
    if (this.enclosures.size() >= App.MAX_ENCLOSURES) {
      System.out.println("No more enclosures can be added as maximum number of enclosures has been reached");
      return;
    }
    for (int i = 0; i < enclosures.length; i++) {
      if (enclosures[i] != null) {
        if (this.enclosures.size() < App.MAX_ENCLOSURES) {
          if (this.enclosures.add(enclosures[i])) {
            System.out.printf("Enclosure added '%s'\n", enclosures[i]);
          } else {
            System.out.printf("Enclosure already exists '%s'\n", enclosures[i]);
          }
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
  
  /**
   * Retrieves all enclosures managed by the service. If no enclosures are present, an empty set is returned.
   * @return a set of all enclosures managed by the service.
   */
  public Set<Enclosure> getAllEnclosures() {
    return this.enclosures == null ? Set.of() : Set.copyOf(this.enclosures);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a specific enclosure from the service based on the provided enclosure object. If the enclosure is not
   * found or if the input is null, null is returned.
   * @param enclosure the enclosure to be retrieved.
   * @return the enclosure if found; otherwise, null.
   */
  public Enclosure getEnclosure(Enclosure enclosure) {
    if (this.enclosures == null || enclosure == null) return null;
    
    return this.enclosures.contains(enclosure) ? enclosure : null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves an enclosure based on its type. If no matching enclosure is found or if the input is null, null is
   * returned.
   * @param enclosureType the type of the enclosure to be retrieved.
   * @return the enclosure if found; otherwise, null.
   */
  public Enclosure getEnclosure(EnclosureType enclosureType) {
    if (this.enclosures == null || enclosureType == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e != null && e.getEnclosureType() == enclosureType) return e;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of enclosures that match the specified safety level. If no matching enclosures are found or if
   * the input is null, an empty set is returned.
   * @param safetyLevel the safety level to filter enclosures by.
   * @return a set of enclosures that match the specified safety level.
   */
  public Set<Enclosure> getEnclosure(SafetyLevel safetyLevel) {
    if (this.enclosures == null || this.enclosures.isEmpty() || safetyLevel == null) return Set.of();
    
    Set<Enclosure> enclosures = new HashSet<>();
    for (Enclosure e : this.enclosures) {
      if (e != null && e.getSafetyLevel() == safetyLevel) enclosures.add(e);
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of enclosures based on the provided name and a boolean indicating whether to search for
   * dinosaurs or employees. If no matching enclosures are found or if the input is null, an empty set is returned.
   * @param name the name of the dinosaur or employee to search for.
   * @param isDinosaur a boolean indicating whether to search for dinosaurs (true) or employees (false).
   * @return a set of enclosures that contain the specified dinosaur or employee.
   */
  public Set<Enclosure> getEnclosure(String name, boolean isDinosaur) {
    if (this.enclosures == null || this.enclosures.isEmpty() || name == null) return Set.of();
    
    Set<Enclosure> enclosures = new HashSet<>();
    if (isDinosaur) {
      for (Enclosure e : this.enclosures) {
        if (e != null) {
          Set<Dinosaur> dinosaurs = e.getDinosaurs();
          for (Dinosaur d : dinosaurs) {
            if (d != null && d.getName().equals(name)) enclosures.add(e);
          }
        }
      }
    }
    else {
      for (Enclosure e : this.enclosures) {
        if (e != null) {
          Set<Employee> employees = e.getEmployees();
          for (Employee empl : employees) {
            if (empl != null && empl.getName().equals(name)) enclosures.add(e);
          }
        }
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of enclosures that contain the specified dinosaur. If no matching enclosures are found or if
   * the input is null, an empty set is returned.
   * @param dinosaur the dinosaur to search for in the enclosures.
   * @return a set of enclosures that contain the specified dinosaur.
   */
  public Set<Enclosure> getEnclosure(Dinosaur dinosaur) {
    if (this.enclosures == null || this.enclosures.isEmpty() || dinosaur == null) return Set.of();
    
    Set<Enclosure> enclosures = new HashSet<>();
    for (Enclosure e : this.enclosures) {
      if (e != null && e.getDinosaurs().contains(dinosaur)) {
        enclosures.add(e);
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of enclosures that contain dinosaurs of the specified type. If no matching enclosures are found or
   * if the input is null, an empty set is returned.
   * @param dinosaurType the type of dinosaur to search for in the enclosures.
   * @return a set of enclosures that contain dinosaurs of the specified type.
   */
  public Set<Enclosure> getEnclosure(DinosaurType dinosaurType) {
    if (this.enclosures == null || this.enclosures.isEmpty() || dinosaurType == null) return Set.of();
    
    Set<Enclosure> enclosures = new HashSet<>();
    for (Enclosure e : this.enclosures) {
      if (e != null) {
        Set<Dinosaur> dinosaurs = e.getDinosaurs();
        for (Dinosaur d : dinosaurs) {
          if (d != null && d.getType() == dinosaurType) enclosures.add(e);
        }
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of enclosures that contain dinosaurs of the specified species. If no matching enclosures are found
   * or if the input is null, an empty set is returned.
   * @param dinosaurSpecies the species of dinosaur to search for in the enclosures.
   * @return a set of enclosures that contain dinosaurs of the specified species.
   */
  public Set<Enclosure> getEnclosure(DinosaurSpecies dinosaurSpecies) {
    if (this.enclosures == null || this.enclosures.isEmpty() || dinosaurSpecies == null) return Set.of();
    
    Set<Enclosure> enclosures = new HashSet<>();
    for (Enclosure e : this.enclosures) {
      if (e != null) {
        Set<Dinosaur> dinosaurs = e.getDinosaurs();
        for (Dinosaur d : dinosaurs) {
          if (d != null && d.getSpecies() == dinosaurSpecies) enclosures.add(e);
        }
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of enclosures that contain the specified employee. If no matching enclosures are found or if
   * the input is null, an empty set is returned.
   * @param employee the employee to search for in the enclosures.
   * @return a set of enclosures that contain the specified employee.
   */
  public Set<Enclosure> getEnclosure(Employee employee) {
    if (this.enclosures == null || this.enclosures.isEmpty() || employee == null) return Set.of();
  
    Set<Enclosure> enclosures = new HashSet<>();
    for (Enclosure e : this.enclosures) {
      if (e != null && e.getEmployees().contains(employee)) {
        enclosures.add(e);
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves a set of enclosures that contain employees with the specified job title. If no matching enclosures are
   * found or if the input is null, an empty set is returned.
   * @param jobTitle the job title to search for in the enclosures.
   * @return a set of enclosures that contain employees with the specified job title.
   */
  public Set<Enclosure> getEnclosure(JobTitle jobTitle) {
    if (this.enclosures == null || this.enclosures.isEmpty() || jobTitle == null) return Set.of();
    
    Set<Enclosure> enclosures = new HashSet<>();
    for (Enclosure e : this.enclosures) {
      if (e != null) {
        Set<Employee> employees = e.getEmployees();
        for (Employee empl : employees) {
          if (empl != null && empl.getJobTitle() == jobTitle) enclosures.add(e);
        }
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves an enclosure that contains a dinosaur with the specified attributes (type, species, and name) within
   * the specified enclosure type. If no matching enclosure is found or if any of the inputs are null, null is returned.
   * @param enclosureType the type of the enclosure to search within.
   * @param dinosaurType the type of the dinosaur to search for.
   * @param dinosaurSpecies the species of the dinosaur to search for.
   * @param dinosaurName the name of the dinosaur to search for.
   * @return the enclosure that contains the specified dinosaur, or null if not found.
   */
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
      
      Set<Dinosaur> dinosaurs = e.getDinosaurs();
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
  
  /**
   * Retrieves a set of enclosures that contain employees with the specified job title and name within the specified
   * enclosure type. If no matching enclosures are found or if any of the inputs are null, an empty set is returned.
   * @param enclosureType the type of the enclosure to search within.
   * @param jobTitle the job title of the employee to search for.
   * @param employeeName the name of the employee to search for.
   * @return a set of enclosures that contain employees with the specified job title and name, or an empty set if none
   * are found.
   */
  public Set<Enclosure> getEnclosure(EnclosureType enclosureType, JobTitle jobTitle, String employeeName) {
    if (this.enclosures == null ||
        this.enclosures.isEmpty() ||
        enclosureType == null ||
        jobTitle == null ||
        employeeName == null
    ) return Set.of();
    
    Set<Enclosure> enclosures = new HashSet<>();
    for (Enclosure e : this.enclosures) {
      if (e != null) {
        Set<Employee> employees = e.getEmployees();
        for (Employee empl : employees) {
          if (empl != null &&
              empl.getName().equals(employeeName) &&
              empl.getJobTitle() == jobTitle
          ) enclosures.add(e);
        }
      }
    }
    return enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified enclosure from the service. If the enclosure does not exist or if the input is null, no
   * action is taken and false is returned. If the enclosure is successfully removed, true is returned.
   * @param enclosure the enclosure to be removed.
   * @return true if the enclosure was successfully removed; false otherwise.
   */
  public boolean removeEnclosure(Enclosure enclosure) {
    if (this.enclosures == null || enclosure == null) return false;
    
    Enclosure e = getEnclosure(enclosure);
    if (e == null) return false;
    
    return this.enclosures.remove(e);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes an enclosure of the specified type from the service. If no matching enclosure is found or if the input is
   * null, no action is taken and false is returned. If the enclosure is successfully removed, true is returned.
   * @param enclosureType the type of the enclosure to be removed.
   * @return true if the enclosure was successfully removed; false otherwise.
   */
  public boolean removeEnclosure(EnclosureType enclosureType) {
    return removeEnclosure(getEnclosure(enclosureType));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified dinosaur from the given enclosure. If the enclosure or dinosaur does not exist, or if any of
   * the inputs are null, no action is taken and false is returned. If the dinosaur is successfully removed from the
   * enclosure, true is returned.
   * @param enclosure the enclosure from which the dinosaur will be removed.
   * @param dinosaur the dinosaur to be removed.
   * @return true if the dinosaur was successfully removed; false otherwise.
   */
  public boolean removeDinosaurFromEnclosure(Enclosure enclosure, Dinosaur dinosaur) {
    if (this.enclosures == null || enclosure == null || dinosaur == null) return false;
    
    // check if the enclosure exists
    Enclosure e = getEnclosure(enclosure);
    if (e == null) return false;
    
    return e.getDinosaurs().remove(dinosaur);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified employee from the given enclosure. If the enclosure or employee does not exist, or if any of
   * the inputs are null, no action is taken and false is returned. If the employee is successfully removed from the
   * enclosure, true is returned.
   * @param enclosure the enclosure from which the employee will be removed.
   * @param employee the employee to be removed.
   * @return true if the employee was successfully removed; false otherwise.oyee will be removed.
   */
  public boolean removeEmployeeFromEnclosure(Enclosure enclosure, Employee employee) {
    if (this.enclosures == null || enclosure == null || employee == null) return false;
    
    // check if the enclosure exists
    Enclosure e = getEnclosure(enclosure);
    if (e == null) return false;
    
    return e.getEmployees().remove(employee);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
