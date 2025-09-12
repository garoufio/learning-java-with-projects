package chapter8;

import java.time.LocalTime;
import java.util.Arrays;

public class Park {
  
  public static final LocalTime OPENING_HOUR = LocalTime.of(10, 0, 0);
  public static final LocalTime CLOSING_HOUR = LocalTime.of(19, 30, 0);
  
  private boolean isOpen;
  private Dinosaur[] dinos;
  private Employee[] employees;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park() {
    super();
    setParkStatus();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Park(Dinosaur[] dinos, Employee[] employees) {
    this();
    this.dinos = Arrays.copyOf(dinos, dinos.length);
    this.employees = Arrays.copyOf(employees, employees.length);
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
  
  public void addDinosaurs(Dinosaur... dinosaur) {
    if (dinosaur == null) {
      System.out.println("No dinosaurs were added");
      return;
    }
    
    if (dinos == null) {
      dinos = Arrays.copyOf(dinosaur, dinosaur.length);
    }
    else {
      Dinosaur[] arr = new Dinosaur[dinos.length + dinosaur.length];
      System.arraycopy(dinos, 0, arr, 0, dinos.length);
      System.arraycopy(dinosaur, 0, arr, dinos.length, dinosaur.length);
      dinos = Arrays.copyOf(arr, arr.length);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur[] getDinosaurs() {
    return dinos;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getSingleDinosaur(int index) {
    if (index < 0 || index >= dinos.length) return null;
    
    return dinos[index];
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getSingleDinosaur(String dinoName) {
    if  (dinoName == null) return null;
    
    for (Dinosaur d : dinos) {
      if (d.getName().equals(dinoName)) {
        return new Dinosaur(d.getName(), d.getAge(), d.getSpecies());
      }
    }
    return null;
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
  
  public Employee getSingleEmployee(int index) {
    if (index < 0 || index >= employees.length) return null;
    
    return employees[index];
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Employee getSingleEmployee(String employeeName) {
    if  (employeeName == null) return null;
    
    for (Employee e : employees) {
      if (e.getName().equals(employeeName)) {
        return new Employee(e.getName(), e.getJobTitle(), e.getYearsOfExperience());
      }
    }
    return null;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
