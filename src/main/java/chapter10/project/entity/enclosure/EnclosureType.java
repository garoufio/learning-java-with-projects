package chapter10.project.entity.enclosure;

import chapter10.project.entity.dinosaur.DinosaurSpecies;
import chapter10.project.entity.employee.JobTitle;

import java.util.List;

public enum EnclosureType {
  RAPTORS_PARK(
      List.of(DinosaurSpecies.TYRANNOSAURUS, DinosaurSpecies.VELOCIRAPTOR, DinosaurSpecies.TRICERATOPS),
      List.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.ZOOLOGIST,
          JobTitle.VETERINARIAN, JobTitle.SECURITY_OFFICER, JobTitle.GENERAL_DIRECTOR,
          JobTitle.EDUCATOR
      )
  ),
  FLYING_CAGE(
      List.of(DinosaurSpecies.PTEROSAUR),
      List.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.ZOOLOGIST,
          JobTitle.VETERINARIAN, JobTitle.SECURITY_OFFICER, JobTitle.GENERAL_DIRECTOR,
          JobTitle.EDUCATOR
      )
  ),
  POOL_PARK(
      List.of(DinosaurSpecies.PLIOSAURS),
      List.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.ZOOLOGIST,
          JobTitle.VETERINARIAN, JobTitle.SECURITY_OFFICER, JobTitle.GENERAL_DIRECTOR,
          JobTitle.EDUCATOR
      )
  ),
  FREE_PARK(
      List.of(
          DinosaurSpecies.STEGOSAURUS, DinosaurSpecies.BRACHIOSAURUS, DinosaurSpecies.SPINOSAURUS,
          DinosaurSpecies.PARASAUROLOPHUS, DinosaurSpecies.ANKYLOSAURUS
      ),
      List.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.ZOOLOGIST,
          JobTitle.VETERINARIAN, JobTitle.SECURITY_OFFICER, JobTitle.GENERAL_DIRECTOR,
          JobTitle.EDUCATOR
      )
  ),
  CENTRAL_BUILDING(
      List.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.VETERINARIAN, JobTitle.VETERINARY_TECHNICIAN,
          JobTitle.GENERAL_DIRECTOR, JobTitle.OPERATIONS_DIRECTOR, JobTitle.HR_MANAGER, JobTitle.HR_ASSISTANT,
          JobTitle.TICKETS_MANAGER, JobTitle.EVENTS_MANAGER, JobTitle.PUBLIC_RELATIONS_MANAGER, JobTitle.FINANCE_MANAGER,
          JobTitle.EDUCATOR, JobTitle.ZOOLOGIST, JobTitle.SECURITY_OFFICER, JobTitle.PARK_MANAGER
      )
  ),
  TICKETS_KIOSK(
      List.of(
          JobTitle.GENERAL_DIRECTOR, JobTitle.HR_MANAGER, JobTitle.HR_ASSISTANT,
          JobTitle.TICKETS_MANAGER, JobTitle.EVENTS_MANAGER, JobTitle.PUBLIC_RELATIONS_MANAGER,
          JobTitle.SECURITY_OFFICER
      )
  ),
  VET_CENTER(
      List.of(
          JobTitle.GENERAL_DIRECTOR, JobTitle.OPERATIONS_DIRECTOR, JobTitle.HR_MANAGER, JobTitle.HR_ASSISTANT,
          JobTitle.VETERINARIAN, JobTitle.VETERINARY_TECHNICIAN, JobTitle.CURATOR, JobTitle.ZOOKEEPER,
          JobTitle.ZOOLOGIST, JobTitle.SECURITY_OFFICER
      )
  ),
  FOOD_STORE(List.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.VETERINARIAN, JobTitle.ZOOLOGIST,
          JobTitle.GENERAL_DIRECTOR, JobTitle.SECURITY_OFFICER
      )
  ),
  PARKING_LOT(
      List.of(
          JobTitle.CURATOR, JobTitle.ZOOKEEPER, JobTitle.VETERINARIAN, JobTitle.VETERINARY_TECHNICIAN,
          JobTitle.GENERAL_DIRECTOR, JobTitle.OPERATIONS_DIRECTOR, JobTitle.HR_MANAGER, JobTitle.HR_ASSISTANT,
          JobTitle.TICKETS_MANAGER, JobTitle.EVENTS_MANAGER, JobTitle.PUBLIC_RELATIONS_MANAGER, JobTitle.FINANCE_MANAGER,
          JobTitle.EDUCATOR, JobTitle.ZOOLOGIST, JobTitle.SECURITY_OFFICER, JobTitle.PARK_MANAGER
      )
  ),
  SURVEILLANCE_BUILDING(List.of(JobTitle.GENERAL_DIRECTOR, JobTitle.SECURITY_OFFICER, JobTitle.OPERATIONS_DIRECTOR));
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<DinosaurSpecies> dinosaurSpecies;
  private List<JobTitle> employees;

  //-------------------------------------------------------------------------------------------------------------------
  
  EnclosureType(List<JobTitle> employees) {
    this(List.of(), employees);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  EnclosureType(List<DinosaurSpecies> dinosaurSpecies, List<JobTitle> employees) {
    this.dinosaurSpecies = dinosaurSpecies;
    this.employees = employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<DinosaurSpecies> getDinosaurSpecies() {
    return dinosaurSpecies;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<JobTitle> getEmployeeJobTitles() {
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
