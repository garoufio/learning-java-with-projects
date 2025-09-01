package chapter7;

import java.time.LocalTime;

public class Exercise7_4 {
  
  public static void main(String[] args) {
    System.out.printf("Is the zoo open now? %b \n", isZooOpen());
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
