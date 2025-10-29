package chapter8.project;

import chapter8.project.entity.Dinosaur;
import chapter8.project.entity.Employee;
import chapter8.project.entity.Enclosure;
import chapter8.project.entity.Ticket;

import java.time.LocalTime;
import java.util.Arrays;

public class Park {
  
  public static final LocalTime OPENING_HOUR = LocalTime.of(10, 0, 0);
  public static final LocalTime CLOSING_HOUR = LocalTime.of(19, 30, 0);
  
  private boolean isOpen;
  private Dinosaur[] dinosaurs;
  private Employee[] employees;
  private Ticket[] tickets;
  private Enclosure[] enclosures;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park() {
    super();
    setParkStatus();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park(Dinosaur[] dinosaurs, Employee[] employees, Ticket[] tickets, Enclosure[] enclosures) {
    this();
    this.dinosaurs = Arrays.copyOf(dinosaurs, dinosaurs.length);
    this.employees = Arrays.copyOf(employees, employees.length);
    this.tickets = Arrays.copyOf(tickets, tickets.length);
    this.enclosures = Arrays.copyOf(enclosures, enclosures.length);
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
