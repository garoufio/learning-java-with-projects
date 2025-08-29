package chapter6;

import java.util.Arrays;

public class Exercise6_7 {
  
  public static void main(String[] args) {
    String[][] seatingMap = {
        {"Seat 1A", "Seat 1B", "Seat 1C"},
        {"Seat 2A", "Seat 2B", "Seat 2C"},
        {"Seat 3A", "Seat 3B", "Seat 3C"},
    };
    
    System.out.println("The zoo seating map is:");
    for (int i = 0; i < seatingMap.length; i++) {
      for (int j = 0; j < seatingMap[i].length; j++) {
        System.out.println(seatingMap[i][j]);
      }
    }
  }
  
}
