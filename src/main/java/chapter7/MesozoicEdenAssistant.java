package chapter7;

import java.time.LocalTime;
import java.util.Scanner;

public class MesozoicEdenAssistant {
  
  private Scanner sc = new Scanner(System.in);
  // Initialize arrays to store dinosaur information
  private String[] dinoNames = new String[10];
  private int[] dinoAges = new int[10];
  private int[] dinoWeights = new int[10];
  private int dinoCount = 0;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args) {
    MesozoicEdenAssistant main = new MesozoicEdenAssistant();
    main.start();
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  public void start() {
    // This is the main loop of the application. It will keep running until the user decides to exit.
    while (true) {
      displayMenu();
      int choice = sc.nextInt();
      handleMenuChoice(choice);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void displayMenu() {
    System.out.println("Welcome to Mesozoic Eden Assistant!");
    System.out.println("1. Add Dinosaur");
    System.out.println("2. Check Park Hours");
    System.out.println("3. Greet Guest");
    System.out.println("4. Check Visitors Count");
    System.out.println("5. Manage Staff");
    System.out.println("6. Exit");
    System.out.print("Enter your choice: ");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleMenuChoice(int choice) {
    switch (choice) {
      case 1:
        addDinosaur();
        break;
      case 2:
        checkParkHours();
        break;
      case 3:
        // greetGuest();
        break;
      case 4:
        // checkVisitorsCount();
        break;
      case 5:
        // manageStaff();
        break;
      case 6:
        System.out.println("Exiting...");
        System.exit(0);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaur() {
    if (dinoCount >= 10) {
      System.out.println("The park is at full capacity. No more dinosaurs can be added.");
      return;
    }
    
    sc.nextLine();  // clear the input
    System.out.println("Enter dinosaur name: ");
    dinoNames[dinoCount] = sc.nextLine();
    System.out.println("Enter dinosaur age: ");
    dinoAges[dinoCount] = sc.nextInt();
    System.out.println("Enter dinosaur weight: ");
    dinoWeights[dinoCount] = sc.nextInt();
    dinoCount++;
    
    System.out.println(dinoNames[dinoCount - 1] + " has been added to the park!");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkParkHours() {
    // Assuming the park is open from 9 am to 7:30 pm.
    if (isZooOpen()) {
      System.out.println("The zoo is currently open.");
    } else {
      System.out.println("The zoo is currently closed.");
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static boolean isZooOpen() {
    LocalTime now = LocalTime.now();
    LocalTime open = LocalTime.of(9, 0, 0, 0);
    LocalTime close = LocalTime.of(19, 30, 0, 0);
    
    return (now.isAfter(open) && now.isBefore(close));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
