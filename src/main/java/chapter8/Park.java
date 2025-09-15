package chapter8;

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
  // Dinosaurs handling
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaurs(Dinosaur... dinosaur) {
    if (dinosaur == null) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    if (dinosaurs == null) {
      dinosaurs = Arrays.copyOf(dinosaur, dinosaur.length);
    }
    else {
      Dinosaur[] arr = new Dinosaur[dinosaurs.length + dinosaur.length];
      System.arraycopy(dinosaurs, 0, arr, 0, dinosaurs.length);
      System.arraycopy(dinosaur, 0, arr, dinosaurs.length, dinosaur.length);
      dinosaurs = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur[] getDinosaurs() {
    return dinosaurs == null ? null : Arrays.copyOf(dinosaurs, dinosaurs.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getSingleDinosaurIndex(Dinosaur dinosaur) {
    if  (dinosaurs == null) return -1;
    
    for (int i = 0; i < dinosaurs.length; i++) {
      if (dinosaurs[i].equals(dinosaur)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getSingleDinosaur(Dinosaur dinosaur) {
    if  (dinosaur == null) return null;
    
    for (Dinosaur d : this.dinosaurs) {
      if (d.equals(dinosaur)) {
        return d;
      }
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) return false;
    
    int index = getSingleDinosaurIndex(dinosaur);
    if (index < 0) return false;
    
    dinosaurs[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Employees handling
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee[] getEmployees() {
    return employees == null ? null : Arrays.copyOf(employees, employees.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEmployees(Employee... employees) {
    if (employees == null) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    if (this.employees == null) {
      this.employees = Arrays.copyOf(employees, employees.length);
    }
    else {
      Employee[] arr = new Employee[this.employees.length + employees.length];
      System.arraycopy(this.employees, 0, arr, 0, this.employees.length);
      System.arraycopy(employees, 0, arr, this.employees.length, employees.length);
      this.employees = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getSingleEmployeeIndex(Employee employee) {
    if (employee == null) return -1;
    
    for (int i = 0; i < employees.length; i++) {
      if (employees[i].equals(employee)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getSingleEmployee(Employee employee) {
    if  (employee == null) return null;
    
    for (Employee e : this.employees) {
      if (e.equals(employee)) {
        return e;
      }
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEmployee(Employee employee) {
    if (employee == null) return false;
    
    int index = getSingleEmployeeIndex(employee);
    if (index < 0) return false;
    
    employees[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Tickets handling
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket[] getTickets() {
    return tickets == null ? null : Arrays.copyOf(tickets, tickets.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addTickets(Ticket... tickets) {
    if (tickets == null) {
      System.out.println("No tickets were added");
      return;
    }
    
    if (this.tickets == null) {
      this.tickets = Arrays.copyOf(tickets, tickets.length);
    }
    else {
      Ticket[] arr = new Ticket[this.tickets.length + tickets.length];
      System.arraycopy(this.tickets, 0, arr, 0, this.tickets.length);
      System.arraycopy(tickets, 0, arr, this.tickets.length, tickets.length);
      this.tickets = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getSingleTicketIndex(Ticket ticket) {
    if (ticket == null) return -1;
    
    for (int i = 0; i < tickets.length; i++) {
      if (tickets[i].equals(ticket)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Ticket getSingleTicket(Ticket ticket) {
    if  (ticket == null) return null;
    
    for (Ticket t : this.tickets) {
      if (t.equals(ticket)) return t;
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeTicket(Ticket ticket) {
    if (ticket == null) return false;
    
    int index = getSingleTicketIndex(ticket);
    if (index < 0) return false;
    
    tickets[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Enclosures handling
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure[] getEnclosures() {
    return enclosures == null ? null : Arrays.copyOf(enclosures, enclosures.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEnclosure(Enclosure... enclosure) {
    if (enclosure == null) {
      System.out.println("No tickets were added");
      return;
    }
    
    if (this.enclosures == null) {
      this.enclosures = Arrays.copyOf(enclosure, enclosure.length);
    }
    else {
      Enclosure[] arr = new Enclosure[this.enclosures.length + enclosure.length];
      System.arraycopy(this.enclosures, 0, arr, 0, this.enclosures.length);
      System.arraycopy(enclosure, 0, arr, this.enclosures.length, enclosure.length);
      this.enclosures = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private int getSingleEnclosureIndex(Enclosure enclosure) {
    if (enclosure == null) return -1;
    
    for (int i = 0; i < enclosures.length; i++) {
      if (enclosures[i].equals(enclosure)) return i;
    }
    return -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Enclosure getSingleEnclosure(Enclosure enclosure) {
    if  (enclosure == null) return null;
    
    for (Enclosure e : this.enclosures) {
      if (e.getEnclosureType().equals(enclosure.getEnclosureType())) {
        return e;
      }
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEnclosure(Enclosure enclosure) {
    if (enclosure == null) return false;
    
    int index = getSingleEnclosureIndex(enclosure);
    if (index < 0) return false;
    
    enclosures[index] = null;
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
