package chapter8;

import java.util.Scanner;

public class Project {
  
  // Use scanner for reading input from the user
  Scanner sc = new Scanner(System.in);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args) {
    Project project = new Project();
    project.start();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void start() {
    /* this is the main loop of the application. It will keep running until the user decides to exit */
    while (true) {
      displayMenu();
      int choice = sc.nextInt();
      handleMenuChoice(choice);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void displayMenu() {
    System.out.println("Welcome to Mesozoic Eden Park Manager!");
    System.out.printf("1. Manage Dinosaurs");
    System.out.printf("2. Manage Park Employees");
    System.out.printf("3. Manage Tickets");
    System.out.printf("4. Check Park Status");
    System.out.printf("5. Handle Special Events");
    System.out.printf("6. Exit");
    System.out.printf("Enter your choice: ");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleMenuChoice(int choice) {
    switch (choice) {
      case 1:
        manageDinosaurs();
        break;
      case 2:
        manageEmployees();
        break;
      case 3:
        manageTickets();
        break;
      case 4:
        checkParkStatus();
        break;
      case 5:
        handleSpecialEvents();
        break;
      case 6:
        System.out.println("Exiting...");
        System.exit(0);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageDinosaurs() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageEmployees() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageTickets() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkParkStatus() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleSpecialEvents() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
