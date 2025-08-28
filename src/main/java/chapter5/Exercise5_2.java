package chapter5;

public class Exercise5_2 {
  
  public static void main(String[] args) {
    boolean isHungry = true;
    int feedingCount = 0;
    
    while (isHungry) {
      System.out.println("Feed the dinosaur");
      feedingCount++;
      if (feedingCount == 5) isHungry = false;
    }
  }
  
}
