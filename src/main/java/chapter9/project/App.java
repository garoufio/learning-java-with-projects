package chapter9.project;

public class App {
  
  public static void main(String[] args) {
    Employee e = new Vet("Steve", 9, "Surgeon");
    System.out.println(e.toString());
    Employee e1 = new Vet("Steve", 9, "Surgeon");
    System.out.println(e1.toString());
    Employee e2 = new Vet("Rohn", 29, "Pathologist");
    System.out.println(e2.toString());
    Employee p = new ParkManager("Steve", 19, true);
    System.out.println(p.toString());
    
    System.out.println(e.equals(e1));
    System.out.println(e.equals(e2));
    System.out.println(e.equals(p));
  }
  
}
