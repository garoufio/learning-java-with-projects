package chapter12.project.api;

import chapter12.project.service.DinosaurCareSystemService;

import java.util.Scanner;

public class DinosaurCareSystemController {
  
  DinosaurCareSystemService dinosaurCareSystemService;
  Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurCareSystemController(Scanner sc, DinosaurCareSystemService dinosaurCareSystemService) {
    this.sc = sc;
    this.dinosaurCareSystemService = dinosaurCareSystemService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageDinosaurCareSystem() {
    for (;;) {
      System.out.printf("\nDinosaurs Care System service:\n");
      System.out.println("1. Check Dinosaurs Health");
      System.out.println("2. Check Enclosures Security Score");
      System.out.println("3. Return to Main Menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          dinosaurCareSystemService.checkDinosaursHealth();
          break;
        case 2:
          dinosaurCareSystemService.checkEnclosuresSecurity();
          break;
        case 3: { }
        default:
          System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 3) {
        System.out.println();
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
