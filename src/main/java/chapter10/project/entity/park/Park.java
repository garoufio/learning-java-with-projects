package chapter10.project.entity.park;

import chapter10.project.entity.dinosaur.Dinosaur;
import chapter10.project.entity.employee.Employee;
import chapter10.project.entity.enclosure.Enclosure;
import chapter10.project.entity.ticket.Ticket;

import java.time.LocalTime;
import java.util.List;

public class Park {
  
  public static final LocalTime OPENING_HOUR = LocalTime.of(10, 0, 0);
  public static final LocalTime CLOSING_HOUR = LocalTime.of(19, 30, 0);
  
  private boolean isOpen;
  private List<Dinosaur> dinosaurs;
  private List<Employee> employees;
  private List<Ticket> tickets;
  private List<Enclosure> enclosures;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park() {
    super();
    setParkStatus();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park(List<Dinosaur> dinosaurs, List<Employee> employees, List<Ticket> tickets, List<Enclosure> enclosures) {
    this();
    this.dinosaurs = dinosaurs;
    this.employees = employees;
    this.tickets = tickets;
    this.enclosures = enclosures;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean getParkStatus() {
    return isOpen;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setParkStatus() {
    LocalTime now = LocalTime.now();
    
    isOpen = now.isAfter(OPENING_HOUR) && now.isBefore(CLOSING_HOUR);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setParkStatus(boolean isOpen) {
    this.isOpen = isOpen;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void openPark() {
    isOpen = true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void closePark() {
    isOpen = false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
