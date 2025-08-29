package chapter6;

public class Exercise6_5 {
  
  public static void main(String[] args) {
    int[] dinosaursAge = { 1, 5, 7 };
    int sumOfAges = 0;
    
    for (int age : dinosaursAge) {
      sumOfAges += age;
    }
    System.out.printf("The average age of all dinosaurs is '%.2f'\n", (double)sumOfAges / dinosaursAge.length);
  }
  
}