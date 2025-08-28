package chapter4;

public class Exercise4_4 {
  
  public static void main(String[] args) {
    double safetyRating = 9.1;
    double safetyRatingThreshold = 8.8;
    
    if (safetyRating >= safetyRatingThreshold) {
      System.out.println("Mesozoic Eden is completely safe");
    } else {
      System.out.println("Mesozoic Eden is not completely safe");
    }
  }
  
}
