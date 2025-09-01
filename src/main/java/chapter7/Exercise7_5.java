package chapter7;

public class Exercise7_5 {
  
  public static void main(String[] args) {
    System.out.println(getGreeting("T-Rex", "Bryan"));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static String getGreeting(String dinosaurName, String visitorName) {
    return "Welcome " + visitorName + "! Your favorite dinosaur " + dinosaurName + " is excited to see you!";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
