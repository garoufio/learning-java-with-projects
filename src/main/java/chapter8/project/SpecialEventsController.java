package chapter8.project;

import java.util.Scanner;

public class SpecialEventsController {
  
  SpecialEventsService specialEventsService;
  Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEventsController(Scanner sc, SpecialEventsService specialEventsService) {
    this.sc = sc;
    this.specialEventsService = specialEventsService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleSpecialEvents() {
    for (;;) {
      System.out.printf("\nSpecial events service:\n");
      System.out.println("1. Show all Special Events");
      System.out.println("2. Add Event");
      System.out.println("3. Find Event");
      System.out.println("4. Remove Event");
      System.out.println("5. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printSpecialEvents(specialEventsService.getAllSpecialEvents());
          break;
        case 2:
          addSpecialEvent();
          break;
        case 3:
          findSpecialEvent();
          break;
        case 4:
          removeSpecialEvent();
          break;
        case 5:
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 5) {
        System.out.println();
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void printSpecialEvents(SpecialEvent... specialEvents) {
    if (specialEvents == null) {
      System.out.println("No special events were added");
      return;
    }
    
    for (SpecialEvent se : specialEvents) {
      if (se != null) System.out.println(se);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addSpecialEvent() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void findSpecialEvent() {
    // TODO
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeSpecialEvent() {
    //
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  
}
