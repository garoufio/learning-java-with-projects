package chapter13.project.entity.enclosure;

import chapter13.project.entity.dinosaur.DinosaurSpecies;
import chapter13.project.entity.employee.JobTitle;

import java.util.List;
import java.util.Set;

/**
 * The `EnclosureType` enum represents different types of enclosures in a dinosaur park. Each enclosure type has a
 * set of dinosaur species that can be housed in it and a set of job titles for employees who can work in that
 * enclosure.
 */
public enum EnclosureType {
  RAPTORS_PARK(
      Set.of(DinosaurSpecies.TYRANNOSAURUS, DinosaurSpecies.VELOCIRAPTOR, DinosaurSpecies.TRICERATOPS),
      Set.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.ZOOLOGIST,
          JobTitle.VETERINARIAN, JobTitle.SECURITY_OFFICER, JobTitle.GENERAL_DIRECTOR,
          JobTitle.EDUCATOR, JobTitle.MAINTENANCE_STAFF, JobTitle.SECURITY_MANAGER
      )
  ),
  FLYING_CAGE(
      Set.of(DinosaurSpecies.PTEROSAUR),
      Set.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.ZOOLOGIST,
          JobTitle.VETERINARIAN, JobTitle.SECURITY_OFFICER, JobTitle.GENERAL_DIRECTOR,
          JobTitle.EDUCATOR, JobTitle.MAINTENANCE_STAFF, JobTitle.SECURITY_MANAGER
      )
  ),
  POOL_PARK(
      Set.of(DinosaurSpecies.PLIOSAURS),
      Set.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.ZOOLOGIST,
          JobTitle.VETERINARIAN, JobTitle.SECURITY_OFFICER, JobTitle.GENERAL_DIRECTOR,
          JobTitle.EDUCATOR, JobTitle.MAINTENANCE_STAFF, JobTitle.SECURITY_MANAGER
      )
  ),
  FREE_PARK(
      Set.of(
          DinosaurSpecies.STEGOSAURUS, DinosaurSpecies.BRACHIOSAURUS, DinosaurSpecies.SPINOSAURUS,
          DinosaurSpecies.PARASAUROLOPHUS, DinosaurSpecies.ANKYLOSAURUS
      ),
      Set.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.ZOOLOGIST,
          JobTitle.VETERINARIAN, JobTitle.SECURITY_OFFICER, JobTitle.GENERAL_DIRECTOR,
          JobTitle.EDUCATOR, JobTitle.MAINTENANCE_STAFF, JobTitle.SECURITY_MANAGER
      )
  ),
  CENTRAL_BUILDING(
      Set.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.VETERINARIAN, JobTitle.VETERINARY_TECHNICIAN,
          JobTitle.GENERAL_DIRECTOR, JobTitle.OPERATIONS_DIRECTOR, JobTitle.HR_MANAGER, JobTitle.HR_ASSISTANT,
          JobTitle.TICKETS_MANAGER, JobTitle.EVENTS_MANAGER, JobTitle.PUBLIC_RELATIONS_MANAGER, JobTitle.FINANCE_MANAGER,
          JobTitle.EDUCATOR, JobTitle.ZOOLOGIST, JobTitle.SECURITY_OFFICER, JobTitle.PARK_MANAGER, JobTitle.CLEANING_STAFF,
          JobTitle.MAINTENANCE_STAFF, JobTitle.SECURITY_MANAGER
      )
  ),
  TICKETS_KIOSK(
      Set.of(
          JobTitle.GENERAL_DIRECTOR, JobTitle.HR_MANAGER, JobTitle.HR_ASSISTANT,
          JobTitle.TICKETS_MANAGER, JobTitle.EVENTS_MANAGER, JobTitle.PUBLIC_RELATIONS_MANAGER,
          JobTitle.SECURITY_OFFICER, JobTitle.CLEANING_STAFF, JobTitle.MAINTENANCE_STAFF,
          JobTitle.SECURITY_MANAGER
      )
  ),
  VET_CENTER(
      Set.of(
          JobTitle.GENERAL_DIRECTOR, JobTitle.OPERATIONS_DIRECTOR, JobTitle.HR_MANAGER, JobTitle.HR_ASSISTANT,
          JobTitle.VETERINARIAN, JobTitle.VETERINARY_TECHNICIAN, JobTitle.CURATOR, JobTitle.ZOOKEEPER,
          JobTitle.ZOOLOGIST, JobTitle.SECURITY_OFFICER, JobTitle.CLEANING_STAFF,
          JobTitle.MAINTENANCE_STAFF, JobTitle.SECURITY_MANAGER
      )
  ),
  FOOD_STORE(Set.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.VETERINARIAN, JobTitle.ZOOLOGIST,
          JobTitle.GENERAL_DIRECTOR, JobTitle.SECURITY_OFFICER, JobTitle.CLEANING_STAFF,
          JobTitle.MAINTENANCE_STAFF, JobTitle.SECURITY_MANAGER
      )
  ),
  PARKING_LOT(
      Set.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.VETERINARIAN, JobTitle.VETERINARY_TECHNICIAN,
          JobTitle.GENERAL_DIRECTOR, JobTitle.OPERATIONS_DIRECTOR, JobTitle.HR_MANAGER, JobTitle.HR_ASSISTANT,
          JobTitle.TICKETS_MANAGER, JobTitle.EVENTS_MANAGER, JobTitle.PUBLIC_RELATIONS_MANAGER, JobTitle.FINANCE_MANAGER,
          JobTitle.EDUCATOR, JobTitle.ZOOLOGIST, JobTitle.SECURITY_OFFICER, JobTitle.PARK_MANAGER, JobTitle.CLEANING_STAFF,
          JobTitle.MAINTENANCE_STAFF, JobTitle.SECURITY_MANAGER
      )
  ),
  SURVEILLANCE_BUILDING(
      Set.of(
          JobTitle.GENERAL_DIRECTOR, JobTitle.SECURITY_OFFICER, JobTitle.OPERATIONS_DIRECTOR, JobTitle.SECURITY_MANAGER
      )
  );
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Set<DinosaurSpecies> dinosaurSpecies;
  private Set<JobTitle> employees;

  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructor for EnclosureType enum with only a set of employees.
   * @param employees Set of JobTitle representing the employees who can work in this enclosure type.
   */
  EnclosureType(Set<JobTitle> employees) {
    this(Set.of(), employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructor for EnclosureType enum with both dinosaur species and employees set.
   * @param dinosaurSpecies Set of DinosaurSpecies representing the dinosaur species in this enclosure type.
   * @param employees Set of JobTitle representing the employees who can work in this enclosure type.
   */
  EnclosureType(Set<DinosaurSpecies> dinosaurSpecies, Set<JobTitle> employees) {
    this.dinosaurSpecies = dinosaurSpecies;
    this.employees = employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Getter for the set of dinosaur species associated with this enclosure type.
   * @return Set of DinosaurSpecies representing the dinosaur species in this enclosure type.
   */
  public Set<DinosaurSpecies> getDinosaurSpecies() {
    return dinosaurSpecies;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Getter for the set of job titles associated with this enclosure type.
   * @return Set of JobTitle representing the employees who can work in this enclosure type.
   */
  public Set<JobTitle> getEmployeeJobTitles() {
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
