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
  
}
