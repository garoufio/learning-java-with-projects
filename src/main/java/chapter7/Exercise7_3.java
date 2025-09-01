package chapter7;

public class Exercise7_3 {
  
  public static void main(String[] args) {
    int[] ages = { 2, 5, 7, 12, 1, 14 };
    System.out.printf("The average age of all dinosaurs in our zoo is '%.2f' years old\n", getAvgAge(ages));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static double getAvgAge(int[] ages) {
    int sum = 0;
    for (int age : ages) {
      sum += age;
    }
    return (double)sum / ages.length;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
