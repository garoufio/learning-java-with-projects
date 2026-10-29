package chapter14.project.entity.park;

import chapter14.project.entity.dinosaur.Dinosaur;
import chapter14.project.entity.employee.Employee;
import chapter14.project.entity.enclosure.Enclosure;
import chapter14.project.entity.ticket.Ticket;

import java.time.LocalTime;
import java.util.Set;

/**
 * Represents a park that contains dinosaurs, employees, tickets, and enclosures. Provides methods to manage the 
 * park's status (open or closed) based on opening and closing hours.
 */
public class Park {
  
  public static final LocalTime OPENING_HOUR = LocalTime.of(10, 0, 0);
  public static final LocalTime CLOSING_HOUR = LocalTime.of(18, 30, 0);
  
  private boolean isOpen;
  private Set<Dinosaur> dinosaurs;
  private Set<Employee> employees;
  private Set<Ticket> tickets;
  private Set<Enclosure> enclosures;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Park instance and sets its initial status based on the current time.
   */
  public Park() {
    super();
    setParkStatus();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new Park instance with the specified sets of dinosaurs, employees, tickets, and enclosures.
   * @param dinosaurs the set of dinosaurs in the park, see {@link Dinosaur}.
   * @param employees the set of employees working in the park, see {@link Employee}.
   * @param tickets the set of tickets available for the park, see {@link Ticket}.
   * @param enclosures the set of enclosures in the park, see {@link Enclosure}.
   */
  public Park(Set<Dinosaur> dinosaurs, Set<Employee> employees, Set<Ticket> tickets, Set<Enclosure> enclosures) {
    this();
    this.dinosaurs = dinosaurs;
    this.employees = employees;
    this.tickets = tickets;
    this.enclosures = enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the current status of the park (open or closed).
   * @return true if the park is open, false otherwise.
   */
  public boolean getParkStatus() {
    return isOpen;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the current status of the park (open or closed) based on the current time.
   */
  public void setParkStatus() {
    LocalTime now = LocalTime.now();
    
    isOpen = now.isAfter(OPENING_HOUR) && now.isBefore(CLOSING_HOUR);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Sets the current status of the park (open or closed) explicitly.
   * @param isOpen true to open the park, false to close it.
   */
  public void setParkStatus(boolean isOpen) {
    this.isOpen = isOpen;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Opens the park by setting its status to open.
   */
  public void openPark() {
    isOpen = true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  /**
   * Closes the park by setting its status to closed.
   */
  public void closePark() {
    isOpen = false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Prints the dinosaurs currently in the park.
   */
  public void printParkDinosaurs() {
    if (dinosaurs == null || dinosaurs.isEmpty()) {
      System.out.println("No dinosaurs in the park.");
    } else {
      System.out.println("Dinosaurs in the park:");
      for (Dinosaur dinosaur : dinosaurs) {
        System.out.println(dinosaur);
      }
    }
  }

  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Prints the employees currently working in the park.
   */
  public void printParkEmployees() {
    if (employees == null || employees.isEmpty()) {
      System.out.println("No employees work in the park.");
    } else {
      System.out.println("Employees working in the park:");
      for (Employee employee : employees) {
        System.out.println(employee);
      }
    }
  }

  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Prints the tickets currently bought for the park.
   */
  public void printParkTickets() {
    if (tickets == null || tickets.isEmpty()) {
      System.out.println("No tickets bought.");
    } else {
      System.out.println("Tickets bought:");
      for (Ticket ticket : tickets) {
        System.out.println(ticket);
      }
    }
  }

  //-------------------------------------------------------------------------------------------------------------------

  /**
   * Prints the enclosures currently in the park.
   */
  public void printParkEnclosures() {
    if (enclosures == null || enclosures.isEmpty()) {
      System.out.println("No enclosures exist in the park.");
    } else {
      System.out.println("Enclosures in the park:");
      for (Enclosure enclosure : enclosures) {
        System.out.println(enclosure);
      }
    }
  }

  //-------------------------------------------------------------------------------------------------------------------
  
}
