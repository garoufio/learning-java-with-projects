package chapter5;

public class Exercise5_4 {
  
  public static void main(String[] args) {
    double totalDinosWeight = 0.0;
    double[] dinosWeight = { 750, 350, 3000, 4000, 900 };
    
    for (double d : dinosWeight) {
      totalDinosWeight += d;
    }
    System.out.printf("The total weight of all dinosaurs in the zoo is '%.2f' Kg\n", totalDinosWeight);
  }
  
}
