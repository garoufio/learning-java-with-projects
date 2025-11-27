package chapter10.project.entity.employee;

public enum JobTitle {
  CURATOR("Manages specific animal collections, plant collections, or exhibits"),
  ZOOKEEPER("Feeds, cleans enclosures, monitors health, provides enrichment"),
  VETERINARIAN("Provides medical care and health services for zoo animals"),
  VETERINARY_TECHNICIAN("Assists the veterinarian with treatments and animal care"),
  GENERAL_DIRECTOR("Oversees the zoo's overall operations and management"),
  OPERATIONS_DIRECTOR("Oversees the events planning, the marketing strategy and the employees schedule"),
  HR_MANAGER("Oversees the employees"),
  HR_ASSISTANT("Organizes the employees schedule"),
  TICKETS_MANAGER("Operates and oversees the ticketing system of the zoo"),
  EVENTS_MANAGER("Organizes and manages zoo events and activities"),
  PUBLIC_RELATIONS_MANAGER("Handles the zoo's public image and communications"),
  FINANCE_MANAGER("Manages the financial aspects of the zoo"),
  EDUCATOR("Leads educational tours and programs for the public"),
  ZOOLOGIST("Studies and works to conserve species and their habitats"),
  SECURITY_OFFICER("Responsible for the overall security of the park"),
  SECURITY_MANAGER("Oversees the security operations and personnel within the zoo"),
  PARK_MANAGER("Responsible for the correct parking of the cars in the parking area"),
  CLEANING_STAFF("Maintains cleanliness and hygiene throughout the zoo premises"),
  MAINTENANCE_STAFF("Handles repairs and maintenance of zoo facilities and infrastructure");

  //-------------------------------------------------------------------------------------------------------------------

  private String description;

  //-------------------------------------------------------------------------------------------------------------------

  JobTitle(String description) {
    this.description = description;
  }

  //-------------------------------------------------------------------------------------------------------------------

  public String getDescription() {
    return description;
  }

  //-------------------------------------------------------------------------------------------------------------------

}
