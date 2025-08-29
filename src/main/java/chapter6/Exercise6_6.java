package chapter6;

import java.util.Arrays;

public class Exercise6_6 {
  
  public static void main(String[] args) {
    String[][] parkMap = new String[3][2];
    
    for (int i = 0; i < 3; i++) {
      parkMap[i][0] = "Enclosure " + (i + 1);
      parkMap[i][1] = "Facility " + (i + 1);
    }
    
    System.out.println("The Park map is:");
    for (String[] row : parkMap) {
      System.out.println(Arrays.toString(row));
    }
  }
  
}
