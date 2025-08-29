package chapter6;

public class Exercise6_2 {
  
  public static void main(String[] args) {
    double[] dinosaursWeight = { 560.50, 900, 188.90, 700, 550 };
    
    double maxWeight = dinosaursWeight[0];
    for (int i = 1; i < dinosaursWeight.length; i++) {
      if (dinosaursWeight[i] > maxWeight) {
        maxWeight = dinosaursWeight[i];
      }
    }
    System.out.printf("The heaviest dinosaur in our zoo, weights '%.2f' Kg\n", maxWeight);
  }
  
}
