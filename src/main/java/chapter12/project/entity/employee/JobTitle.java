package chapter12.project.entity.employee;

import java.time.DayOfWeek;
import java.time.LocalTime;

public enum JobTitle {
  CURATOR(
      "Curator",
      "Manages specific animal collections, plant collections, or exhibits",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  ZOOKEEPER(
      "Zookeeper",
      "Feeds, cleans enclosures, monitors health, provides enrichment",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
      },
      LocalTime.of(12, 30),
      LocalTime.of(20, 30)
  ),
  VETERINARIAN(
      "Veterinarian",
      "Provides medical care and health services for zoo animals",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  VETERINARY_TECHNICIAN(
      "Veterinary Technician",
      "Manages and operates the devices in the vet center",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  GENERAL_DIRECTOR(
      "General Director",
      "Oversees the zoo's overall operations and management",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  OPERATIONS_DIRECTOR(
      "Operations Director",
      "Oversees the events planning, the marketing strategy and the employees schedule",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  HR_MANAGER(
      "HR Manager",
      "Oversees the employees",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  HR_ASSISTANT(
      "HR Assistant",
      "Organizes the employees schedule",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  TICKETS_MANAGER(
      "Tickets Manager", "Operates and oversees the ticketing system of the zoo",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
      },
      LocalTime.of(10, 0),
      LocalTime.of(18, 0)
  ),
  EVENTS_MANAGER(
      "Events Manager",
      "Organizes and manages zoo events and activities",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY
      },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  PUBLIC_RELATIONS_MANAGER(
      "Public Relations Manager",
      "Handles the zoo's public image and communications",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  FINANCE_MANAGER(
      "Finance Manager",
      "Manages the financial aspects of the zoo",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  EDUCATOR("Educator",
      "Leads educational tours and programs for the public",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
      },
      LocalTime.of(10, 0),
      LocalTime.of(18, 0)
  ),
  ZOOLOGIST(
      "Zoologist",
      "Studies and works to conserve species and their habitats",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY },
      LocalTime.of(9, 0),
      LocalTime.of(17, 0)
  ),
  SECURITY_OFFICER(
      "Security Officer",
      "Responsible for the overall security of the park",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
      },
      LocalTime.of(9, 0),
      LocalTime.of(19, 0)
  ),
  SECURITY_MANAGER(
      "Security Manager",
      "Oversees the security operations and personnel within the zoo",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY
      },
      LocalTime.of(9, 0),
      LocalTime.of(19, 0)
  ),
  PARK_MANAGER(
      "Park Manager",
      "Responsible for the correct parking of the cars in the parking area",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
      },
      LocalTime.of(10, 0),
      LocalTime.of(18, 30)
  ),
  CLEANING_STAFF(
      "Cleaning Staff",
      "Maintains cleanliness and hygiene throughout the zoo premises",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
      },
      LocalTime.of(12, 0),
      LocalTime.of(20, 0)
  ),
  MAINTENANCE_STAFF(
      "Maintenance Staff",
      "Handles repairs and maintenance of zoo facilities and infrastructure",
      new DayOfWeek[]{ DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
      },
      LocalTime.of(12, 0),
      LocalTime.of(20, 0)
  );

  //-------------------------------------------------------------------------------------------------------------------

  private final String title;
  private final String description;
  private DayOfWeek[] workingDays;
  private LocalTime startTime;
  private LocalTime endTime;

  //-------------------------------------------------------------------------------------------------------------------

  JobTitle(String title, String description, DayOfWeek[] workingDays, LocalTime startTime, LocalTime endTime) {
    this.title = title;
    this.description = description;
    this.workingDays = workingDays;
    this.startTime = startTime;
    this.endTime = endTime;
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
  
  public DayOfWeek[] getWorkingDays() {
    return workingDays;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalTime getStartTime() {
    return startTime;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalTime getEndTime() {
    return endTime;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
