package chapter10.project.entity.employee;

public enum JobTitle {
  CURATOR("Curator", "Manages specific animal collections, plant collections, or exhibits"),
  ZOOKEEPER("Zookeeper", "Feeds, cleans enclosures, monitors health, provides enrichment"),
  VETERINARIAN("Veterinarian", "Provides medical care and health services for zoo animals"),
  VETERINARY_TECHNICIAN("Veterinary Technician", "Manages and operates the devices in the vet center"),
  GENERAL_DIRECTOR("General Director", "Oversees the zoo's overall operations and management"),
  OPERATIONS_DIRECTOR("Operations Director", "Oversees the events planning, the marketing strategy and the employees schedule"),
  HR_MANAGER("HR Manager", "Oversees the employees"),
  HR_ASSISTANT("HR Assistant", "Organizes the employees schedule"),
  TICKETS_MANAGER("Tickets Manager", "Operates and oversees the ticketing system of the zoo"),
  EVENTS_MANAGER("Events Manager", "Organizes and manages zoo events and activities"),
  PUBLIC_RELATIONS_MANAGER("Public Relations Manager", "Handles the zoo's public image and communications"),
  FINANCE_MANAGER("Finance Manager", "Manages the financial aspects of the zoo"),
  EDUCATOR("Educator", "Leads educational tours and programs for the public"),
  ZOOLOGIST("Zoologist", "Studies and works to conserve species and their habitats"),
  SECURITY_OFFICER("Security Officer", "Responsible for the overall security of the park"),
  SECURITY_MANAGER("Security Manager", "Oversees the security operations and personnel within the zoo"),
  PARK_MANAGER("Park Manager", "Responsible for the correct parking of the cars in the parking area"),
  CLEANING_STAFF("Cleaning Staff", "Maintains cleanliness and hygiene throughout the zoo premises"),
  MAINTENANCE_STAFF("Maintenance Staff", "Handles repairs and maintenance of zoo facilities and infrastructure");

  //-------------------------------------------------------------------------------------------------------------------

  private String title;
  private String description;

  //-------------------------------------------------------------------------------------------------------------------

  JobTitle(String title, String description) {
    this.title = title;
    this.description = description;
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  public String getTitle() {
    return title;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  public String getDescription() {
    return description;
  }

  //-------------------------------------------------------------------------------------------------------------------

}
