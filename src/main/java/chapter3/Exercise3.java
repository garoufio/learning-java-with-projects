package chapter3;

public class Exercise3 {
  
  public static void main(String[] args) {
    int year = 2025;
    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    
    System.out.printf("Is %d year leap year? %b\n", year, isLeapYear);
  }
  
}
