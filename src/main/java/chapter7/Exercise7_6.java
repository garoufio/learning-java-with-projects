package chapter7;

public class Exercise7_6 {
  
  public static void main(String[] args) {
    System.out.printf(
        "Can '%d' number of visitors enter the zoo? %b\n",
        8, acceptMoreVisitors(56, 8, 70)
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static boolean acceptMoreVisitors(int currentNumberOfVisitors, int numberOfNewVisitors, int maxNumberOfVisitors) {
    return (numberOfNewVisitors + currentNumberOfVisitors) <= maxNumberOfVisitors;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
