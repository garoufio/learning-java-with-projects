package chapter8;

import java.util.Arrays;

public class Exercise8_3 {
  
  public static void main(String[] args) {
    Dinosaur[] dinos = createDinosaurs(3);
    Employee[] employees = createEmployees(3);
    
    printArray(dinos, "Dinosaur");
    printArray(employees, "Employee");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static Dinosaur[] createDinosaurs(int numberOfDinosaurs) {
    Dinosaur[] dinos = new Dinosaur[numberOfDinosaurs];
    
    for  (int i = 0; i < numberOfDinosaurs; i++) {
      dinos[i] = new Dinosaur("DinoName" + (i + 1), i * 2 + 1, DinosaurType.CARNIVORE, DinosaurSpecies.TRICERATOPS);
    }
    return dinos;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static Employee[] createEmployees(int numberOfEmployees) {
    Employee[] employees = new Employee[numberOfEmployees];
    
    for   (int i = 0; i < numberOfEmployees; i++) {
      employees[i] = new Employee(
          "EmployeeName" + (i + 1), JobTitle.CURATOR, i * 2 + 1
      );
    }
    return employees;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void printArray(Object[] objects, String type) {
    switch (type.toLowerCase()) {
      case "dinosaur":
        Dinosaur[] dinosaurs = (Dinosaur[]) objects;
        System.out.println("Our Dinosaurs are:");
        for (Dinosaur dinosaur : dinosaurs) {
          System.out.println(dinosaur);
        }
        System.out.println();
        break;
      case "employee":
        System.out.println("Out Employees are:");
        Employee[] employees = (Employee[]) objects;
        for (Employee employee : employees) {
          System.out.println(employee);
        }
        System.out.println();
        break;
      default:
        System.out.println("Invalid type");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
