package chapter5;

public class Exercise5_6 {
  
  public static void main(String[] args) {
    boolean isZooSafe = true;
    int count = 0;
    
    do {
      System.out.println("Performing security check...");
      count++;
      if (count == 15) isZooSafe = false;
    } while (isZooSafe);
    System.out.println("The zoo is unsafe");
  }
}
