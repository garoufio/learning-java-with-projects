package chapter13.project.api;

import chapter13.project.entity.activity.Activity;
import chapter13.project.entity.dinosaur.Dinosaur;
import chapter13.project.exception.AppException;
import chapter13.project.exception.InputException;
import chapter13.project.service.DinosaurCareSystemService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
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
      try {
        System.out.print("""
            
            Dinosaurs Care System service:
            1. Check Dinosaurs Health
            2. Check Enclosures Security Score
            3. Activities Schedule
            4. Return to Main Menu
            Enter your choice:\t""");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> dinosaurCareSystemService.checkDinosaursHealth();
          case 2 -> dinosaurCareSystemService.checkEnclosuresSecurity();
          case 3 -> manageActivitiesSchedule();
          case 4 -> {
            System.out.println();
            return;
          }
          default -> System.out.println("Invalid choice. Please try again.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 4.");
        sc.nextLine(); // Consume the invalid input
      } catch (AppException e) {
        System.out.printf("""
          Error while managing Dinosaur Care System: %s
          Please try again or return to Main Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Manages the activities schedule by providing a menu for viewing, adding, and removing activities in the Dinosaur
   * Care System.
   */
  private void manageActivitiesSchedule() {
    for (;;) {
      try {
        System.out.print("""
            
            Activities Schedule:
            1. View Activities Schedule
            2. Add Activity
            3. Remove Activity
            4. Return to Dinosaur Care System Menu
            Enter your choice:\t""");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> printActivities();
          case 2 -> addActivity();
          case 3 -> removeActivity();
          case 4 -> {
            System.out.println();
            return;
          }
          default -> System.out.println("Invalid choice. Please try again.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 4.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while adding/removing activities: %s
          Please try again or return to Dinosaur Care System Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the list of all scheduled activities in the Dinosaur Care System.
   */
  private void printActivities() {
    Set<Activity> activities = dinosaurCareSystemService.getActivities();
    if (activities.isEmpty()) {
      System.out.println("No activities are scheduled in the Dinosaur Care System.");
      return;
    }
    System.out.println("Activities Schedule:");
    for (Activity a : activities) {
      System.out.println(a);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds a new activity to the Dinosaur Care System after collecting necessary details from the user.
   * @throws InputException if there is an error in user input while adding the activity.
   */
  private void addActivity() throws InputException {
    if (dinosaurCareSystemService.getDinosaurs().isEmpty()) {
      System.out.println("No dinosaurs are available in the Dinosaur Care System and no activities can be added.");
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
    Set<Dinosaur> dinosaur = selectDinosaur();
    
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
   * @throws InputException if there is an error in user input while removing the activity.
-   */
  private void removeActivity() throws InputException {
    String name = Util.readActivityField(sc, "name");
    Set<Activity> allActivities = dinosaurCareSystemService.getActivities();
    
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
   * Prompts the user to select a dinosaur from the list of available dinosaurs. The user can choose a specific
   * dinosaur or select all dinosaurs. If no dinosaurs are available, an empty set is returned.
   * @return The selected Dinosaur object(s), as a set or an empty set.
   * @throws InputException if there is an error in user input while selecting the dinosaur(s).
   */
  private Set<Dinosaur> selectDinosaur() throws InputException {
    if (dinosaurCareSystemService.getDinosaurs().isEmpty()) return Set.of();
    
    int choice;
    Set<Dinosaur> selectedDinosaurs = new HashSet<>();
    for (;;) {
      System.out.println("Select a dinosaur from the list:");
      int i = 1;
      for (Dinosaur d : dinosaurCareSystemService.getDinosaurs()) {
        System.out.printf("%d. %s\n", i++, d);
      }
      System.out.printf("""
      %d. All
      Enter your choice:\t""",
          i
      );
      
      try {
        choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
      } catch (InputMismatchException e) {
        sc.nextLine(); // Consume the invalid input
        throw new InputException("Invalid number while selecting dinosaur(s).");
      }
      if (choice > 0 && choice < i) {
        selectedDinosaurs.add((Dinosaur) dinosaurCareSystemService.getDinosaurs().toArray()[choice - 1]);
      } else if (choice == i) {
        selectedDinosaurs.addAll(dinosaurCareSystemService.getDinosaurs());
        break;
      } else {
        System.out.println("Invalid choice. Please try again.");
      }
    }
    return selectedDinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
