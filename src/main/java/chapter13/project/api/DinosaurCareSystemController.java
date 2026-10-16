package chapter13.project.api;

import chapter13.project.entity.activity.Activity;
import chapter13.project.entity.dinosaur.Dinosaur;
import chapter13.project.service.DinosaurCareSystemService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class DinosaurCareSystemController {
  
  DinosaurCareSystemService dinosaurCareSystemService;
  Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new DinosaurCareSystemController with the specified Scanner and DinosaurCareSystemService.
   * @param sc the Scanner to read user input.
   * @param dinosaurCareSystemService the service to manage dinosaur care system operations.
   */
  public DinosaurCareSystemController(
      Scanner sc,
      DinosaurCareSystemService dinosaurCareSystemService
  ) {
    this.sc = sc;
    this.dinosaurCareSystemService = dinosaurCareSystemService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Manages the Dinosaur Care System by providing a menu for various operations such as checking dinosaurs' health, 
   * checking enclosures' security score, and managing activities schedule.
   */
  public void manageDinosaurCareSystem() {
    for (;;) {
      System.out.println("\nDinosaurs Care System service:");
      System.out.println("1. Check Dinosaurs Health");
      System.out.println("2. Check Enclosures Security Score");
      System.out.println("3. Activities Schedule");
      System.out.println("4. Return to Main Menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          dinosaurCareSystemService.checkDinosaursHealth();
          break;
        case 2:
          dinosaurCareSystemService.checkEnclosuresSecurity();
          break;
        case 3:
          manageActivitiesSchedule();
          break;
        case 4:
          System.out.println();
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Manages the activities schedule by providing a menu for viewing, adding, and removing activities.
   */
  private void manageActivitiesSchedule() {
    for (;;) {
      System.out.println("\nActivities Schedule:");
      System.out.println("1. View Activities Schedule");
      System.out.println("2. Add Activity");
      System.out.println("3. Remove Activity");
      System.out.println("4. Return to Dinosaur Care System Menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printActivities();
          break;
        case 2:
          addActivity();
          break;
        case 3:
          removeActivity();
          break;
        case 4:
          System.out.println();
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the list of all scheduled activities in the Dinosaur Care System.
   */
  private void printActivities() {
    if (dinosaurCareSystemService.getActivities().isEmpty()) {
      System.out.println("No activities are scheduled in the Dinosaur Care System.");
      return;
    }
    
    System.out.println("Activities Schedule:");
    for (Activity a : dinosaurCareSystemService.getActivities()) {
      System.out.println(a);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds a new activity to the Dinosaur Care System after collecting necessary details from the user.
   */
  private void addActivity() {
    if (dinosaurCareSystemService.getDinosaurs().isEmpty()) {
      System.out.println("No dinosaurs are available in the Dinosaur Care System.");
      return;
    }
    
    // name
    String name = Util.readActivityField(sc, "name");
    // description
    String description = Util.readActivityField(sc, "description");
    // tms
    LocalDateTime tms = Util.readActivityTms(sc);
    // duration
    Duration duration = Util.readActivityDuration(sc);
    // select dinosaur from list of dinosaurs
    Dinosaur dinosaur = selectDinosaur();
    
    Activity activity = new Activity(name, description, dinosaur, tms, duration);
    if (dinosaurCareSystemService.activityExists(activity)) {
      System.out.println("Activity already exists in the Dinosaur Care System.");
      return;
    }
    dinosaurCareSystemService.logActivity(activity);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes an existing activity from the Dinosaur Care System based on the activity name provided by the user.
   */
  private void removeActivity() {
    String name = Util.readActivityField(sc, "name");
    List<Activity> allActivities = dinosaurCareSystemService.getActivities();
    
    for  (Activity a : allActivities) {
      if (a != null && a.getName().equals(name)) {
        if (dinosaurCareSystemService.removeActivity(a)) {
          System.out.printf("Activity '%s' was removed from the Dinosaur Care System\n", a);
        } else {
          System.out.printf("Activity '%s' was not removed from the Dinosaur Care System.\n", a);
        }
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prompts the user to select a dinosaur from the list of available dinosaurs.
   * @return The selected Dinosaur object, or null if no dinosaurs are available.
   */
  private Dinosaur selectDinosaur() {
    if (dinosaurCareSystemService.getDinosaurs().isEmpty()) return null;
    
    for (;;) {
      System.out.println("Select a dinosaur from the list:");
      int i = 1;
      for (Dinosaur d : dinosaurCareSystemService.getDinosaurs()) {
        System.out.printf("%d. %s\n", i++, d);
      }
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      if (choice > 0 && choice <= dinosaurCareSystemService.getDinosaurs().size()) {
        return dinosaurCareSystemService.getDinosaurs().get(choice - 1);
      } else {
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
