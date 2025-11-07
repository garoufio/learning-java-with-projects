package chapter9.project;

import chapter9.project.entity.employee.Employee;
import chapter9.project.entity.employee.ParkManager;
import chapter9.project.entity.employee.Vet;

public class App {
  
  public static final int MAX_VISITORS = 300;
  public static final int MAX_DINOSAURS = 50;
  public static final int MAX_EMPLOYEES = 40;
  public static final int MAX_ENCLOSURES = 20;
  public static final int MAX_SPECIAL_EVENTS = 100;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args) {
    Employee e = new Vet("Steve", 9, "Surgeon");
    System.out.println(e);
    Employee e1 = new Vet("Steve", 9, "Surgeon");
    System.out.println(e1);
    Employee e2 = new Vet("Rohn", 29, "Pathologist");
    System.out.println(e2);
    Employee p = new ParkManager("Steve", 19, true);
    System.out.println(p);
    
  }
  
}
