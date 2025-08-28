package chapter4;

public class Exercise4_6 {
  
  public static void main(String[] args) {
    double dinosaurWeight = 350.80;
    
    if (dinosaurWeight < 350) {
      System.out.printf("The dinosaur should eat 1 time per day");
    } else if (dinosaurWeight < 750) {
      System.out.printf("The dinosaur should eat 2 times per day");
    } else if (dinosaurWeight < 1000) {
      System.out.printf("The dinosaur should eat 3 times per day");
    } else {
      System.out.printf("The dinosaur should eat 4 times per day");
    }
  }
  
}
