package chapter6;

public class Exercise6_3 {
  
  public static void main(String[] args) {
    double[] dinosaursWeight = { 560.50, 900, 188.90, 700, 550 };
    
    double minWeight = dinosaursWeight[0];
    for (int i = 1; i < dinosaursWeight.length; i++) {
      if (dinosaursWeight[i] < minWeight) {
        minWeight = dinosaursWeight[i];
      }
    }
    System.out.printf("The lightest dinosaur in our zoo, weights '%.2f' Kg\n", minWeight);
  }
  
}
